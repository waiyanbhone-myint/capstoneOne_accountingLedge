package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Scanner;

public class HelperForReport {
    static Scanner scanner = new Scanner(System.in);

    public static void conditionDependOnUserChoice(){
        boolean condition = true;
        while(condition) {

            switch (reportMenuAndUserChoice()) {
                case 1:
                    monthToDate();
                    space();
                    break;
                case 2:
                    previousMonth();
                    space();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Please choose only an available option.");
            }
        }
    }

    public static Integer reportMenuAndUserChoice(){
        System.out.println("Reports Menu\n============");
        System.out.println("1) Month To Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("6) Back to Ledger");

        System.out.println("Choose an option: ");
        return scanner.nextInt();
    }

    public static void monthToDate(){
        System.out.println("Which month of transaction you would like to display? Please enter the number of month: ");
        int userInputOfMonth = scanner.nextInt();

        System.out.printf("%-12s | %-8s | %-20s | %-15s | %-10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");

        System.out.println("------------------------------------------------------------------");

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));

            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] data = line.split("\\|");
                LocalDate date = LocalDate.parse(data[0].trim());
                int month = date.getMonthValue();
                if(userInputOfMonth == month){
                    System.out.printf("%-12s | %-8s | %-20s | %-15s | %-10s%n",
                            data[0], data[1], data[2], data[3], data[4]);
                }
            }
        } catch (Exception e) {

        }

    }

    public static void previousMonth(){
        LocalDate today = LocalDate.now();
        LocalDate lastMonth = today.minusMonths(1);
        int lastMonthValue = lastMonth.getMonthValue();

        try{
            System.out.printf("%-12s | %-8s | %-20s | %-15s | %-10s%n",
                    "Date", "Time", "Description", "Vendor", "Amount");

            System.out.println("------------------------------------------------------------------");

            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));

            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] data = line.split("\\|");
                LocalDate date = LocalDate.parse(data[0].trim());
                int month = date.getMonthValue();

                if(lastMonthValue == month){
                    System.out.printf("%-12s | %-8s | %-20s | %-15s | %-10s%n",
                            data[0], data[1], data[2], data[3], data[4]);
                }
            }
        } catch (Exception e) {
            System.out.println("There are no transactions for last month");

        }

    }

    public static void space() {
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }
}
