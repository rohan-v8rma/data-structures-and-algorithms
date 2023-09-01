// https://practice.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1

class Solution {
    // Approach using Priority Queue. TC: E.log(E)
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        int[] shortestPaths = new int[V];
        
        Arrays.setAll(shortestPaths, idx -> Integer.MAX_VALUE);
        
        // Since we are already at the source node.
        shortestPaths[S] = 0;
        
        PriorityQueue<int[]> nodesByDistance = new PriorityQueue<>(
                // Comparison based on the distance of the nodes.
                (node1, node2) -> Integer.compare(node1[1], node2[1])
            );
        nodesByDistance.offer(new int[]{S, 0});
        
        
        int[] currentNode;
        
        while(V != 0) {
            currentNode = nodesByDistance.poll();
            
            // Removing the entries that are NOT using optimal paths.
            if (shortestPaths[currentNode[0]] < currentNode[1]) {
                continue;
            }
            
            V--;
            
            for(ArrayList<Integer> adjNode: adj.get(currentNode[0])) {
                int adjNodeNum = adjNode.get(0);
                int weight = adjNode.get(1);
                
                // Only adding the entry to PQ if the newly found path is shorter
                if(currentNode[1] + weight < shortestPaths[adjNodeNum]) {
                    shortestPaths[adjNodeNum] = currentNode[1] + weight;
                    nodesByDistance.offer(new int[]{adjNodeNum, shortestPaths[adjNodeNum]});    
                }
                
            }
            
        }
        
        
        return shortestPaths;
    }
    
    // Approach using TreeSet. TC: E.log(V)
    // static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        
    //     int[] shortestPathArr = new int[V];
        
    //     // Setting all the other vertices to be infinitely far away.
    //     Arrays.setAll(shortestPathArr, idx -> (int)1e9);
        
    //     // We are currently at the source.
    //     shortestPathArr[S] = 0;
        
    //     // Will hold at max V elements.
    //     TreeSet<int[]> nodeSet = new TreeSet<>(
    //         (arr0, arr1) -> {
    //             // Comparing the distances first
    //             int returnVal = Integer.compare(arr0[1], arr1[1]);             
                
    //             /* 
    //             If the distances are equal, we compare the node numbers
                
    //             This ensures no duplicate nodes with same distance are added.
    //             */
    //             if(returnVal == 0) {
    //                 return Integer.compare(arr0[0], arr1[0]);
    //             }
                
    //             return returnVal;
    //         }
    //     );
        
    //     nodeSet.add(new int[]{S, 0});
        
        
    //     while(!nodeSet.isEmpty()) {
    //         int[] currentNode = nodeSet.first();
            
    //         nodeSet.remove(currentNode);
            
    //         for(ArrayList<Integer> adjNode: adj.get(currentNode[0])) {
    //             int nodeNum = adjNode.get(0);
    //             int nodeWt = adjNode.get(1);
                
    //             int adjNewDist = nodeWt + currentNode[1];
                
    //             if(adjNewDist < shortestPathArr[nodeNum]) {
    //                 if(shortestPathArr[nodeNum] < (int)1e9) {
    //                     nodeSet.remove(new int[]{nodeNum, shortestPathArr[nodeNum]});
    //                 }
                    
    //                 shortestPathArr[nodeNum] = adjNewDist;
                    
    //                 nodeSet.add(new int[]{nodeNum, shortestPathArr[nodeNum]});
    //             }
    //         }
    //     }
        
    //     return shortestPathArr;
    // }
}