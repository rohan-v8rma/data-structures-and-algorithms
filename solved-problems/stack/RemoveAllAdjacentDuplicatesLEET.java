// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
// TODO: Make notes

class Solution {
    public String removeDuplicates(String s) {
        ArrayDeque<Character> st = new ArrayDeque<>();

        for(char c: s.toCharArray()) {
            if(st.isEmpty() || st.peekFirst() != c) {
                st.offerFirst(c);
            }
            else {
                while(!st.isEmpty() && st.peekFirst() == c) {
                    st.pollFirst();
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        while(!st.isEmpty()) {
            sb.append(st.pollLast());
        }

        return sb.toString();
    }
}