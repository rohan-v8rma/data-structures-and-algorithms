// https://practice.geeksforgeeks.org/problems/minimum-spanning-tree/1

class Solution {
    /*
    Kruskal's algorithm for finding MST
    */
    static int findParent(int vertex) {
        if(parent[vertex] == vertex) {
            return vertex;
        }
        
        // Path compression
        return parent[vertex] = findParent(parent[vertex]);
    }
    
    static boolean union(int u, int v) {
        int parentU = findParent(u);
        int parentV = findParent(v);
        
        /* 
        Including this edge will result in the formation of a cycle.
        
        So, return false to indicate that union is not possible
        */
        if(parentU == parentV) {
            return false;
        }
        
        int sizeU = size[parentU];
        int sizeV = size[parentV];
        
        if(sizeU >= sizeV) {
            parent[parentV] = parentU;
            size[parentU] += size[parentV];
        }
        else {
            parent[parentU] = parentV;
            size[parentV] += size[parentU];
        }
        
        return true;
    }

    static int[] parent;
    static int[] size;

    static int spanningTree(int V, int E, int edges[][]){
    
        parent = new int[V];
        Arrays.setAll(parent, idx -> idx);
        
        size = new int[V];
        Arrays.setAll(size, idx -> 1);
        
        // Sorting the edges according to edge weight.
        Arrays.sort(edges, (e1, e2) -> Integer.compare(e1[2], e2[2]));
        
        int mstSum = 0;
        
        // for(int[] edge: edges) {

        // }
        // Change to while-loop resulted in lot of unnecessary iterations being skipped.
        
        int edgeIdx = 0;
        while(V != 1) {
            int[] edge = edges[edgeIdx++];
            
            if(union(edge[0], edge[1])) {
                /* 
                Addition of each edge into MST results in 1 vertex being included in the MST.
                
                Which is why we subtract 1 from V, until V == 1; meaning all vertices included.
                
                We don't go till V == 0 because when we included first edge in MST, 2 vertices
                were included at once instead of just 1.
                */
                mstSum += edge[2];
                V--;
            }
        }
        
        
        return mstSum;
    }
}