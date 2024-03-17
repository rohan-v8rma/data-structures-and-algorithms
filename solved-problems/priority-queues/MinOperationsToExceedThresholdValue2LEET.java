// https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-ii/

class Solution {
    public int minOperations(int[] nums, int k) {
        int op = 0;
        
        PriorityQueue<Long> heap = new PriorityQueue<>(Arrays.stream(nums).mapToLong(i -> i).boxed().collect(Collectors.toList()));
        
        
        while(heap.size() >= 2 && heap.peek() < k) {
            long first = heap.poll();
            
            long second = heap.poll();
            
            heap.offer((first * 2 + second));    
            
            
            op++;
        }
        
        return op;
    }
}