package Lab3;

import java.util.*;

public class ReportAnalysis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Medical Report:");
        String report = sc.nextLine();

        // Count words
        String words[] = report.split(" ");
        int word_count = words.length;

        // Count sentences by splitting on dot (.)
        String sentences[] = report.split("\\.");
        int sentence_count = sentences.length;

        // Count occurrences of 'disease'
        int disease_count = 0;
        for (int i = 0; i < words.length; i++) {
            String temp = words[i].toLowerCase();
            if (temp.contains("disease")) {
                disease_count++;
            }
        }

        System.out.println("\n--- Report Analysis ---");
        System.out.println("Total Words: " + word_count);
        System.out.println("Total Sentences: " + sentence_count);
        System.out.println("Occurrences of 'disease': " + disease_count);
    }
}
