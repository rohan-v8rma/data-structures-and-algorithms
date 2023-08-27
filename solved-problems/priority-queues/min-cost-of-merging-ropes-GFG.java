// https://practice.geeksforgeeks.org/problems/minimum-cost-of-ropes-1587115620/1

class Solution
{
    //Function to return the minimum cost of connecting the ropes.
    long minCost(long arr[], int n) 
    {
        /*
        When a collection is provided to the constructor, 
        it uses a BOTTOM UP heapify operation to efficiently 
        create a valid heap from the given elements; in O(N).
        */
        
        // Not able to import IntStream in GFG
        // PriorityQueue<Long> minHeap = new PriorityQueue<>(IntStream.of(arr).boxed().toList());
        
        List<Long> arrList = new ArrayList<>();
        for(long num: arr) {
            arrList.add(num);
        }
        PriorityQueue<Long> minHeap = new PriorityQueue<>(arrList);
        
        long cost = 0;
        
        long firstRope, secondRope, newRope;
        
        while(minHeap.size() >= 2) {
            firstRope = minHeap.poll();
            secondRope = minHeap.poll();
            
            newRope = firstRope + secondRope;
            
            cost += newRope;
            
            minHeap.offer(newRope);
        }
    
        return cost;
    }
}