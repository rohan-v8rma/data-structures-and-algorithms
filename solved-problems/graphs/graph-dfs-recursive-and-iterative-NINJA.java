// https://www.codingninjas.com/studio/problems/dfs-traversal_630462

public class Solution {
    
    // Recursive depth-first search on graph
    // public static ArrayList<ArrayList<Integer>> depthFirstSearch(int v, int e, ArrayList<ArrayList<Integer>> edges) {
    //     boolean[] visited = new boolean[v];

    //     // Building adjacency list.
    //     ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    //     for(int startingV = 0; startingV < v; startingV++) {
    //         adjList.add(new ArrayList<>());
    //     }

    //     int from, to;

    //     for(ArrayList<Integer> edge: edges) {
    //         from = edge.get(0);
    //         to = edge.get(1);
    //         adjList.get(from).add(to);
    //         adjList.get(to).add(from);
    //     }

    //     ArrayList<ArrayList<Integer>> dfsTraversals = new ArrayList<>();
    //     ArrayList<Integer> dfsTraversal;

    //     for(int vertex = 0; vertex < v; vertex++) {
    //         if(!visited[vertex]) {
    //             dfsTraversal = new ArrayList<>();
    //             dfs(vertex, adjList, dfsTraversal, visited);
    //             dfsTraversals.add(dfsTraversal);
    //         }
    //     }

    //     return dfsTraversals;
    // }

    // static void dfs(int node, ArrayList<ArrayList<Integer>> adjList, ArrayList<Integer> dfsTraversal, boolean[] visited) {
    //     dfsTraversal.add(node);
    //     visited[node] = true;

    //     for(int adjNode: adjList.get(node)) {
    //         if(!visited[adjNode]) {
    //             dfs(adjNode, adjList, dfsTraversal, visited);
    //         }
    //     }
    // }

    // Iterative depth-first search on a graph
    public static ArrayList<ArrayList<Integer>> depthFirstSearch(int v, int e, ArrayList<ArrayList<Integer>> edges) {
        boolean[] visited = new boolean[v];

        // Building adjacency list.
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for(int startingV = 0; startingV < v; startingV++) {
            adjList.add(new ArrayList<>());
        }

        int from, to;

        for(ArrayList<Integer> edge: edges) {
            from = edge.get(0);
            to = edge.get(1);
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        ArrayList<ArrayList<Integer>> dfsTraversals = new ArrayList<>();
        ArrayList<Integer> dfsTraversal = null;

        Deque<StackObj> stack = new ArrayDeque<>();

        // Iterative DFS.
        for(int startingV = 0; startingV < v; startingV++) {
            if(!visited[startingV]) {
                visited[startingV] = true;
                dfsTraversal = new ArrayList<>();
                dfsTraversal.add(startingV);

                ArrayList<Integer> adjNodes = adjList.get(startingV);
                
                // If the node has adjacent nodes to process, we had its stack object to stack.
                if(!adjNodes.isEmpty()) {
                    stack.push(new StackObj(adjNodes));
                }
                
            }            

            // Visiting all nodes reachable from the starting vertex.
            while(!stack.isEmpty()) {
                StackObj top = stack.peek();

                int adjVertex = top.adjNodes.get(top.index++);

                // No more adjacent vertices.
                if(top.index == top.adjNodes.size()) {
                    stack.pop();
                }

                if(!visited[adjVertex]) {
                    visited[adjVertex] = true;
                    dfsTraversal.add(adjVertex);
                    stack.push(new StackObj(adjList.get(adjVertex)));
                }
            }

            if(dfsTraversal != null) {
                dfsTraversals.add(dfsTraversal);
                dfsTraversal = null;
            }
        }

        return dfsTraversals;

    }

    static class StackObj {
        ArrayList<Integer> adjNodes;
        int index;

        StackObj(ArrayList<Integer> _adjNodes) {
            adjNodes = _adjNodes;
            index = 0;
        }
    }
}