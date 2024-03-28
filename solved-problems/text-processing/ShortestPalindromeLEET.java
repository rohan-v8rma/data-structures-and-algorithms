class Solution {
    /*_____Close to O(N^2) approach, borderline 10^8_____*/

    // static boolean checkPal(String s, int start, int end) {
    //     while(start < end) {
    //         if(s.charAt(start++) != s.charAt(end--)) return false;
    //     }

    //     return true;
    // }

    // public String shortestPalindrome(String ip) {
    //         if(ip.equals("")) return "";

    //         // The starting character is a palindrome itself.
    //         int palindromeTill = 0;

    //         //! TLE
    //         // for(int i = 1; i < ip.length(); i++) {
    //         //     if(checkPal(ip, 0, i)) {
    //         //         palindromeTill = i;
    //         //     }
    //         // }
            
    //         for(int i = ip.length() - 1; i >= 1; i--) {
    //             if(checkPal(ip, 0, i)) {
    //                 palindromeTill = i;
    //                 break;
    //             }
    //         }

    //         StringBuilder prefix = new StringBuilder();

    //         // Then we add all the characters to prefix, that are not in the longest palindrome.
    //         prefix.append(ip.substring(palindromeTill + 1));

    //         prefix.reverse();

    //         prefix.append(ip);

    //         return prefix.toString();
    // }


    /*_____Still O(N^2) but reduction from 581ms to 186ms just by using character-array_____*/

    // static boolean checkPal(char[] c, int start, int end) {
    //     while(start < end) {
    //         if(c[start++] != c[end--]) return false;
    //     }

    //     return true;
    // }

    // public String shortestPalindrome(String ip) {
    //         if(ip.equals("")) return "";

    //         // The starting character is a palindrome itself.
    //         int palindromeTill = 0;

    //         char[] ipArr = ip.toCharArray();

    //         //! Still TLE
    //         // for(int i = 1; i < ip.length(); i++) {
    //         //     if(checkPal(ipArr, 0, i)) {
    //         //         palindromeTill = i;
    //         //     }
    //         // }
            
    //         for(int i = ip.length() - 1; i >= 1; i--) {
    //             if(checkPal(ipArr, 0, i)) {
    //                 palindromeTill = i;
    //                 break;
    //             }
    //         }

    //         StringBuilder prefix = new StringBuilder();

    //         // Then we add all the characters to prefix, that are not in the longest palindrome.
    //         prefix.append(ip.substring(palindromeTill + 1));

    //         prefix.reverse();

    //         prefix.append(ip);

    //         return prefix.toString();
    // }

    /*_____Flawed used of LPS: Won't work. See notes_____*/

    // public String shortestPalindrome(String ip) {
    //     int n = ip.length();

    //     StringBuilder sb = new StringBuilder();

    //     sb.append(ip);
    //     sb.reverse();

    //     String revIp = sb.toString();   

    //     if(ip.equals(revIp)) return ip;

    //     sb = new StringBuilder();

    //     int[] LPS = new int[n];

    //     int prefixLen = 0;

    //     for(int lpsIdx = 1; lpsIdx < n; lpsIdx++) {
    //         // We match prefixes in original string, with suffixes in reversed string.
    //         while(ip.charAt(prefixLen) != revIp.charAt(lpsIdx)) {
    //             if(prefixLen == 0) break;

    //             prefixLen = LPS[prefixLen - 1];
    //         }

    //         if(ip.charAt(prefixLen) == revIp.charAt(lpsIdx)) {
    //             prefixLen++;
    //         }

    //         LPS[lpsIdx] = prefixLen;
    //     }

    //     /*
    //     LPS[n - 1] is the important value for us, since it considers 
    //     entire lengths of ip and revIp for getting Longest Prefix that is also Suffix

    //     LPS[n - 1] value gets us the longest prefix of ip, that is equal to suffix of revIp.

    //     This is the length of the longest palindrome part of ip, since by definition, 
    //     palindromes are strings read out the same in reverse.
    //     */


    //     sb.append(revIp.substring(0, n - LPS[n - 1]));
    //     sb.append(ip);

    //     return sb.toString();
    // }

    /*_____KMP String matching method: 
    
    TC: O(2 * n + 2 * n) in worst case.
    SC: O(n) for LPS array.
    
    _____*/

    // public String shortestPalindrome(String ip) {
    //     int n = ip.length();

    //     StringBuilder sb = new StringBuilder();

    //     sb.append(ip);
    //     sb.reverse();

    //     String revIp = sb.toString();   

    //     if(ip.equals(revIp)) return ip;

    //     sb = new StringBuilder();

    //     int[] LPS = new int[n];

    //     int prefixLen = 0;

    //     // Creating LPS array for 'ip' string.
    //     for(int lpsIdx = 1; lpsIdx < n; lpsIdx++) {
            
    //         while(ip.charAt(prefixLen) != ip.charAt(lpsIdx)) {
    //             if(prefixLen == 0) break;

    //             prefixLen = LPS[prefixLen - 1];
    //         }

    //         if(ip.charAt(prefixLen) == ip.charAt(lpsIdx)) {
    //             prefixLen++;
    //         }

    //         LPS[lpsIdx] = prefixLen;
    //     }

    //     int originalStrIdx = 0;

    //     // KMP matching, keeping `ip` as PATTERN and `rev` as the TEXT
    //     for(int matchIdx = 0; matchIdx < n; matchIdx++) {
    //         while(ip.charAt(originalStrIdx) != revIp.charAt(matchIdx)) {
    //             if(originalStrIdx == 0) break;

    //             originalStrIdx = LPS[originalStrIdx - 1];
    //         }

    //         if(ip.charAt(originalStrIdx) == revIp.charAt(matchIdx)) {
    //             originalStrIdx++;
    //         }
    //     }

    //     sb.append(revIp.substring(0, n - originalStrIdx));
    //     sb.append(ip);

    //     return sb.toString();
    // }

    /*_____LPS only method: 
    
    TC: O(2 * l) = O(2 * (2 * n + 1)) = O(4 * n)
    SC: O(l) = O(2 * n + 1), so double the space complexity.
    _____*/

    public String shortestPalindrome(String ip) {
        StringBuilder sb = new StringBuilder();

        sb.append(ip);

        sb.reverse();

        String revIp = sb.toString();

        sb.reverse();
        sb.append('#');
        sb.append(revIp);

        String str_rts = sb.toString();

        int n = ip.length();
        int l = str_rts.length();

        int[] LPS = new int[l];

        int prefixLen = 0;

        // Starting from 1, since 1 length substring has 0 length LPS
        for(int lpsIdx = 1; lpsIdx < l; lpsIdx++) {
            while(str_rts.charAt(prefixLen) != str_rts.charAt(lpsIdx)) {
                if(prefixLen == 0) break;

                prefixLen = LPS[prefixLen - 1];
            }

            if(str_rts.charAt(prefixLen) == str_rts.charAt(lpsIdx)) {
                prefixLen++;
            }

            LPS[lpsIdx] = prefixLen;
        }

        sb = new StringBuilder();

        sb.append(revIp.substring(0, n - LPS[l - 1]));
        sb.append(ip);

        return sb.toString();
    }
}