class AssignmentSubmissionThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Student " + i + " submitted assignment");
            // Sleep for half a second to simulate time taken
            try { Thread.sleep(500); } catch (Exception e) {} 
        }
    }
}

class AssignmentEvaluationThread extends Thread {
    @Override
    public void run() {
        // Sleep for just a tiny bit at the start so Submitter prints first!
        try { Thread.sleep(100); } catch (Exception e) {} 

        for (int i = 1; i <= 5; i++) {
            System.out.println("Student " + i + " assignment evaluated");
            // Sleep for half a second to simulate time taken
            try { Thread.sleep(500); } catch (Exception e) {}
        }
    }
}

public class Submit {
    public static void main(String[] args) {
        AssignmentSubmissionThread submitter = new AssignmentSubmissionThread();
        AssignmentEvaluationThread evaluator = new AssignmentEvaluationThread();

        submitter.start();
        evaluator.start();
    }
}