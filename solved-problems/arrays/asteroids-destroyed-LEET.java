// https://leetcode.com/problems/destroying-asteroids

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        // We sort the asteroids so that planet gets easiest to destroy asteroids first.
        Arrays.sort(asteroids);

        // The sum is stored in long since asteroid masses are large (10^5 * 10^5).
        long preSum = mass;

        for(int asteroid: asteroids) {
            if(preSum < asteroid) {
                return false;
            }

            preSum += asteroid;
        }

        return true;
    }
}