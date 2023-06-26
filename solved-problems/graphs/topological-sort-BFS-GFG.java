// https://practice.geeksforgeeks.org/problems/topological-sort/0


class Solution
{
    static void calculateIndegree(int[] inDegrees, ArrayList<ArrayList<Integer>> adj) {
        
        for(ArrayList<Integer> adjNodes: adj) {
            for(int toVertex: adjNodes) {
                inDegrees[toVertex]++;
            }
        }
    }
    
    //Function to return list containing vertices in Topological order. 
    static int[] topoSortBFS(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        Queue<Integer> bfsQueue = new LinkedList<>();
        
        int[] inDegrees = new int[V];
        int[] topSortOrder = new int[V];
        int topSortIndex = 0;
        
        calculateIndegree(inDegrees, adj);
        
        for(int vertex = 0; vertex < V; vertex++) {
            if(inDegrees[vertex] == 0) {
                bfsQueue.offer(vertex);
                
                /*
                inDegrees made -1 to represent the vertex has been visited.
                
                This eliminates the need for a visited array, 
                reducing Space Complexity from O(2N) to O(N).
                */
                inDegrees[vertex] -= 1; 
            }
        }
        
        while(!bfsQueue.isEmpty()) {
            int currentVertex = bfsQueue.poll();
            
            topSortOrder[topSortIndex++] = currentVertex;
            
            for(int adjNode: adj.get(currentVertex)) {
                inDegrees[adjNode] -= 1;
                
                // No more in-degrees left, so it can be processed in bfs next.
                if(inDegrees[adjNode] == 0) {
                    bfsQueue.offer(adjNode);
                }
            }
        }
        
        return topSortOrder;
    }
}
