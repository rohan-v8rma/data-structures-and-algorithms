// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-142/problems
// TODO: make notes

import java.util.*;

class Solution {
    static int[][] dp;
    
    public static int[] getCtOf2sAnd3s(
        int prevNode,
        int currentNode, 
        int[] Values, 
        ArrayList<ArrayList<Integer>> adjList
    ) {
        if(dp[currentNode][2] == 1) {
            return new int[]{
                dp[currentNode][0], 
                dp[currentNode][1]
            };
        }
        
        int[] ctOf2sAnd3s = new int[2];
       
        if(Values[currentNode] > 1) {
            ctOf2sAnd3s[Values[currentNode] - 2]++;
        }
        
        for(int adjNode: adjList.get(currentNode)) {
            if(adjNode != prevNode) {
                int[] adjContent = getCtOf2sAnd3s(currentNode, adjNode, Values, adjList);
                
                ctOf2sAnd3s[0] += adjContent[0];
                ctOf2sAnd3s[1] += adjContent[1];
            }
        }
        
        dp[currentNode][2] = 1;
        dp[currentNode][0] = ctOf2sAnd3s[0];
        dp[currentNode][1] = ctOf2sAnd3s[1];
        
        return ctOf2sAnd3s;
    }
    
    public static void dfs(
        int prevNode,
        int currentNode,
        int[] Values,
        ArrayList<ArrayList<Integer>> adjList
    ) {
        for(int adj: adjList.get(currentNode)) {
            if(adj != prevNode) {
                int[] adjContent = getCtOf2sAnd3s(currentNode, adj, Values, adjList);
                
                if(
                    (adjContent[0] > 0 && ctOf2s - adjContent[0] > 0)
                    &&
                    (adjContent[1] > 0 && ctOf3s - adjContent[1] > 0)
                ) {
                    ctOfDivisions++;
                }
                
                dfs(currentNode, adj, Values, adjList);
            }
        }
    }
    
    static int ctOf2s;
    static int ctOf3s;
    static int ctOfDivisions;
    
    public static int splittingEdges(int N, int[] Values, int[][] Edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(2 * N);
        
        dp = new int[N + 1][3];
        
        for(int i = 0; i < N; i++) adjList.add(new ArrayList<>());
        
        boolean[] isNotRoot = new boolean[N];
        
        int root = 0;
        ctOf2s = 0;
        ctOf3s = 0;
        
        for(int node = 0; node < N; node++) {
            if(adjList.get(node).size() == 1) {
                root = node;
            }
            
            switch(Values[node]) {
                case 2: ctOf2s++; break;
                case 3: ctOf3s++; break;
                default: ;
            }
        }
        
        for(int[] Edge: Edges) {
            
            isNotRoot[Edge[1]] = true;
            
            adjList.get(Edge[0]).add(Edge[1]);
            adjList.get(Edge[1]).add(Edge[0]);
        }
        
        ctOfDivisions = 0;
        
        dfs(N, root, Values, adjList);
        
        return ctOfDivisions;
    }
}
        
