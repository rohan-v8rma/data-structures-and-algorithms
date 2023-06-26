// https://practice.geeksforgeeks.org/problems/topological-sort/0

class Solution 
{
    static void dfs(
        int src, 
        Stack<Integer> orderingStack, 
        boolean[] visited, 
        ArrayList<ArrayList<Integer>> adj
    ) {
        visited[src] = true;
        
        for(int adjacentNode: adj.get(src)) {
            if(!visited[adjacentNode]) {
                dfs(adjacentNode, orderingStack, visited, adj);
            }
        }
        
        /* 
        Before backtracking, we insert the current vertex into a stack. 
        
        This way in the base case, when the vertex has no adjacent vertices, 
        it is inserted in the end of the topological order, because we will
        be popping the elements of this stack and inserting the top element
        starting from the beginning of the sorted array.
        
        This is in accordance with the principles of topological sort.
        */
        orderingStack.push(src);
        return;
    }
    
    //Function to return list containing vertices in Topological order. 
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        Stack<Integer> orderingStack = new Stack<>();
        
        boolean[] visited = new boolean[V];   
        
        for(int src = 0; src < V; src++) {
            if(!visited[src]) {
                dfs(src, orderingStack, visited, adj);
            }
        }
        
        int[] topSort = new int[V];
        
        int index = 0;
        while(!orderingStack.isEmpty()) {
            topSort[index++] = orderingStack.pop();
        }
        
        // add your code here
        return topSort;
    }
}