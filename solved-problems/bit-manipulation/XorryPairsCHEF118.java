// https://www.codechef.com/problems/XORRY2

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();
        
        for(int i = 1; i <= t; i++) {
           int x = s.nextInt();
           
           int msbOfX = (int)(Math.log(x) / Math.log(2));
           
           int a = 1 << msbOfX;
           int b = x ^ a;
           
        //   int copyOfB = b;
           
           int xorryPairs = 1;
           int setBitsInB = 0;
           int totalBitsInB = 0;
           
           while(b > 0) {
               totalBitsInB++;
               int bit = b & 1;
               
               if(bit == 1) {
                   setBitsInB++;
               }
               
               b >>= 1;
           }
           
            xorryPairs *= (int)(Math.pow(2, totalBitsInB - setBitsInB));
           
           
           
            
            System.out.println(xorryPairs);      
        }       
	}
}
