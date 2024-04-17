import java.io.*;
import java.net.Socket;
import java.util.Scanner;
class Hamming_Client {
public static void main(String[] args) throws IOException {
Socket socket = new Socket("localhost", 8000);
DataInputStream fromServer = new
DataInputStream(socket.getInputStream());
DataOutputStream toServer = new
DataOutputStream(socket.getOutputStream());
Scanner sc = new Scanner(System.in);
String codeword;
String redundant_bits;
while (true) {
System.out.println("(From Client) Enter the code word:");
codeword = sc.nextLine();
System.out.println("(From Client) Enter the number of redundant bits:");
redundant_bits=sc.nextLine();
toServer.writeUTF(codeword);
toServer.writeUTF(redundant_bits);
String msg = fromServer.readUTF();
System.out.println("Server:" + msg);
}
}
}