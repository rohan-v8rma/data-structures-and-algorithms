// https://leetcode.com/problems/check-if-word-is-valid-after-substitutions/

class Solution {
    public boolean isValid(String s) {
        ArrayDeque<Character> charStack = new ArrayDeque<>();

        for(char c: s.toCharArray()) {
            if(c == 'c') {
                if(
                    !(
                        charStack.size() >= 2 
                        && charStack.pop() == 'b'
                        && charStack.pop() == 'a'
                    )
                ) {
                    return false;
                }
            }
            else {
                charStack.push(c);
            }
        }

        return charStack.isEmpty();
    }
}