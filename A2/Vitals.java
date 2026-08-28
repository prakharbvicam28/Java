package A2;

import java.util.*;

public class Vitals {

    // 2D array to store vital signs: [Row = Patient][Col 0: Name, Col 1: Temp, Col 2: BP, Col 3: Sugar]
    static String vitals_table[][] = new String[10][4];

    // Method to fetch patient objects and store details into 2D array
    public static void StoreIn2DArray() {
        for (int i = 0; i < Patient.p_count; i++) {
            Patient p = Patient.p_arr[i];
            if (p != null) {
                vitals_table[i][0] = p.pat_name;
                vitals_table[i][1] = String.valueOf(p.temp);
                vitals_table[i][2] = String.valueOf(p.bp);
                vitals_table[i][3] = String.valueOf(p.sugar);
            }
        }
    }

    // Method to display the 2D array in tabular form
    public static void DisplayVitalsTable() {
        StoreIn2DArray();

        if (Patient.p_count == 0) {
            System.out.println("No patient vitals recorded yet.");
            return;
        }

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("                   PATIENT VITAL SIGNS REPORT                     ");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-15s | %-12s | %-10s | %-12s\n", "Patient Name", "Temp (°F)", "BP (mmHg)", "Sugar (mg/dL)");
        System.out.println("------------------------------------------------------------------");

        for (int i = 0; i < Patient.p_count; i++) {
            System.out.printf("%-15s | %-12s | %-10s | %-12s\n",
                    vitals_table[i][0],
                    vitals_table[i][1],
                    vitals_table[i][2],
                    vitals_table[i][3]);
        }
        System.out.println("------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- VITALS MENU ---");
            System.out.println("Press 1 to Insert Patient Vitals");
            System.out.println("Press 2 to Display Vitals Table");
            System.out.println("Press 3 to Exit");
            System.out.print("Enter Choice: ");
            int ch = sc.nextInt();

            if (ch == 1) {
                if (Patient.p_count >= 10) {
                    System.out.println("Patient capacity reached (Max 10).");
                    continue;
                }
                Patient p = new Patient();
                System.out.print("Enter Patient Name: ");
                p.pat_name = sc.next();
                System.out.print("Enter Temperature (°F): ");
                p.temp = sc.nextDouble();
                System.out.print("Enter BP (mmHg): ");
                p.bp = sc.nextInt();
                System.out.print("Enter Sugar Level (mg/dL): ");
                p.sugar = sc.nextInt();

                Patient.p_arr[Patient.p_count] = p;
                Patient.p_count++;
                System.out.println("Vitals successfully inserted!");

            } else if (ch == 2) {
                DisplayVitalsTable();
            } else if (ch == 3) {
                System.out.println("Exiting...");
                break;
            }
        }
    }
}
