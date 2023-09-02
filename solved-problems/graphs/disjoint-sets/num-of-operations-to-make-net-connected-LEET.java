// https://leetcode.com/problems/number-of-operations-to-make-network-connected

class Solution {
    //* DFS approach
    // public int makeConnected(int n, int[][] connections) {
        
    //     adjList = new ArrayList<>(2 * n);

    //     for(int computer = 0; computer < n; computer++) {
    //         adjList.add(new ArrayList<>());
    //      }

    //     for(int[] connection: connections) {
    //         adjList.get(connection[0]).add(connection[1]);
    //         adjList.get(connection[1]).add(connection[0]);
    //     }

    //     int numOfComponents = 0;
    //     extraWires = 0;

    //     visited = new boolean[n];
    //     pathVisited = new boolean[n];

    //     for(int computer = 0; computer < n; computer++) {
    //         if(!visited[computer]) {
    //             numOfComponents++;

    //             // dfs called on the computer because visited[computer] = false
    //             dfs(computer);
    //         }
    //     }

    //     // In order to connect n components of the network, we need n - 1 wires.
    //     if( extraWires < (numOfComponents - 1) ) {
    //         return -1;
    //     }

    //     return numOfComponents - 1;
    // }

    // static ArrayList<ArrayList<Integer>> adjList;
    // static int extraWires;
    // static boolean[] visited;
    // static boolean[] pathVisited;

    // // This DFS function is called on unvisited computers to VISIT THEM.
    // void dfs(int computer) {
    //     /* 
    //     We set both to true without checking because dfs is called on a computer
    //     only when visited[computer] = false.

    //     Also, if visited[computer] = false, there is no chance of it being path-visited.
    //     */
    //     visited[computer] = true;
    //     pathVisited[computer] = true;

    //     for(int adjComputer: adjList.get(computer)) {
    //         // The adjacent computer has been visited before
    //         if(visited[adjComputer]) {
    //             /* 
    //             But, it was not visited in the current DFS path.

    //             This means some other computer visited this adjacent computer,
    //             using some other path.

    //             Since this adjacent computer is already reachable by another computer,
    //             the wire connecting the current computer to this adjacent computer is
    //             EXTRA, and can be re-used for making the network connectd.
    //             */
    //             if(!pathVisited[adjComputer]) {
    //                 extraWires++;
    //             }
    //         }
    //         // The adjacent computer has NOT been visited before, so we call DFS.
    //         else {
    //             dfs(adjComputer);
    //         }
    //     }

    //     // Before returning from this path, we set pathVisited of current computer as false.
    //     pathVisited[computer] = false;
    // }

    //* DFS approach (OPTIMIZED)
    // public int makeConnected(int n, int[][] connections) {
    //     // (n - 1) wires are needed at minimum to make entire network connected.
    //     if(connections.length < (n - 1)) {
    //         return -1;
    //     }

    //     adjList = new ArrayList<>(2 * n);

    //     for(int computer = 0; computer < n; computer++) {
    //         adjList.add(new ArrayList<>());
    //     }

    //     for(int[] connection: connections) {
    //         adjList.get(connection[0]).add(connection[1]);
    //         adjList.get(connection[1]).add(connection[0]);
    //     }

    //     int numOfComponents = 0;

    //     visited = new boolean[n];

    //     for(int computer = 0; computer < n; computer++) {
    //         if(!visited[computer]) {
    //             numOfComponents++;
    //             // dfs called on the computer because visited[computer] = false
    //             dfs(computer);
    //         }
    //     }


    //     /* 
    //     In order to connect k components of the network, 
    //     k - 1 operations need to be performed
        
    //     We directly return k - 1, since we have already eliminated cases
    //     where it is not possible to make the entire network connected.
    //     */
    //     return numOfComponents - 1;
    // }

    // static ArrayList<ArrayList<Integer>> adjList;
    // static boolean[] visited;

    // // This DFS function is called on unvisited computers to VISIT THEM.
    // void dfs(int computer) {
    //     /* 
    //     Since, dfs is called on a computer only when visited[computer] = false;
    //     we can directly set visited[computer] = true
    //     */
    //     visited[computer] = true;

    //     for(int adjComputer: adjList.get(computer)) {
    //         // The adjacent computer has NOT been visited before
    //         if(!visited[adjComputer]) {
    //             dfs(adjComputer);
    //         }
    //     }
    // }

    //* Approach using Disjoint Sets.
    // public int makeConnected(int n, int[][] connections) {

    //     numOfComponents = n;
    //     extraWires = 0;

    //     size = new int[n];
    //     Arrays.setAll(size, idx -> 1);
    //     parent = new int[n];
    //     Arrays.setAll(parent, idx -> idx);

    //     for(int[] connection: connections) {
    //         unionBySize(connection[0], connection[1]);
    //     }

    //     // In order to connect n components of the network, we need n - 1 wires.
    //     if( extraWires < (numOfComponents - 1) ) {
    //         return -1;
    //     }

    //     return numOfComponents - 1;
    // }

    // static int extraWires;
    // static int numOfComponents;

    // static int[] size;
    // static int[] parent;

    // static int getParent(int node) {
    //     if(parent[node] == node) {
    //         return node;
    //     }

    //     return parent[node] = getParent(parent[node]);
    // }

    // static void unionBySize(int U, int V) {
    //     int parentU = getParent(U);
    //     int parentV = getParent(V);

    //     /* 
    //     The union was unsuccessful since the nodes have same parent.

    //     In the case of an unsuccessful union, we know this is an extra edge/wire.
    //     */
    //     if(parentU == parentV) {
    //         extraWires++;
    //         return;
    //     }

    //     /* 
    //     Since union is possible, we know 2 components will be merged into 1.

    //     So we reduce numOfComponents by 1.
    //     */
    //     numOfComponents--;

    //     if(size[U] >= size[V]) {
    //         size[U] += size[V];
    //         parent[parentV] = parentU;
    //     }
    //     else {
    //         size[V] += size[U];
    //         parent[parentU] = parentV;
    //     }
    // }

    //* Approach using Disjoint Sets (OPTIMIZED)
    public int makeConnected(int n, int[][] connections) {
        
        // (n - 1) wires are needed at minimum to make entire network connected.
        if(connections.length < (n - 1)) {
            return -1;
        }

        numOfComponents = n;

        size = new int[n];
        Arrays.setAll(size, idx -> 1);
        parent = new int[n];
        Arrays.setAll(parent, idx -> idx);

        for(int[] connection: connections) {
            unionBySize(connection[0], connection[1]);
        }

        /* 
        In order to connect k components of the network, 
        k - 1 operations need to be performed
        
        We directly return k - 1, since we have already eliminated cases
        where it is not possible to make the entire network connected.
        */
        return numOfComponents - 1;
    }

    static int numOfComponents;

    static int[] size;
    static int[] parent;

    static int getParent(int node) {
        if(parent[node] == node) {
            return node;
        }

        return parent[node] = getParent(parent[node]);
    }

    static void unionBySize(int U, int V) {
        int parentU = getParent(U);
        int parentV = getParent(V);

        // The union was unsuccessful since the nodes have same parent.
        if(parentU == parentV) {
            return;
        }

        /* 
        Since union is possible, we know 2 components will be merged into 1.

        So we reduce numOfComponents by 1.
        */
        numOfComponents--;

        if(size[U] >= size[V]) {
            size[U] += size[V];
            parent[parentV] = parentU;
        }
        else {
            size[V] += size[U];
            parent[parentU] = parentV;
        }
    }
}