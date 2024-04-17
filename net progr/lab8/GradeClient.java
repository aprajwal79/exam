import java.io.*;
import java.net.*;

public class GradeClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            System.out.print("Enter the mark: ");
            String mark = userInput.readLine();

            out.println(mark);

            String grade = in.readLine();
            System.out.println("Grade: " + grade);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
