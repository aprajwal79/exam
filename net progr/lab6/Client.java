import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 12345); // Connect to server on localhost:12345

            System.out.println("Connected to server: " + clientSocket.getRemoteSocketAddress());

            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());

            // Generate Hamming code
            String data = "1010101"; // Sample data for illustration
            String hammingCode = generateHammingCode(data);

            // Send Hamming code to server
            out.writeUTF(hammingCode);

            // Receive response from server
            String response = in.readUTF();
            System.out.println("Response from server: " + response);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to generate Hamming code
    private static String generateHammingCode(String data) {
        int r = calculateParityBits(data.length());
        int[] hammingArray = new int[data.length() + r + 1];
        int j = 0;
        for (int i = 1; i <= hammingArray.length; i++) {
            if ((Math.log(i) / Math.log(2)) % 1 == 0) {
                hammingArray[i - 1] = -1; // Placeholder for parity bit
            } else {
                hammingArray[i - 1] = Character.getNumericValue(data.charAt(j++));
            }
        }
        for (int i = 0; i < r; i++) {
            int parityIndex = (int) Math.pow(2, i);
            for (int k = parityIndex - 1; k < hammingArray.length; k += 2 * parityIndex) {
                for (int l = k; l < k + parityIndex && l < hammingArray.length; l++) {
                    if (hammingArray[l] != -1) {
                        hammingArray[parityIndex - 1] ^= hammingArray[l];
                    }
                }
            }
        }
        StringBuilder hammingCode = new StringBuilder();
        for (int i : hammingArray) {
            hammingCode.append(i);
        }
        return hammingCode.toString();
    }

    // Method to calculate the number of parity bits required
    private static int calculateParityBits(int dataLength) {
        int r = 0;
        while (Math.pow(2, r) < dataLength + r + 1) {
            r++;
        }
        return r;
    }
}
