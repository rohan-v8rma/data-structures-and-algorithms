class Solution {
    /*
    Constraint analysis:

    See notebook for full calculation.

    Since N = 500,

    N^3      = 1.25 * 10^8   >   10^8
    N^2      = 2.5  * 10^5   <   10^8
    N.log(N) = 2.32 * 10^3   <   10^8. So, it is ok to sort the array.
    */

    /* 
    Bruteforce: O(N^3 + N.log(N))

    Sort the array and try all unique combinations manually.

    Notice the concise syntax.
    */
    public int threeSumClosest1(int[] nums, int target) {        
        // Sorting the array.
        Arrays.sort(nums);

        int prevTripletLeft = 1001;

        int closestSum = Integer.MAX_VALUE;

        int prevTripLeft = 1001, prevTripMid = 1001, prevTripRight = 1001;
        int tripLeftIdx = 0, tripMidIdx, tripRightIdx;
     
        while(tripLeftIdx < (nums.length - 2)) {
            
            if(
                prevTripLeft
                == 
                (prevTripLeft = nums[tripLeftIdx++])
            ) {
                continue;
            }

            tripMidIdx = tripLeftIdx;
            
            while(tripMidIdx < (nums.length - 1)) {
                
                if(
                    prevTripMid
                    == 
                    (prevTripMid = nums[tripMidIdx++])
                ) { 
                    continue;
                }

                tripRightIdx = tripMidIdx;
                
                while(tripRightIdx < (nums.length)) {
                    
                    if(
                        prevTripRight
                        == 
                        (prevTripRight = nums[tripRightIdx++]) 
                    ) {
                        continue;
                    }

                    int sum = prevTripLeft + prevTripMid + prevTripRight;
                    
                    if(
                        Math.abs(sum - target) 
                        < 
                        Math.abs(closestSum - target)
                    ) {
                        closestSum = sum;
                    }
                }  

                prevTripRight = 1001;

            }

            prevTripMid = 1001;

        }

        return closestSum;
    }
    
    /* 
    Optimal 1: O(N^2 + N.log(N)). 
    
    Slight modification of original 3 sum.
    */
    public int threeSumClosest(int[] nums, int target) {        
        // Sorting the array.
        Arrays.sort(nums);

        int closestSum = Integer.MAX_VALUE;
        int sum;

        int prevTripLeft = 1001;
        
        int tripLeftIdx, tripMidIdx, tripRightIdx;

        for(
            tripLeftIdx = 0; 
            tripLeftIdx < (nums.length - 2); 
            tripLeftIdx++
        ) {    
            if(
                prevTripLeft
                == 
                (prevTripLeft = nums[tripLeftIdx])
            ) {
                continue;
            }

            tripMidIdx = tripLeftIdx + 1;
            tripRightIdx = nums.length - 1;
            
            while(tripMidIdx < tripRightIdx) {
                
                sum = prevTripLeft + nums[tripMidIdx] + nums[tripRightIdx];
                
                if(Math.abs(target - sum) < Math.abs(target - closestSum)) {
                    closestSum = sum;
                }

                if(sum < target) {
                    tripMidIdx++;
                }
                else if(sum > target) {
                    tripRightIdx--;
                }
                else {
                    return target;
                }
            }
        }

        return closestSum;
    }

    /* 
    Optimal 2: O(N^2 + N.log(N)). 
    
    Added provision for skipping duplicate mid and right values
    in triplet. 
    
    However this proved to be more of an overhead as testcases 
    are not designed that way.
    */
    public int threeSumClosest2(int[] nums, int target) {        
        // Sorting the array.
        Arrays.sort(nums);

        int closestSum = Integer.MAX_VALUE;
        int sum;

        int prevTripLeft = 1001;
        
        int tripLeftIdx, tripMidIdx, tripRightIdx;

        for(
            tripLeftIdx = 0; 
            tripLeftIdx < (nums.length - 2); 
            tripLeftIdx++
        ) {    
            if(
                prevTripLeft
                == 
                (prevTripLeft = nums[tripLeftIdx])
            ) {
                continue;
            }

            tripMidIdx = tripLeftIdx + 1;
            tripRightIdx = nums.length - 1;
            
            int prevTripMid = nums[tripMidIdx], prevTripRight = nums[tripRightIdx];

            while(tripMidIdx < tripRightIdx) {

                sum = prevTripLeft + prevTripMid + prevTripRight;
                
                if(Math.abs(target - sum) < Math.abs(target - closestSum)) {
                    closestSum = sum;
                }

                if(sum < target) {
                    tripMidIdx++;
                    // Skipping duplicate triplet mid values.
                    while(
                        (
                        prevTripMid 
                        == 
                        (prevTripMid = nums[tripMidIdx])
                        )
                        &&
                        (tripMidIdx < tripRightIdx)
                    ) {
                        tripMidIdx++;
                    }
                }
                else if(sum > target) {
                    tripRightIdx--;
                    // Skipping duplicate triplet right values.
                    while(
                        (
                        prevTripRight 
                        == 
                        (prevTripRight = nums[tripRightIdx])
                        )
                        &&
                        (tripMidIdx < tripRightIdx)
                    ) {
                        tripRightIdx--;
                    }
                }
                else {
                    return target;
                }
            }
        }

        return closestSum;
    }
}