// https://practice.geeksforgeeks.org/contest/gfg-weekly-160-rated-contest/problems

class Solution {
    /*
    ____Binary search solution____
    
    TC: (N ^ 3) * ( log(N) ^ 3)   
    */
    // static int[] find(ArrayList<Integer> charIndices, int startIdx) {
    //     int start = 0;
    //     int end = charIndices.size();
        
    //     int closest = charIndices.size();
        
    //     while(start <= end) {
    //         int mid = (start + end) / 2;
            
    //         if(mid >= charIndices.size()) {
    //             end = mid - 1;
    //             continue;
    //         }
            
    //         int indexValue = charIndices.get(mid);
            
    //         if(indexValue >= startIdx) {
    //             closest = Math.min(closest, mid);
    //             end = mid - 1;
    //         }
    //         else {
    //             start = mid + 1;
    //         }
    //     }
        
    //     return new int[]{closest, charIndices.size() - 1};
    // }
    
    // static int mod = 1000000007;
    
    // static int add(int a, int b) {
    //     return (a + b) % mod;
    // }
    
    // public static int geekCount(String s) {
    //     ArrayList<Integer> gIdx = new ArrayList<>();
    //     ArrayList<Integer> eIdx = new ArrayList<>();
    //     ArrayList<Integer> kIdx = new ArrayList<>();
        
    //     for(int i = 0; i < s.length(); i++) {
    //         switch(s.charAt(i)) {
    //             case 'g':
    //                 gIdx.add(i);
    //                 break;
    //             case 'e':
    //                 eIdx.add(i);
    //                 break;
    //             case 'k':
    //                 kIdx.add(i);
    //                 break;
    //             default:
    //                 break;
    //         }
    //     }
        
    //     // System.out.println(gIdx);
    //     // System.out.println(eIdx);
    //     // System.out.println(kIdx);
        
    //     int result = 0;
        
    //     for(int gI: gIdx) {
    //         int[] eRange1 = find(eIdx, gI + 1);
            
    //         // System.out.println(Arrays.toString(eRange1));
            
    //         for(int eI1 = eRange1[0]; eI1 <= eRange1[1]; eI1++) {
            
    //             // System.out.println(eIdx.get(eI1));
    //             int[] eRange2 = find(eIdx, eIdx.get(eI1) + 1);
                
    //             for(int eI2 = eRange2[0]; eI2 <= eRange2[1]; eI2++) {
                    
    //                 int[] kRange = find(kIdx, eIdx.get(eI2) + 1);
                    
    //                 result = add(result, kRange[1] - kRange[0] + 1);
    //             }
    //         }
    //     }
        
    //     return result;
    // }
    
    /*
    ____OPTIMAL SOLUTION____
    
    TC: O(N)
    */
    
    static int mod = 1000000007;
    
    static int add(int a, int b) {
        return (a + b) % mod;
    }
    
    public static int geekCount(String s) {
        int g = 0; // How many `g` have been seen
        int ge = 0; // How many `ge` have been seen
        int gee = 0; // How many `gee` have been seen
        int geek = 0; // How many `geek` have been seen
        
        for(char c: s.toCharArray()) {
            switch(c) {
                case 'g':
                    g = add(g, 1);
                    break;
                case 'e':
                    /*
                    When we see an `e`, 
                    
                    1. The number of `ge` seen increases by count of `g`.
                    - This is because suppose 3 `g`s have been seen till now
                    - So an `e`, will mean 3 new pairs of `ge` are formed.
                    
                    2. The number of `gee` seen increases by count of `gee`.
                    - This is because suppose 3 `ge`s have been seen till now
                    - So an `e`, will mean 3 new pairs of `gee` are formed.
                    */
                    gee = add(gee, ge);
                    ge = add(ge, g); 
                    break;
                case 'k':
                    // Similar logic as above.
                    geek = add(geek, gee);
                    break;
                default:
                    break;
            }
        }
        
        return geek;
    }
}
