// https://practice.geeksforgeeks.org/contest/gfg-weekly-155-rated-contest/problems

/*
 * Effectively we are matching the characters of a string with the characters of all strings after it
 ? Which is why it is a permutation and combination problem, due to lack of a better category 
*/

class Solution {
    public static void updateCharCts(String s, ArrayList<int[]> charCts) {
        char[] sArr = s.toCharArray();
        
        for(int i = 0; i < sArr.length; i++) {
            if(charCts.size() <= i) {
                charCts.add(new int[26]);
            }
            
            charCts.get(i)[sArr[i] - 'a']++;
        }
    }
    
    public static int countSameChars(String s, ArrayList<int[]> charCts) {
        char[] sArr = s.toCharArray();
        
        int sameChars = 0;
        
        for(int i = 0; i < Math.min(sArr.length, charCts.size()); i++) {
            sameChars += charCts.get(i)[sArr[i] - 'a'];
        }
        
        return sameChars;
    }
    
    public static int[] matchingCnt(int n, String[] X) {
        int[] result = new int[X.length];
        
        ArrayList<int[]> charCts = new ArrayList<>();
        
        updateCharCts(X[n - 1], charCts);
        
        for(int i = n - 2; i >= 0; i--) {
            result[i] = countSameChars(X[i], charCts);
            updateCharCts(X[i], charCts);
        }
    
        return result;    
    }
}
