import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        final String SERVER_IP = "localhost";
        final int SERVER_PORT = 12345;
        byte[] sendData;
        byte[] receiveData = new byte[1024];
        
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter a string to check if it's a palindrome: ");
            String sentence = userInput.readLine();

            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("From Server: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
