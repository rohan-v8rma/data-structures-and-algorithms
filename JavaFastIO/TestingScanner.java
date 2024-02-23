import java.io.*;
import java.util.*;

public class TestingScanner {
    public static void main(String[] args) {
        Scanner ip = new Scanner(System.in);

        long start = System.nanoTime();

        long numbers = 1000000;

        for(int i = 1; i <= numbers; i++) {
            ip.nextInt();
        }

        long end = System.nanoTime();

        System.out.printf("Scanner took an average of %f ns\n", ((end - start)) / (double)numbers);
    }
}
