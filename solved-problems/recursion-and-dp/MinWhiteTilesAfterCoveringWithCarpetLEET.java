// https://leetcode.com/problems/minimum-white-tiles-after-covering-with-carpets/description/

class Solution {
    /*_____RECURSIVE MEMOIZED SOLUTION: Using hashmap_____*/

    // public int getKey(int index, int numCarpets) {
    //     /* 
    //     These 2 values are sufficient for acting as identifiers in the DP hashmap.

    //     This is because how many white tiles stay uncovered at and after an index,
    //     only depends on the number of carpets left.
    //     */
    //     return (index << 10) + numCarpets;
    // }

    // public int carpet(
    //     String floor, 
    //     int index, 
    //     int numCarpets, 
    //     int carpetLen
    // ) {
    //     if(index >= floor.length()) return 0;

    //     int key = getKey(index, numCarpets);

    //     if(dp.containsKey(key)) return dp.get(key);

    //     int returnVal = 0;

    //     // White tile encountered
    //     if(floor.charAt(index) == '1') {
    //         // Letting the tile stay uncovered.
    //         returnVal = 1 + carpet(floor, index + 1, numCarpets, carpetLen);

    //         int beforeChange = returnVal;

    //         // If carpets are left over, we cover the tile and see if we reach min value.
    //         if(numCarpets > 0) {
    //             returnVal = Math.min(
    //                 returnVal,
    //                 carpet(floor, index + carpetLen, numCarpets - 1, carpetLen)
    //             );
    //         }

            
    //     }
    //     // A black uncovered tile.
    //     else {
    //         returnVal = carpet(
    //             floor, 
    //             index + 1, 
    //             numCarpets, 
    //             carpetLen
    //         );
    //     }
        
    //     dp.put(key, returnVal);
        
    //     return returnVal; 
    // }

    // HashMap<Integer, Integer> dp;

    // public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
    //     dp = new HashMap<>();

    //     return carpet(floor, 0, numCarpets, carpetLen);
    // }


    /*_____RECURSIVE MEMOIZED SOLUTION: Using 2D array_____*/

    // public int carpet(
    //     String floor, 
    //     int index, 
    //     int numCarpets, 
    //     int carpetLen
    // ) {
    //     if(index >= floor.length()) return 0;

    //     if(dp[numCarpets][index] != -1) return dp[numCarpets][index];

    //     int returnVal = 0;

    //     // White tile encountered
    //     if(floor.charAt(index) == '1') {
    //         // Letting the tile stay uncovered.
    //         returnVal = 1 + carpet(floor, index + 1, numCarpets, carpetLen);

    //         // If carpets are left over, we cover the tile and see if we reach min value.
    //         if(numCarpets > 0) {
    //             returnVal = Math.min(
    //                 returnVal,
    //                 carpet(floor, index + carpetLen, numCarpets - 1, carpetLen)
    //             );
    //         }

            
    //     }
    //     // A black uncovered tile.
    //     else {
    //         returnVal = carpet(
    //             floor, 
    //             index + 1, 
    //             numCarpets, 
    //             carpetLen
    //         );
    //     }
        
    //     dp[numCarpets][index] = returnVal;
        
    //     return returnVal; 
    // }

    // static int[][] dp;

    // public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
    //     dp = new int[numCarpets + 1][floor.length()];

    //     for(int[] dpArray: dp) Arrays.fill(dpArray, -1);

    //     return carpet(floor, 0, numCarpets, carpetLen);
    // }


    /*
    ____ITERATIVE TABULATION SOLUTION: Using 2D array_____
    
    We need to reverse the direction in which the solution is built;
    which is something recursion automatically does for us.

    We keep 2 counts of carpets at a time in this.
    */
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int[] dp1 = new int[floor.length() + 1];
        int[] dp2 = new int[floor.length() + 1];

        int whiteTileCt = 0;
        for(int floorIdx = floor.length() - 1; floorIdx >= 0; floorIdx--) {
            if(floor.charAt(floorIdx) == '1') whiteTileCt++;
            
            dp1[floorIdx] = whiteTileCt;
        }

        // Initially, `dp1` contains white tile count on and ahead of a particular index.

