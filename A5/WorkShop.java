/*
class StudentRegistration extends Thread {
    int studentId;

    StudentRegistration(int id) {
        studentId = id;
    }

    @Override
    public void run() {
        if (WorkShop.availableSeats > 0) {
            WorkShop.availableSeats--;
            System.out.println("Student " + studentId + " reserved a seat.");
        } else {
            System.out.println("Student " + studentId + " failed. No seats.");
        }
    }
} */


// --- SYNCHRONIZED VERSION ---
class StudentRegistration extends Thread {
    int studentId;

    StudentRegistration(int id) {
        studentId = id;
    }

    @Override
    public void run() {
        WorkShop.reserveSeat(studentId);
    }
}

public class WorkShop {
    static int availableSeats = 5;

    // By adding "synchronized", Java puts a lock on this method.
    // Only ONE thread is allowed to be inside this method at a time!
    public static synchronized void reserveSeat(int studentId) {
        if (availableSeats > 0) {
            availableSeats--;
            System.out.println("Student " + studentId + " reserved a seat.");
        } else {
            System.out.println("Student " + studentId + " failed. No seats.");
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            StudentRegistration student = new StudentRegistration(i);
            student.start();
        }
    }
}
