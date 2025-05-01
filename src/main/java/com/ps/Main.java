package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            switch (homeScreen()) {
                case "D":
                    depositMoney();
                    space();
                    break;
                case "P":
                    paymentMoney();
                    space();
                    break;
                case "L":
                    LedgerScreen.startLedgerScreen();
                    space();
                    break;
                case "X":
                    System.out.println("Thank you for using our application!");
                    running = false;
                    break;
                default:
                    System.out.println("Please choose a valid option (D, P, L, X).\n");
            }
        }
    }

    public static String homeScreen() {
        System.out.println("\nWelcome to the Accounting Ledger Application");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment (Debit)");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.print("Choose an option: ");

        return scanner.next().toUpperCase();
    }

    public static Transaction enterDetails() {
        LocalDate date = LocalDate.now();

        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.parse(now.format(formatter));

        scanner.nextLine(); // consume leftover newline
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        return new Transaction(date, time, description, vendor, amount);
    }

    public static void depositMoney() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            Transaction t = enterDetails();
            writer.write(t.getDate() + " | " + t.getTime() + " | " + t.getDescription() + " | " +
                    t.getVendor() + " | " + t.getAmount());
            writer.newLine();
            System.out.println("Deposit added successfully!");
        } catch (Exception e) {
            System.out.println("Something went wrong while recording the deposit.");
        }
    }

    public static void paymentMoney() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            Transaction t = enterDetails();
            writer.write(t.getDate() + " | " + t.getTime() + " | " + t.getDescription() + " | " +
                    t.getVendor() + " | " + (t.getAmount() * -1));
            writer.newLine();
            System.out.println("Payment added successfully!");
        } catch (Exception e) {
            System.out.println("Something went wrong while recording the payment.");
        }
    }

    public static void space() {
        System.out.println("\n\n");
    }
}
