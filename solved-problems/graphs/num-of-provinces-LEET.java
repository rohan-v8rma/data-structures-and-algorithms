// Problem link: https://leetcode.com/problems/number-of-provinces/submissions/

class Solution {
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
    
    void executeDFS(int vertex, int[][] isConnected, boolean[] visited) {
        visited[vertex] = true;    
        for(int toVertex = 0; toVertex < visited.length; toVertex++) {
            if(visited[toVertex] == false && isConnected[vertex][toVertex] == 1) {
                executeDFS(toVertex, isConnected, visited);
            }
        }
    }
    
    public int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        
        int numOfProvinces = 0;
        
        for(int index = 0; index < visited.length; index++) {
            if(!visited[index]) {
                executeDFS(index, isConnected, visited);
                numOfProvinces++;
            }
        }
        
        return numOfProvinces;
    }
}