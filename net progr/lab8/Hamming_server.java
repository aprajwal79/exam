import java.io.*;
import java.net.*;
import java.util.Scanner;
class Hamming_Server {
public static void main(String[] args) throws IOException {
ServerSocket serverSocket = new ServerSocket(8000);
System.out.println("Server started.");
System.out.println("Server executes Hamming Code based on 'Even Parity'");
while (true) {
Socket socket = serverSocket.accept();
System.out.println("Client connected.");
DataInputStream inputFromClient = new
DataInputStream(socket.getInputStream());
DataOutputStream outputToClient = new
DataOutputStream(socket.getOutputStream());
Scanner sc = new Scanner(System.in);
String msg;
while (true) {
String codeword_s = inputFromClient.readUTF();String redundant_bits_s = inputFromClient.readUTF();
int num=Integer.parseInt(redundant_bits_s);
if(num==0){
System.out.println("Redundant Bits are 0 so we can't predict error and hence interpreted as error free");
}
else{
System.out.println("Received data from client");
}
msg=hammingCode(codeword_s,redundant_bits_s);
outputToClient.writeUTF(msg);
}
}
}
public static String hammingCode(String cw, String redundant){
int num=Integer.parseInt(redundant);
while(num>=1){
int count=0;int power=(int)Math.pow(2,num-1);
char bit=cw.charAt(power-1);
for(int i=power+1;i<=cw.length();i++){
if(((i>>num-1)& 1)==1 ) {
if (cw.charAt(i - 1) == '1') {
count++;
}
}
}
if((count%2==0 && bit=='1')||(count%2!=0 && bit=='0')){
return "Bad Data";
}
num--;
}
return "Good Data";
}
}