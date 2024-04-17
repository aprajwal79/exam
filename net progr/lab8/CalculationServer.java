import java.io.*;
import java.net.*;

public class CalculationServer {
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
                    int number = Integer.parseInt(inputLine.trim());
                    int result = calculateResult(number);
                    out.println(result);
                }

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculateResult(int number) {
        return (int) Math.pow(2, number) - 1;
    }
}
