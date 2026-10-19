import java.io.*;
import java.net.*;

public class Q4_Client { 
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port)) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            // Request sent to server
            String request = "TIMETABLE";
            System.out.println("Client sending request: " + request);
            writer.println(request);

            // Server response
            String response = reader.readLine();
            System.out.println("Server Response: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}