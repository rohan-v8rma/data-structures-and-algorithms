// https://practice.geeksforgeeks.org/problems/topological-sort/0

class Solution
{
    static void dfs(int src, AnswerObj ans, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
        visited[src] = true;
        
        for(int adjacentNode: adj.get(src)) {
            if(!visited[adjacentNode]) {
                dfs(adjacentNode, ans, visited, adj);
            }
        }
        
        /* 
        Before backtracking, we insert the current vertex. 
        
        This way in the base case, when the vertex has no adjacent vertices, 
        it is inserted in the end of the topological order.
        
        This is in accordance with the principles of topological sort.
        */
        ans.insert(src);
        return;
    }
    
    //Function to return list containing vertices in Topological order. 
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        AnswerObj answer = new AnswerObj(V);
        
        boolean[] visited = new boolean[V];   
        
        for(int src = 0; src < V; src++) {
            if(!visited[src]) {
                dfs(src, answer, visited, adj);
            }
        }
        
        // add your code here
        return answer.topSortedVertices;
    }
}

class AnswerObj {
    int[] topSortedVertices;
    int currentIndex;
    
    AnswerObj(int V) {
        topSortedVertices = new int[V];
        currentIndex = V - 1;
    }
    void insert(int vertex) {
        topSortedVertices[currentIndex--] = vertex;
        return;
    } 
}