// Problem link: https://leetcode.com/problems/generate-parentheses

class Solution {
    /*
    If suppose n = 3, the bracket pairs can be numbered 1, 2 and 3.

    1 will always open first.

    Let:
    - ( represent 1
    - [ represent 2
    - { represent 3


                (
               / \
             ([   ()
            / |   |
         ([{ ([]  ()[
    */

    static void generateCombos(
        String currentCombo, 
        List<String> combos, 
        Stack<Integer> openStack, 
        int nextToOpen,
        int n
    ) {
        // No brackets left to open AND all open brackets have been closed
        if(nextToOpen == n && openStack.isEmpty()) {
            combos.add(currentCombo);
            return;
        }

        // We still have brackets that are left to open
        if(nextToOpen < n) {
            // Adding the new bracket to the currently open stack
            openStack.push(nextToOpen);
            generateCombos(currentCombo + "(", combos, openStack, nextToOpen + 1, n);    
            openStack.pop(); // Backtracking step
        }

        // Combinations where instead of opening nextToOpen, we close first.
        if(!openStack.isEmpty()) {
            int nextClose = openStack.pop();
            generateCombos(currentCombo + ")", combos, openStack, nextToOpen, n);
            openStack.push(nextClose); // Backtracking step
        }
    }   

    public List<String> generateParenthesis(int n) {
        boolean[] closed = new boolean[n];

        List<String> combos = new ArrayList<>();

        Stack<Integer> openStack = new Stack<>();
        String currentCombo = "(";
        // 0 signifies first bracket is open, 1 would signify second bracket is open.
        openStack.push(0);

        generateCombos(currentCombo, combos, openStack, 1, n);
        
        return combos;
    }
}