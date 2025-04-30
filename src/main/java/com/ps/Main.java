package com.ps;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.nio.Buffer;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean idk = true;
        while (idk) {
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
                    Helper.startLedgerScreen();
                    space();
                    break;
                case "X":
                    System.out.println("Thank you for using our application!");
                    idk = false;
                    break;
                default:
                    System.out.println("Please only type the valid one");
            }
        }
    }

    //--------------- Helping Method -------------//

    public static String homeScreen() {
        String userChoice;

        System.out.println("Welcome to Accounting Ledger Application");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment (Debit)");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.println("Choose an option: ");

        userChoice = scanner.next().toUpperCase();
        return userChoice;
    }

    public static Transaction enterDetails() {

        System.out.println("Enter date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.next());

        System.out.println("Enter time (HH:MM)");
        LocalTime time = LocalTime.parse(scanner.next());

        System.out.println("Enter description: ");
        String description = scanner.next();

        System.out.println("Enter vendor: ");
        String vendor = scanner.next();

        System.out.println("Enter amount: ");
        double amount = scanner.nextDouble();

        Transaction transaction = new Transaction(date, time, description, vendor, amount);

        return transaction;

    }

    public static void depositMoney() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true));

            Transaction t = enterDetails();
            writer.write(t.getDate() + " | " + t.getTime() + " | " + t.getDescription() + " | " +
                    t.getVendor() + " | " + t.getAmount());

            writer.newLine();
            writer.close();
            System.out.println("Deposit added successfully!");
        } catch (Exception e) {
            System.out.println("Something went wrong! We will let the team know\n" +
                    "Sorry for the inconvenience");
        }
    }

    public static void paymentMoney() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true));

            Transaction t = enterDetails();
            writer.write(t.getDate() + " | " + t.getTime() + " | " + t.getDescription() + " | " +
                    t.getVendor() + " | " + t.getAmount() * -1);

            writer.newLine();
            writer.close();
            System.out.println("Payment added successfully!");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    public static void space() {
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }


}