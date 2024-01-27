import java.io.*;
import java.util.*;

public class FastOutput {
    public static void main(String[] args) {
        /* 
		BufferedOutputStream uses a buffer array based buffering strategy, 
		unlike PrintStream, that buffers till the next newline character.
		*/
        try(BufferedOutputStream o = new BufferedOutputStream(System.out)) {
            try(PrintStream p = new PrintStream(o)) {
                p.format("%d ", 2); // Works like System.out.printf
                p.print("hello"); // Works like System.out.print
                p.println("hello"); // Works like System.out.println
            }
            /*  
            No catch required for PrintStream, 
            since it performs error handling in a less explicit way.
            */
        }
        catch(IOException e) {

        }
    }
}
