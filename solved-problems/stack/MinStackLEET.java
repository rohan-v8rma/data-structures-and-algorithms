/*____Solution that has O(N) space complexity due to extra stack___*/
// class MinStack {
//     ArrayDeque<Integer> stack;
//     ArrayDeque<int[]> minElementStack;

//     public MinStack() {
//         stack = new ArrayDeque<>();
//         minElementStack = new ArrayDeque<>();
//     }
    
//     public void push(int val) {
//         stack.push(val);

//         if(minElementStack.isEmpty()) {
//             minElementStack.push(new int[]{val, 0});
//         }
        
//         int[] minElement = minElementStack.peek();

//         /* 
//         If the value is less than the minimum element,
//         this is the new minimum element.
//         */
//         if(val < minElement[0]) {
//             minElementStack.push(new int[]{val, 1});
//         }
//         /* 
//         If val is same as minimum element, 
//         we just increase the number of minimum elements present.
//         */
//         else if(val == minElement[0]) {
//             minElement[1]++;
//         }
//     }
    
//     public void pop() {
//         int poppedValue = stack.pop();
        
//         int[] minElement = minElementStack.peek();

//         if(poppedValue == minElement[0]) {
//             minElement[1]--;

//             if(minElement[1] == 0) {
//                 minElementStack.pop();
//             }
//         }
//     }
    
//     public int top() {
//         return stack.peek();
//     }
    
//     public int getMin() {
//         return minElementStack.peek()[0];
//     }
// }

/*___Solution that has EXACT O(N) space complexity due to long instead of int____*/
class MinStack {
    long minElement;
    ArrayDeque<Long> stack;

    public MinStack() {
        stack = new ArrayDeque<>();
    }
    
    public void push(long val) {
        if(stack.isEmpty()) {
            minElement = val;
        }

        if(val < minElement) {
            stack.push(2 * val - minElement);
            minElement = val;
        }
        else {
            stack.push(val);    
        }
        
    }
    
    public void pop() {
        long poppedValue = stack.pop();
        
        if(stack.isEmpty()) {
            minElement = Long.MAX_VALUE;
        }
        else if(poppedValue < minElement) {
            minElement = 2 * minElement - poppedValue;
        }
    }
    
    public int top() {
        long peekedValue = stack.peek();

        if(peekedValue < minElement) {
            return (int)minElement;
        }
        
        return (int)peekedValue;
    }
    
    public int getMin() {
        return (int)minElement;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */