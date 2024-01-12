// https://practice.geeksforgeeks.org/problems/shortest-path-in-undirected-graph/1

class Solution {
    /* 
    shortest path to all nodes using TOP SORT 
    
    TC: O(V + E), since entire adjacency list iterated over in worst case 
    (see useful tips in notebook for why TC = SC)
    
    For detailed TC analysis, see notebook.
    */
	public int[] shortestPath(int N,int M, int[][] edges) {
	    
	   // int[] parentArray = new int[N];
	    int[] shortestPaths = new int[N];
	    // All the nodes are unreachable in the beginning.
	    Arrays.setAll(shortestPaths, i -> -1);
	    // We are at node 0.
	    shortestPaths[0] = 0;
	    
	    // Adjacency Matrix
	    ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();
        
        for(int node = 0; node < N; node++) {
            adjList.add(new ArrayList<>());
        }
        
	    for(int[] edge: edges) {
	        adjList.get(edge[0]).add(new int[]{edge[1], edge[2]});
	    }
	    
	    getTopSort(adjList, N);

	    // Getting index of node 0, in the top sort order.
	    int zeroIdx = 0;
	    while(topSort[zeroIdx] != 0) {
	        zeroIdx++;
	    };
	    
	    // Getting the shortest paths of nodes reachable from node 0.
	    for(int nodeIdx = zeroIdx; nodeIdx < N; nodeIdx++) {
	        
	        int currentNode = topSort[nodeIdx];
	        
	        for(int[] adjNode: adjList.get(currentNode)) {
	        
	            if(shortestPaths[adjNode[0]] == -1) {
	                shortestPaths[adjNode[0]] = shortestPaths[currentNode] + adjNode[1];
	            }
	            else{
	                shortestPaths[adjNode[0]] = Math.min(
	                    shortestPaths[adjNode[0]],
	                    shortestPaths[currentNode] + adjNode[1]
	                );
	            }
	        }
	    }
	    
		
		return shortestPaths;
	}
	
	static int[] topSort;
	int topSortIdx;
	
	public void getTopSort(ArrayList<ArrayList<int[]>> adjList, int N) {
	    
	    topSort = new int[N];
	    topSortIdx = N - 1;
	    
	    boolean[] visited = new boolean[N];
	    
	    for(int src = 0; src < N; src++) {
	        if(!visited[src]) {
	            dfs(src, adjList, visited);        
	        }
	    }
	}
	
	public void dfs(int node, ArrayList<ArrayList<int[]>> adjList, boolean[] visited) {
	    visited[node] = true;
	    
	    for(int[] adjNode: adjList.get(node)) {
	        int toNode = adjNode[0];
	        
	        if(!visited[toNode]) {
	            dfs(toNode, adjList, visited);
	        }
	    }
	    
	    topSort[topSortIdx--] = node;
	}
	
}