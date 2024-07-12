// https://leetcode.com/problems/validate-stack-sequences/
// TODO: Make notes

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        ArrayDeque<Integer> st = new ArrayDeque<>();

        int pushIdx = 0;
        int poppedIdx = 0;

        while(poppedIdx < popped.length) {
            // If the stack's top matched what has to be popped, we pop it.
            if(!st.isEmpty() && popped[poppedIdx] == st.peek()) {
                // System.out.println("hello");
                st.pop();
                poppedIdx++;
            }
            else {
                if(pushIdx < pushed.length) {
                    st.push(pushed[pushIdx++]);
                    // System.out.printf("%s, %d\n", st, poppedIdx);
                }
                else {
                    break;   
                }
            }
        }
        
        return st.isEmpty();
    }
}