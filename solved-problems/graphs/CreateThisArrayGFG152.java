// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-152/problems
// TODO: Make notes

class Solution{
    public int[] createArray(int M, int N, int Requirements[][]){
        // BRUH, this is top sort.
        
        int[] array = new int[N];
        
        int[] degrees = new int[N];
    
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        for(int i = 0; i < N; i++) adj.add(new ArrayList<>());
        
        for(int[] Requirement: Requirements) {
            adj.get(Requirement[0]).add(Requirement[1]);
            degrees[Requirement[1]]++;
        }
        
        int val = 1;
        
        int visited = 0;
        
        while(visited != N) {
            
            ArrayList<Integer> zeroDegNodes = new ArrayList<>();
            
            for(int i = 0; i < N; i++) {
                if(degrees[i] == 0) {
                    zeroDegNodes.add(i);
                    visited++;
                }
            }

            // Cycle present
            if(zeroDegNodes.size() == 0) return new int[N];
            
            for(int zNode: zeroDegNodes) {
                array[zNode] = val;
                degrees[zNode] = -1;
                    
                for(int adjNode: adj.get(zNode)) degrees[adjNode]--;
            }
            
            val++;
        }
        
        
        return array;
    }
}