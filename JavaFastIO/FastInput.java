import java.util.*;
import java.io.*;

public class FastInput {
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
        catch(IOException e) {}
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