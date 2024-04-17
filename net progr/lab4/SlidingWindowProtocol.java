import java.util.*;

public class SlidingWindowProtocol {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the window size:");
        int windowSize = scanner.nextInt();
        System.out.println("Enter the number of frames to be sent:");
        int framesCount = scanner.nextInt();

        int[] frames = new int[framesCount];
        System.out.println("Enter the frames:");
        for (int i = 0; i < framesCount; i++) {
            frames[i] = scanner.nextInt();
        }

        System.out.println("Frames sent: ");
        for (int i = 0; i < framesCount; i += windowSize) {
            for (int j = i; j < Math.min(i + windowSize, framesCount); j++) {
                System.out.print(frames[j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
