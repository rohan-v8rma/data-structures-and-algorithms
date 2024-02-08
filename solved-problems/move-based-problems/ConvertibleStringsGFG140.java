// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-140

class Solution {
    static int getGcd(int a, int b) {
        if(a == 0) {
            return b;
        }
        
        if(a > b) {
            getGcd(b, a);
        }
        
        return getGcd(b % a, a);
    }
    
    // static int canBeMadeSame(String a1, String a2) {
    //     int[] a1Map = new int[26];
    //     int[] a2Map = new int[26];
        
    //     for(char a1Char: a1.toCharArray()) {
    //         a1Map[a1Char - 'a']++;
    //     }
        
    //     for(char a2Char: a2.toCharArray()) {
    //         a2Map[a2Char - 'a']++;
    //     }
        
    //     int excessCharacters = 0;
        
    //     ArrayList<Integer> lengthsOfShiftsReq = new ArrayList<>();
        
    //     for(int alphabet = 0; alphabet < 26; alphabet++) {
    //         excessCharacters += a1Map[alphabet];
            
    //         /* 
    //         Excess characters from previous/current alphabets in a1, 
    //         can't match the count of the alphabet in a2.
            
    //         So, return 0 to indicate a1 cannot be converted to a2.
    //         */
    //         if(excessCharacters < a2Map[alphabet]) {  
    //             return 0;
    //         }
    //         else {
    //             excessCharacters -= a2Map[alphabet];
    //         }
            
    //         // Some excess characters are left, that need to be shifted further.
    //         if(excessCharacters != 0) {
    //             lengthsOfShiftsReq.add(excessCharacters);
    //         }
    //     }
        
    //     // With chinese remainder theorem, gcd of any number with 0, gets the number.
    //     int gcdOfShiftLengths = 0;
        
    //     for(int shiftLength: lengthsOfShiftsReq) {
    //         gcdOfShiftLengths = getGcd(shiftLength, gcdOfShiftLengths);
            
            
    //         // GCD cannot be 1, since k has to be greater than 1.
    //         /*
    //         Suppose shifts of 2, then 4, then 2 are required.
            
    //         We can have k = 2, and still perform all the shifts.
    //         */
    //         if(gcdOfShiftLengths == 1) {
    //             return 0;
    //         }
    //     }
        
    //     return 1;
    // }
    
    // OPTIMIZED VERSION OF canBeMadeSame function
    static int canBeMadeSame(String a1, String a2) {
        int[] a1Map = new int[26];
        int[] a2Map = new int[26];
        
        for(char a1Char: a1.toCharArray()) {
            a1Map[a1Char - 'a']++;
        }
        
        for(char a2Char: a2.toCharArray()) {
            a2Map[a2Char - 'a']++;
        }
        
        int excessCharacters = 0;
        
        // With chinese remainder theorem, gcd of any number with 0, gets the number.
        int gcdOfShiftLengths = 0;
        
        ArrayList<Integer> lengthsOfShiftsReq = new ArrayList<>();
        
        for(int alphabet = 0; alphabet < 26; alphabet++) {
            excessCharacters += a1Map[alphabet];
            
            /* 
            Excess characters from previous/current alphabets in a1, 
            can't match the count of the alphabet in a2.
            
            So, return 0 to indicate a1 cannot be converted to a2.
            */
            if(excessCharacters < a2Map[alphabet]) {  
                return 0;
            }
            else {
                excessCharacters -= a2Map[alphabet];
            }
            
            // Even if excessCharacters == 0, gcd will stay same. as per chinese remainder
            gcdOfShiftLengths = getGcd(gcdOfShiftLengths, excessCharacters);
            
            // GCD cannot be 1, since k has to be greater than 1.
            /*
            Suppose shifts of 2, then 4, then 2 are required.
            
            We can have k = 2, and still perform all the shifts.
            */
            if(gcdOfShiftLengths == 1) {
                return 0;
            }
        }
        
        return 1;
    }
    
    public static ArrayList<Integer> CompatibleStrings(int N, String[] A1, String[] A2) {
        // code here
        ArrayList<Integer> ans = new ArrayList<Integer>(2 * N);
        
        for(int i = 0; i < N; i++) {
            ans.add(canBeMadeSame(A1[i], A2[i]));
        }        
        
        return ans;
    }
}