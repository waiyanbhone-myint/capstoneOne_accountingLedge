package com.ps;

import java.util.Scanner;

public class HelperForReport {
    static Scanner scanner = new Scanner(System.in);

    public static void conditionDependOnUserChoice(){

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

    }
}
