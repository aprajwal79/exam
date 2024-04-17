
import java.net.*;
import java.io.*;

public class sp_server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is running...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = br.readLine()) != null) {
                if(message == "EXIT")
                {
                    break;
                }
                System.out.println("Message from client: " + message);
                
            }

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
