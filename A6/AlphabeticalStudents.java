import java.util.*;

class Student {
    int studentId;
    String name;
    String course;

    Student(int studentId, String name, String course) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
    }

    @Override
    public String toString() {
        return "ID: " + studentId + " | Name: " + name + " | Course: " + course;
    }
}

public class AlphabeticalStudents {

    // Collection to store currently admitted students
    static List<Student> admittedStudents = new ArrayList<>();

    // Method to display names of admitted students in alphabetical order
    // Uses TreeSet which automatically maintains unique elements in sorted order
    public static void displayStudentsAlphabetically() {
        TreeSet<String> sortedNames = new TreeSet<>();

        for (Student s : admittedStudents) {
            sortedNames.add(s.name);
        }

        System.out.println("========== ADMITTED STUDENTS (ALPHABETICAL ORDER) ==========");
        if (sortedNames.isEmpty()) {
            System.out.println("No admitted students found.");
        } else {
            int rank = 1;
            for (String name : sortedNames) {
                System.out.println(rank + ". " + name);
                rank++;
            }
        }
        System.out.println("============================================================\n");
    }

    // Method to cancel admission of a student
    public static void cancelAdmission(int studentId) {
        Student studentToRemove = null;
        for (Student s : admittedStudents) {
            if (s.studentId == studentId) {
                studentToRemove = s;
                break;
            }
        }

        if (studentToRemove != null) {
            admittedStudents.remove(studentToRemove);
            System.out.println("Admission cancelled for: " + studentToRemove.name + " (ID: " + studentId + ")\n");
        } else {
            System.out.println("Student with ID " + studentId + " not found.\n");
        }
    }

    public static void main(String[] args) {
        // Pre-stored 5 admitted student records
        admittedStudents.add(new Student(101, "Vikas Kumar", "MCA"));
        admittedStudents.add(new Student(102, "Amit Singh", "B.Tech"));
        admittedStudents.add(new Student(103, "Rahul Sharma", "MCA"));
        admittedStudents.add(new Student(104, "Priya Verma", "MCA"));
        admittedStudents.add(new Student(105, "Anjali Mehra", "MBA"));

        // Display current admitted list
        System.out.println("All Admitted Students:");
        for (Student s : admittedStudents) {
            System.out.println(s);
        }
        System.out.println();

        // Display students in alphabetical order using TreeSet
        displayStudentsAlphabetically();

        // User input to cancel admission and verify updated alphabetical list
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student ID to cancel admission: ");
        int idToCancel = sc.nextInt();

        cancelAdmission(idToCancel);

        // Display updated alphabetical order (cancelled student will not appear)
        System.out.println("After cancellation:");
        displayStudentsAlphabetically();

        sc.close();
    }
}

