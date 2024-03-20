// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-146/problems


class Solution {
    /*
    TC: O(N + Q)
    SC: O(N)
    */
    // public static long geekTasks(int n, int m, int q, int[][] tasks) {
        
    //     int[] rowsIncrementCt = new int[n];
        
    //     // Getting elements having max value.
    //     long maxRow = n - 1;
    //     long maxCol = m - 1;
        
        
    //     for(int[] task: tasks) {
    //         maxRow = Math.min(maxRow, task[0]);
    //         maxCol = Math.min(maxCol, task[1]);
            
    //         rowsIncrementCt[task[0]] = Math.max(
    //             rowsIncrementCt[task[0]],
    //             task[1] + 1 // To represent actual increment count.
    //         );
    //     }
        
    //     long maxValueElements = (maxRow + 1) * (maxCol + 1); 
        
    //     long incrementedElements = 0;
        
    //     long maxIncrementedElements = 0;
        
    //     for(int row = n - 1; row >= 0; row--) {
    //         maxIncrementedElements = Math.max(maxIncrementedElements, rowsIncrementCt[row]);

    //         incrementedElements += maxIncrementedElements;
    //     }
        
    //     return incrementedElements - maxValueElements;
    // }
    
    /*
    TC: O(Q.log(Q) + Q)
    SC: O(1)
    */
    public static long geekTasks(int n, int m, int q, int[][] tasks) {
        
        // Sorting, so that we can easily get number of incremented elements.
        Arrays.sort(tasks, (a1, a2) -> (
            (a1[0] - a2[0]) == 0 
            ? (a1[1] - a2[1]) 
            : (a1[0] - a2[0])
            )
        );
        
        // Used for getting count of elements having max value.
        long maxValRow = Integer.MAX_VALUE;
        long maxValCol = Integer.MAX_VALUE;
        
        long incrementedElements = tasks[q - 1][1] + 1;
        
        long maxIncrementedElements = 0;

        int[] prevTask = tasks[q - 1];
        
        for(int i = q - 1; i >= 0; i--) {
            int[] task = tasks[i];
            
            maxValRow = Math.min(maxValRow, task[0]);
            maxValCol = Math.min(maxValCol, task[1]);
            
            
            /* 
            There are some rows between previous and current task
            that have uncounted elements
            */
            if(prevTask[0] - task[0] > 1) {
                incrementedElements += (prevTask[0] - task[0] - 1) * maxIncrementedElements;
            }
            
            maxIncrementedElements = Math.max(maxIncrementedElements, task[1] + 1);
            
            // The previous row is not same as the current row.
            if(prevTask[0] > task[0]) {
                incrementedElements += maxIncrementedElements;    
            }
            
            prevTask = task;
        }
        
        // Suppose the queries ended at row 1. Elements in row 0 are uncounted.
        incrementedElements += tasks[0][0] * maxIncrementedElements;
        
        
        long maxValueElements = (maxValRow + 1) * (maxValCol + 1); 
        
        
        return incrementedElements - maxValueElements;
    }
}
        
