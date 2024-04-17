import java.io.*;
import java.net.*;

public class ServerFactorial {
    public static void main(String[] args) {
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

    private static void handleClient(Socket clientSocket) {
        try {
            // Receive number from client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            int number = Integer.parseInt(in.readLine());
            System.out.println("Received number from client: " + number);

            // Calculate factorial
            long factorial = calculateFactorial(number);

            // Send factorial to client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(factorial);
            System.out.println("Sent factorial to client: " + factorial);

            // Close connection
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long calculateFactorial(int number) {
        if (number == 0 || number == 1)
            return 1;
        else
            return number * calculateFactorial(number - 1);
    }
}
