import java.io.*;

public class TestingFastOutput {
    public static void main(String[] args) {
        try(BufferedOutputStream o = new BufferedOutputStream(System.out)) {
            try(PrintStream p = new PrintStream(o)) {
                long start = System.nanoTime();

                long numbers = 1000000;
        
                for(int i = 1; i <= numbers; i++) {
                    p.print(i);
                }
        
                p.println();
        
                long end = System.nanoTime();
        
                p.format("FastOutput took an average of %f ns\n", ((end - start)) / (double)numbers);
            }
        }
        catch(Exception e) {}
        
    }   
}
