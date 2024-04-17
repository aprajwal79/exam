import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Choose a port number

            System.out.println("Server waiting for client on port " + serverSocket.getLocalPort());

            Socket server = serverSocket.accept();
            System.out.println("Connected to " + server.getRemoteSocketAddress());

            DataInputStream in = new DataInputStream(server.getInputStream());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());

            // Receive Hamming code from client
            String receivedCode = in.readUTF();

            // Check for errors in the received code
            boolean hasError = checkHammingCode(receivedCode);

            // Send response to client
            if (hasError) {
                out.writeUTF("bad data");
            } else {
                out.writeUTF("good data");
            }

            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to check Hamming code for errors
    private static boolean checkHammingCode(String code) {
        // Implement Hamming code check logic here
        int r = (int) Math.ceil(Math.log(code.length() + 1) / Math.log(2));
        int n = code.length();
        int[] parityBits = new int[r];
        for (int i = 0; i < r; i++) {
            int parityIndex = (int) Math.pow(2, i) - 1;
            for (int j = parityIndex; j < n; j += 2 * parityIndex + 1) {
                for (int k = 0; k < parityIndex + 1 && j + k < n; k++) {
                    parityBits[i] ^= Character.getNumericValue(code.charAt(j + k));
                }
            }
        }
        boolean hasError = false;
        for (int i : parityBits) {
            if (i != 0) {
                hasError = true;
                break;
            }
        }
        return hasError;
    }
}
