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
        return "Student ID: " + studentId + " | Name: " + name + " | Course: " + course;
    }
}

public class SearchStudent {

    // Using HashMap for fast, key-based lookup using unique studentId
    static Map<Integer, Student> studentRecords = new HashMap<>();

    // Method to search student using unique studentId
    public static void searchStudent(int studentId) {
        if (studentRecords.containsKey(studentId)) {
            Student student = studentRecords.get(studentId);
            System.out.println("\n--- Student Found ---");
            System.out.println("ID     : " + student.studentId);
            System.out.println("Name   : " + student.name);
            System.out.println("Course : " + student.course);
            System.out.println("---------------------\n");
        } else {
            System.out.println("\nSearch Failed: Student with ID " + studentId + " not found.\n");
        }
    }

    // Helper method to add a student
    public static void addStudent(int id, String name, String course) {
        studentRecords.put(id, new Student(id, name, course));
    }

    public static void main(String[] args) {
        // Pre-stored 5 student records
        addStudent(101, "Rahul Sharma", "MCA");
        addStudent(102, "Priya Verma", "MCA");
        addStudent(103, "Amit Singh", "B.Tech");
        addStudent(104, "Neha Gupta", "MBA");
        addStudent(105, "Vikas Kumar", "MCA");

        // Display all available records
        System.out.println("========== AVAILABLE STUDENT RECORDS ==========");
        for (Student s : studentRecords.values()) {
            System.out.println(s);
        }
        System.out.println("===============================================\n");

        // Take studentId from user
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student ID to search: ");
        int idToSearch = sc.nextInt();

        // Perform search
        searchStudent(idToSearch);

        sc.close();
    }
}

