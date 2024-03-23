// https://leetcode.com/problems/kth-ancestor-of-a-tree-node/description/

class TreeAncestor {
    
    // BRUTEFORCE (Leads to TLE due to (5*10^4)^2 )
    
    // int n;
    // int[] parent;

    // public TreeAncestor(int n, int[] parent) {
    //     this.n = n;
    //     this.parent = parent;
    // }
    
    // public int getKthAncestor(int node, int k) {
    //     int ancestor = node;
    //     int count = 0;
        
    //     while(++count <= k) {
    //         ancestor = parent[ancestor];
            
    //         if(ancestor == -1) {
    //             break;
    //         }
    //     }

    //     return ancestor;
    // }

    /* 
    OPTIMAL: Uses binary lifting.

    See notebook for full concept if needed.
    */

    int n;
    int[] parent;
    int[][] binLifts;

    public TreeAncestor(int n, int[] parent) {
        this.n = n;
        this.parent = parent;
        
        /*
        If there are n nodes, we can perform any binary lift
        that is from 2^0 to 2^k, where 2^k <= n .

        n  because if 8 nodes are there in skewed tree, the bottom node 
        can at max go up to 7th ancestor. But, 8th ancestor is -1.
        */
        int maxPossibleBinLift = (int)(Math.log(n)/Math.log(2));
        /*
        k + 1 since binary lifts are from 2^0 to 2^k.
        */
        binLifts = new int[maxPossibleBinLift + 1][n];

        for(int node = 0; node < n; node++) {
            /*
            This is for getting the first ancestor of node,
            i.e. a binary lift of 2^0 meaning 1.
            */
            binLifts[0][node] = parent[node];
        }

        int liftAncestor;

        for(int currBinLift = 1; currBinLift <= maxPossibleBinLift; currBinLift++) {
            /*
            currBinLift of 1, stands for 2^1
            */
            for(int node = 0; node < n; node++) {    
                
                // First lift
                liftAncestor = binLifts[currBinLift - 1][node];

                if(liftAncestor != -1) {
                    // Second lift
                    liftAncestor = binLifts[currBinLift - 1][liftAncestor];
                    
                    /*
                    Performing 2 lifts using previous level to double the 
                    lift performed.

                    If previous lift level was 4 i.e., 2^4 = 16, twice of that
                    lift is 32 which is 2^5.
                    */    
                }

                binLifts[currBinLift][node] = liftAncestor;
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        int ancestor = node;
        int count = 0;
        
        int nextLift;

        while(k != 0) {
            // nextLift = (int)(Math.log(k)/Math.log(2));
            // Not using log to calculate nextLift improved runtime from 81ms to 71ms
            nextLift = 0;
            int copyOfK = k;
            while (copyOfK != 1) {
                copyOfK >>= 1;
                nextLift++;
            }

            // k -= Math.pow(2, nextLift);
            /* 
            Instead of using above function, use below. This does k -= 2^nextLift.
            Improved runtime from 94ms to 81ms.
            */
            k -= 1 << nextLift;
            
            ancestor = binLifts[nextLift][ancestor];

            if(ancestor == -1) {
                break;
            }
        }


        return ancestor;
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */