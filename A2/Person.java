package A2;

import java.util.*;

public class Person {
    static Scanner sc = new Scanner(System.in);
    String doc_name, specialization, degree, pat_name, disease, doc_assigned;
    int fee, years_of_exp, age;

    static Person det[] = new Person[20];
    static int i = 0;

    static void DisplayDetails() {
        System.out.println("    Press 1 for Doc Details");
        System.out.println("    Press 2 for Patient  Details");
        System.out.println("    Press 3 to Exit");
        System.out.print("      Enter Choice: ");
        int ch = sc.nextInt();
        if (ch == 1) {
            for (Person p : det) {
                if (p != null && p instanceof Doctor) {
                    System.out.println("Name: " + p.doc_name);
                    System.out.println("Specialization: " + p.specialization);
                    System.out.println("Degree: " + p.degree);
                    System.out.println("Fee: " + p.fee);
                    System.out.println("Years Of Experience: " + p.years_of_exp);
                    System.out.println("-----------------------------");
                }

            }
        } else if (ch == 2) {
            for (Person p : det) {
                if (p != null && p instanceof Patient) {
                    System.out.println("Name: " + p.pat_name);
                    System.out.println("Disease: " + p.disease);
                    System.out.println("Doc_Assigned: " + p.doc_assigned);
                    System.out.println("Age: " + p.age);
                    System.out.println("-----------------------------");
                }
            }
        } else if (ch == 3) {
            return;
        }
    }

    public static void main(String[] args) {
        while(true){
            System.out.println("Press 1 to Insert Doctor Details");
            System.out.println("Press 2 to Insert Patient Details");
            System.out.println("Press 3 to View Details");
            System.out.println("Press 4 to Calculate Bill");
            System.out.println("Press 5 to Exit");
            System.out.print("Enter Choice: ");
            int ch = sc.nextInt();
            if (ch == 1) {
                Doctor D = new Doctor();
                D.InsertDocDetails();
            } else if (ch == 2) {
                Patient P = new Patient();
                P.InsertPatientDetails();
            } else if (ch == 3) {
                DisplayDetails();
            } else if (ch == 4) {
                Patient P = new Patient();
                P.calculateBill();
            } else if (ch == 5) {
                System.out.println("Exiting....");
                return;
            }
        }
    }

}
