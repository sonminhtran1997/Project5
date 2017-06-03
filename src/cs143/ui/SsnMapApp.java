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
        rm.delete(123);
    }

    public static void add() {
        //TODO - write this code
//        rm.add(new retiree())
    }

    public static void get(long ssn) {
        //TODO - write this code
    }

}
