import java.io.*;
import java.net.*;
import java.util.*;

public class ServerMeaning {
    private static Map<String, String> dictionary = new HashMap<>();

    public static void main(String[] args) {
        initializeDictionary();

        try {
            // Create server socket
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("Server started...");

            while (true) {
                // Accept incoming connections
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Handle client request
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initializeDictionary() {
        // Initialize dictionary with some entries
        dictionary.put("apple", "a fruit");
        dictionary.put("banana", "an edible fruit");
        dictionary.put("cat", "a domestic animal");
        dictionary.put("dog", "a domestic animal");
        dictionary.put("rabbit", "a domestic animal");
        dictionary.put("horse", "a domestic animal");
        dictionary.put("tiger", "a wild animal");
        dictionary.put("lion", "a wild animal");
        dictionary.put("elephant", "a wild animal");
        dictionary.put("computer", "an Electronic device");
        dictionary.put("mobile phone", "an Electronic device");
        dictionary.put(" Tablet", "an Electronic device");
        dictionary.put("house", "a living place");
        dictionary.put("bat", "an animal");
        dictionary.put("cricket", "a game");

    }

    private static void handleClient(Socket clientSocket) {
        try {
            // Receive word from client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String word = in.readLine();
            System.out.println("Received word from client: " + word);

            // Find meaning in dictionary
            String meaning = dictionary.get(word);
            if (meaning != null) {
                // Send meaning to client
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(meaning);
                System.out.println("Sent meaning to client: " + meaning);
            } else {
                // If word not found, add to dictionary with a placeholder meaning
                dictionary.put(word, "Meaning not found");
                System.out.println("Added word to dictionary: " + word);
            }

            // Close connection
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
