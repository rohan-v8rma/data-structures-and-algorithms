// https://leetcode.com/problems/minimum-cost-for-cutting-cake-ii
// TODO: Make notes

class Solution {
    /*
    ____BRUTEFORCE____

    TC: O( (M + N)! )
    */
    // long cutThisBlock(int minR, int maxR, int minC, int maxC, int[] hCut, int[] vCut) {
    //     if(minR == maxR && minC == maxC) return 0;

    //     long cost = Long.MAX_VALUE;

    //     for(int r = minR; r < maxR; r++) {
    //         cost = Math.min(
    //             cost,
    //             hCut[r] 
    //             + cutThisBlock(minR, r, minC, maxC, hCut, vCut)
    //             + cutThisBlock(r + 1, maxR, minC, maxC, hCut, vCut)
    //         );
    //     }

    //     for(int c = minC; c < maxC; c++) {
    //         cost = Math.min(
    //             cost,
    //             vCut[c] 
    //             + cutThisBlock(minR, maxR, minC, c, hCut, vCut)
    //             + cutThisBlock(minR, maxR, c + 1, maxC, hCut, vCut)
    //         );
    //     }

    //     // System.out.printf("minR: %d, maxR: %d, minC: %d, maxC: %d, cost: %d\n", minR, maxR, minC, maxC, cost);
    //     return cost;
    // }

    // public long minimumCost(int m, int n, int[] hCut, int[] vCut) {
    //     return cutThisBlock(0, m - 1, 0, n - 1, hCut, vCut);
    // }

    /*
    ___OPTIMAL (GREEDY)____

    We simply take the most expensive cut as soon as possible,
    so that we incur less additional cost for the other cuts.

    Consider the cost h and v of continuous cuts in different direction.
    - If cut in horizontal line first, we will pay one more v
    - If cut in vertical line first, we will pay one more h

    So, it doesn't matter if their are already 4 columns and 3 rows, or
    whatever. The difference in cost is only: 
    - if we cut horizontal first, 4 * h + (3 + 1) * v
    - if we cut vertical first, (4 + 1) * h + 3 * v

    So, we can simply greedily pick the cut we want to make, as the difference
    would only be according to the actual cost of the 2 cuts.
    */
    public long minimumCost(int m, int n, int[] hCut, int[] vCut) {
        long cost = 0;

        Arrays.sort(hCut);
        Arrays.sort(vCut);

        int vMul = 1;
        int hMul = 1;

        int vIdx = vCut.length - 1;
        int hIdx = hCut.length - 1;

        while(hIdx >= 0 && vIdx >= 0) {
            if(hCut[hIdx] > vCut[vIdx]) {
                cost += hMul * hCut[hIdx--];
                /* 
                We made a horizontal cut first, so now all vertical cuts
                will have to be made one additional time.

                Suppose we made a horizontal cut like this:
                - - - 
                - - -

                - - -

                Now, vertical cut after first column, will have to be made
                both above and below.
                */
                vMul++;
            }
            else {
                cost += vMul * vCut[vIdx--];
                hMul++;
            }
        }

        while(hIdx >= 0) {
            cost += hMul * hCut[hIdx--];    
        }

        while(vIdx >= 0) {
            cost += vMul * vCut[vIdx--];    
        }

        return cost;
    }
}