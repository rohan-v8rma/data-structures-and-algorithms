// https://leetcode.com/problems/sum-of-distances-in-tree/description/

class Solution {
    /*____BRUTEFORCE O(N^2)____*/

    // int sumOfDistances(int node, int prevNode, int distTillNow) {
    //     int sum = 0;

    //     for(int adjNode: adj.get(node)) {
    //         if(adjNode != prevNode) {
    //             sum += distTillNow + 1;
    //             sum += sumOfDistances(adjNode, node, distTillNow + 1);
    //         }
    //     }

    //     return sum;
    // }

    // static ArrayList<ArrayList<Integer>> adj;

    // public int[] sumOfDistancesInTree(int n, int[][] edges) {
    //     adj = new ArrayList<>();

    //     for(int i = 0; i < n; i++) adj.add(new ArrayList<>());

    //     int[] result = new int[n];

    //     for(int[] edge: edges) {
    //         adj.get(edge[0]).add(edge[1]);
    //         adj.get(edge[1]).add(edge[0]);
    //     }

    //     for(int node = 0; node < n; node++) {
    //         result[node] = sumOfDistances(node, -1, 0);
    //     }

    //     return result;
    // }
    

    /*____OPTIMAL: O(N)____*/

    // This DFS function gets the sub-tree node counts for each node.
    int dfs1(int node, int prev, int dist) {
        // This includes the node itself
        int subTreeNodeCt = 1;

        for(int adjNode: adj.get(node)) {
            if(adjNode != prev) {
                subTreeNodeCt += dfs1(adjNode, node, dist + 1);
            }
        }  

        sumFor0 += dist;

        return subTreeNodeCts[node] = subTreeNodeCt;
    }

    // This DFS function calculates the sum of distances from all nodes, for each individual node.
    void dfs2(int node, int prev, int n) {
        for(int adjNode: adj.get(node)) {
            if(prev != adjNode) {
                result[adjNode] = (
                    result[node] 
                    // Nodes from which distance has increased
                    + (n - subTreeNodeCts[adjNode])
                    // Nodes from which distance has decreased
                    - subTreeNodeCts[adjNode]
                );

                dfs2(adjNode, node, n);
            }
        }
    }

    static ArrayList<ArrayList<Integer>> adj;
    static int[] subTreeNodeCts;
    static int[] result;
    static int sumFor0;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        adj = new ArrayList<>();

        for(int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for(int[] edge: edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        subTreeNodeCts = new int[n];
        result = new int[n];
        sumFor0 = 0;

        dfs1(0, -1, 0);

        // Sum of distances for node 0.
        result[0] = sumFor0;

        dfs2(0, -1, n);

        return result;

    }
    
}