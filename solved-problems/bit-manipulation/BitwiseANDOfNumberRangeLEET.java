// https://leetcode.com/problems/bitwise-and-of-numbers-range/submissions/

class Solution {
    // Bruteforce: O(N) involving iteration over all elements and performing AND individually on all of them.

    /* 
    Optimal 1: 
    Max 32 bits processed in worst case which can be considered O(1)
    */
    public int rangeBitwiseAnd1(int left, int right) {
        int result = 0;

        int msbOfLeft = (int)(Math.log(left)/Math.log(2));
        int msbOfRight = (int)(Math.log(right)/Math.log(2));

        // If left and right have common MSB, the entire range will have the bit.
        while((msbOfLeft == msbOfRight)) {
            /* 
            Since left is smaller and will surely reach 0 first, 
            we only check left.
            */
            if(left == 0) {
                break;
            }   

            // This is a number containing only the common MSB position bit set.
            int poweredBit = 1 << msbOfLeft;
            
            /* 
            Adding the common position MSB to result since entire range will
            be possessing that bit.
            */
            result += poweredBit;
            
            // Removing the common position MSB from left and right
            left -= poweredBit;
            right -= poweredBit;

            // Calculating the next-most significant bits of left and right.
            msbOfLeft = (int)(Math.log(left)/Math.log(2));
            msbOfRight = (int)(Math.log(right)/Math.log(2));
        }
        

        return result;
    }

    /* 
    Optimal 2: 
    Max 32 bits processed in worst case which can be considered O(1)

    Little bit better than Optimal 1 since it doesn't use 
    as many logarithmic operations, only bitwise.
    */
    public int rangeBitwiseAnd(int left, int right) {
        
        int numOfRightShifts = 0;
        
        /*
        Finding the bits which didn't flip across the entire range.

        This will be same as the result of AND operation across entire range.

        See notebook for more explanation.
        */
        while(left != right) {
            left >>= 1;
            right >>= 1;
            numOfRightShifts++;
        }

        return (left << numOfRightShifts);
    }
}