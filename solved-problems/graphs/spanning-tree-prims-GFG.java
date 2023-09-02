// https://practice.geeksforgeeks.org/problems/minimum-spanning-tree/1

class Solution {
    /* 
    Prim's algorithm using Priority Queue
    
    See notebook for TC analysis
    */
    static int spanningTreePQ(int V, int E, int edges[][]) {
        
        ArrayList<ArrayList<int[]>> adjEdges = new ArrayList<>();
        for(int node = 0; node < V; node++) {
            adjEdges.add(new ArrayList<>());
        }

        for(int[] edge: edges) {
            adjEdges.get(edge[0]).add(edge);
            adjEdges.get(edge[1]).add(edge);
        }

        boolean[] isIncluded = new boolean[V];

        PriorityQueue<int[]> smallestEdge = new PriorityQueue<>(
            (edge1, edge2) -> Integer.compare(edge1[2], edge2[2])
        );

        addAllPQ(adjEdges.get(0), smallestEdge);
        isIncluded[0] = true;

        int mstSum = 0;

        while(V != 1) {
            int[] nextEdge = smallestEdge.poll();
            int from = nextEdge[0];
            int to = nextEdge[1];

            if(!isIncluded[to]) {
                V--;
                mstSum += nextEdge[2];
                isIncluded[to] = true;
                addAllPQ(adjEdges.get(to), smallestEdge);
            }
            else if(!isIncluded[from]) {
                V--;
                mstSum += nextEdge[2];
                isIncluded[from] = true;
                addAllPQ(adjEdges.get(from), smallestEdge);
            }
        }

        return mstSum;

    }
    
    static void addAllPQ(ArrayList<int[]> edges, PriorityQueue<int[]> PQ) {
        for(int[] edge: edges) {
            PQ.offer(edge);
        }
    }
    
    /* 
    Prim's algorithm using TreeSet
    
    See notebook for TC analysis
    */
    static int spanningTree(int V, int E, int edges[][]){
        
        ArrayList<ArrayList<int[]>> adjEdges = new ArrayList<>();
        for(int node = 0; node < V; node++) {
            adjEdges.add(new ArrayList<>());
        }

        for(int[] edge: edges) {
            adjEdges.get(edge[0]).add(edge);
            adjEdges.get(edge[1]).add(edge);
        }

        boolean[] isIncluded = new boolean[V];

        TreeSet<int[]> smallestEdge = new TreeSet<>(
            (edge1, edge2) -> {
                int returnVal = Integer.compare(edge1[2], edge2[2]);
                
                if(returnVal == 0) {
                    returnVal = Integer.compare(edge1[1], edge2[1]);
                }
                
                if(returnVal == 0) {
                    return Integer.compare(edge1[0], edge2[0]);
                }
                
                return returnVal;
            }
        );

        smallestEdge.addAll(adjEdges.get(0));
        isIncluded[0] = true;

        int mstSum = 0;

        while(V != 1) {
            int[] nextEdge = smallestEdge.first();
            
            int from = nextEdge[0];
            int to = nextEdge[1];

            if(!isIncluded[to]) {
                V--;
                mstSum += nextEdge[2];
                isIncluded[to] = true;
                
                /* 
                It tries to add nextEdge again, but since it is already present 
                there is nothing added.
                
                However, log(E) is still taken for comparisons to reach nextEdge present in tree.
                */
                smallestEdge.addAll(adjEdges.get(to));
            }
            else if(!isIncluded[from]) {
                V--;
                mstSum += nextEdge[2];
                isIncluded[from] = true;
                /* 
                It tries to add nextEdge again, but since it is already present 
                there is nothing added.
                
                However, log(E) is still taken for comparisons to reach nextEdge present in tree.
                */
                smallestEdge.addAll(adjEdges.get(from));
            }
            
            /* 
            Since we remove this edge after the addAll execution, 
            we ensure this edge is NOT processed again a
            */
            smallestEdge.remove(nextEdge);
        }

        return mstSum;

    }
}