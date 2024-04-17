import java.net.*;
import java.io.*;

public class sp_client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while ((message = br.readLine()) != null) {
                out.println(message);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
