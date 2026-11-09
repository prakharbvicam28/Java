// package A1;
import java.util.*;
class Staff{
    String staffName,dept,Email,hireDate;
    int staffID,Phone;
    public Staff(String staffName, int staffID, String dept, String Email, int Phone, String hireDate) {
        this.staffName = staffName;
        this.staffID = staffID;
        this.dept = dept;
        this.Email = Email;
        this.Phone = Phone;
        this.hireDate = hireDate;
    }
}



public class StaffRecord {
    Scanner sc=new Scanner(System.in);
    Staff arr[]=new Staff[10];
    int index=0;
    public static void main(String[] args) {
        StaffRecord record = new StaffRecord();
        int choice = 0;
        while (choice != 3) {
            System.out.println("\n--- Staff Record Menu ---");
            System.out.println("1. Insert Staff");
            System.out.println("2. Fetch Details");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            if (record.sc.hasNextInt()) {
                choice = record.sc.nextInt();
                record.sc.nextLine(); // consume the leftover newline
                switch (choice) {
                    case 1:
                        record.InsertStaffDetails();
                        break;
                    case 2:
                        record.FetchStaff();
                        break;
                    case 3:
                        System.out.println("Exiting program...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                record.sc.next(); // clear invalid input
            }
        }
    }
    public void InsertStaffDetails(){
        if (index == 10) {
            System.out.println("Staff Capacity Reached.");
            return;
        }
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Staff ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter Department: ");
        String dept = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Phone (without country code): ");
        int phone = sc.nextInt();
        sc.nextLine(); // consume newline
        
        System.out.print("Enter Hire Date (DD/MM/YYYY): ");
        String hireDate = sc.nextLine();
        Staff st = new Staff(name, id, dept, email, phone, hireDate);
        arr[index] = st;
        index++;
        System.out.println("Staff added successfully!");
    }
    public void FetchStaff(){
        if (index == 0) {
            System.out.println("No Staff Record Found.");
            return;
        }
        for (int i = 0; i < index; i++) {
            Staff rec = arr[i];
            System.out.println("Name: " + rec.staffName + "  |  ID: " + rec.staffID + "  |  Dept: " + rec.dept + "  |  Email: " + rec.Email + "  |  Phone: " + rec.Phone + "  |  Hire Date: " + rec.hireDate);
        }
    }
}

