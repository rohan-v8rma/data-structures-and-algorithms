// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-150/problems

class Solution {
    static int mod = 1000000007;
    
    static int add(long a, int b) {
        return (int)((a + b) % mod);
    }
    
    static int mul(long a, int b) {
        return (int)((a * b) % mod);
    }
    
    static ArrayList<Integer> posSetElements;
    
    /* 
    This function calculates the number of pairs whose operation result has ith bit set.
    
    So, we will have to multiply the return value with (1 << i), 
    to get the actual contribution to the result sum
    */
    static int getContributionOfIthBit(int i) {
        Collections.sort(posSetElements);
        
        int pairResultsWithIthCarry = 0;
        
        int twoI = 1 << i;
        
        // Since we've sorted the array, r will always be greater than l.
        int l = 0;
        int r = posSetElements.size() - 1;
        
        while(l < r) {
            /* 
            The lower portion of the elements a (pointed by l) & b (pointed by r), 
            generate a carry.
            
            So, all elements having lower portion greater than a's one will also
            generate a carry with b.
            */
            if(posSetElements.get(l) + posSetElements.get(r) >= twoI) {
                // Element at r, paired with l, l + 1, ..., r - 1.
                pairResultsWithIthCarry += r - l;  
                
                // Moving to a fresh `r` to make more pairs
                r--;
            }
            else {
                // No carry is generated, so we need to increase value of `a`
                l++;
            }
        }
        
        return mul(twoI, pairResultsWithIthCarry);
    }
    
    public static int arrayOperations(int n, int[] arr) {
        int resultSum = 0;
        
        /* 
        It is given that array has only positive elements,
        so we need not consider the MSB, which creates negative numbers using 2s complement.
        
        Also, no need to consider 0th bit, since carry cannot come from any previous position,
        since -1th bit does NOT exist.
        */
        for(int bitPos = 30; bitPos >= 1; bitPos--) {
            /* 
            This list will have lower portions (bits less than i) of potential a & b, 
            where ith bit is set, so they have chance of having ith bit 
            being set after the operation.
            */
            posSetElements = new ArrayList<>();
            
            // 2^10, which is 10000000000, converted to 1111111111
            int mask = (1 << bitPos) - 1;
            
            for(int element: arr) {
                if(((element >> bitPos) & 1) == 1) {
                    posSetElements.add(element & mask);
                }
            }
            
            resultSum = add(resultSum, getContributionOfIthBit(bitPos));
        }
        
        return resultSum;
    }
}
