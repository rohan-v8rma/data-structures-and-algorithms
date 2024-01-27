import java.util.*;
import java.io.*;

public class FasterInput {
    BufferedReader readerInstance;
    StringTokenizer st;

    public FastInput() {
        readerInstance = new BufferedReader(new InputStreamReader(System.in));
    }
    
    private void readFromBuffer() {
        try {
            st = new StringTokenizer(readerInstance.readLine());
        }
        catch(IOException e) {};
    }

    String next() {
        if(st == null || !st.hasMoreTokens()) readFromBuffer();
        
        return st.nextToken();
    }
    
    String nextLine() {
        StringBuilder s = new StringBuilder();
        
        if(!st.hasMoreTokens()) readFromBuffer();
        
        while(st.hasMoreTokens()) {
            s.append(String.format("%s ", st.nextToken()));
        }
        readFromBuffer();
        
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