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

public class AdmissionSystem {

    // Collection of currently admitted students
    static List<Student> admittedStudents = new ArrayList<>();

    // Waiting list for each course following FIFO order (Queue)
    static Map<String, Queue<Student>> waitingLists = new HashMap<>();

    // Method to display all admitted students
    public static void displayAdmittedStudents() {
        System.out.println("========== CURRENTLY ADMITTED STUDENTS ==========");
        if (admittedStudents.isEmpty()) {
            System.out.println("No admitted students.");
        } else {
            for (Student s : admittedStudents) {
                System.out.println(s);
            }
        }
        System.out.println("=================================================\n");
    }

    // Method to display waiting list for a course
    public static void displayWaitingList(String course) {
        Queue<Student> waitQueue = waitingLists.get(course);

        System.out.println("----- Waiting List for " + course + " -----");
        if (waitQueue == null || waitQueue.isEmpty()) {
            System.out.println("Waiting list is empty.");
        } else {
            int position = 1;
            for (Student s : waitQueue) {
                System.out.println(position + ". ID: " + s.studentId + ", Name: " + s.name);
                position++;
            }
        }
        System.out.println();
    }

    // Method to cancel admission
    public static void cancelAdmission(int studentId) {
        Student studentToCancel = null;

        // 1. Search for student using studentId
        for (Student s : admittedStudents) {
            if (s.studentId == studentId) {
                studentToCancel = s;
                break;
            }
        }

        // 2. If the student does not exist, display an appropriate message
        if (studentToCancel == null) {
            System.out.println("Cancellation Failed: Student with ID " + studentId + " is not found in the admitted list.\n");
            return;
        }

        // 3. If student exists:
        // Remove from admitted-student collection
        admittedStudents.remove(studentToCancel);
        String course = studentToCancel.course;

        // Display cancellation confirmation
        System.out.println("\n--- Admission Cancelled Successfully ---");
        System.out.println("Cancelled Student: " + studentToCancel.name + " (ID: " + studentToCancel.studentId + ") from Course: " + course);

        // 4. Check whether a waiting list exists for that course
        Queue<Student> waitQueue = waitingLists.get(course);

        // 5. If waiting list is not empty, admit first student (FIFO)
        if (waitQueue != null && !waitQueue.isEmpty()) {
            Student nextStudent = waitQueue.poll(); // Selects and removes first student in FIFO order
            admittedStudents.add(nextStudent);      // Admit automatically

            System.out.println("Seat allocated to next candidate from waiting list:");
            System.out.println("Newly Admitted -> ID: " + nextStudent.studentId + ", Name: " + nextStudent.name + ", Course: " + nextStudent.course);
        } else {
            System.out.println("No students in the waiting list for " + course + ". Seat remains vacant.");
        }
        System.out.println("----------------------------------------\n");
    }

    public static void main(String[] args) {
        // Pre-stored 5 admitted student records
        admittedStudents.add(new Student(101, "Rahul Sharma", "MCA"));
        admittedStudents.add(new Student(102, "Priya Verma", "MCA"));
        admittedStudents.add(new Student(103, "Amit Singh", "B.Tech"));
        admittedStudents.add(new Student(104, "Neha Gupta", "MBA"));
        admittedStudents.add(new Student(105, "Vikas Kumar", "MCA"));

        // Pre-stored waiting list for MCA (FIFO order using LinkedList Queue)
        Queue<Student> mcaWaiting = new LinkedList<>();
        mcaWaiting.add(new Student(201, "Aman Gupta", "MCA"));
        mcaWaiting.add(new Student(202, "Sneha Patel", "MCA"));
        waitingLists.put("MCA", mcaWaiting);

        // Pre-stored waiting list for B.Tech
        Queue<Student> btechWaiting = new LinkedList<>();
        btechWaiting.add(new Student(203, "Rohan Das", "B.Tech"));
        waitingLists.put("B.Tech", btechWaiting);

        // Empty waiting list for MBA
        Queue<Student> mbaWaiting = new LinkedList<>();
        waitingLists.put("MBA", mbaWaiting);

        // Display initial admitted records and waiting lists
        displayAdmittedStudents();
        displayWaitingList("MCA");
        displayWaitingList("B.Tech");
        displayWaitingList("MBA");

        // User input to cancel admission
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student ID to cancel admission: ");
        int idToCancel = sc.nextInt();

        // Perform cancellation
        cancelAdmission(idToCancel);

        // Display updated lists after cancellation
        System.out.println("Updated status after operation:");
        displayAdmittedStudents();
        displayWaitingList("MCA");
        displayWaitingList("B.Tech");
        displayWaitingList("MBA");

        sc.close();
    }
}
