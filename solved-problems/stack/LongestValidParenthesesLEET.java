// https://leetcode.com/problems/longest-valid-parentheses/

class Solution {

    // OK SOLUTION

    // public boolean isOpen(char c) {
    //     return c == '(';
    // }

    // public int longestValidParentheses(String s) {
    //     int maxLength = 0;

    //     ArrayDeque<Integer> startPt = new ArrayDeque<>();

    //     // This is the base case.
    //     startPt.push(-1);

    //     char[] cArr = s.toCharArray();

    //     for(int i = 0; i < s.length(); i++) {
    //         char c = cArr[i];

    //         if(isOpen(c)) {
    //             startPt.push(i);
    //         }
    //         else {
    //             // Case when we encounter a close bracket.

    //             // No open bracket is present to match with the close bracket.
    //             if(startPt.peek() == -1 || !isOpen(cArr[startPt.peek()])) {
    //                 /* 
    //                 This is the point after which the new valid substrings
    //                 will start.
    //                 */
    //                 startPt.push(i);
    //             }
    //             else {
    //                 // Matched the open bracket.
    //                 startPt.pop();

    //                 /* 
    //                 The index after last unmatched open bracket, will be
    //                 starting point of the current valid substring.
    //                 */
    //                 maxLength = Math.max(maxLength, i - startPt.peek());
    //             }
    //         }
    //     }

    //     return maxLength;
    // }

    // OPTIMAL: MORE INTUITIVE SOLUTION
    public boolean isOpen(char c) {
        return c == '(';
    }

    public int longestValidParentheses(String s) {
        int maxLength = 0;

        ArrayDeque<Integer> unmatchedOpenBrackets = new ArrayDeque<>();

        // This is the base case.
        unmatchedOpenBrackets.push(-1);

        int lastUnmatchedClosedBracket = -1;

        char[] cArr = s.toCharArray();

        for(int i = 0; i < s.length(); i++) {
            char c = cArr[i];

            if(isOpen(c)) {
                unmatchedOpenBrackets.push(i);
            }
            else {
                // Case when we encounter a close bracket.

                // No open bracket is present to match with the close bracket.
                if(unmatchedOpenBrackets.peek() == -1) {
                    lastUnmatchedClosedBracket = i;
                }
                else {
                    // Matched the open bracket.
                    unmatchedOpenBrackets.pop();

                    /* 
                    The index after last unmatched open bracket, will be
                    starting point of the current valid substring.
                    */
                    maxLength = Math.max(
                        maxLength, 
                        i - Math.max(
                            /*
                            Whichever is the latest, 
                            the last unmatched open bracket OR
                            the last unmatched closed bracket.
                            */
                            unmatchedOpenBrackets.peek(),
                            lastUnmatchedClosedBracket
                        )
                    );
                }
            }
        }

        return maxLength;
    }
}