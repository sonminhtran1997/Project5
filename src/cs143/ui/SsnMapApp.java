package cs143.ui;

import cs143.business.RetireeManager;
import cs143.domain.Retiree;
import java.util.Scanner;

public class SsnMapApp {

    private static Scanner scanIn = new Scanner(System.in);
    private static RetireeManager rm = new RetireeManager();

    public static void main(String[] args) {
        long input = 0;
        do {
            input = displayMenu();
            if (input == 0) {
                delete();
            } else if (input == 1) {
                add();
            } else if (input > 1) {
                get(input);
            }
        } while (input >= 0);
    }

    public static long displayMenu() {
        //TODO - write this code
        long input = 0;
        System.out.println("Select an option");
        System.out.println("\t\t-1 Exit");
        System.out.println("\t\t0 Delete a Retiree");
        System.out.println("\t\t1 Add a Retiree");
        System.out.println("\tSSN Get Monthly Benefits");
        System.out.print(">");
        try {
            input = scanIn.nextLong();
        } catch (Exception e) {
            System.out.println("The selection must be number in the list");
            return -1;
        }
        return input;
    }

    public static void delete() {
        long ssn;
        boolean checkSsn;
        System.out.print("Enter the Social Security number you want to delete: ");
        try {
            do {
                checkSsn = true;
                ssn = scanIn.nextLong();
                if (ssn <= 1000000) {
                    checkSsn = false;
                }
                if (ssn >= 999999999) {
                    checkSsn = false;
                }
                if (!checkSsn) {
                    System.out.println("Invalid ssn enter again:");
                }
            } while (!checkSsn);
            rm.delete(ssn);
        } catch (Exception e) {
            System.out.println("SSN must be 9 digit number");
            System.exit(0);
        }
    }

    public static void add() {
        long ssn;
        String name;
        double benefit;
        boolean checkSsn = true;
        boolean checkName = true;
        boolean checkBenefit = true;
        try {
            System.out.print("Enter the name of the SSN holder: ");
            scanIn.nextLine();
            name = scanIn.nextLine();
            System.out.println("Enter the Social Security number you want to insert");
            do {
                checkSsn = true;
                ssn = scanIn.nextLong();
                if (ssn <= 1000000) {
                    checkSsn = false;
                }            
                if (ssn >= 999999999) {
                    checkSsn = false;
                }       
                if (!checkSsn) {
                    System.out.println("Invalid ssn enter again:");
                }
            } while (!checkSsn);
            System.out.println("Enter the amount of benefit money: ");
            do {
                checkBenefit = true;
                benefit = scanIn.nextDouble();
                if (benefit < 0 || benefit > 999999999) {
                    checkBenefit = false;
                }
                if (!checkBenefit) {
                    System.out.println("Invalid benefit, enter again: ");
                }
            } while (!checkName);
            rm.add(new Retiree(ssn, name, benefit));

        } catch (Exception e) {
            System.out.println("Data input is invalid");
            System.exit(0);
        }

    }

    public static void get(long ssn) {
        if (rm.get(ssn) == null) {
            System.out.println("Retiree does not exist in the system!");
        } else {
            System.out.println(rm.get(ssn).toString());
        }
    }

}
