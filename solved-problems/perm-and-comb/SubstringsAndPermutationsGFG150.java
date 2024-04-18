// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-150/problems
// TODO: Make notes

class Solution {
    public static ArrayList<Integer> substringsAndPermutations(int n, int m, String s, String[] arr) {
        ArrayList<ArrayList<Integer>> indices = new ArrayList<>();
        
        for(int i = 0; i < 10; i++) {
            indices.add(new ArrayList<>());
        }
        
        char[] sArr = s.toCharArray();
        
        for(int i = 0; i < n; i++) {
            char c = sArr[i];
            
            indices.get(c - '0').add(i);
        }
        
        ArrayList<Integer> op = new ArrayList<>();
        
        int[] ct = new int[10];
        
        for(int j = 0; j < m; j++) {
            Arrays.fill(ct, 0);
            
            for(char c: arr[j].toCharArray()) {
                ct[c - '0']++;
            }
            
            int maxIdx = 0;
            
            for(int digit = 0; digit < 10; digit++) {
                if(ct[digit] != 0) {
                    ArrayList<Integer> digitIndices = indices.get(digit);
                    /* 
                    Seeing if the required count of digits can be met.
                    
                    We are effectively seeing if the number of indices 
                    at which the required digit was seen is more than the
                    required count of the required digit.
                    */
                    if(digitIndices.size() < ct[digit]) {
                        maxIdx = -2;
                        break;
                    }
                    
                    maxIdx = Math.max(maxIdx, digitIndices.get(ct[digit] - 1));
                }
            }
            
            op.add(maxIdx + 1);
        }
        
        
        return op;
    }
}

    // // EXTENDED SOLUTION THAT WORKS FOR ALL CHARACTERS, NOT just 0-9
    // public static ArrayList<Integer> substringsAndPermutations(int n, int m, String s, String[] arr) {
    //     HashMap<Character, ArrayList<Integer>> indices = new HashMap<>();
        
    //     char[] sArr = s.toCharArray();
        
    //     for(int i = 0; i < n; i++) {
    //         char c = sArr[i];
            
    //         indices.putIfAbsent(c, new ArrayList<>());
            
    //         indices.get(c).add(i);
    //     }
        
    //     ArrayList<Integer> op = new ArrayList<>();
        
    //     ArrayList<Integer> blank = new ArrayList<>();
        
    //     HashMap<Character, Integer> ct;
        
    //     for(int j = 0; j < m; j++) {
    //         ct = new HashMap<>();
            
    //         for(char c: arr[j].toCharArray()) {
    //             ct.put(c, ct.getOrDefault(c, 0) + 1);
    //         }
            
    //         int maxIdx = 0;
            
    //         for(Map.Entry<Character, Integer> entry: ct.entrySet()) {
    //             char c = entry.getKey();
    //             int cRequiredCt = entry.getValue();
                
    //             ArrayList<Integer> charIndices = indices.getOrDefault(c, blank);
    //             /* 
    //             Seeing if the required count of digits can be met.
                
    //             We are effectively seeing if the number of indices 
    //             at which the required digit was seen is more than the
    //             required count of the required digit.
    //             */
    //             if(charIndices.size() < cRequiredCt) {
    //                 maxIdx = -2;
    //                 break;
    //             }
                
    //             maxIdx = Math.max(maxIdx, charIndices.get(cRequiredCt - 1));
    //         }
            
    //         op.add(maxIdx + 1);
    //     }
        
        
    //     return op;
    // }
}