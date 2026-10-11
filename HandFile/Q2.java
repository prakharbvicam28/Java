import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample input: "101,Ananya,85,91,78"
        System.out.println("Enter student record (e.g., 101,Ananya,85,91,78): ");
        String input = sc.nextLine();

        // 1. Using String method: split() to extract data tokens
        String[] tokens = input.split(",");

        // 2. Converting ID to Wrapper class Integer
        Integer studentId = Integer.valueOf(tokens[0].trim());
        String name = tokens[1].trim();

        // 3. Converting marks to Wrapper class Double/Integer and calculating total
        double total = 0.0;
        int subjectCount = tokens.length - 2;
        Double[] marks = new Double[subjectCount];
        for (int i = 2; i < tokens.length; i++) {
            // Using Double wrapper type
            marks[i-2] = Double.valueOf(tokens[i].trim());
            total += marks[i-2];
        }

        // 4. Calculating Average
        double average = total / subjectCount;

        // 5. Using StringBuffer to build the formatted student report
        StringBuffer report = new StringBuffer();
        report.append("Student ID : ").append(studentId).append("\n");
        report.append("Name       : ").append(name).append("\n");
        report.append("Marks      : ");

        for (int i = 0; i < marks.length; i++) {
            report.append(marks[i]);
            if (i < marks.length - 1) {
                report.append(", ");
            }
        }

        report.append("\nTotal Marks: ").append(total).append("\n");
        report.append("Average    : ").append(String.format("%.2f", average)).append("\n");
        System.out.println(report.toString());

        sc.close();
    }
}
