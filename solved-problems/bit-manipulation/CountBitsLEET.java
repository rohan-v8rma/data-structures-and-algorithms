// https://leetcode.com/problems/counting-bits

class Solution {
    // Bruteforce: O(N.log(N))
    // public int[] countBits(int n) {
    //     int[] ans = new int[n + 1];

    //     for(int i = 0; i <= n; i++) {
    //         int num = i;
    //         while(num > 0) {
    //             int currentDigit = num & 1;

    //             ans[i] += currentDigit;

    //             num >>= 1;
    //         }
    //     }

    //     return ans;
    // }

    /* 
    OPTIMAL: O(N)

    We can notice that there is a pattern in the occurrence of bits, and we have capitalized on that.
    */
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];

        for(
            int power = 0; 
            (1 << power) <= n; 
            power++
        ) {
            int powerOf2 = 1 << power;
            int nextPowerOf2 = 1 << (power + 1);

            for(
                int number = powerOf2; 
                (number < nextPowerOf2) && (number <= n);
                number++
            ) {
                /* 
                Using a Dynamic Programming type approach, 
                where we use the previously calculated results

                The +1 represents the newly added MSB.
                */
                ans[number] = 1 + ans[number - powerOf2];
            }
        }
        
        return ans;
    }
}
