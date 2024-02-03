// https://leetcode.com/problems/find-the-maximum-number-of-elements-in-subset

class Solution {
    static int getCount(int num, int[] ct) {
        int actualCt = (int)Math.min(ct[1], 1);
        
        int baseVal = ct[0];
        
        if(num == baseVal) {
            return actualCt;
        }
        
        num = (int)Math.sqrt(num);
        
        while(num != baseVal) {
            actualCt += 2;
            num = (int)Math.sqrt(num);
        }
        
        actualCt += 2;
        
        return actualCt;
    }
    
    public int maximumLength(int[] nums) {
        Arrays.sort(nums);
        
        int i = 0;
        
        while(i < nums.length && nums[i] == 1) {
            i++;
        }
        
        int maxCt = i - (1 - (i % 2)); // Odd number of 1s can be in subset.
        
        HashMap<Integer, int[]> subsetMap = new HashMap<>();
        
        for(; i < nums.length; i++) {
            int[] elementDetails = subsetMap.getOrDefault(nums[i], new int[2]);
            
            int countOfElement = elementDetails[1];

            if(countOfElement > 0) {
                elementDetails[1]++;
                continue;
            }
            
            double sqrtDouble = Math.sqrt(nums[i]);
            
            if(sqrtDouble % 1 == 0) {
                  int sqrt = (int)sqrtDouble;

                  elementDetails = subsetMap.getOrDefault(sqrt, new int[2]);
                  
                  int countOfSqrt = elementDetails[1];

                  if(countOfSqrt >= 1) {
                      subsetMap.remove(sqrt);
                      if(countOfSqrt == 1) {
                          // Since sqrt only seen 1 time, subset ends here.
                          maxCt = Math.max(getCount(sqrt, elementDetails), maxCt);    
                      }
                      else {
                          // 1 "nums[i]" seen. all smaller numbers seen twice or more
                          elementDetails[1] = 1; 

                          subsetMap.put(nums[i], elementDetails);    
                          /* 
                          Continuing here since, we don't want to overrwrite nums[i] map value
                          by executing the code below.
                          */
                          continue; 
                      }                      
                  }
            }
            
            // nums[i] is the starting of a new subset.
            elementDetails[0] = nums[i];
            elementDetails[1] = 1; // First nums[i] seen.
            subsetMap.put(nums[i], elementDetails);
        }
    
        for(Map.Entry<Integer, int[]> ct: subsetMap.entrySet()) {
            maxCt = Math.max(getCount(ct.getKey(), ct.getValue()), maxCt);
        }
        
        return maxCt;
    }
}
