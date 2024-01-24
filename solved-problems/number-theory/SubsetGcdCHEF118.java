// https://www.codechef.com/problems/GCDPERM

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	static void showSubset(int N, int K) {
	    int diff = N / K;
	    
	    int term = diff;
	    
	    while(K != 0) {
	        System.out.printf("%d ", term);
	        term += diff;
	        
	        K--;   
	    }
	    
	    System.out.println();
	}
	
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        Scanner s = new Scanner(System.in);
        
        int t = s.nextInt();
        
        for(int i = 1; i <= t; i++) {
            showSubset(s.nextInt(), s.nextInt());
        }
	}
}
