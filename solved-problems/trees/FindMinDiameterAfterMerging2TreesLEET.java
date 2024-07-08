// https://leetcode.com/problems/find-minimum-diameter-after-merging-two-trees/description/

class Solution {
    /*
    ____BFS APPROACH____
    */
    // int[] bfs(int[][] edges) {
    //     int numNodes = edges.length + 1;

    //     int[] degrees = new int[numNodes];

    //     ArrayList<HashSet<Integer>> adj = new ArrayList<>();

    //     for(int node = 0; node < numNodes; node++) adj.add(new HashSet<>());

    //     for(int[] edge: edges) {
    //         degrees[edge[0]]++;
    //         degrees[edge[1]]++;

    //         adj.get(edge[0]).add(edge[1]);
    //         adj.get(edge[1]).add(edge[0]);
    //     }

    //     LinkedList<Integer> leaves = new LinkedList<>();

    //     for(int node = 0; node < numNodes; node++) {
    //         if(degrees[node] == 1) {
    //             leaves.offer(node);
    //         }
    //     }

    //     int distance = 0;
    //     int leavesInThisLevel = leaves.size();

    //     while(leavesInThisLevel > 1) {
    //         if(leavesInThisLevel == 2) {
    //             int firstLeaf = leaves.peek();

    //             int adjNode = adj.get(firstLeaf).iterator().next();


    //             if(degrees[adjNode] == 1) {
    //                 /*
    //                 There are 2 leaves left, connected to each
    //                 other directly. Either of these would give
    //                 us a minimum height tree.

    //                 The diameter is from the leaf on one side
    //                 of the removal to the other longest side of
    //                 the removal.

    //                 The diameter cannot be any other path, since
    //                 it would be shorter as demonstrated by the fact
    //                 that those nodes were removed earlier.

    //                 AND it cannot be longer since we literally
    //                 don't have any edges left to remove.
    //                 */
    //                 return new int[]{distance + 1, 2 * distance + 1};
    //             }
    //         }

    //         for(int leaf = 0; leaf < leavesInThisLevel; leaf++) {
    //             int leafNum = leaves.poll();

    //             int adjNode = adj.get(leafNum).iterator().next();

    //             degrees[leafNum] = 0;

    //             degrees[adjNode]--;

    //             // Removing the leaf from the node's adjacency list.
    //             adj.get(adjNode).remove(leafNum);

    //             // The node is a leaf itself.
    //             if(degrees[adjNode] == 1) {
    //                 leaves.offer(adjNode);
    //             }
    //         }
    //         distance++;

    //         leavesInThisLevel = leaves.size();
    //     }

    //     /*
    //     We reach here only 1 leaf is there in a level,
    //     which means we have reduced the tree to just the root node.

    //     Note that to reach this point, we would have removed equal
    //     number of children from atleast 2 directions of this 
    //     "CURRENT LEAF", for it to still be left.

    //     So, the number of leaves removed, on both sides added up
    //     is the diameter of the tree.
    //     */
    //     return new int[]{distance, 2 * distance};

    // }

    // public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {      
    //     int[] maxDistance1 = bfs(edges1);
    //     int[] maxDistance2 = bfs(edges2);

    //     return Math.max(
    //         maxDistance1[0] + maxDistance2[0] + 1,
    //         Math.max(
    //             maxDistance1[1],
    //             maxDistance2[1]
    //         )
    //     );
    // }

    /*
    ____DFS APPROACH____
    */
    int getHeightOfNaryTree(int curr, int parent, ArrayList<ArrayList<Integer>> adj) {
        int maxHeight = 0;
        int secondMaxHeight = 0;

        int height = 0;

        for(int adjNode: adj.get(curr)) {
            if(adjNode != parent) {
                int heightOfSubtree = 1 + getHeightOfNaryTree(adjNode, curr, adj);

                if(maxHeight < heightOfSubtree) {
                    secondMaxHeight = maxHeight;
                    maxHeight = heightOfSubtree;
                }
                else {
                    secondMaxHeight = Math.max(secondMaxHeight, heightOfSubtree);
                }
            }
        }

        diameterOfNaryTree = Math.max(
            diameterOfNaryTree, 
            maxHeight + secondMaxHeight
        );

        return maxHeight;
    }

    int diameterOfNaryTree;

    int diameter(int[][] edges) {
        diameterOfNaryTree = 0;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        /* 
        Not that since they are trees, the number of edges 
        will be 1 less than the number of nodes in the tree
        */
        for(int i = 0; i <= edges.length; i++) adj.add(new ArrayList<>());

        for(int[] edge: edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        getHeightOfNaryTree(0, -1, adj);

        return diameterOfNaryTree;
    }

    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int diameter1 = diameter(edges1);
        int diameter2 = diameter(edges2);

        /* 
        The minimum diameter on merging will be when we join 
        the diameters of the 2 graphs/tree

        Also, the diameter of the graphs on their own may also be
        the diameter of the combined graph.

        To get half of the diameter:
        - Suppose diameter is 9, 4 nodes on 1 side 
          and 5 nodes on 1 side. So half is 5 since that 
          would contribute the most to the diameter.
        - Suppose diameter is 8, 4 nodes on both sides,
          so half is 4.

        If we add 1 to any of the 2 values and divide by 2,
        we'll get the exact diameters.
        */
        return Math.max(
            (diameter1 + 1) / 2 + 1 + (diameter2 + 1) / 2,
            Math.max(diameter1, diameter2)
        );
    }    
}