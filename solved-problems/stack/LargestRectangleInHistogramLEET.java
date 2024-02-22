// https://leetcode.com/problems/largest-rectangle-in-histogram

class Solution {
    // O(N^2) solution.
    // public int largestRectangleArea(int[] heights) {
    //     ArrayList<int[]> rectangles = new ArrayList<>();

    //     int max = heights[0];

    //     rectangles.add(new int[]{heights[0], 1});

    //     for(int i = 1; i < heights.length; i++) {
    //         ArrayList<int[]> newRectangles = new ArrayList<>();

    //         max = Math.max(heights[i], max);

    //         newRectangles.add(new int[]{heights[i], 1});

    //         for(int[] rectangle: rectangles) {
    //             int combinedHeight = Math.min(rectangle[0], heights[i]);
    //             int combinedWidth = rectangle[1] + 1;

    //             max = Math.max(combinedHeight * combinedWidth, max);

    //             newRectangles.add(new int[]{combinedHeight, combinedWidth});
    //         }

    //         rectangles = newRectangles;
    //     }
        
    //     return max;
    // }

    // O(N^2) solution
    // public int largestRectangleArea(int[] heights) {
    //     ArrayList<int[]> rectangles = new ArrayList<>();

    //     int max = heights[0];

    //     rectangles.add(new int[]{heights[0], 1});

    //     for(int i = 1; i < heights.length; i++) {
    //         ArrayList<int[]> newRectangles = new ArrayList<>();

    //         max = Math.max(heights[i], max);

    //         newRectangles.add(new int[]{heights[i], 1});

    //         for(int[] rectangle: rectangles) {
    //             int combinedHeight = Math.min(rectangle[0], heights[i]);
    //             int combinedWidth = rectangle[1] + 1;

    //             max = Math.max(combinedHeight * combinedWidth, max);

    //             newRectangles.add(new int[]{combinedHeight, combinedWidth});
    //         }

    //         rectangles = newRectangles;
    //     }
        
    //     return max;
    // }

    // OPTIMAL (O(2 * N))
    public int largestRectangleArea(int[] heights) {
        ArrayDeque<int[]> stack = new ArrayDeque<>();

        int N = heights.length;

        int maxArea = 0;

        for(int hIdx = 0; hIdx < N; hIdx++) {
            int bar = heights[hIdx];

            /* 
            If all bars before this one are smaller, 
            start point is current index only
            */
            int barStart = hIdx;

            // The height of previous bar is greater than current bar.
            while(!stack.isEmpty() && stack.peek()[0] > bar) {

                // Removing the larger bar.
                int[] prevBar = stack.pop();
            
                /* 
                Moving startPt of current smaller bar, 
                to previous bar's starting point.
                */
                barStart = prevBar[1];
                
                /* 
                Seeing if area of previous bar till present index, 
                is the maximum till now. 
                */
                maxArea = Math.max(
                    maxArea, 
                    prevBar[0] * (hIdx - prevBar[1])
                );
            }

            /* 
            If height of previous bar, is equal to current bar,
            no need to add current bar, because previous bar has a better
            starting point and it will resultingly contribute a larger area.
            */
            if(stack.isEmpty() || stack.peek()[0] != bar) {
                stack.push(new int[]{bar, barStart});
            }
        }

        while(!stack.isEmpty()) {
            int[] bar = stack.pop();

            maxArea = Math.max(maxArea, bar[0] * (N - bar[1]));

        }

        return maxArea;
    }
}
