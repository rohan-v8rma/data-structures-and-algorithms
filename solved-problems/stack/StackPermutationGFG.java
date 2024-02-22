// https://www.geeksforgeeks.org/problems/stack-permutations/1

class Solution {
    public static int isStackPermutation(int n, int[] ip, int[] op) {
        // code here
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        int opIdx = 0;
        
        for(int ipIdx = 0; ipIdx < n; ipIdx++) {
            if(ip[ipIdx] == op[opIdx]) {
                opIdx++;
                while(!stack.isEmpty()) {
                    if(stack.peek() == op[opIdx]) {
                        stack.pop();
                        opIdx++;
                    }
                    else {
                        break;
                    }
                }
            }
            else {
                stack.push(ip[ipIdx]);
            }
        }
        
        for( ; opIdx < n; opIdx++) {
            if(stack.pop() != op[opIdx]) {
                return 0;
            }
        }
        
        return 1;
    }
}
