public class Solution {
    // Function for calculating in-degrees for applying Kahn's algorithm.    
    static void calculateIndegree(int[] inDegrees, ArrayList<ArrayList<Integer>> adj) {
        
        for(ArrayList<Integer> adjNodes: adj) {
            for(int toVertex: adjNodes) {
                inDegrees[toVertex]++;
            }
        }
    }
    
    /*  
    Makes use of top sort array, to check if a valid top sort is possible.

    TC: O(V + E)
    SC: O(V) + O(V) + O(V)
    */
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        
        Queue<Integer> bfsQueue = new LinkedList<>();
        
        int[] inDegrees = new int[V];
        calculateIndegree(inDegrees, adj);   
        
        ArrayList<Integer> topSort = new ArrayList<>();
        
        for(int vertex = 0; vertex < V; vertex++) {
            if(inDegrees[vertex] == 0) {
                // Marking the vertex as visited.
                inDegrees[vertex] = -1;
                bfsQueue.offer(vertex);
            }
        }
        
        while(!bfsQueue.isEmpty()) {
            int currentVertex = bfsQueue.poll();
            topSort.add(currentVertex);
            
            for(int adjacentNode : adj.get(currentVertex)) {
                inDegrees[adjacentNode] -= 1;
                
                if(inDegrees[adjacentNode] == 0) {
                    // Marking the adjacent node as visited.
                    inDegrees[adjacentNode] = -1;
                    bfsQueue.offer(adjacentNode);
                }
            }
        }
        
        
        if(topSort.size() < V) {
            /*
            Valid top sort NOT possible. So, graph is NOT acyclic.
            
            Cycle is present so return true.
            */
            return true;
        }
        
        // No cycle in graph since all vertices processed in BFS queue
        return false;
    }



    /*  
    Checks in-degrees at the end to check if all vertices were processed in BFS.

    Removes need for top sort array but increases time complexity.

    TC: O(V + E) + O(V)
    SC: O(V) + O(V)
    */
    public boolean isCyclicBfsBetter(int V, ArrayList<ArrayList<Integer>> adj) {
        
        Queue<Integer> bfsQueue = new LinkedList<>();

        
        int[] inDegrees = new int[V];
        calculateIndegree(inDegrees, adj);   
        
        
        for(int vertex = 0; vertex < V; vertex++) {
            if(inDegrees[vertex] == 0) {
                // Marking the vertex as visited.
                inDegrees[vertex] = -1;
                bfsQueue.offer(vertex);
            }
        }
        
        while(!bfsQueue.isEmpty()) {
            int currentVertex = bfsQueue.poll();
            
            for(int adjacentNode : adj.get(currentVertex)) {
                inDegrees[adjacentNode] -= 1;
                
                if(inDegrees[adjacentNode] == 0) {
                    // Marking the adjacent node as visited.
                    inDegrees[adjacentNode] = -1;
                    bfsQueue.offer(adjacentNode);
                }
            }
        }
        
        for(int inDegree: inDegrees) {
            /* 
            Some vertex wasn't visited, meaning it wasn't eligible to get added to 
            BFS queue for processing.
            
            This means the graph has a cycle. (See visualized logic in notebook).
            */
            if(inDegree != -1) {
                return true;
            }
        }
        
        // No cycle in graph since all vertices processed in BFS queue
        return false;
    }

    /*
    BEST OF BOTH WORLDS

    Makes use of a processed variable which keeps track of variables processed in BFS queue.

    No top sort array so SC saved.
    No inDegrees looping so TC saved.

    TC: O(V + E)
    SC: O(V) + O(V)
    */
    public boolean isCyclicOptimized(int V, ArrayList<ArrayList<Integer>> adj) {
        
        Queue<Integer> bfsQueue = new LinkedList<>();
        
        int[] inDegrees = new int[V];
        calculateIndegree(inDegrees, adj);   
        
        int processed = 0;
        
        for(int vertex = 0; vertex < V; vertex++) {
            if(inDegrees[vertex] == 0) {
                // Marking the vertex as visited.
                inDegrees[vertex] = -1;
                bfsQueue.offer(vertex);
            }
        }
        
        while(!bfsQueue.isEmpty()) {
            int currentVertex = bfsQueue.poll();
            
            processed++;
            
            for(int adjacentNode : adj.get(currentVertex)) {
                inDegrees[adjacentNode] -= 1;
                
                if(inDegrees[adjacentNode] == 0) {
                    // Marking the adjacent node as visited.
                    inDegrees[adjacentNode] = -1;
                    bfsQueue.offer(adjacentNode);
                }
            }
        }
        
        
        if(processed < V) {
            /* 
            Some vertex wasn't visited, meaning it wasn't eligible to get added to 
            BFS queue for processing.
            
            This means the graph has a cycle. (See visualized logic in notebook).
            */
            return true;
        }
        
        // No cycle in graph since all vertices processed in BFS queue
        return false;
    }
}