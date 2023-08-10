// Problem link: https://leetcode.com/problems/longest-substring-without-repeating-characters

class Solution {
    /*
    Constraint analysis:

    Since the string consists of letters, digits, symbols and spaces,
    we CANNOT use a simple index array of length 26.

    For simplicity, let us use a hashmap to store a character and its
    corresponding index.
    */

    // O(N) solution.
    public int lengthOfLongestSubstring(String s) {
        // int[] charOccurPositions = new int[26];
        // /* 
        // Initializing character occurrence positions as -1
        // since none of the characters have occurred yet.
        // */
        // for(int initIdx = 0; initIdx < 26; initIdx++) {
        //     charOccurPositions[initIdx] = -1;
        // }

        Map<Character, Integer> charOccurPositions = new HashMap<>();

        int longestSubstr = 0;
        int windowStart = 0;
        for(int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char currentChar = s.charAt(windowEnd);
            /* 
            We return -1 as default to signify that the character
            hasn't occurred yet.
            */
            int currentCharPosition = charOccurPositions
            .getOrDefault(currentChar, -1);

            /* 
            This character is already inside the current window,
            so we move the start of the window to the position 
            just after the initial occurrence of the character.

            In this way, we remove the first occurrence of the character,
            allowing us to include the new occurrence within the window.
            */
            if(currentCharPosition >= windowStart) {
                windowStart = currentCharPosition + 1;
            }

            /*
            In any case, we have to update the occurrence position
            of the current character, whether it WAS already inside 
            the current window or not.
            */
            charOccurPositions.put(currentChar, windowEnd);

            longestSubstr = Math.max(
                longestSubstr, 
                windowEnd - windowStart + 1
            );
        }

        return longestSubstr;
    }
}