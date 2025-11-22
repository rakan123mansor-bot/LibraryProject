/*
 * Group Members:
 * 1) Rakan mansour alhaidari   ID: 446102988
 */

import java.util.Scanner;

public class Member {

    // بيانات العميل
    private int id;
    private String name;
    private int borrowedCount;
    private int numViewBorrowed;
    private int numBorrows;
    private int numReturns;
    private double sessionFees;

    //  جمع لكل العملاء
    public static double TotalRevenue = 0.0;
    public static int TotalViewBorrowed = 0;
    public static int TotalBorrows = 0;
    public static int TotalReturns = 0;

    // ثوابت
    private final int MAX_BOOKS = 5;
    private final double FEE = 0.50;

    // باني الكلاس
    public Member(int id, String name, int borrowedCount) {
        this.id = id;
        this.name = name;
        this.borrowedCount = borrowedCount;
        this.numViewBorrowed = 0;
        this.numBorrows = 0;
        this.numReturns = 0;
        this.sessionFees = 0.0;
    }

    // فحص إذا يقدر يستعير
    public boolean canBorrow() {
        return borrowedCount < MAX_BOOKS;
    }

    // فحص إذا يقدر يرجّع
    public boolean canReturn() {
        return borrowedCount > 0;
    }

    // عرض عدد الكتب
    public void viewBorrowedCount() {
        numViewBorrowed++;
        TotalViewBorrowed++;
        System.out.println(name + " has borrowed " + borrowedCount + " books.");
    }

    // استعارة كتاب
    public boolean borrowOne() {
        if (canBorrow()) {
            borrowedCount++;
            numBorrows++;
            TotalBorrows++;
            sessionFees += FEE;
            TotalRevenue += FEE;
            System.out.printf(name + " borrowed one book. Now you have %d books.\n", borrowedCount);
            return true;
        } else {
            System.out.println(name + " cannot borrow more than " + MAX_BOOKS + " books.");
            return false;
        }
    }

    // إرجاع كتاب
    public boolean returnOne() {
        if (canReturn()) {
            borrowedCount--;
            numReturns++;
            TotalReturns++;
            System.out.printf(name + " returned one book. Now you have %d books.\n", borrowedCount);
            return true;
        } else {
            System.out.println(name + " has no books to return.");
            return false;
        }
    }

    // عرض إحصائيات الجلسة
    public void displayStatistics() {
        System.out.println("------ Member Statistics ------");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Borrowed Count: " + borrowedCount);
        System.out.println("View Borrowed: " + numViewBorrowed);
        System.out.println("Borrow Operations: " + numBorrows);
        System.out.println("Return Operations: " + numReturns);
        System.out.printf("Session Fees: $%.2f\n", sessionFees);
        System.out.println("--------------------------------");
    }

    // تصفير الإحصائيات
    public void reset() {
        numViewBorrowed = 0;
        numBorrows = 0;
        numReturns = 0;
        sessionFees = 0.0;
        System.out.println(name + " session statistics have been reset.");
    }

    // get و set
    public int getId() { return id; }
    public String getName() { return name; }
    public int getBorrowedCount() { return borrowedCount; }
    public void setBorrowedCount(int borrowedCount) { this.borrowedCount = borrowedCount; }

    // منيو الأدمن
    public static void adminMenu(Scanner input) {
        int adminChoice;
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1) View Total Revenue");
            System.out.println("2) Most Frequent Operation");
            System.out.println("3) Return to Main Menu");
            System.out.print("Enter choice (1-3): ");

            String line = input.nextLine();
            adminChoice = 0;

            if (line.length() == 1 && line.charAt(0) >= '1' && line.charAt(0) <= '3') {
                adminChoice = line.charAt(0) - '0';

                if (adminChoice == 1) {
                    System.out.printf("Total revenue: $%.2f\n", TotalRevenue);
                } else if (adminChoice == 2) {
                    System.out.println("Total borrows: " + TotalBorrows);
                    System.out.println("Total returns: " + TotalReturns);
                    if (TotalBorrows > TotalReturns)
                        System.out.println("Most frequent: Borrow");
                    else if (TotalReturns > TotalBorrows)
                        System.out.println("Most frequent: Return");
                    else
                        System.out.println("Borrow and Return are tied.");
                }
            } else {
                System.out.println("Invalid choice. Please enter 1-3");
            }

        } while(adminChoice != 3);
    }

    // منيو المستخدم
    public void userMenu(Scanner input) {
        int userChoice;
        do {
            System.out.println("\nUser Menu:");
            System.out.println("1) View Borrowed Books");
            System.out.println("2) Borrow Book ($0.50 fee)");
            System.out.println("3) Return Book");
            System.out.println("4) View Session Summary");
            System.out.println("5) Logout to Main Menu");
            System.out.print("Enter choice (1-5): ");

            String line = input.nextLine();
            userChoice = 0;

            if(line.length() == 1 && line.charAt(0) >= '1' && line.charAt(0) <= '5') {
                userChoice = line.charAt(0) - '0';

                if(userChoice == 1) viewBorrowedCount();
                else if(userChoice == 2) borrowOne();
                else if(userChoice == 3) returnOne();
                else if(userChoice == 4) displayStatistics();
                else if(userChoice == 5) System.out.println("Logging out...");
            } else {
                System.out.println("Invalid choice. Please enter 1-5");
            }

        } while(userChoice != 5);
    }
}
