package A7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * UDP-based Group Chat Server.
 * Listens for incoming datagram packets, tracks active clients by SocketAddress,
 * and broadcasts messages to all connected clients.
 */
public class GroupChatServer {
    private static final int PORT = 9876;
    private static final int BUFFER_SIZE = 1024;

    // Thread-safe set of connected client addresses (IP + Port)
    private static final Set<SocketAddress> clientAddresses = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   UDP Group Chat Server Started on Port " + PORT);
        System.out.println("==========================================");

        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            byte[] buffer = new byte[BUFFER_SIZE];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(packet); // Wait for incoming datagram

                SocketAddress senderAddress = packet.getSocketAddress();
                String receivedMessage = new String(packet.getData(), 0, packet.getLength()).trim();

                // Process each packet on a separate worker thread for concurrent handling
                new Thread(() -> processAndBroadcast(serverSocket, senderAddress, receivedMessage)).start();
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void processAndBroadcast(DatagramSocket socket, SocketAddress sender, String message) {
        // Handle client disconnection
        if (message.endsWith(": /leave") || message.endsWith(": exit")) {
            clientAddresses.remove(sender);
            System.out.println("Client disconnected: " + sender + " (Active clients: " + clientAddresses.size() + ")");
            broadcast(socket, "[Server]: A user left the chat.", null);
            return;
        }

        // Register new client address if not already present
        boolean isNew = clientAddresses.add(sender);
        if (isNew) {
            System.out.println("New client joined from " + sender + " (Total active: " + clientAddresses.size() + ")");
        }

        System.out.println(sender + " -> " + message);

        // Broadcast to all clients (exclude sender so they don't see duplicate echoes)
        broadcast(socket, message, sender);
    }

    private static void broadcast(DatagramSocket socket, String message, SocketAddress sender) {
        byte[] data = message.getBytes();

        synchronized (clientAddresses) {
            for (SocketAddress client : clientAddresses) {
                // Skip sending message back to the sender
                if (sender != null && client.equals(sender)) {
                    continue;
                }
                try {
                    DatagramPacket sendPacket = new DatagramPacket(data, data.length, client);
                    socket.send(sendPacket);
                } catch (IOException e) {
                    System.err.println("Error sending to " + client + ": " + e.getMessage());
                }
            }
        }
    }
}
