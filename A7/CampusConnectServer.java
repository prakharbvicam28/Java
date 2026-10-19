package A7;

import java.io.*;
import java.net.*;

class StudentHandler extends Thread {
    private Socket socket;

    public StudentHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String studentName = reader.readLine();
            System.out.println("Student connected: " + studentName);

            String response = "Hello " + studentName + ", you are connected.";
            writer.println(response);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class CampusConnectServer {
    public static void main(String[] args) {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("CampusConnect Server started on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                StudentHandler handler = new StudentHandler(socket);
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
