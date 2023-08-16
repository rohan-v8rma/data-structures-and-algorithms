// https://leetcode.com/problems/merge-intervals    

class Solution {
    // Uses arraylist containing int[] arrays.
    public int[][] merge(int[][] intervals) {

        /* 
        Arrays.sort takes an implemented `Comparator` functional interface
        as the second argument.
        */

        /* 
        Using an anonymous inner class, which provides the definition
        of the abstract method.
        */
        // Arrays.sort(
        //     intervals,
        //     new Comparator<int[]>() {
        //         @Override
        //         public int compare(int[] arr0, int[] arr1) {
        //             return Integer.compare(arr0[0], arr1[0]);
        //         }
        //     }
        // );

        /* 
        Using a lambda expression instead, for providing the definition
        of the abstract method.
        */
        Arrays.sort(
            intervals, 
            (int[] arr0, int[] arr1) -> {
                return Integer.compare(arr0[0], arr1[0]);
            }
        );

        List<int[]> mergedIntervals = new ArrayList<>();        

        int[] currInterval = new int[2];
        int nextInterval = 0;
        while(nextInterval < intervals.length) {
            
            currInterval[0] = intervals[nextInterval][0];
            currInterval[1] = intervals[nextInterval++][1];

            int overlapInterval = nextInterval;
            while(
                overlapInterval < intervals.length
                &&
                intervals[overlapInterval][0] <= currInterval[1]
            ) {
                currInterval[1] = Math.max(
                    intervals[overlapInterval++][1],
                    currInterval[1]
                );

            }

            nextInterval = overlapInterval;

            mergedIntervals.add(currInterval.clone());
        }

        /* 
        `mergedIntervals.stream()` converts the arraylist containing int[]  
        arrays to a stream of int[] arrays.

        Next, we convert that stream of 1d primitive integer arrays,
        to a 2d array.

        The `toArray` function requires a `generator` parameter, which is a 
        function that produces a new array of the desired type and size.

        Its return type is an array containing the elements of the stream.

        int[][]::new is a METHOD REFERENCE to the method for creating a 2d
        primitive array.
        */
        int[][] mergedIntervalsPrim = mergedIntervals
        .stream()
        .toArray(int[][]::new);

        
        return mergedIntervalsPrim;
    }
}