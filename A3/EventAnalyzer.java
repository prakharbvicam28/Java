package A3;

import java.util.Arrays;
import java.util.Scanner;

public class EventAnalyzer {

    void VenueAllocate(String[] events, String[] venues, String[][] pt, int pt_c) {
        // PQ10: Loop through all events and check case-insensitively
        for (int e = 0; e < events.length; e++) {
            String event = events[e];
            int prt = 0;
            
            // Count participants registered for this event
            for (int i = 0; i < pt_c; i++) {
                if (pt[i][1].equalsIgnoreCase(event)) { 
                    prt++;
                }
            }
            
            if (prt >= 3) {
                // Allocate corresponding venue and fixed time
                System.out.println("Event: " + event + " | Participants: " + prt + " | Venue Allocated: " + venues[e] + " at 10:00 AM");
            }
            else {
                System.out.println("Event: " + event + " | Participants: " + prt + " | Insufficient Participants :( ");
            }
        }
    }

    void SearchParticipants(String[][] pt, int pt_c, Scanner sc) {
        if (pt_c == 0) {
            System.out.println("No participants registered yet!");
            return;
        }
        System.out.println();
        System.out.println("1 --> Alphabetically");
        System.out.println("2 --> Longest name");
        System.out.println("3 --> Shortest name");
        System.out.println("4 --> Count by starting letter");
        System.out.println("5 --> Vowel count");
        System.out.print("Enter choice: ");
        int searchCh = sc.nextInt();
        
        switch (searchCh) {
            case 1:
                String[] names = new String[pt_c];
                for (int i = 0; i < pt_c; i++) {
                    names[i] = pt[i][0];
                }
                Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
                System.out.println("Names alphabetically:");
                for (String n : names) {
                    System.out.println(n);
                }
                break;
            case 2:
                String longest = pt[0][0];
                for (int i = 1; i < pt_c; i++) {
                    if (pt[i][0].length() > longest.length()) {
                        longest = pt[i][0];
                    }
                }
                System.out.println("Longest name: " + longest);
                break;
            case 3:
                String shortest = pt[0][0];
                for (int i = 1; i < pt_c; i++) {
                    if (pt[i][0].length() < shortest.length()) {
                        shortest = pt[i][0];
                    }
                }
                System.out.println("Shortest name: " + shortest);
                break;
            case 4:
                System.out.print("Enter search letter: ");
                String letter = sc.next().toLowerCase();
                int letterCount = 0;
                for (int i = 0; i < pt_c; i++) {
                    if (pt[i][0].toLowerCase().startsWith(letter)) {
                        letterCount++;
                    }
                }
                System.out.println("Count: " + letterCount);
                break;
            case 5:
                for (int i = 0; i < pt_c; i++) {
                    String name = pt[i][0];
                    int vowels = 0;
                    for (int j = 0; j < name.length(); j++) {
                        char ch = Character.toLowerCase(name.charAt(j));
                        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                            vowels++;
                        }
                    }
                    System.out.println(name + " has " + vowels + " vowels");
                }
                break;
            default:
                System.out.println("Invalid search choice");
        }
    }
}
