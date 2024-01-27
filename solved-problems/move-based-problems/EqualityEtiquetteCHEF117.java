import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static long getMoves(int diff) {
	    if(diff <= 1) {
	        return diff;
	    }
	    
	    long start = 0;
	    long end = diff;
	    
	    long requiredN = Integer.MAX_VALUE;
	    
	    while(start <= end) {
	        long n = (start + end) / 2;
	        
	        long sumTillN = (n * (n + 1)) / 2;
	        
	        if(sumTillN == diff) {
	            return n;
	        }
	        
	        if(sumTillN > diff) {
	            requiredN = Math.min(n, requiredN);
	            end = n - 1;
	        }
	        else {
	            start = n + 1;
	        }
	    }
	    
	    long actualSumTillN = (requiredN * (requiredN + 1)) / 2;
	    
	    
	    while((actualSumTillN - diff) % 2 != 0) {
	        requiredN++;
	        actualSumTillN += requiredN;
	    }
	    
	    return requiredN;
	}
	
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        Scanner s = new Scanner(System.in);
        
        int n = s.nextInt();
        
        for(int test = 1; test <= n; test++) {
            System.out.println(
                getMoves(Math.abs(s.nextInt() - s.nextInt()))
            );
        }
	}
}
