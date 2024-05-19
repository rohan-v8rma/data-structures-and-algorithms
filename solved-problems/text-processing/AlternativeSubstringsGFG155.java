// https://practice.geeksforgeeks.org/contest/gfg-weekly-155-rated-contest/problems

class Solution {
    public static long nSum(long n) {
        return (n * (n + 1)) / 2;
    }
    
    public static long maxAlternatingSubstring(int N, String S) {
        // code here
        long subCt = 0;
        
        ArrayList<Integer> secLens = new ArrayList<>();
        
        int curLen = 1;
        int last = S.charAt(0);
        
        for(int i = 1; i < S.length(); i++) {
            if(S.charAt(i) == last) {
                secLens.add(curLen);
                curLen = 1;
            }
            else {
                last = '1' - last + '0';
                curLen++;
            }
        }
        
        secLens.add(curLen);
        
        if(secLens.size() == 1) {
            N += 1;
            subCt = nSum(N);
        }
        else {
            int combIdxStart = 0;
            int val1 = secLens.get(0);
            int val2 = secLens.get(1);
            
            for(int i = 1; i < secLens.size() - 1; i++) {
                int cur1 = secLens.get(i);
                int cur2 = secLens.get(i + 1);
                
                if(
                    (nSum(cur1 + cur2 + 1) + nSum(val1) + nSum(val2))
                    > (nSum(val1 + val2 + 1) + nSum(cur1) + nSum(cur2))
                )
                {
                    val1 = cur1;
                    val2 = cur2;
                    combIdxStart = i;
                }
            }
            
            for(int i = 0; i < secLens.size(); i++) {
                if(i == combIdxStart) {
                    subCt += nSum(secLens.get(i++) + secLens.get(i) + 1);
                }
                else {
                    subCt += nSum(secLens.get(i));
                }
            }
            
        }
        
        // System.out.println(secLens);
        
        
        return subCt;
    }
}
