package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Scanner;

public class ReportScreen {
    static Scanner scanner = new Scanner(System.in);

    public static void conditionDependOnUserChoice() {
        boolean keepGoing = true;

        while (keepGoing) {
            switch (reportMenuAndUserChoice()) {
                case 1:
                    monthToDate();
                    break;
                case 2:
                    previousMonth();
                    break;
                case 3:
                    yearToDate();
                    break;
                case 4:
                    previousYear();
                    break;
                case 5:
                    searchByVendor();
                    break;
                case 6:
                    customSearch();
                case 0:
                    return; // back to Ledger
                case 7:
                    Main.homeScreen();
                    return;
                default:
                    System.out.println("\nPlease choose a valid option (0-5, H).\n");
            }
            space();
        }
    }

    public static int reportMenuAndUserChoice() {
        System.out.println("Reports Menu\n============");
        System.out.println("1) Month To Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("6) Custom Search");
        System.out.println("7) Home");
        System.out.println("0) Back to Ledger");
        System.out.println("=====================");
        System.out.print("Choose an option: ");
        return scanner.nextInt();
    }

    public static void customSearch() {
        scanner.nextLine();

        System.out.print("Enter start date (YYYY-MM-DD) or press Enter to skip: ");
        String startInput = scanner.nextLine();
        LocalDate startDate;
        if (startInput.isEmpty()) {
            startDate = null;
        } else {
            startDate = LocalDate.parse(startInput);
        }

        System.out.print("Enter end date (YYYY-MM-DD) or press Enter to skip: ");
        String endInput = scanner.nextLine();
        LocalDate endDate;
        if (endInput.isEmpty()) {
            endDate = null;
        } else {
            endDate = LocalDate.parse(endInput);
        }

        System.out.print("Enter description or press Enter to skip: ");
        String descriptionInput = scanner.nextLine().toLowerCase();

        System.out.print("Enter vendor or press Enter to skip: ");
        String vendorInput = scanner.nextLine().toLowerCase();

        System.out.print("Enter amount or press Enter to skip: ");
        String amountInput = scanner.nextLine();
        Double amount;
        if (amountInput.isEmpty()) {
            amount = null;
        } else {
            amount = Double.parseDouble(amountInput);
        }

        printHeader();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                LocalDate date = LocalDate.parse(data[0].trim());
                String time = data[1].trim();
                String description = data[2].trim().toLowerCase();
                String vendor = data[3].trim().toLowerCase();
                double entryAmount = Double.parseDouble(data[4].trim());

                boolean matches = true;

                if (startDate != null && date.isBefore(startDate)) matches = false;
                if (endDate != null && date.isAfter(endDate)) matches = false;
                if (!descriptionInput.isEmpty() && !description.contains(descriptionInput)) matches = false;
                if (!vendorInput.isEmpty() && !vendor.contains(vendorInput)) matches = false;
                if (amount != null && entryAmount != amount) matches = false;

                if (matches) {
                    printTransaction(data);
                    found = true;
                }
            }
        } catch (Exception e) {
            //System.out.println("Error during custom search.");
        }

        if (!found) {
            System.out.println("No transactions matched your search criteria.");
        }
    }

    public static void monthToDate() {
        System.out.println("Do you want to view current month? (Y/N): ");
        String choice = scanner.next().toUpperCase();

        int monthCheck;
        if (choice.equals("Y")) {
            monthCheck = LocalDate.now().getMonthValue();
        } else {
            System.out.println("Enter the month (1-12): ");
            monthCheck = scanner.nextInt();
        }

        printHeader();
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                LocalDate date = LocalDate.parse(data[0].trim());

                if (date.getMonthValue() == monthCheck) {
                    printTransaction(data);
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading transactions.");
        }
    }

    public static void previousMonth() {
        LocalDate today = LocalDate.now();
        LocalDate lastMonth = today.minusMonths(1);

        printHeader();
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                LocalDate date = LocalDate.parse(data[0].trim());
                if (date.getMonthValue() == lastMonth.getMonthValue() && date.getYear() == lastMonth.getYear()) {
                    printTransaction(data);
                }
            }
        } catch (Exception e) {
            System.out.println("There are no transactions for the previous month.");
        }
    }

    public static void yearToDate() {
        int currentYear = LocalDate.now().getYear();

        printHeader();
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                LocalDate date = LocalDate.parse(data[0].trim());
                if (date.getYear() == currentYear) {
                    printTransaction(data);
                }
            }
        } catch (Exception e) {
            System.out.println("No year-to-date transactions found.");
        }
    }

    public static void previousYear() {
        int previousYear = LocalDate.now().getYear() - 1;

        printHeader();
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                LocalDate date = LocalDate.parse(data[0].trim());
                if (date.getYear() == previousYear) {
                    printTransaction(data);
                }
            }
        } catch (Exception e) {
            System.out.println("No transactions found for previous year.");
        }
    }

    public static void searchByVendor() {
        scanner.nextLine(); // clear leftover newline
        System.out.print("Enter the vendor name to search for: ");
        String vendorSearch = scanner.nextLine().trim().toLowerCase();

        printHeader();
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                String vendor = data[3].trim().toLowerCase();
                if (vendor.contains(vendorSearch)) {
                    printTransaction(data);
                    found = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error while searching for vendor.");
        }

        if (!found) {
            System.out.println("No transactions found for vendor: " + vendorSearch);
        }
    }

    public static void printHeader() {
        System.out.printf("%-12s | %-8s | %-20s | %-15s | %-10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("------------------------------------------------------------------");
    }

    public static void printTransaction(String[] data) {
        System.out.printf("%-12s | %-8s | %-20s | %-15s | %-10s%n",
                data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim());
    }

    public static void space() {
        System.out.println("\n\n");
    }
}
