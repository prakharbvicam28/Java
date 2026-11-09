// package A1;
import java.util.*;
public class Result {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your marks: ");
        int marks = sc.nextInt();
        char grade = calculateGrade(marks);
        System.out.println("Your grade is: " + grade);
        sc.close();
    }
    public static char calculateGrade(int marks) {
        int category;
        if (marks >= 90 && marks <= 100) {
            category = 1;
        } else if (marks >= 75 && marks < 90) {
            category = 2;
        } else if (marks >= 60 && marks < 75) {
            category = 3;
        } else {
            category = 4;
        }

        switch (category) {
            case 1:
                return 'A';
            case 2:
                return 'B';
            case 3:
                return 'C';
            case 4:
                return 'F';
            default:
                return 'F';
        }
    }
}

