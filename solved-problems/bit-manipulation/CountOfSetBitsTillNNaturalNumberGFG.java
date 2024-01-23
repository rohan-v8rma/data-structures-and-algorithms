// https://www.geeksforgeeks.org/problems/count-total-set-bits-1587115620/1

class Solution{
    
    //Function to return sum of count of set bits in the integers from 1 to n.
    public static int countSetBits(int n){
        // If n = 1, number of set bits till 1 is 1.
        // n = 0, NOT a natural number so returning 0 is fine.
        if(n <= 1) {
            return n;
        }
        
        int i = 0;
        
        // Trying to find the greatest power of 2, less than n.
        while((1 << i) <= n) {
            i++;
        }
        
        i--;
        
        
        return (
            i * (1 << (i - 1)) // Number of set bits, upto 2^i. 
            + n - (1 << i) + 1 // MSBs of 2^i and above, but still within N.
            + countSetBits(n - (1 << i)) // Counting inner set bits of the numbers 2^i and above.
        );
        
    }
}