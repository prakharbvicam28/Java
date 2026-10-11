import java.util.*;

class AnswerServer {
    private int t_sub = 0;
    private final List<String> sub_List = Collections.synchronizedList(new ArrayList<>());

    public synchronized void submitAnswer(String studentName) {
        t_sub++;
        sub_List.add(studentName);
        System.out.println(studentName + " submitted answer. (Total: " + t_sub + ")");
    }

    public int getTotalSubmissions() {
        return t_sub;
    }

    public List<String> getSubmissionList() {
        return sub_List;
    }
}

class StudentThread extends Thread {
    private final String studentName;
    private final AnswerServer server;

    public StudentThread(String studentName, AnswerServer server) {
        this.studentName = studentName;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            server.submitAnswer(studentName);
        } catch (InterruptedException e) {
            System.out.println(studentName + " was interrupted during submission.");
        }
    }
}

public class Q3
{
    public static void main(String[] args) {
        AnswerServer server = new AnswerServer();

        StudentThread s1 = new StudentThread("Student-101 (Ananya)", server);
        StudentThread s2 = new StudentThread("Student-102 (Rohan)", server);
        StudentThread s3 = new StudentThread("Student-103 (Priya)", server);
        StudentThread s4 = new StudentThread("Student-104 (Aman)", server);

        // Assign different thread priorities
        s1.setPriority(Thread.MAX_PRIORITY); // Priority 10
        s2.setPriority(Thread.MIN_PRIORITY); // Priority 1

        s1.start();
        s2.start();
        s3.start();
        s4.start();

        try {
            s1.join();
            s2.join();
            s3.join();
            s4.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("\n--- Final Submission Summary ---");
        System.out.println("Total Submissions: " + server.getTotalSubmissions());
        System.out.println("Submission Order: " + server.getSubmissionList());
        
    }
}

