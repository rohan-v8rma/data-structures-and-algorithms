// https://leetcode.com/problems/number-of-provinces

class Solution {
    /*
    * DFS approach 1.

    Too complicated DFS logic.
    */
    //     public int findCircleNum(int[][] isConnected) {
    //         boolean[] visited = new boolean[isConnected.length];    
    //         int numOfProvinces = 0;
        
    //         for(int index = 0; index < visited.length; index++) {
    //             if(executeDFS(index, isConnected, visited)) {
    //                 numOfProvinces++;    
    //             }
    //         }
    
    //         return numOfProvinces;
    //     }

    //     boolean executeDFS(int vertex, int[][] isConnected, boolean[] visited) {
    //         if(visited[vertex]) {
    //             return false;
    //         }
            
    //         visited[vertex] = true;
    //         for(int toVertex = 0; toVertex < visited.length; toVertex++) {
    //             if(isConnected[vertex][toVertex] == 1) {
    //                 executeDFS(toVertex, isConnected, visited);
    //             }
    //         }
            
    //         return true;
    //     }
                
    /*
    * DFS approach 2.

    Simplified DFS logic.

    ? DFS is only called on a vertex, if it is surely unvisited.
    */
    // public int findCircleNum(int[][] isConnected) {
    //     boolean[] visited = new boolean[isConnected.length];
        
    //     int numOfProvinces = 0;
        
    //     for(int index = 0; index < visited.length; index++) {
    //         if(!visited[index]) {
    //             executeDFS(index, isConnected, visited);
    //             numOfProvinces++;
    //         }
    //     }
        
    //     return numOfProvinces;
    // }
    
    // void executeDFS(int vertex, int[][] isConnected, boolean[] visited) {
    //     visited[vertex] = true;    
    //     for(int toVertex = 0; toVertex < visited.length; toVertex++) {
    //         if(visited[toVertex] == false && isConnected[vertex][toVertex] == 1) {
    //             executeDFS(toVertex, isConnected, visited);
    //         }
    //     }
    // }

    //* Disjoint Set approach
    public int findCircleNum(int[][] isConnected) {
        int V = isConnected.length;

        parent = new int[V + 1];
        // setAll operation is incredibly fast operation. Won't affect TC
        Arrays.setAll(parent, idx -> idx);

        size = new int[V + 1];
        // setAll operation is incredibly fast operation. Won't affect TC
        Arrays.setAll(size, idx -> 1);

        // Without any nodes being grouped into provinces, there are V provinces initially.
        numOfProvinces = V;

        // Time complexity is N(N - 1)/2 for this loop.
        for(int i = 1; i <= V; i++) {
            // This condition avoids doublets and an edge being processed twice
            for(int j = i + 1; j <= V; j++) {
                if(isConnected[i - 1][j - 1] == 1) {
                    unionBySize(i, j);

                    /* 
                    When number of provinces become 1, there can be no further reduction
                    in that number. 

                    So, we return 1 to avoid checking any more edges as an OPTIMIZATION.
                    */
                    if(numOfProvinces == 1) {
                        return 1;
                    }
                }
            }    
        }

        return numOfProvinces;
    }

    static int numOfProvinces;
    static int[] parent;
    static int[] size;

    int findParent(int node) {
        if(node == parent[node]) {
            return node;
        }

        // Path compression
        return parent[node] = findParent(parent[node]);
    }

    void unionBySize(int U, int V) {
        int parentU = findParent(U);
        int parentV = findParent(V);

        if(parentU != parentV) {
            
            /* 
            A union is about to take place meaning 2 provinces will be combined into 1.

            So, we reduce the number of provinces by 1.
            */
            numOfProvinces--;

            if(size[U] >= size[V]) {
                parent[parentV] = parentU;
                size[U] += size[V];
            }
            else {
                parent[parentU] = parentV;
                size[V] += size[U];
            }
        }

    }

}