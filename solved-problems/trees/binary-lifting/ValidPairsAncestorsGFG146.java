// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-146/problems
// TODO: Implement space optimized binary lifting approach

class Solution {
    /*____BASIC BINARY LIFTING APPROACH____*/
    
    static int getValidAncestorCt(int[][][] ancestors, int node, int nodeVal) {
        int totalSum = 0;
        
        int totalCt = 0;
        
        while(
            ancestors[1][node][0] != 0
            && ancestors[1][node][1] + totalSum <= nodeVal
        ) {
            int level = 1;
            int bestLevelPossible = 0;
            
            while(
                ancestors[level][node][0] != 0
                && ancestors[level][node][1] + totalSum <= nodeVal
            ) {
                bestLevelPossible = level++;
            }
            
            if(bestLevelPossible == 0) break;
            
            totalSum += ancestors[bestLevelPossible][node][1];
            
            node = ancestors[bestLevelPossible][node][0];
            
            totalCt += Math.pow(2, bestLevelPossible - 1);
        }
        
        // System.out.printf("%d, %d\n", node, totalCt);
        
        return totalCt;
    }
    
    public static void makeTree(
        int currentNode, 
        int prevNode,
        ArrayList<ArrayList<int[]>> adjList, 
        int[][][] ancestors
    ) {
        for(int[] adj: adjList.get(currentNode)) {
            if(adj[0] != prevNode) {
                ancestors[1][adj[0]][0] = currentNode;
                ancestors[1][adj[0]][1] = adj[1];
                
                makeTree(adj[0], currentNode, adjList, ancestors);
            }
        }
    }
    
    public static long validPairs(int n, int[] val, int[][] edges) {
        // code here
        int[][][] ancestors = new int[15][n + 1][2];
        
        ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();
        
        for(int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        
        for(int[] edge: edges) {
            adjList.get(edge[0]).add(new int[]{edge[1], edge[2]});
            adjList.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
        
        // Getting first level ancestors from the adjList, considering root is at 1.
        makeTree(1, 0, adjList, ancestors);
        
        // Creating binary lifting matrix.
        for(int level = 2; level < 15; level++) {
            for(int i = 1; i <= n; i++) {
                
                int[] firstAncestor = ancestors[level - 1][i];
                int[] secondAncestor = ancestors[level - 1][firstAncestor[0]];
                
                if(secondAncestor[0] == 0) continue;
                
                ancestors[level][i][0] = secondAncestor[0];
                ancestors[level][i][1] = firstAncestor[1] + secondAncestor[1];
            }
        }
        
        long validPairs = 0;
        
        for(int i = 1; i <= n; i++) {
            validPairs += getValidAncestorCt(ancestors, i, val[i - 1]);
        }
        
        return validPairs;
    }
}
        
