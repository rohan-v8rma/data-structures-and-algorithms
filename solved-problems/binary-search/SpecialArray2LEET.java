// https://leetcode.com/contest/weekly-contest-398/problems/special-array-ii/
// OR https://leetcode.com/problems/special-array-ii
// TODO: Make notes

class Solution {
    public boolean binSearch(
        ArrayList<Float> falsePts, 
        int[] queryRange
    ) {
        int lowI = 0; 
        int highI = falsePts.size() - 1;
        
        while(lowI <= highI) {
            int midI = ( lowI + highI ) / 2;
            float midElement = falsePts.get(midI);
            
            /* 
            Note that the element can never be equal to the query high or low value 
            since it has 0.5
            */
            
            // This same adjacent parity occurs in our range
            if(queryRange[0] < midElement && midElement < queryRange[1]) return false;
            
            if(queryRange[1] < midElement) {
                highI = midI - 1;
            }
            else {
                lowI = midI + 1;  
            }
        }
        
        return true;
    }
    
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        ArrayList<Float> falsePts = new ArrayList<>();
        
        for(int i = 1; i < nums.length; i++) {
            // same parity.
            if(nums[i] % 2 == nums[i - 1] % 2) {
                falsePts.add(i - 0.5f);
            }
        }

        /*  
        We need to check whether any same parity pairs are in the given ranges (queries[i])

        So we place the mid point of all same parity pairs into a sorted array and then
        perform binary search using the query end points.

        This gives us a N.log(K) solution where:
        - N is the number of queries
        - K is the number of same parity pairs
        */

        
        boolean[] answers = new boolean[queries.length];
        Arrays.fill(answers, true);
        
        for(int i = 0; i < queries.length; i++) {
            answers[i] = binSearch(falsePts, queries[i]);
        }
        
        return answers;
    }
}