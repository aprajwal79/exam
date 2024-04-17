import java.io.*;
import java.net.*;
import java.util.*;

public class ClientMeaning {
    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("apple", "banana", "cat", "dog", "elephant", "fish", "goat", "horse", "icecream", "jacket", "kangaroo", "lion", "monkey", "noodle", "orange", "parrot", "queen", "rabbit", "snake", "tiger");

        try {
            // Create socket
            Socket socket = new Socket("localhost", 9999);

            // Pick a random word from the list
            Random random = new Random();
            String word = wordList.get(random.nextInt(wordList.size()));

            // Send word to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(word);
            System.out.println("Sent word to server: " + word);

            // Receive meaning from server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String meaning = in.readLine();
            System.out.println("Received meaning from server: " + meaning);

            // Close connection
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
