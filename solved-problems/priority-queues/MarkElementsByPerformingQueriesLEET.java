// https://leetcode.com/problems/mark-elements-on-array-by-performing-queries/

class Solution {
    // Ok solution since priority queue NOT really needed.
    // public long[] unmarkedSumArray(int[] nums, int[][] queries) {
    //     long sum = 0;
        
    //     PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> {
    //         int result = Integer.compare(a1[0], a2[0]);
            
    //         if(result == 0) result = Integer.compare(a1[1], a2[1]);
            
    //         return result;
    //     });
        
        
    //     for(int i = 0; i < nums.length; i++) {
    //         int num = nums[i];
            
    //         sum += num;
            
    //         pq.add(new int[]{num, i});
    //     }
        
    //     long[] sums = new long[queries.length];
        
        
    //     for(int q = 0; q < queries.length; q++) {
    //         int[] query = queries[q];
            
    //         // Element to be marked is NOT already marked 
    //         if(nums[query[0]] != Integer.MAX_VALUE) {
    //             sum -= nums[query[0]];
                
    //             nums[query[0]] = Integer.MAX_VALUE;
    //         }
            
    //         while(query[1] > 0 && !pq.isEmpty()) {
    //             int[] poppedNum = pq.poll();
                
    //             // The number has not been marked
    //             if(nums[poppedNum[1]] != Integer.MAX_VALUE) {
    //                 query[1]--;
                    
    //                 sum -= poppedNum[0];
                    
    //                 nums[poppedNum[1]] = Integer.MAX_VALUE;
    //             }
                
    //         }
            
    //         sums[q] = sum;
    //     }
        
    //     return sums;
    // }

    // Best solution since it uses a sorted array to mark elements.
    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        long sum = 0;
        
        ArrayList<int[]> arr = new ArrayList<>(nums.length * 2);

        
        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            
            sum += num;
            
            arr.add(new int[]{num, i});
        }

        Collections.sort(arr, (a1, a2) -> {
            int result = Integer.compare(a1[0], a2[0]);
            
            if(result == 0) result = Integer.compare(a1[1], a2[1]);
            
            return result;
        });

        int popIdx = 0;
        
        long[] sums = new long[queries.length];
        
        
        for(int q = 0; q < queries.length; q++) {
            int[] query = queries[q];
            
            // Element to be marked is NOT already marked 
            if(nums[query[0]] != Integer.MAX_VALUE) {
                sum -= nums[query[0]];
                
                nums[query[0]] = Integer.MAX_VALUE;
            }
            
            while(query[1] > 0 && popIdx < nums.length) {
                int[] poppedNum = arr.get(popIdx++);
                
                // The number has not been marked
                if(nums[poppedNum[1]] != Integer.MAX_VALUE) {
                    query[1]--;
                    
                    sum -= poppedNum[0];
                    
                    nums[poppedNum[1]] = Integer.MAX_VALUE;
                }
                
            }
            
            sums[q] = sum;
        }
        
        return sums;
    }
}