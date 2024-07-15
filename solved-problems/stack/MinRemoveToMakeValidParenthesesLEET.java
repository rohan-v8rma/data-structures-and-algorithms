// https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
// TODO: Make notes

class Solution {
    /*
    In this question, it doesn't matter which open/close parentheses
    we remove, since all of them only take 1 away from the length
    of the final string.

    In the string `lee(t(c)o)de)`, it doesn't matter if we remove
    the 2nd last or last bracket, both will make the string valid.
    */
    public String minRemoveToMakeValid(String s) {
        ArrayDeque<Character> st = new ArrayDeque<>();

        int totalOpen = 0;
        int numOfOpen = 0;

        for(char c: s.toCharArray()) { 
            if(c == '(') {
                totalOpen++;
                numOfOpen++;
                st.offerLast(c);
            }
            else if(c == ')') {
                if(numOfOpen > 0) {
                    st.offerLast(c);
                    numOfOpen--;
                }
            }
            else {
                st.offerLast(c);
            }
        }

        StringBuilder sb = new StringBuilder();

        while(totalOpen > numOfOpen) {
            if(st.peekFirst() == '(')  {
                totalOpen--;
            }

            sb.append(st.pollFirst());
        }

        while(!st.isEmpty()) {
            char c = st.pollFirst();
            
            if(c != '(') {
                sb.append(c);
            }
        }


        return sb.toString();
    }
}