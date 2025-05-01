package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Helper {
    static Scanner scanner = new Scanner(System.in);

    public static void startLedgerScreen() {

        switch (ledgerMenuDisplayAndReturnUserChoice()) {
            case "A":
                showAllTransactions();
                space();
                break;
            case "D":
                showDepositTransaction();
                space();
                break;
            case "P":
                showPaymentTransaction();
                space();
                break;
            case "R":
                HelperForReport.conditionDependOnUserChoice();
                break;
            case "H":
                space();
                Main.homeScreen();
                break;
        }
    }

    //----------- Helper Method ---------//

    public static String ledgerMenuDisplayAndReturnUserChoice() {
        System.out.println("====== Ledger ======");
        System.out.println("A) All Transactions");
        System.out.println("D) Deposits Only");
        System.out.println("P) Payments Only");
        System.out.println("R) Reports");
        System.out.println("H) Home");

        System.out.println("Choose an option: ");
        String userChoice = scanner.next().toUpperCase();

        return userChoice;
    }

    public static void showAllTransactions() {
        System.out.printf("%-12s | %-8s | %-20s | %-15s | %-10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");

        System.out.println("------------------------------------------------------------------");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] transaction = line.split("\\|");
                System.out.printf("%-12s | %-8s | %-20s | %-15s | %-10s%n",
                        transaction[0].trim(), transaction[1].trim(), transaction[2].trim(),
                        transaction[3].trim(), transaction[4].trim());
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Not good");
        }
    }

    public static void showPaymentTransaction() {
        System.out.printf("%-12s | %-8s | %-20s | %-15s | %-10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");

        System.out.println("------------------------------------------------------------------");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] transaction = line.split("\\|");
                if (Double.parseDouble(transaction[4]) < 0) {
                    System.out.printf("%-12s | %-8s | %-20s | %-15s | %-10s%n",
                            transaction[0].trim(), transaction[1].trim(), transaction[2].trim(),
                            transaction[3].trim(), transaction[4].trim());
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Payment method not good");
        }

    }

    private static void showDepositTransaction() {
        System.out.printf("%-12s | %-8s | %-20s | %-15s | %-10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");

        System.out.println("------------------------------------------------------------------");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] transaction = line.split("\\|");
                if (Double.parseDouble(transaction[4]) > 0) {
                    System.out.printf("%-12s | %-8s | %-20s | %-15s | %-10s%n",
                            transaction[0].trim(), transaction[1].trim(), transaction[2].trim(),
                            transaction[3].trim(), transaction[4].trim());
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Deposit method not good");
        }
    }

    public static void space() {
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }
}
