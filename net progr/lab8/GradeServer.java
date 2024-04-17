import java.io.*;
import java.net.*;

public class GradeServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Waiting for client...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String inputLine = in.readLine();
                int mark = Integer.parseInt(inputLine.trim());
                String grade = calculateGrade(mark);

                out.println(grade);

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String calculateGrade(int mark) {
        if (mark >= 85 && mark <= 100)
            return "Grade A";
        else if (mark >= 70 && mark <= 84)
            return "Grade B";
        else if (mark >= 60 && mark <= 69)
            return "Grade C";
        else if (mark >= 50 && mark <= 59)
            return "Grade D";
        else
            return "Fail";
    }
}
