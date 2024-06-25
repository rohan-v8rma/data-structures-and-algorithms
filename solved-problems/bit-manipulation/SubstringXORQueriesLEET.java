// https://leetcode.com/problems/substring-xor-queries
// TODO: Make notes

class Solution {
    // /*
    // ____SELF DEVELOPED BRUTEFORCE_____

    // TC: O(31 * s.length + queries.length)
    // Time: ~115ms
    // */
    // public int[][] substringXorQueries(String s, int[][] queries) {
    //     HashMap<Integer, int[]> numRange = new HashMap<>();

    //     // Iterate, from 1-bit length to 31-bit length, since int goes to 2^31 - 1, which takes 31 bits.
    //     for(int bitLength = 1; bitLength <= 31; bitLength++) {
    //         // 2 ^ bitLength is the maximum possible combinations of this length
    //         int maxPossibleCombinations = 1 << bitLength;
        
    //         int achievedCombinations = 0;

    //         for(int i = 0; i <= (s.length() - bitLength); i++) {
    //             // System.out.printf("\nb%d: Iteration %d:\n", bitLength, i);

    //             // Taking this substring.
    //             String sSub = s.substring(i, i + bitLength);
    //             // Converting it to decimal.
    //             int numInDecimal = Integer.parseInt(sSub, 2);

    //             /*
    //             If the number is already in hashmap, it means 1 of 2 things:
    //             1. The number was achieved in an earlier same-length substring.
    //             2. The number was achieved in an earlier smaller-length substring (meaning the current
    //             substring has leading 0s, making the value of it equal to that smaller length substring)

    //             In both cases, we need to keep the smaller and earlier substring.
    //             */
    //             if(!numRange.containsKey(numInDecimal)) {
    //                 achievedCombinations++;
    //                 numRange.put(numInDecimal, new int[]{i, i + bitLength - 1});
    //                 // System.out.printf("%s, %d, %s\n", sSub, numInDecimal, Arrays.toString(numRange.get(numInDecimal)));
    //             }

    //             if(achievedCombinations == maxPossibleCombinations) break;
    //         }
    //     }

    //     // Answer needed when the corresponding substring is not present in the entire binary substring.
    //     int[] substringNotPresent = new int[]{-1, -1};

    //     for(int i = 0; i < queries.length; i++) {
    //         /*
    //         Property of XOR, 

    //         if sub ^ first = second, then first ^ second = sub
    //         */
    //         int xorResult = queries[i][0] ^ queries[i][1];

    //         queries[i] = numRange.getOrDefault(xorResult, substringNotPresent);
    //     }
        

    //     return queries;
    // }

    /*
    ____OPTIMAL____

    Optimized for leading 0s.
    
    TC: Same as above
    Time: ~50ms
    */

    int[][] substringXorQueries(String s, int[][] queries) {
        HashMap<Integer, int[]> numRange = new HashMap<>();

        for(int i = 0; i < s.length(); i++) {
            // Leading 0, so no point starting our strings from here.
            if(s.charAt(i) == '0') {
                if(!numRange.containsKey(0)) {
                    numRange.put(0, new int[]{i, i});
                }
            }
            else {
                for(
                    int bitLength = 1; 
                    bitLength <= 31 && i + bitLength <= s.length(); 
                    bitLength++
                ) {
                    // Taking this substring.
                    String sSub = s.substring(i, i + bitLength);
                    // Converting it to decimal.
                    int numInDecimal = Integer.parseInt(sSub, 2);

                    /*
                    If the number is already in hashmap, the number was achieved in 
                    an earlier same-length substring.
                    
                    We removed the chance of leading 0s, so that is not possible
                    */
                    if(!numRange.containsKey(numInDecimal)) {
                        numRange.put(numInDecimal, new int[]{i, i + bitLength - 1});
                    }
                }
            }
            
        }

        // Answer needed when the corresponding substring is not present in the entire binary substring.
        int[] substringNotPresent = new int[]{-1, -1};

        for(int i = 0; i < queries.length; i++) {
            /*
            Property of XOR, 

            if sub ^ first = second, then first ^ second = sub
            */
            int xorResult = queries[i][0] ^ queries[i][1];

            queries[i] = numRange.getOrDefault(xorResult, substringNotPresent);
        }
        

        return queries;
    }
}