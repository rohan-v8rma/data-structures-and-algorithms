// https://www.codechef.com/problems/ADVITIYA7

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int mod = 1000000007;
    
    static int mul(long a, long b) {
        return (int)((a * b) % mod);
    }
    
    static ArrayList<int[]> getPrimeFactorization(int X) {
        ArrayList<int[]> primeFactors = new ArrayList<>();
        
        for(int factor = 2; factor * factor <= X; factor++) {
            if(X % factor == 0) {
                int[] factorData = new int[]{factor, 0};
                
                while(X % factor == 0) {
                    factorData[1]++;
                    X /= factor;
                }
                
                primeFactors.add(factorData);
            }
            
            if(X == 1) {
                break;
            }
        }
        
        if(X != 1 && primeFactors.size() == 0) {
            primeFactors.add(new int[]{X, 1});
        }
        
        return primeFactors;
    }
    
    
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        try(BufferedOutputStream o = new BufferedOutputStream(System.out)) {
            try(PrintStream p = new PrintStream(o)) {
                Scanner s = new Scanner(System.in);
                int t = s.nextInt();
                
                for(int test = 1; test <= t; test++) {
                    int X = s.nextInt();
                    int Q = s.nextInt();
                    
                    ArrayList<int[]> primeFactors = getPrimeFactorization(X);
                    
                    for(int query = 1; query <= Q; query++) {
                        int P = s.nextInt();
                        
                        int ct = 1;
                        if(X == 1 || P == 1) {
                            /* 
                            Count is only 1, in both these cases.
                            
                            a. For X == 1, all prime powers are 0.
                            So x_i * P = y_i, has only 1 solution for 
                            each prime factor, irrespective of value of P.
                            Since 0 = 0.
                            
                            b. For P == 1, x_i * P = y_i, implies the same 
                            condition as x_i = y_i * P
                            */
                        }
                        else {
                            for(int[] primeFactor: primeFactors) {
                                if(primeFactor[1] % P == 0) {
                                    ct = mul(ct, 2);
                                }
                            }    
                        }
                        
                        p.println(ct);
                    }
                }
            }
        }
        catch(Exception e) {
            
        }
	}
}
