// package A1;
import java.util.*;

public class Person {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice = 0;

        while (choice != 3) {

            System.out.println("\n===== PERSON MANAGEMENT SYSTEM =====");

            System.out.println("1. Work with Student Details");

            System.out.println("2. Work with Staff Details");

            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            
            if (sc.hasNextInt()) {

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                       
                        StudentRecord.main(new String[]{});
                        break;

                    case 2:
                        StaffRecord.main(new String[]{});
                        break;

                    case 3:
                        System.out.println("Exiting the main application...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }

            } else {

                System.out.println("Invalid input. Please enter a number.");
                sc.next(); 

            }
           
        }
        sc.close();

    }
}


