#include <iostream>
using namespace::std;

// Problem is to find the number of ways to climb to the top of the stair case, using a just 1 step at a time or 2 steps at time or a combination of both.

int climbStairsUsingRecursion(int numberOfSteps) {
    if (numberOfSteps < 0) { // Negative steps are not possible so this won't be counted as a method to reach the top
        return 0;
    }
    if(numberOfSteps == 0) { // Top has been reached, so this can be counted as a method to reach the top
        return 1;
    }
    else {
        // 1 step (1 way) which is why 1 step is reduced in the first recursion call 
        // or 
        // 2 steps (1 way) which is why 2 steps are reduced in the second recursion call
        return(climbStairsUsingRecursion(numberOfSteps - 1) + climbStairsUsingRecursion(numberOfSteps - 2));
    }

}


int climbStairsUsingDynamicProgramming(int numberOfSteps) {
    int* climbStairsWayArray = new int[numberOfSteps + 1];

    for(int index = 0; index <= numberOfSteps; index++) {
        if(index == 0) {
            climbStairsWayArray[index] = 1;
        }
        else if(index == 1) { 
        // Separate condition for index = 1 because index - 2 for index = 1 will lead to negative index
            climbStairsWayArray[index] = climbStairsWayArray[index - 1];
        }
        else {
            // 1 step(Start of 1st set of paths) which is why 1 step is reduced in the first array access
            // or 
            // 2 steps(Start of 2nd set of paths) which is why 2 steps are reduced in the second array access
            climbStairsWayArray[index] = climbStairsWayArray[index - 1] + climbStairsWayArray[index - 2];
        }
    }

    int returnVal = climbStairsWayArray[numberOfSteps];
    // Freeing memory using array pointer deletion operator so that out-of-memory error does not occur
    delete[] climbStairsWayArray;

    return returnVal;

}

int main() {
    cout << climbStairsUsingDynamicProgramming(2) << endl;
    cout << climbStairsUsingDynamicProgramming(3) << endl;
}
