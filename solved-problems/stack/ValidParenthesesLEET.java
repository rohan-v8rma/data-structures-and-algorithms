// https://leetcode.com/problems/valid-parentheses/

class Solution {
    public boolean matched(char previousOpen, char currentClose) {
        if(
            (
                previousOpen == '('
                && currentClose == ')'
            )
            || (
                previousOpen == '{'
                && currentClose == '}'
            )
            || (
                previousOpen == '['
                && currentClose == ']'
            )
        ) {
            return true;
        }

        return false;
    }
    public boolean isOpen(char c) {
        if(c == '(' || c == '{' || c == '[') {
            return true;
        }

        return false;
    }

    public boolean isValid(String s) {
        ArrayDeque<Character> parenStack = new ArrayDeque<>();

        for(char paren: s.toCharArray()) {
            if(isOpen(paren)) {
                parenStack.push(paren);
            }
            else {
                if(parenStack.isEmpty()) return false;

                if(matched(parenStack.peek(), paren)) {
                    parenStack.pop();
                }
                else {
                    return false;
                }
            }
        }

        return parenStack.isEmpty();
    }
}