// https://leetcode.com/problems/count-collisions-on-a-road/description/
// TODO: Make notes

class Solution {
    /*_____Approach using STACK_____*/

    // static boolean isR(char c) {
    //     return c == 'R';
    // }

    // /* 
    // `true` in stack means a car moving right
    // `false` in stack means a car that is stationary
    // */
    // static int collideSWithRs(ArrayDeque<Boolean> stack) {
    //     int collisions = 0;

    //     // While there are Rs to collide with.
    //     // stack.peek() check means we check for R. 
    //     while(!stack.isEmpty() && stack.peek()) {
    //         collisions += 1;
    //         stack.pop();
    //     }

    //     /*
    //     We push 'S' only if stack is empty, 
    //     because 2 contiguous 'S' are not useful.
    //     */
    //     if(stack.isEmpty()) stack.push(isR('S'));

    //     return collisions;
    // }

    // public int countCollisions(String directions) {
    //     /* 
    //     Stack will not store L cars

    //     When L is encountered:
    //     - If R is on top, collide and replace R with S.
    //     - If S is on top, collide and let the S stay.
    //     - If stack empty, L car will go away.
    //     */

    //     char[] dir = directions.toCharArray();
    //     int collisions = 0;

    //     // True is R, False is S.
    //     ArrayDeque<Boolean> stack = new ArrayDeque<>();

    //     int i = 0;

    //     // Finding, first non-L char.
    //     for(; i < dir.length; i++) {
    //         if(dir[i] != 'L') {
    //             stack.push(isR(dir[i]));
    //             break;
    //         }
    //     }

    //     for(i = i + 1; i < dir.length; i++) {
    //         char d = dir[i];

    //         if(d == 'R') {
    //             stack.push(isR('R'));
    //         }
    //         else if(d == 'S') {
    //             collisions += collideSWithRs(stack);
    //         }
    //         else if(d == 'L') {
    //             /* 
    //             If we encountered an L, we know stack won't be empty.
    //             An R or S will definitely be there.
    //             */

    //             // If there is an S, we just collide with it once.
    //             // !stack.peek() check means we check for S.
    //             if(!stack.peek()) {
    //                 collisions += 1;
    //             }
    //             else {
    //                 // Collision between L and R
    //                 collisions += 2;   
    //                 stack.pop();

    //                 collisions += collideSWithRs(stack);
    //             }
    //         }
    //     }

    //     return collisions;
    // }

    /*_____Approach using 2 pointers____*/

    public int countCollisions(String directions) {
        char[] dirArr = directions.toCharArray();

        int left = 0;
        int right = dirArr.length - 1;

        /* 
        Ignoring Left moving cars at the beginning, 
        that have nothing to the left of them, 
        so no collision possible
        */
        while(left < dirArr.length && dirArr[left] == 'L') left++;

        /*
        Ignoring Right moving cars at the end,
        that have nothing to the right of them, 
        so no collision possible
        */
        while(right > -1 && dirArr[right] == 'R') right--;

        /* 
        We have eliminated the cars that have nothing 
        to collide with, by this point
        */
        int collisions = 0;

        for(int i = left; i <= right; i++) {
            /*
            If the car is moving left or right,
            it will contribute 1 to the number of collisions.

            If the car is not moving it can serve as a collidable
            object, but won't increase the count of collisions.
            */
            if(dirArr[i] == 'L' || dirArr[i] == 'R') {
                collisions++;
            }
        }

        return collisions;
    }
}