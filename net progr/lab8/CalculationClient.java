import java.io.*;
import java.net.*;

public class CalculationClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            for (int i = 0; i < 5; i++) {
                out.println(i);
                String result = in.readLine();
                System.out.println("Server result for " + i + ": " + result);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
