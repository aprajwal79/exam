import java.io.*;
import java.net.*;

public class ClientDate {
    public static void main(String[] args) {
        try {
            // Create socket
            Socket socket = new Socket("localhost", 9999);

            // Receive date and time from server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String dateTime = in.readLine();
            System.out.println("Received from server: " + dateTime);

            // Close connection
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
