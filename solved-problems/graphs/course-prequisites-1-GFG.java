// https://practice.geeksforgeeks.org/problems/prerequisite-tasks/0

class Solution {
    static int topSortPossibleKahn(ArrayList<Integer>[] adj, int[] inDegreeArr) {
        Queue<Integer> bfsQueue = new LinkedList<>();
        
        // Add all vertices having 0 in-degree to bfsQueue
        for(int index = 0; index < inDegreeArr.length; index++) {
            if(inDegreeArr[index] == 0) {
                bfsQueue.offer(index);
            }
        }
        
        int visited = 0;
        
        /*
        Time Complexity of this approach is
        For each iteration of the while loop
        while loop check and other operations : O(1)
        reducing in degrees of adjacent Vertices : O(E/V)
        
        TC: V * (1 + E/V) = V + E
        This is because we are using adjacency list.
        
        
        If in case we used adjacency matrix, 
        for each iteration of the while loop
        while loop check and other operations : O(1)
        iterating over all vertices to see whether they are adjacent or not
        AND reducing in degrees of the adjacent ones: O(V) + O(E/V)
        
        TC: V * (1 + V + E/V) = V + V^2 + E
        
        This results in time limit exceeded.
        */
        while(bfsQueue.peek() != null) {
            int nextVertex = bfsQueue.poll();
            
            for(int adjacentVertex: adj[nextVertex]) {
                /* 
                Subtracting inDegree from adjacent vertices, 
                of the vertex that is about to be added in top sort order.
                */
                
                /* 
                We don't need to check whether the toVertex is already in
                top sort order, because if edge from nextVertex hasn't yet
                been explored, it is NOT possible for toVertex to be in top
                sort order.
                */
                inDegreeArr[adjacentVertex] -= 1;
                if(inDegreeArr[adjacentVertex] == 0) {
                    bfsQueue.offer(adjacentVertex);
                }
                
            }
            
            // This is to symbolize that the node has been added to top sort order.
            visited += 1;
        }
        
        
        // Meaning, not all vertices were added to top sort, hence top sort not possible
        return visited;
    }    
    
    public boolean isPossible(int N, int[][] prerequisites) {
        
        // Initializing a 2-d array in java
        ArrayList<Integer>[] adj = new ArrayList[N];
        for(int index = 0; index < N; index++) {
            adj[index] = new ArrayList<>();
        }
        
        int[] inDegreeArray = new int[N];
        
        for(int[] pair: prerequisites) {
            
            // Adding to the indegree of a vertex.
            adj[pair[1]].add(pair[0]);
            
            inDegreeArray[pair[0]] += 1;
        }
        
        return (topSortPossibleKahn(adj, inDegreeArray) == N);
    }
}