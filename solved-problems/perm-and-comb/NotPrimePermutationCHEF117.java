import java.util.*;
import java.lang.*;
import java.io.*;

class FastInput {
    BufferedReader bufferObject;
    String[] strArray;
    int strIdx;

    public FastInput() {
        bufferObject = new BufferedReader(new InputStreamReader(System.in));
        readFromBuffer();
    }

    private void readFromBuffer() {
        try {
            strArray = bufferObject.readLine().split("[\\W]+");
            strIdx = 0;
        }
        catch(IOException e) {

        }
    }

    String next() {
        if(strIdx >= strArray.length) readFromBuffer();
        return strArray[strIdx++];
    }
    
    String nextLine() {
        StringBuilder s = new StringBuilder();
        
        if(strIdx >= strArray.length) readFromBuffer();
        while(strIdx < strArray.length) {
            s.append(String.format("%s ", strArray[strIdx++]));
        }
        
        return s.toString();
    }

    int nextInt() { return Integer.parseInt(next()); }
    long nextLong() { return Long.parseLong(next()); }
    double nextDouble() { return Double.parseDouble(next()); }
    // Implement similar methods for Boolean, Float and Short, if needed...

    int[] readIntArray(int n) {
        int[] intArr = new int[n];
    
        for(int i = 0; i < n; i++) intArr[i] = nextInt();

        return intArr;
    }

    long[] readLongArray(int n) {
        long[] longArr = new long[n];

        for(int i = 0; i < n; i++) longArr[i] = nextLong();

        return longArr;
    }
}

class Codechef
{  
	public static void main (String[] args) throws java.lang.Exception {
		// your code goes here
		/* 
		This uses a buffer size based buffering strategy, 
		instead of PrintStream's methodology that buffers till the
		next newline.
		*/
	try(BufferedOutputStream o = new BufferedOutputStream(System.out)) {
    try(PrintStream p = new PrintStream(o)) {
        FastInput ip = new FastInput();
        int t = ip.nextInt();
        
        // System.out.println(t);
        
        for(int test = 1; test <= t; test++) {
            int arrLen = ip.nextInt();
            
            if(arrLen <= 2) {
                p.println(-1);
                
                ip.nextLine();
                // for(int i = 0; i < arrLen; i++) ip.nextInt();
                
                continue;
            }
            
            int num;
                                
            for(int i = 0; i < arrLen; i++) {
                num = ip.nextInt();
                if(num <= 3) {
                    num = 4 - num;
                }
                
                p.format("%d ", num);
            }
            
            p.println();
        }
	
        
    }}
       
        
	}
}
