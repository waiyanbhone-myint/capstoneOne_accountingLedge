package com.ps;
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
        switch(homeScreen()){
            case "D":
                depositMoney();
                break;
            case "P":
                paymentMoney();
                break;
            case "L":
                ledgerScreen();
                break;
        }
    }

    //--------------- Helping Method -------------//

    public static String homeScreen(){
        String userChoice;

        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment (Debit)");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.println("Choose an option: ");

        userChoice = scanner.next().toUpperCase();
        return userChoice;
    }

    public static Transaction enterDetails(){

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

    public static void depositMoney(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true));

            Transaction t = enterDetails();
            writer.write(t.getDate() + " | " + t.getTime() + " | " + t.getDescription() + " | " +
                    t.getVendor() + " | " + t.getAmount());

            writer.close();
            writer.newLine();
            System.out.println("Deposit added successfully!");
        }
        catch (Exception e){
            System.out.println("Something went wrong! We will let the team know\n" +
                    "Sorry for the inconvenience");
        }
    }

    public static void  paymentMoney(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv"));

            Transaction t = enterDetails();
            writer.write(t.getDate() + " | " + t.getTime() + " | " + t.getDescription() + " | " +
                    t.getVendor() + " | " + t.getAmount()*-1);

            writer.close();
            System.out.println("Payment added successfully!");
        }
        catch (Exception e){
            System.out.println("Something went wrong");
        }
    }

    public static void  ledgerScreen(){
        System.out.println("====== Ledger ======");

        System.out.printf("%-12s | %-8s | %-20s | %-15s | %-10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");

        System.out.println("---------------------------------------------" +
                "---------------------");

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));

            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] transaction = line.split("\\|");
                System.out.printf("%-12s | %-8s | %-20s | %-15s | %-10s%n",
                        transaction[0].trim(), transaction[1].trim(), transaction[2].trim(),
                        transaction[3].trim(), transaction[4].trim());
            }
            bufferedReader.close();
        }
        catch (Exception e){
            System.out.println("Not good");
        }
    }


}