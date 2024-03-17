// https://leetcode.com/problems/daily-temperatures

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] wait = new int[temperatures.length];

        ArrayDeque<int[]> stack = new ArrayDeque<>();

        for(int tIdx = 0; tIdx < temperatures.length; tIdx++) {
            int currTemp = temperatures[tIdx];
            
            // The current temp is a higher one.
            while(!stack.isEmpty() && stack.peek()[0] < currTemp) {
                int[] prevTemp = stack.pop();
                
                /* 
                The wait time is the current day, 
                minus the day of the previous temperature
                */
                wait[prevTemp[1]] = tIdx - prevTemp[1];
            }

            // Pushing the current temperature, along with its day.
            stack.push(new int[]{currTemp, tIdx});
        }

        return wait;
    }
}
