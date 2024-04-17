import java.net.*;
import java.io.*;

public class tcp_server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is running...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while (true) {
                message = br.readLine();
                if (message.equals("bye")) {
                    break;
                }
                System.out.println("Client: " + message);

                System.out.print("Server: ");
                message = userInput.readLine();
                out.println(message);
                if (message.equals("bye")) {
                    break;
                }
            }

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
