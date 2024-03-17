// https://leetcode.com/problems/find-the-sum-of-the-power-of-all-subsequences/

class Solution {
    /*____UTILITY FUNCTIONS____*/
    
    static int mod = 1000000007;
    
    static int sum(int a, int b) {
        return (a % mod + b % mod) % mod;
    }
    
    static int mul(long a, long b) {
        return (int)((a * b) % mod);
    }
    
    static int pow(int a, int b) {
        int result = 1;
        
        while(b > 0) {
            if((b & 1) == 1) result = mul(result, a);
            
            a = mul(a, a);
            b >>= 1;
        }
        
        return result;
    }

    
    /*____BASIC APPROACH: Keeping includeCt in recursive function____*/


    // static  int getKey(int idx, int includeCt, int sum) {
    //     return sum * 1000000 + includeCt * 1000 + idx;
    // }

    // public int recurse(int idx, int sum, int includeCt, int[] nums, int k) {
    //     int power = 0;
    //     int key = getKey(idx, includeCt, sum);  
        
    //     if(sum == k) {
    //         power = pow(2, nums.length - includeCt);
    //     }
    //     else {
    //         if(idx >= nums.length) return 0;
        

    //         if(map.containsKey(key)) {
    //             return map.get(key);
    //         }

    //         if(sum < k) {
    //             power = sum(power, recurse(idx + 1, sum + nums[idx], includeCt + 1, nums, k));
    //             power = sum(power, recurse(idx + 1, sum, includeCt, nums, k));
    //         }
    //     }
        
        
    //     map.put(key, power);
        
    //     return power;
    // }
    
    // static HashMap<Integer, Integer> map;
    
    // public int sumOfPower(int[] nums, int k) {
    //      map = new HashMap<>();
        
    //     return recurse(0, 0, 0, nums, k);
    // }


    /*____OPTIMIZED APPROACH: Getting includeCt data from alternative sources____*/


    static  int getKey(int idx, int sum) {
        return sum * 1000 + idx;
    }

    public int recurse(int idx, int sum, int[] nums, int k) {
        int power = 0;
        int key = getKey(idx, sum);  
        
        if(sum == k) {
            /* 
            Subsequences where the indices after the point where sum became k
            are either INCLUDED or NOT INCLUDED
            */
            power = pow(2, nums.length - idx);
        }
        else {
            if(idx >= nums.length) return 0;
        

            if(map.containsKey(key)) {
                return map.get(key);
            }

            if(sum < k) {
                power = sum(power, recurse(idx + 1, sum + nums[idx], nums, k));

                /* 
                The case for when we don't include current element in subsequence

                In case 2^(n - i) is returned, we make it 2^(n - includeCt),
                by multiplying by 2 and accounting for the 2 versions of each
                subsequence which HAD or DIDN't HAVE current element.
                */
                power = sum(power, mul(2, recurse(idx + 1, sum, nums, k)));
            }
        }
        
        map.put(key, power);
        
        return power;
    }
    
    static HashMap<Integer, Integer> map;
    
    public int sumOfPower(int[] nums, int k) {
         map = new HashMap<>();
        
        return recurse(0, 0, nums, k);
    }
}