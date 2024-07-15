// https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/

class Solution {
    /*
    ____BRUTEFORCE____

    TC: O(N^2), since it is possible we encounter test cases like

    Example 1:
    (a(b(c(d(e)f)g)h)i)
    Cost of reversals = 1 + 3 + 5 + 7 + 9
    Length of string = 19
    */
    // public String reverseParentheses(String s) {
    //     int numOfOpenBrackets = 0;
    //     int strIdx = 0;

    //     ArrayDeque<Character> result = new ArrayDeque<>();
    //     ArrayDeque<Integer> st = new ArrayDeque<>();
    //     Queue<Character> reversalQue = new LinkedList<>();

    //     char[] c = s.toCharArray();

    //     int numChars = 0;

    //     for(int i = 0; i < c.length; i++) {
    //         if(c[i] == '(') {
    //             st.push(numChars);
    //         }
    //         else if(c[i] == ')') {
    //             int charsToRev = numChars - st.pop();

    //             for(int rev = 0; rev < charsToRev; rev++) {
    //                 reversalQue.offer(result.pollLast());
    //             }

    //             for(int rev = 0; rev < charsToRev; rev++) {
    //                 result.offerLast(reversalQue.poll());
    //             }
    //         }
    //         else {
    //             result.offerLast(c[i]);
    //             numChars++;
    //         }
    //     }


    //     StringBuilder sb = new StringBuilder();
    //     while(!result.isEmpty()) {
    //         sb.append(result.pollFirst());
    //     }

    //     return sb.toString();
    // }

    /*
    ____BRUTEFORCE 2____
    (SLOWER BUT EASIER TO UNDERSTAND)

    TC: O(N^2), since it is possible we encounter test cases like

    Example 1:
    (a(b(c(d(e)f)g)h)i)
    Cost of reversals = 1 + 3 + 5 + 7 + 9
    Length of string = 19
    */
    // static StringBuilder sb = new StringBuilder();

    // String revString(String s) {
    //     sb.setLength(0);
    //     sb.append(s);
    //     sb.reverse();
    //     return sb.toString();
    // }

    // public String reverseParentheses(String s) {
    //     int numOfOpenBrackets = 0;
    //     int strIdx = 0;

    //     String result = "";
    //     ArrayDeque<Integer> st = new ArrayDeque<>();

    //     char[] c = s.toCharArray();

    //     int numChars = 0;

    //     for(int i = 0; i < c.length; i++) {
    //         if(c[i] == '(') {
    //             st.push(numChars);
    //         }
    //         else if(c[i] == ')') {
    //             int charsToRev = numChars - st.pop();
    //             int totalChars = result.length();

    //             result = (
    //                 result.substring(0, totalChars - charsToRev)
    //                 + revString(result.substring(totalChars - charsToRev))
    //             );
    //         }
    //         else {
    //             result += c[i];
    //             numChars++;
    //         }
    //     }

    //     return result;
    // }

    /*
    ____OPTIMAL_____

    TC: O(N)

o  Warmhole teleportation method
    */
    // public String reverseParentheses(String s) {
    //     /* 
    //     Preparation step, storing open and closing bracket pairs

    //     This allows for the remaining algo to work in O(N)
    //     */
    //     HashMap<Integer, Integer> matchBracketPos = new HashMap<>();

    //     ArrayDeque<Integer> st = new ArrayDeque<>();

    //     char[] c = s.toCharArray();

    //     for(int i = 0; i < c.length; i++) {
    //         if(c[i] == '(') {
    //             st.push(i);
    //         }
    //         else if(c[i] == ')') {
    //             int openBracketIdx = st.pop();

    //             matchBracketPos.put(openBracketIdx, i);
    //             matchBracketPos.put(i, openBracketIdx);
    //         }
    //     }

    //     int inc = 1;
    //     int i = 0;

    //     ArrayList<Character> result = new ArrayList<>();

    //     while(i < c.length) {
    //         if(c[i] == '(' || c[i] == ')') {
    //             inc *= -1;
    //             i = matchBracketPos.get(i);
    //         }
    //         else {
    //             result.add(c[i]);
    //         }

    //         i += inc;
    //     }


    //     return result
    //         .stream()
    //         .map(e->e.toString())
    //         .reduce((acc, e) -> acc  + e)
    //         .get();
    // }

    /*
    ____MOST OPTIMAL_____

    No use of Stream API.

    TC: O(N)

    Wormhole teleportation method
    */
    public String reverseParentheses(String s) {
        /* 
        Preparation step, storing open and closing bracket pairs

        This allows for the remaining algo to work in O(N)
        */
        HashMap<Integer, Integer> matchBracketPos = new HashMap<>();

        ArrayDeque<Integer> st = new ArrayDeque<>();

        char[] c = s.toCharArray();

        int numBrackets = 0;

        for(int i = 0; i < c.length; i++) {
            if(c[i] == '(') {
                st.push(i);
                numBrackets++;
            }
            else if(c[i] == ')') {
                int openBracketIdx = st.pop();

                matchBracketPos.put(openBracketIdx, i);
                matchBracketPos.put(i, openBracketIdx);
            }
        }

        int inc = 1;
        int i = 0;

        int resultIdx = 0;
        char[] result = new char[c.length - 2 * numBrackets];

        while(i < c.length) {
            if(c[i] == '(' || c[i] == ')') {
                inc *= -1;
                i = matchBracketPos.get(i);
            }
            else {
                result[resultIdx++] = c[i];
            }

            i += inc;
        }


        return new String(result);
    }
}