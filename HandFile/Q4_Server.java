import java.io.*;
import java.net.*;

public class Q4_Server {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Campus Resource Server started on port " + port);

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String request = reader.readLine();
            System.out.println("Request Received: " + request);

            String response;
            if (request == null) {
                response = "INVALID REQUEST";
            } else {
                switch (request.trim().toUpperCase()) {
                    case "NOTES":
                        response = "Notes: Unit 1 Java Basics uploaded on Portal.";
                        break;
                    case "TIMETABLE":
                        response = "Java Lab - Monday - Room 204 - 10:00 AM";
                        break;
                    case "RESULT":
                        response = "Result: MCA Semester 1 Results Published.";
                        break;
                    default:
                        response = "ERROR: Invalid Request. Options: NOTES, TIMETABLE, RESULT";
                        break;
                }
            }

            writer.println(response);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}