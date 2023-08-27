// https://practice.geeksforgeeks.org/problems/kth-smallest-element5635/1

//? Similar to kth-largest-sum-in-binary-tree-LEET.java
class Solution {
    // BRUTEFORCE (N.log(N))
    // public static int kthSmallest(int[] arr, int l, int r, int k) { 
    //     Arrays.sort(arr);
    //     return arr[k - 1];
    //     // return 0;
    // } 
    

    // OPTIMAL: N.log(K), uses Priority queue. Similar to Kth level sum.
    public static int kthSmallest(int[] arr, int l, int r, int k) { 
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        
        for(int num: arr) {
            if(maxHeap.size() < k) {
                maxHeap.offer(num);
            }
            else if(maxHeap.peek() > num) {
                maxHeap.poll();
                maxHeap.offer(num);
            }
        }
        
        return maxHeap.poll();
    } 
}