// package A1;
import java.util.*;
class Student
{
    String studentName,course;
    int studentID,marks;
}
public class StudentRecord extends Student
{
    Scanner sc=new Scanner(System.in);
    Student arr[]=new Student[10];
    int index=0;
    public static void main(String[] args)
    {
        StudentRecord record = new StudentRecord();
        int choice = 0;
        while (choice != 3) {
            System.out.println("\n--- Student Record Menu ---");
            System.out.println("1. Insert Student");
            System.out.println("2. Fetch Details");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            

            if (record.sc.hasNextInt()) {
                choice = record.sc.nextInt();
                switch (choice) {
                    case 1:
                        record.InsertStudent();
                        break;
                    case 2:
                        record.FetchStudents();
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
    public void InsertStudent()
    {
        if (index==9)
            {
                System.out.println("Student Capacitu Reached");
                return; }
        Student st=new Student();
        System.out.print("Enter Name: ");
        st.studentName=sc.next();
        System.out.println();

        System.out.print("Enter Student ID: ");
        st.studentID=sc.nextInt();
        System.out.println();

        System.out.print("Enter Course ");
        st.course=sc.next();
        System.out.println();

        System.out.print("Enter Marks");
        st.marks=sc.nextInt();
        System.out.println();

        arr[index]=st;
        index=index+1;
    }
    public void FetchStudents()
    {
        if (index==0)
            {
                System.out.println("No Student Record Found");
                return;
            }
        for (int i = 0; i < index; i++) {
            Student rec = arr[i];
            System.out.println("Name:  "+rec.studentName+"  ID:  "+rec.studentID+"  Course:  "+rec.course+"  Marks:  "+rec.marks);
        }
    }
}
