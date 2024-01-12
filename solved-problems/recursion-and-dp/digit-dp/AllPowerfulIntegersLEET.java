// https://leetcode.com/problems/powerful-integers

class Solution {
    /*_____* Recursive Memoization method (Self Developed) *___*/

    // static boolean[][] alreadyChecked;

    static int getMaxPow(int n, int bound) {
        // Log can't be calculated in these cases.
        if(n == 1 || bound == 0) {
            return 0;
        }

        // The power of `n` which will get it closest to bound without exceeding it.
        return (int)(Math.log(bound) / Math.log(n));
    }

    
    // static void checkIfPowerful(
    //     int x, 
    //     int y, 
    //     int bound, 
    //     Set<Integer> powerfulList, 
    //     int xPow, 
    //     int yPow
    // ) {
    //     if(alreadyChecked[xPow][yPow]) {
    //         return;
    //     }

    //     int result = (int)(Math.pow(x, xPow) + Math.pow(y, yPow));

    //     if(result <= bound) { 
    //         powerfulList.add(result);
    //         alreadyChecked[xPow][yPow] = true;

    //         if(x != 1 && xPow + 1 < alreadyChecked.length) {
    //             checkIfPowerful(x, y, bound, powerfulList, xPow + 1, yPow);
    //         }
            
    //         if(y != 1 && yPow + 1 < alreadyChecked[0].length) {
    //             checkIfPowerful(x, y, bound, powerfulList, xPow, yPow + 1);
    //         }
    //     }
    // }

    // public List<Integer> powerfulIntegers(int x, int y, int bound) {
    //     Set<Integer> powerfulList = new HashSet<>();

    //     // Max power of X, which won't exceed bound.
    //     int maxPowOfX = getMaxPow(x, bound);
    //     // Max power of Y, which won't exceed bound.
    //     int maxPowOfY = getMaxPow(y, bound);

    //     // This is the DP matrix for preventing repeated recursion calls.
    //     alreadyChecked = new boolean[maxPowOfX + 1][maxPowOfY + 1];

    //     checkIfPowerful(x, y, bound, powerfulList, 0, 0);

    //     return new ArrayList<>(powerfulList);
    // }

    /*_____* Iterative DP method (OPTIMAL) *___*/
    
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> powerfulList = new HashSet<>();

        // Max power of X, which won't exceed bound.
        int maxPowOfX = getMaxPow(x, bound);
        // Max power of Y, which won't exceed bound.
        int maxPowOfY = getMaxPow(y, bound);

        for(int powX = 0; powX <= maxPowOfX; powX++) {
            for(int powY = 0; powY <= maxPowOfY; powY++) {
                int result = (int)(Math.pow(x, powX) + Math.pow(y, powY));
                if(result <= bound) {
                    powerfulList.add(result);
                }
            }    
        }
        
        return powerfulList.stream().collect(Collectors.toList());
    }
}