package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class LedgerScreen {
    static Scanner scanner = new Scanner(System.in);

    public static void startLedgerScreen() {
        switch (ledgerMenuDisplayAndReturnUserChoice()) {
            case "A":
                showAllTransactions();
                break;
            case "D":
                showTransactionsByType(true); // deposits
                break;
            case "P":
                showTransactionsByType(false); // payments
                break;
            case "R":
                HelperForReport.conditionDependOnUserChoice();
                break;
            case "H":
                Main.homeScreen();
                break;
            default:
                System.out.println("❌ Invalid option.");
        }

        space(); // print some spacing after each screen
    }

    // ---------- Menu ----------
    public static String ledgerMenuDisplayAndReturnUserChoice() {
        System.out.println("====== Ledger ======");
        System.out.println("A) All Transactions");
        System.out.println("D) Deposits Only");
        System.out.println("P) Payments Only");
        System.out.println("R) Reports");
        System.out.println("H) Home");
        System.out.print("Choose an option: ");
        return scanner.next().toUpperCase();
    }

    // ---------- All Transactions ----------
    public static void showAllTransactions() {
        ArrayList<Transaction> transactions = readTransactionsFromFile();

        // Sort by date
        transactions.sort(Comparator.comparing(Transaction::getDate));

        printHeader();
        for (Transaction t : transactions) {
            t.print();
        }
    }

    // ---------- Payments and Deposits ----------
    public static void showTransactionsByType(boolean isDeposit) {
        printHeader();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");

                //explain the logic here
                double amount = Double.parseDouble(data[4].trim());
                boolean match;
                if (isDeposit) {
                    match = amount > 0;
                } else {
                    match = amount < 0;
                }

                if (match) {
                    System.out.printf("%-12s | %-8s | %-20s | %-15s | %-10s%n",
                            data[0].trim(), data[1].trim(), data[2].trim(),
                            data[3].trim(), data[4].trim());
                }

            }
        } catch (Exception e) {
            System.out.println("Error loading transactions.");
        }
    }

    // ---------- Helpers ----------
    public static ArrayList<Transaction> readTransactionsFromFile() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");

                LocalDate date = LocalDate.parse(data[0].trim());
                LocalTime time = LocalTime.parse(data[1].trim());
                String description = data[2].trim();
                String vendor = data[3].trim();
                double amount = Double.parseDouble(data[4].trim());

                transactions.add(new Transaction(date, time, description, vendor, amount));

            }
        } catch (Exception e) {
            System.out.println("Could not read transactions file.");
        }

        return transactions;
    }

    public static void printHeader() {
        System.out.printf("%-12s | %-8s | %-20s | %-15s | %-10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("------------------------------------------------------------------");
    }

    public static void space() {
        System.out.println("\n\n");
    }
}
