// Problem link: https://leetcode.com/problems/insert-interval

class Solution {
    // Solution using while-loop (SELF-DEVELOPED)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // The intervals are already sorted.

        List<int[]> finalIntervals = new ArrayList<>();

        int currentInterval = 0;

        while(
            currentInterval < intervals.length
            &&
            intervals[currentInterval][1] < newInterval[0]
        ) {
            finalIntervals.add(intervals[currentInterval++]);
        }


        if(currentInterval < intervals.length) {
            newInterval[0] = Math.min(
                intervals[currentInterval][0], 
                newInterval[0]
            );

            while(
                currentInterval < intervals.length
                &&    
                newInterval[1] >= intervals[currentInterval][0]
            ) {
                newInterval[1] = Math.max(
                    newInterval[1],
                    intervals[currentInterval++][1]
                );
            }
        }
        
        finalIntervals.add(newInterval);

        while(currentInterval < intervals.length) {
            finalIntervals.add(intervals[currentInterval++]);
        }   

        return finalIntervals.toArray(int[][]::new);
    }

    // An approach that uses for-loop
    // public int[][] insert(int[][] intervals, int[] newInterval) {
    //     // The intervals are already sorted.

    //     List<int[]> finalIntervals = new ArrayList<>();

    //     int currentInterval = 0;

    //     for(int[] interval: intervals) {
    //         /* 
    //         The entire current interval comes before the start of the 
    //         new interval. So, we directly add the current interval
    //         */
    //         if(interval[1] < newInterval[0]) {
    //             finalIntervals.add(interval);
    //         }
    //         /* 
    //         The entire new interval comes before the start of the 
    //         current interval. So, we directly add the new interval
    //         and make the current interval as the new interval.
    //         */
    //         else if(newInterval[1] < interval[0]) {
    //             finalIntervals.add(newInterval);
    //             newInterval = interval;
    //         }
    //         /*
    //         This is the case where there is some overlap between
    //         the intervals, which is why we update the newInterval
    //         accordingly; to represent the merged interval.
    //         */
    //         else {
    //             newInterval[0] = Math.min(newInterval[0], interval[0]);
    //             newInterval[1] = Math.max(newInterval[1], interval[1]);
    //         }
    //     }

    //     finalIntervals.add(newInterval);

    //     return finalIntervals.toArray(int[][]::new);
    // }
}