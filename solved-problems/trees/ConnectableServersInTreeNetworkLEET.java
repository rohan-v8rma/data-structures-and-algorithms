// https://leetcode.com/problems/count-pairs-of-connectable-servers-in-a-weighted-tree-network/

class Solution {
    // BRUTEFORCE: O(N^3)
    // static void dfs(int currentNode, int previousNode, int value, int currentWeight, int[] parent, int[] weight) {
    //     parent[currentNode] = value;
    //     weight[currentNode] = currentWeight;
        
    //     for(int[] node: adjList.get(currentNode)) {
    //         if(node[0] != previousNode) {
    //             dfs(node[0], currentNode, value, currentWeight + node[1], parent, weight);
    //         }
    //     }
    // }
    
    // static int countConnectablePairs(int node, int n, int signalSpeed) {
    //     int[] parent = new int[n];
    //     int[] weight = new int[n];
        
    //     parent[node] = -1;
        
    //     for(int[] adjNode: adjList.get(node)) {
    //         dfs(adjNode[0], node, adjNode[0], adjNode[1], parent, weight);
    //     }
        
    //     int connectable = 0;
        
    //     for(int from = 0; from < n; from++) {
    //         if(from != node && weight[from] % signalSpeed == 0) {
    //             for(int to = from + 1; to < n; to++) {
    //                 if(to != node && weight[to] % signalSpeed == 0) {
    //                     if(parent[from] != parent[to]) {
    //                         connectable++;
    //                     }
                             
    //                 }
    //             }    
    //         }
            
    //     }
        
    //     return connectable;
    // }
    
    // static ArrayList<ArrayList<int[]>> adjList;
    
    // public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
    //     adjList = new ArrayList<>();
    
    //     int n = edges.length + 1;
        
    //     for(int i = 0; i < n; i++) adjList.add(new ArrayList<>());
        
    //     for(int[] edge: edges) {
            
    //         adjList.get(edge[0]).add(new int[]{edge[1], edge[2]});
    //         adjList.get(edge[1]).add(new int[]{edge[0], edge[2]});
    //     }
        
    //     int[] connectable = new int[n];
        
    //     for(int node = 0; node < n; node++) {
    //         connectable[node] = countConnectablePairs(node, n, signalSpeed);
    //     }
        
    //     return connectable;
    // }

    // O(N * (N + K)), no TLE
    static int dfs(
        int currentNode, 
        int previousNode, 
        int currentWeight, 
        int signalSpeed
    ) {
        int validNodes = 0;

        if(currentWeight % signalSpeed == 0) validNodes++;

        for(int[] adj: adjList.get(currentNode)) {
            if(adj[0] != previousNode) {
                validNodes += dfs(
                    adj[0], 
                    currentNode, 
                    currentWeight + adj[1], 
                    signalSpeed
                );
            }
        }

        return validNodes;
    }

    static int countConnectablePairs(int node, int n, int signalSpeed) {
        ArrayList<Integer> validNodesInBranches = new ArrayList<>();

        for(int[] adj: adjList.get(node)) {
            int val = dfs(adj[0], node, adj[1], signalSpeed);
            if(val != 0) validNodesInBranches.add(val);
        }

        int size = validNodesInBranches.size();

        int connectablePairs = 0;

        // Combinations of all branch values, 2 at a time.
        for(int i = 0; i < size; i++) {
            for(int j = i + 1; j < size; j++) {
                connectablePairs += (
                    validNodesInBranches.get(i)
                    *
                    validNodesInBranches.get(j)
                );
            }
        }

        return connectablePairs;
    }

    static ArrayList<ArrayList<int[]>> adjList;
    static int[] connectable;

    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        adjList = new ArrayList<>();
    
        int n = edges.length + 1;
        
        for(int i = 0; i < n; i++) adjList.add(new ArrayList<>());
        
        for(int[] edge: edges) {
            
            adjList.get(edge[0]).add(new int[]{edge[1], edge[2]});
            adjList.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
        
        connectable = new int[n];

        for(int i = 0; i < n; i++) {
            connectable[i] = countConnectablePairs(i, n, signalSpeed);
        }

        return connectable;
    }
}