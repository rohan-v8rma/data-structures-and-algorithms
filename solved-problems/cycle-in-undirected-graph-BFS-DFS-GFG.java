
class Solution {
    public boolean sourceCycleDFS(int src, int parent, ArrayList<ArrayList<Integer>> adj, int[] visited) {
        visited[src] = 1;
        
        for(int adjacentNode: adj.get(src)) {
            
            // Ignoring the adjacent node which is the parent itself.
            if(adjacentNode == parent) {
                continue;
            }
            
            // Node is not parent and has already been visited, so cycle is present.
            if(visited[adjacentNode] == 1) {
                return true;
            }
            
            // Making a recursive DFS call to the adjacent node, if it is not already visited.
            // If it returns true, we return true, that there is a cycle.
            if(sourceCycleDFS(adjacentNode, src, adj, visited)) {
                return true;
            };
        }
        
        // After exhausting all adjacent nodes, returning false.
        return false;
    }
    
    public boolean sourceCycleBFS(int src, ArrayList<ArrayList<Integer>> adj, int[] visited) {
        Queue<int[]> bfs = new LinkedList<>();
        
        // Adding the source node, with parent -1, i.e., no parent.
        bfs.offer(new int[]{src, -1});
        // Marking the source node as visited.
        visited[src] = 1;
        
        while(!bfs.isEmpty()) {
            int[] currentNode = bfs.poll();
            
            for(int adjacentNode: adj.get(currentNode[0])) {
            
                /*
                currentNode[1] is the parent of the node being process.
                The adjacent node is the parent itself, where we just came from, so we ignore.
                */
                if( (adjacentNode == currentNode[1])) {
                    continue;
                }
                
                /* 
                This node has already been visited by BFS traversal starting from 0. 
                So, that indicates there is a cycle in the undirected graph.
                */
                if(visited[adjacentNode] == 1) {
                    return true;
                }
                
                /*
                The adjacentNode wasn't already marked as visited, so mark it now,
                and add it to the queue.
                */
                visited[adjacentNode] = 1;
                bfs.offer(new int[]{adjacentNode, currentNode[0]});
            }
        }
        
        return false;
    }
    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        
        // for(int index = 0; index < V; index++) {
        //     System.out.printf("Node %d:\n", index);
        //     for(int adjNode: adj.get(index)) {
        //         System.out.printf("%d, ", adjNode);
        //     }
        //     System.out.println(" ");
        // }
        
        
        // Visited array
        int[] visited = new int[V];
        
        
        for(int source = 0; source < V; source++) {
            if( (visited[source] == 0) && (sourceCycleDFS(source, -1, adj, visited))) {
                return true;
            }
        }
        
        return false;
        
    }
}