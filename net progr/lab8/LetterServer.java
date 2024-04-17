import java.io.*;
import java.net.*;
import java.util.*;

public class LetterServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Waiting for client...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    String[] parts = inputLine.split(" ");
                    String word = parts[0];
                    int number = Integer.parseInt(parts[1]);
                    char letterToSend = getLetterToSend(word, number);
                    out.println(letterToSend);
                }

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static char getLetterToSend(String word, int number) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char c : word.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        for (char c : word.toCharArray()) {
            if (charCountMap.get(c) == number) {
                return c;
            }
        }

        // If the letter which occurs 'number' times is not found,
        // return the letter which occurs at maximum number of times
        char maxOccurringChar = word.charAt(0);
        int maxCount = 0;
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxOccurringChar = entry.getKey();
            }
        }

        return maxOccurringChar;
    }
}
