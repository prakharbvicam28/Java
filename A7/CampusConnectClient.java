package A7;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CampusConnectClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port);
             Scanner sc = new Scanner(System.in)) {

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String studentName;
            if (args.length > 0) {
                studentName = args[0];
            } else {
                System.out.print("Enter student name: ");
                studentName = sc.nextLine();
            }

            writer.println(studentName);

            String response = reader.readLine();
            System.out.println("Server Response: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
