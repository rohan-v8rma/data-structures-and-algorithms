    /*
    OPTIMIZATION FOR TIME LIMIT EXCEEDED:
    
    We create two separate arrays:
    visited AND pathVisited
    
    Purpose of visited array:
    This is for keeping track of on which nodes we need to restart DFS due to them being
    unreachable from the initially selected source.
    
    Purpose of pathVisited array:
    This is for keeping track of the nodes that have been visited in the current DFS path.
    
    Upon backtracking, we remove the pathVisited value for the node we backtracked from,
    in order to prevent false cycle detection.

    Also, don't run DFS on nodes you have already run DFS on.
    This is because if a cycle were to be detected, it would have been detected in the first call itself.

    SINCE CYCLES HAVE THE PROPERTY THAT WHATEVER NODE YOU START FROM, YOU CAN REACH BACK TO THE NODE YOU
    STARTED FROM, HENCE GUARANTEEING DETECTION OF CYCLE IRRESPECTIVE OF WHERE YOU STARTED.
    */
    
    class Solution {
        boolean detectCycleDFS(
            int src, 
            int parent,
            ArrayList<ArrayList<Integer>> adj,
            boolean[] visited,
            boolean[] pathVisited
        ) {
            visited[src] = true;
            
            // This value will be removed when we backtrack
            pathVisited[src] = true;
            
            for(int adjacentNode: adj.get(src)) {
                /* 
                The adjacent node is already marked as path-visited, which means
                it was marked as a part of the current DFS cycle detection.
                
                So cycle detected, and we return true.
                */
                if(pathVisited[adjacentNode]) {
                    return true;    
                }
                /* 
                The below node is NOT path visited, but it is visited
                so we won't be calling DFS on it again.
                
                This is because since we have already visited this node,
                DFS has already been called on this node once.
                
                If calling DFS at that time didn't lead to cycle detection,
                it is impossible that it will this time.
                
                Keep this in mind:
                A --> B
                |     |
                V     V
                D --> C
                As a solid example.
                */
                if(visited[adjacentNode]) {
                    continue;
                }
                /* 
                If the node is not visited, we run DFS on it, 
                and return true if cycle is detected inside the recursive call.
                */
                if(
                    !visited[adjacentNode]
                    &&
                    detectCycleDFS(adjacentNode, src, adj, visited, pathVisited)
                ) {
                    return true;  
                };
            }
            
            /* 
            Marking the source vertex as not PATH-VISITED as part of the backtracking procedure
            to prevent false cycle detection.
            
            OBSERVE THAT the node is kept as VISITED.
            
            FALSE CYCLE -> becomes cyclic only if edge directions are removed
            A --> B
            |     |
            V     V
            D --> C
            */
            pathVisited[src] = false;
            
            // Backtracking
            return false;
        }
        
        // Function to detect cycle in a directed graph.
        public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
            // code here
            boolean[] visited = new boolean[V];
            boolean[] pathVisited = new boolean[V];
                
            for(int vertex = 0; vertex < V; vertex++) {
                if(!visited[vertex] && detectCycleDFS(vertex, -1, adj, visited, pathVisited)) {
                    return true;
                }
            }
            
            return false;
        }
    }