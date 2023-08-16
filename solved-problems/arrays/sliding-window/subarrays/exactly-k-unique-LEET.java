// https://leetcode.com/problems/subarrays-with-k-different-integers/

class Solution {
    // FAILED ATTEMPT
    // public int subarraysWithKDistinct(int[] nums, int k) {
    //     /*
    //     Use linked hash set to maintain order to what elements
    //     can be removed first, to remove the minimum number of elements
    //     from the window.

    //     Then use a character map to get the last seen index of that character,
    //     and set the windowStart = last seen index + 1;

    //     This way, we won't have to search 26 indices to see which character
    //     was seen the earliest (meaning at a lesser index within the current
    //     window)

    //     THIS IS WRONG BECAUSE WE WILL REDUCE THE WINDOW SIZE TOO MUCH IN SOME
    //     CASES. IT IS BETTER TO REMOVE ELEMENTS ONE-BY-ONE
    //     */
    //     int numOfGoodSubArr = 0;

    //     int[] numWindowCount = new int[nums.length + 1];

    //     // Arrays.setAll(numWindowCount, (i) -> -1);

    //     // Setting up the initial window
    //     int windowStart = 0, windowEnd = 0;
    //     int distinctNumsInWindow = 0;
    //     while(windowEnd < nums.length) {
    //         while(
    //             distinctNumsInWindow < k
    //             &&
    //             windowEnd < nums.length
    //         ) {
    //             int currentNum = nums[windowEnd++];
                
    //             if(numWindowCount[currentNum]++ == 0) {
    //                 distinctNumsInWindow++;
    //             }
    //         }

    //         if(distinctNumsInWindow == k) {
    //             System.out.println(Arrays.toString(Arrays.copyOfRange(nums, windowStart, windowEnd)));
    //             numOfGoodSubArr++;
    //             while(
    //                 windowEnd < nums.length
    //             ) {
    //                 int currentNum = nums[windowEnd++];

    //                 if(numWindowCount[currentNum]++ == 0) {
    //                     /* 
    //                     Contracting the window back to the point
    //                     where distinct characters are equal to k
    //                     */
    //                     numWindowCount[currentNum] = 0;
    //                     windowEnd--;
    //                     break;
    //                 }

    //                 System.out.println(Arrays.toString(Arrays.copyOfRange(nums, windowStart, windowEnd)));
    //                 numOfGoodSubArr += 1 + windowEnd - windowStart - 2;
    //             }
    //         }

    //         while(
    //             distinctNumsInWindow == k
    //             &&
    //             windowStart <= windowEnd
    //         ) {
    //             int numToBeRemoved = nums[windowStart++];
    //             if(--numWindowCount[numToBeRemoved] == 0) {
    //                 distinctNumsInWindow--;
    //                 break;
    //             }
    //             System.out.println(Arrays.toString(Arrays.copyOfRange(nums, windowStart, windowEnd)));
    //         }
    //     }
        
    //     return numOfGoodSubArr;
    // }

    // Working approach with 2 hashmaps
    public int subarraysWithKDistinct(int[] nums, int k) {
        Map<Integer, Integer> atmostK = new HashMap<>();
        Map<Integer, Integer> atmostKless1 = new HashMap<>();

        int winStart = 0, winEndK = 0, winEndKless1 = 0;

        int goodSubarr = 0;

        while(
            winEndK < nums.length 
            || 
            // this is necessary for the cases when k = 1.
            (k - 1 != 0 && winEndKless1 < nums.length) 
        ) {
            while(winEndKless1 < nums.length) {
                int currentNum = nums[winEndKless1++];

                if(
                    atmostKless1.size() < k - 1
                    ||
                    (
                        atmostKless1.size() == (k - 1)
                        &&
                        atmostKless1.containsKey(currentNum)
                    )
                ) {
                    atmostKless1.put(
                        currentNum, 
                        1 + atmostKless1.getOrDefault(currentNum, 0)
                    );

                    goodSubarr -= winEndKless1 - winStart;
                }
                else {
                    winEndKless1--;
                    break;
                }
            }

            while(winEndK < nums.length) {
                int currentNum = nums[winEndK++];

                if(
                    atmostK.size() < k
                    ||
                    (
                        atmostK.size() == k
                        &&
                        atmostK.containsKey(currentNum)
                    )
                ) {
                    atmostK.put(
                        currentNum, 
                        1 + atmostK.getOrDefault(currentNum, 0)
                    );

                    goodSubarr += winEndK - winStart;
                }
                else {
                    winEndK--;
                    break;
                }
            }

            /* 
            We need one of the map sizes to reduce, to expand one of the 
            windows
            */
            while(atmostKless1.size() == k - 1 && atmostK.size() == k) {

                // removing the starting index of both the windows
                int elementToBeReleased = nums[winStart++];


                int ct = atmostK.get(elementToBeReleased);
                if(ct == 1) {
                    atmostK.remove(elementToBeReleased);
                }
                else {
                    atmostK.put(elementToBeReleased, ct - 1);
                }

                if(atmostKless1.size() != 0) {
                    ct = atmostKless1.get(elementToBeReleased);
                    // ct = atmostKless1.getOrDefault(elementToBeReleased, -1);
                    if(ct == 1) {
                        atmostKless1.remove(elementToBeReleased);
                    }
                    else {
                        atmostKless1.put(elementToBeReleased, ct - 1);
                    }
                }
                
            }
            

        }

        return goodSubarr;
    }
}