        for(int carpetCt = 1; carpetCt <= numCarpets; carpetCt++) {
            for(int floorIdx = floor.length() - 1; floorIdx >= 0; floorIdx--) {
                int returnVal = 0;
 
                // White tile encountered
                if(floor.charAt(floorIdx) == '1') {

                    // Letting the tile stay uncovered.
                    returnVal = 1 + dp2[floorIdx + 1];

                    // If carpets are left over, we cover the tile and see if we reach min value.
                    if(numCarpets > 0) {
                        // All tiles exhausted.
                        if(floorIdx + carpetLen >= floor.length()) {
                            returnVal = 0;
                        }
                        // Decreasing a carpet and covering the tiles
                        else {
                            returnVal = Math.min(
                                returnVal,
                                dp1[floorIdx + carpetLen]
                            );
                        }   
                    }
                }
                // A black uncovered tile, so the number of uncovered tiles is the same as the higher position.
                else {
                    // Value stays the same since number of white tiles don't change.
                    returnVal = dp2[floorIdx + 1];
                }   

                dp2[floorIdx] = returnVal;
            }

            dp1 = dp2;
            dp2 = new int[floor.length() + 1];
        }

        // Minimum visible white tiles, on and after 0th index, with numCarpets count of carpets.
        return dp1[0];
    }

    // TODO: Make this solution work.
    /*
    ____ITERATIVE TABULATION SOLUTION: Using 2D array_____
    
    We need to reverse the direction in which the solution is built;
    which is something recursion automatically does for us.

    We keep 2 adjacents indices, with all carpet counts in this solution.
    */
    // public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
    //     int[][] dp1 = new int[numCarpets + 1][2];
    //     int[][] dp2 = new int[numCarpets + 1][2];

    //     /*
    //     0th index in dp1 means 0 carpets left to use.
    //     1st index in dp1 means 1 carpet left to use.
    //     So on, numCarpets index means all carpets left to use.

    //     So initially, dp1 would have all 0s if the last tile is black.

    //     If last tile is white, it would have numCarpets index as 1, because
    //     for all carpets to be available for use, we have to skip covering
    //     the last tile.
    //     */
    //     dp1[numCarpets][0] = floor.charAt(floor.length() - 1) - '0';

    //     // All the tiles that have used a carpet, the remaining carpet length is `carpetLen - 1`
    //     for(int i = 0; i < numCarpets; i++) dp1[i][1] = carpetLen - 1;

    //     /*
    //     In all other cases, we overlap (numCarpets - index) carpets and say
    //     that no white tile is visible
    //     */

    //     for(int floorIdx = floor.length() - 2; floorIdx >= 0; floorIdx--) {
    //         /* 
    //         To maintain the max number of carpets, we don't use any carpet,
    //         and increase number of white tiles visible if current tile is white.
    //         */
    //         dp2[numCarpets][0] = floor.charAt(floorIdx) - '0' + dp1[numCarpets][0];

    //         for(int carpetCt = numCarpets - 1; carpetCt >= 0; carpetCt--) {
    //             if(floor.charAt(floorIdx) == '1') {
    //                 // A carpet is left over.
    //                 if(dp1[carpetCt][1] > 0) {
    //                     dp2[carpetCt][0] = dp1[carpetCt][0];
    //                     // The carpet covers yet another tile.
    //                     dp2[carpetCt][1] = dp1[carpetCt][1] - 1; 
    //                 }
    //                 // NO carpet left over. More profitable to let tile stay uncovered.
    //                 else if(dp1[carpetCt][0] + 1 < dp1[carpetCt + 1][0]) {
    //                     dp2[carpetCt][0] = 1 + dp1[carpetCt][0];
    //                 }
    //                 // Using up a carpet is more profitable now.
    //                 else {
    //                     dp2[carpetCt][0] = dp1[carpetCt + 1][0];
    //                     /*
    //                     We are using a carpet, 
    //                     so now the carpet can cover (carpetLen - 1) more tiles.
    //                     */
    //                     dp2[carpetCt][1] = carpetLen - 1; 
    //                 }
    //             }
    //             // Since the tile is black, we don't care whether a carpet is there or not.
    //             else {
    //                 dp2[carpetCt][0] = dp1[carpetCt][0];
    //                 dp2[carpetCt][1] = Math.max(dp1[carpetCt][1] - 1, 0);
    //             }
    //         }

    //         dp1 = dp2;
    //         dp2 = new int[numCarpets + 1][2];

    //         for(int[] carpetCt: dp1) System.out.println(Arrays.toString(carpetCt));
    //         System.out.println();
    //     }

        

    //     // White tiles visible, with 0 carpets left to be used.
    //     return dp1[0][0];
    // }
}