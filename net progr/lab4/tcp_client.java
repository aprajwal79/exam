import java.net.*;
import java.io.*;

public class tcp_client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while (true) {
                System.out.print("Client: ");
                message = userInput.readLine();
                out.println(message);
                if (message.equals("bye")) {
                    break;
                }

                message = br.readLine();
                if (message.equals("bye")) {
                    break;
                }
                System.out.println("Server: " + message);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
