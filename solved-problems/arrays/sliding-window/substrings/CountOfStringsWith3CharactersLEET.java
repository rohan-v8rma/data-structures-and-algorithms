// https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
// Leetcode solution: https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/solutions/4938603/6ms-runtime-java-o-1-space-complexity/
// TODO: Make notes

class Solution {
    // TC: O(N), SC: O(1)
    // public int numberOfSubstrings(String s) {
    //     /*
    //     Strings with:
    //     1. only a
    //     2. only b
    //     3. only c
    //     4. a & b
    //     5. b & c
    //     6. c & a
    //     7. a, b & c
    //     */

    //     /* 
    //     These variables represent the count of substrings 
    //     having only the characters mentioned in the names of the variables
    //     till the immediate last position
    //     */
    //     int a = 0;
    //     int b = 0;
    //     int c = 0;
    //     int ab = 0;
    //     int bc = 0;
    //     int ca = 0;
    //     int abc = 0;

    //     int total = 0;

    //     for(char character: s.toCharArray()) {
    //         switch(character) {
    //             case 'a':
    //                 a++;
                    
    //                 abc += bc;
    //                 bc = 0;
                    
    //                 ab += b;
    //                 b = 0;
                    
    //                 ca += c;
    //                 c = 0;

    //                 break;
    //             case 'b':
    //                 b++;
                    
    //                 abc += ca;
    //                 ca = 0;

    //                 bc += c;
    //                 c = 0;

    //                 ab += a;
    //                 a = 0;

    //                 break;
    //             case 'c':
    //                 c++;

    //                 abc += ab;
    //                 ab = 0;

    //                 bc += b;
    //                 b = 0;

    //                 ca += a;
    //                 a = 0;

    //                 break;
    //             default: ;
    //         }

    //         total += abc;
    //     }

    //     return total;
    // }

    public int numberOfSubstrings(String s) {
        int lastA = -1;
        int lastB = -1;
        int lastC = -1;

        int countOfStringsWithABC = 0;

        for(int i = 0; i < s.length(); i++) {
            switch(s.charAt(i)) {
                case 'a': 
                    lastA = i;
                    break;
                case 'b': 
                    lastB = i;  
                    break;
                case 'c': 
                    lastC = i;
                    break;
                default: ;
            }

            if(lastA >= 0 && lastB >= 0 && lastC >= 0) {
                // aabbc, so `a` is the one that occurred least recently, at lastA = 1.
                // So, we have 2 possible strings ending at `c`: `aabbc` and `abbc`
                countOfStringsWithABC += Math.min(Math.min(lastA, lastB), lastC) + 1;
            }
        }

        return countOfStringsWithABC;

    }

}
