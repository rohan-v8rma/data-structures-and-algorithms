// https://leetcode.com/problems/minimum-height-trees/

class Solution {
    /*____BRUTEFORCE: O(N^2)____*/

    // int dfs(int current, int prev, int height) {
    //     // Assuming this is a leaf node
    //     int actualHeight = height;

    //     for(int adjNode: adj.get(current)) {
    //         if(adjNode != prev) {
    //             actualHeight = Math.max(
    //                 actualHeight,
    //                 dfs(adjNode, current, height + 1)
    //             );
    //         }
    //     }

    //     return actualHeight;
    // }

    // ArrayList<ArrayList<Integer>> adj;

    // public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    //     adj = new ArrayList<>();

    //     for(int i = 0; i < n; i++) adj.add(new ArrayList<>());

    //     for(int[] edge: edges) {
    //         adj.get(edge[0]).add(edge[1]);
    //         adj.get(edge[1]).add(edge[0]);
    //     }

    //     int[] height = new int[n];
 
    //     int minHeight = Integer.MAX_VALUE;

    //     for(int node = 0; node < n; node++) {
    //         height[node] = dfs(node, -1, 0);
    //         minHeight = Math.min(minHeight, height[node]);
    //     }

    //     ArrayList<Integer> result = new ArrayList<>();

    //     for(int node = 0; node < n; node++) {
    //         if(height[node] == minHeight) {
    //             result.add(node);
    //         }
    //     }

    //     return (List)result;
    // }

    /*
    ____MOST OPTIMAL: O(2N + E). DFS Approach ____

    Has the least runtime
    */

    // int dfs1(int current, int prev) {
    //     int additionalHeight = -1;

    //     for(int adjNode: adj.get(current)) {
    //         if(adjNode != prev) {
    //             additionalHeight = Math.max(
    //                 additionalHeight,
    //                 dfs1(adjNode, current)
    //             );
    //         }
    //     }

    //     return height[current] = 1 + additionalHeight;
    // }

    // void dfs2(int current, int prev) {
    //     /*
    //     Heights of sub-trees of current node, in the assumption
    //     that current is a node with no children, so its children 
    //     who are 1 edge away have -1 height, making height of current as 0.
    //     */
    //     int secondMaxHeight = -1;
    //     int maxHeight = -1;
        
    //     /* 
    //     It is most profitable for us to go to the node 
    //     that is contributing most to the height i.e.,
    //     sub-tree with max height.

    //     That way, we have a chance of reducing the height.
    //     */

    //     ArrayList<Integer> subTreeWithMaxHeight = new ArrayList<>();

    //     for(int adjNode: adj.get(current)) {
    //         // Updating 1st and 2nd maximums as per adjacent node heights.
    //         if(height[adjNode] >= maxHeight) {
    //             if(height[adjNode] > maxHeight) {
    //                 subTreeWithMaxHeight = new ArrayList<>();
                    
    //                 secondMaxHeight = maxHeight;
    //                 maxHeight = height[adjNode];
    //             }

    //             subTreeWithMaxHeight.add(adjNode);
    //         }
    //         else if(height[adjNode] > secondMaxHeight) {                
    //             secondMaxHeight = height[adjNode];
    //         }
    //     }
        
    //     /*
    //     - If the array is empty, we anyways don't have a node to go to.
    //     - If there is more than 1 node in the array, then going to the 
    //       node won't help reduce the height. It would increase it.
          
    //       E.g:  0
    //            / \
    //           1   2
    //       Going to 1, won't help reduce height, it would increase it by 1.
    //     */
    //     if(subTreeWithMaxHeight.size() != 1) return;

    //     // Getting the only sub-tree node contributing to max height.
    //     int newRoot = subTreeWithMaxHeight.get(0);

    //     // We've already visited this node.
    //     if(newRoot == prev) return;

    //     /* 
    //     New height of current node is the height of 2nd max
    //     plus 1 edge connecting to it (since there is only one 1st max)
    //     */
    //     height[current] = 1 + secondMaxHeight;

    //     /* 
    //     height of new root node is either:
    //     - the node's original height (pointing downwards)
    //     - 1 edge + through previous root, with its updated height.
    //     */
    //     height[newRoot] = Math.max(height[current] + 1, height[newRoot]);

        
    //     /* 
    //     No need to go to this node since its height 
    //     is already greater than the minHeight.

    //     This new root and any of its children will have height greater
    //     than minHeight.
    //     */
    //     if(height[newRoot] > minHeight) return;
    //     if(height[newRoot] < minHeight) minHeightNodes.clear();

    //     // Updating minHeight value if needed.
    //     minHeight = Math.min(minHeight, height[newRoot]);

    //     minHeightNodes.add(newRoot);

    //     dfs2(subTreeWithMaxHeight.get(0), current);
    // }

    // ArrayList<ArrayList<Integer>> adj;
    // int[] height;

    // ArrayList<Integer> minHeightNodes;
    // int minHeight;

    // public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    //     adj = new ArrayList<>();

    //     for(int i = 0; i < n; i++) adj.add(new ArrayList<>());

    //     for(int[] edge: edges) {
    //         adj.get(edge[0]).add(edge[1]);
    //         adj.get(edge[1]).add(edge[0]);
    //     }

    //     height = new int[n];

    //     dfs1(0, -1);

    //     minHeight = height[0];

    //     minHeightNodes = new ArrayList<>();

    //     minHeightNodes.add(0);
        
    //     dfs2(0, -1);

    //     return minHeightNodes;
    // }

    /*
    ____OPTIMAL: BFS approach____

    Better in the sense that it is easier to understand.
    */

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1) return Arrays.asList(0);

        ArrayList<HashSet<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < n; i++) adj.add(new HashSet<>());

        int[] degrees = new int[n];

        // Calculating degrees of each vertex.
        for(int[] edge: edges) {
            degrees[edge[0]]++;
            degrees[edge[1]]++;

            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> leaves = new LinkedList<>();
        boolean[] visited = new boolean[n];

        // Adding leaves to a queue, and marking them as visited.
        for(int node = 0; node < n; node++) {
            if(degrees[node] == 1) {
                leaves.offer(node);
                visited[node] = true;
            }
        }

        /* 
        We are removing leaves level-by-level, since leaves won't
        responsible for minimum height trees, but rather
        nodes towards the center that have medium distance from
        all leaves.
        */
        while(leaves.size() > 1) {
            /* 
            There are just 2 leaves left which are conneted to
            each other (validated using degree check).
            
            Since they are left over, they are equidistant 
            from actual leaves in the tree, so both of them 
            are labels of minimum height trees.
            */
            if(leaves.size() == 2) {
                int firstLeaf = leaves.peek();
                int adjNode = adj.get(firstLeaf).iterator().next();

                if(degrees[adjNode] == 1) {
                    return Arrays.asList(firstLeaf, adjNode);
                }
            }

            int leavesInThisLevel = leaves.size();

            for(
                int removeLeaf = 0; 
                removeLeaf < leavesInThisLevel; 
                removeLeaf++
            ) {
                int leaf = leaves.poll();

                // Reducing degree of the node connected to this leaf
                int adjNode = adj.get(leaf).iterator().next();
                degrees[adjNode]--;
                
                // Removing the leaf from the nodes' adjacency list as well
                adj.get(adjNode).remove(leaf);

                // If the node has now become a leaf, we add it to queue.
                if(degrees[adjNode] == 1 && !visited[adjNode]) {
                    leaves.offer(adjNode);
                    visited[adjNode] = true;
                }
            }

        }

        return Arrays.asList(leaves.poll());
    }
}