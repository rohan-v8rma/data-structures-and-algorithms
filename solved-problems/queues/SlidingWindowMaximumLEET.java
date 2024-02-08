class Solution {
    // BRUTEFORCE
    // public int[] maxSlidingWindow(int[] nums, int k) {
    //     int[] maxInWindow = new int[nums.length - k + 1];

    //     Arrays.fill(maxInWindow, -10001);

    //     for(int i = 0; i < nums.length; i++) {
    //         for(
    //             // Targets the window, in which nums[i] is the last element
    //             int windowIdx = Math.max(0, i - k + 1); 
    //             // Target the window, in which nums[i] is the first element
    //             windowIdx <= i && windowIdx < maxInWindow.length;
    //             windowIdx++
    //         ) {
    //             maxInWindow[windowIdx] = Math.max(nums[i], maxInWindow[windowIdx]);
    //         }
    //     }

    //     return maxInWindow;
    // }

    public void addElementIntoQue(
        ArrayDeque<Integer> monotonicDecQue, 
        int element
    ) {
            while(
                !monotonicDecQue.isEmpty()
                && monotonicDecQue.peekLast() < element
            ) {
                monotonicDecQue.pollLast();
            }

            monotonicDecQue.offerLast(element);
    }

    /*____O(N) solution using MONOTONICALLY DECREASING QUEUE____*/
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] maxInWindow = new int[nums.length - k + 1];
 
        ArrayDeque<Integer> monotonicDecQue = new ArrayDeque<>();

        for(int i = 0; i < k; i++) {
            addElementIntoQue(monotonicDecQue, nums[i]);            
        }

        maxInWindow[0] = monotonicDecQue.peekFirst();

        for(int i = k; i < nums.length; i++) {
            /*
            Removing the element, that just got excluded from the window,
            CONDITIONALLY from the queue.

            If the queue has the element at its front, we remove it.

            Otherwise, we know that it has been removed, when a greater
            element was added into the queue.
            */
            if(monotonicDecQue.peekFirst() == nums[i - k]) {
                monotonicDecQue.pollFirst();
            }

            addElementIntoQue(monotonicDecQue, nums[i]);

            /*
            We know that because we are maintaining the MONOTONICALLY
            DECREASING property of queue, the first element in the queue,
            will be the maximum element present in the current window.
            */
            maxInWindow[i - k + 1] = monotonicDecQue.peekFirst();
        }

        return maxInWindow;
    }
}