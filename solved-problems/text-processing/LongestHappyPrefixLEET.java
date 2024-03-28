// https://leetcode.com/problems/longest-happy-prefix

class Solution {
    public String longestPrefix(String s) {
        int n = s.length();

        // This is the array that we use in KMP, and is calculated in pre-processing step of KMP.
        int[] LPS = new int[n];

        int prefixSuffixLen = 0;

        for(int lpsIdx = 1; lpsIdx < n; lpsIdx++) {
            while(s.charAt(lpsIdx) != s.charAt(prefixSuffixLen)) {
                if(prefixSuffixLen == 0) break;

                // Check intuition for this in Text Processing NOTES.
                prefixSuffixLen = LPS[prefixSuffixLen - 1];
            }

            if(s.charAt(lpsIdx) == s.charAt(prefixSuffixLen)) {
                prefixSuffixLen++;
            }
            
            LPS[lpsIdx] = prefixSuffixLen;
        }

        return s.substring(0, LPS[n - 1]);
    }

    // Run-time went from 20% to 90%, just by using character array.
    // public String longestPrefix(String s) {
    //     int n = s.length();

    //     char[] c = s.toCharArray();

    //     // This is the array that we use in KMP, and is calculated in pre-processing step of KMP.
    //     int[] LPS = new int[n];

    //     int prefixSuffixLen = 0;

    //     for(int lpsIdx = 1; lpsIdx < n; lpsIdx++) {
    //         while(c[lpsIdx] != c[prefixSuffixLen]) {
    //             if(prefixSuffixLen == 0) break;

    //             // Check intuition for this in Text Processing NOTES.
    //             prefixSuffixLen = LPS[prefixSuffixLen - 1];
    //         }

    //         if(c[lpsIdx] == c[prefixSuffixLen]) {
    //             prefixSuffixLen++;
    //         }
            
    //         LPS[lpsIdx] = prefixSuffixLen;
    //     }

    //     return s.substring(0, LPS[n - 1]);
    // }
}