import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientFactorial {
    public static void main(String[] args) {
        try {
            // Create socket
            Socket socket = new Socket("localhost", 9999);

            // Get number from user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter an integer number: ");
            int number = scanner.nextInt();

            // Send number to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(number);
            System.out.println("Sent number to server: " + number);

            // Receive factorial from server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            long factorial = Long.parseLong(in.readLine());
            System.out.println("Factorial received from server: " + factorial);

            // Close connection
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
