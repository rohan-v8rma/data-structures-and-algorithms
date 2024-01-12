public class Solution {
    public static List<Integer> bfsTraversal(int n, List<List<Integer>> adj){
        
        boolean[] visited = new boolean[n];
        
        Queue<Integer> bfsQueue = new LinkedList<>();
        

        List<Integer> bfsTraversal = new ArrayList<>();

        // This for-loop is for unconnected components.
        for(int vertex = 0; vertex < n; vertex++) {
            if(!visited[vertex]) {
                // Restarting BFS for unconnected components.
                bfsQueue.offer(vertex);
                visited[vertex] = true;
            }

            // Traversing all nodes connected to this node, directly or indirectly.
            while(!bfsQueue.isEmpty()) {
                int currentNode = bfsQueue.poll();

                List<Integer> adjNodes = adj.get(currentNode);
                
                for(int adjNode: adjNodes) {
                    if(!visited[adjNode]) {
                        bfsQueue.offer(adjNode);    
                        visited[adjNode] = true;
                    }
                }

                bfsTraversal.add(currentNode);
            }
        }

        return bfsTraversal;
    }
}

