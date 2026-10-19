package A7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * UDP-based Group Chat Client.
 * Runs two concurrent threads:
 *  1. MessageReceiverThread: Continuously listens for broadcast messages from the server.
 *  2. MessageSenderThread: Continuously reads console input and sends datagram packets to the server.
 */
public class GroupChatClient {
    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 9876;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputUsername = "";
        if (args.length > 0) {
            inputUsername = args[0].trim();
        } else {
            System.out.print("Enter your username for the group chat: ");
            inputUsername = scanner.nextLine().trim();
        }

        final String username = inputUsername.isEmpty()
            ? "User_" + (System.currentTimeMillis() % 1000)
            : inputUsername;

        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(SERVER_HOST);

            System.out.println("==========================================");
            System.out.println("  Connected to UDP Group Chat as [" + username + "]");
            System.out.println("  Type your messages and press Enter.");
            System.out.println("  Type 'exit' or '/leave' to disconnect.");
            System.out.println("==========================================");

            // Send initial join packet so the server registers this client
            String joinMessage = "[" + username + " joined the chat]";
            byte[] joinData = joinMessage.getBytes();
            DatagramPacket joinPacket = new DatagramPacket(joinData, joinData.length, serverAddress, SERVER_PORT);
            clientSocket.send(joinPacket);

            // Thread 1: Listening for incoming messages from the server
            Thread receiverThread = new Thread(() -> {
                byte[] buffer = new byte[BUFFER_SIZE];
                try {
                    while (!clientSocket.isClosed()) {
                        DatagramPacket incomingPacket = new DatagramPacket(buffer, buffer.length);
                        clientSocket.receive(incomingPacket);
                        String incomingMsg = new String(incomingPacket.getData(), 0, incomingPacket.getLength());
                        System.out.println(incomingMsg);
                    }
                } catch (IOException e) {
                    if (!clientSocket.isClosed()) {
                        System.err.println("Receive error: " + e.getMessage());
                    }
                }
            }, "ReceiverThread-" + username);

            // Thread 2: Reading user input and sending to the server
            Thread senderThread = new Thread(() -> {
                try {
                    while (!clientSocket.isClosed()) {
                        if (!scanner.hasNextLine()) {
                            break;
                        }
                        String text = scanner.nextLine().trim();
                        if (text.isEmpty()) {
                            continue;
                        }

                        if (text.equalsIgnoreCase("exit") || text.equalsIgnoreCase("/leave")) {
                            String leaveMsg = "[" + username + "]: /leave";
                            byte[] leaveBytes = leaveMsg.getBytes();
                            DatagramPacket leavePacket = new DatagramPacket(
                                leaveBytes, leaveBytes.length, serverAddress, SERVER_PORT
                            );
                            clientSocket.send(leavePacket);
                            System.out.println("You have left the chat.");
                            clientSocket.close();
                            break;
                        }

                        String formattedMsg = "[" + username + "]: " + text;
                        byte[] sendData = formattedMsg.getBytes();
                        DatagramPacket packet = new DatagramPacket(
                            sendData, sendData.length, serverAddress, SERVER_PORT
                        );
                        clientSocket.send(packet);
                    }
                } catch (IOException e) {
                    if (!clientSocket.isClosed()) {
                        System.err.println("Send error: " + e.getMessage());
                    }
                }
            }, "SenderThread-" + username);

            // Start both threads for simultaneous messaging
            receiverThread.start();
            senderThread.start();

            // Wait for sender thread to finish (when user exits)
            senderThread.join();
            clientSocket.close();
            receiverThread.interrupt();

        } catch (Exception e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}