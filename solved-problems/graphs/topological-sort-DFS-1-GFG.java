// https://practice.geeksforgeeks.org/problems/topological-sort/0

class Solution {
    
    // DFS approach for top sort.
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        visited = new boolean[V];
        topSortIdx = V - 1;
        topSortArr = new int[V];
        
        for(int startV = 0; startV < V; startV++) {
            if(!visited[startV]) {
                dfs(startV, adj);
            }
        }
        
        return topSortArr;
    }
    
    static boolean[] visited;
    static int topSortIdx;
    static int[] topSortArr;
    
    static void dfs(int node, ArrayList<ArrayList<Integer>> adj) {
        // Marking the node as visited.
        visited[node] = true;
        
        for(int adjNode: adj.get(node)) {
            if(!visited[adjNode]) {
                dfs(adjNode, adj);
            }
        }
        
        /* 
        After all adjacent nodes have been visited, we add the current vertex 
        to the top sort order AHEAD of all added nodes so that property of top 
        sort is maintained.

        This way in the base case, when the vertex has no adjacent vertices, 
        it is inserted in the end of the topological order.
        */
        topSortArr[topSortIdx--] = node;
    }
}