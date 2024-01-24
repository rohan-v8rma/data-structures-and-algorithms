// https://www.codechef.com/problems/UNIQUEDECODE

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int[][] dpMatrix;
    static char[] prev;
    static char[] charArr;
    static boolean noneSelected;
    
    static int modAdd(int a, int b) {
    	return (a + b) % 1000000007;
    }
    
	static int getUniqueCount(int i) {
	    int dpIdx = 0; // prev is blank
	    
	   // System.out.printf("%d ", i);
	   // System.out.println(Arrays.toString(prev));
	    
	    if(prev[0] == 'a') {
	        dpIdx = 1;
	        if(prev[1] == 'b') {
	            dpIdx = 2;
	            if(prev[2] == 'a') {
	                dpIdx = 3;
	            }
	        }
	    }
	    
	    if(i == charArr.length) {
	        if(dpIdx == 3 || noneSelected) { // prev is aba so non-decipherable.
	            return 0;
	        }
	        
	        return 1;
	    }
	    
	    
	    if(dpMatrix[dpIdx][i] != -1) {
	        return dpMatrix[dpIdx][i];
	    }
	    
	    int total = 0;
	    
	    // Go without selecting any character.
	    total = modAdd(total, getUniqueCount(i + 1));
	    
	    boolean originalNoneSelected = noneSelected;
	    
	    if(noneSelected) {
	        noneSelected = false;
	    }
	    
        char c = charArr[i];
	        
        if(c == 'a') {
            // Prev: 'aba'
            // if(dpIdx == 3) {
            //     ;
            // }
            
            // Prev: 'ab'
            if(dpIdx == 2) {
                prev[2] = 'a';
                total = modAdd(total, getUniqueCount(i + 1));
                prev[2] = 'x';
            }
            
            // Prev: 'a'
            if(dpIdx == 1) {
               total = modAdd(total, getUniqueCount(i + 1));
            }
           
            if(dpIdx == 0) {
                prev[0] = 'a';
                total = modAdd(total, getUniqueCount(i + 1));
                prev[0] = 'x';
            }
        }
        else if(c == 'b') {
            // aba
            if(dpIdx == 3) {
                // abab is uniquely decodable
                prev[2] = 'x';
                total = modAdd(total, getUniqueCount(i + 1));
                prev[2] = 'a';
            }
            
            // a
            if(dpIdx == 1) {
                prev[1] = 'b';
                total = modAdd(total, getUniqueCount(i + 1));
                prev[1] = 'x';
            }
        }
        
        noneSelected = originalNoneSelected;
	    
	    return dpMatrix[dpIdx][i] = total;
	}
	
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();
        
        s.nextLine();
        
        for(int i = 1; i <= t; i++) {
            String str = s.nextLine();
            
            charArr = str.toCharArray();
            
            dpMatrix = new int[4][str.length()];
            
            Arrays.fill(dpMatrix[0], -1);
            Arrays.fill(dpMatrix[1], -1);
            Arrays.fill(dpMatrix[2], -1);
            Arrays.fill(dpMatrix[3], -1);

            noneSelected = true;
            
            prev = new char[]{'x', 'x', 'x'};
            
            System.out.println(getUniqueCount(0));
        }
	}
}
