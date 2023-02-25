#include <iostream>
using namespace ::std;

/* 
In this algorithm, we start from 0th object selection, which is the root node and doesn't represent any particular object. 

The objects are numbered from 1 to `numOfObjects`, getting us a total of numOfObjects.

The base case of the `branchAndBound()` function is when (currentObject == numOfObjects+1)
*/

int max(int n1, int n2) {
    return ( (n1 > n2) ? n1 : n2);
}


// In this function, we calculate the upper bound of the object `currentObject`
int calculateUpperBound(
    int currentObject, 
    int* profitArr, 
    int* weightArr, 
    int* partialSolutionArr, 
    int capacityLeft, 
    int numOfObjects
    ) {

    int upperBound = 0;

    // These are the objects whose INCLUSION or EXCLUSION is decided in upper nodes of the current sub-tree.
    for(int decidedObjects = 1; decidedObjects <= currentObject; decidedObjects++) {       
            
        //? We check for INCLUSION or EXCLUSION of the object before changing the values of the bound, along with checking if there is space to accomodate the object in the knapsack
        if(weightArr[decidedObjects] > capacityLeft) {
            break; //! Breaking the loop if no space is left in the knapsack
        }
        else if(partialSolutionArr[decidedObjects]) {
            upperBound -= profitArr[decidedObjects];
            capacityLeft -= weightArr[decidedObjects];
        }
        else {
            continue;
        }

    }

    // These are the objects whose INCLUSION or EXCLUSION is yet to be decided, so we keep adding these objects to the bound until there is no space lieft 
    for(int undecidedObjects = currentObject + 1; undecidedObjects <= numOfObjects; undecidedObjects++) {

        //? We ONLY check if there is space to accomodate the object in the knapsack
        if(weightArr[undecidedObjects] > capacityLeft) {
            break; //! Breaking the loop if no space is left in the knapsack
        }
        else {
            upperBound -= profitArr[undecidedObjects];
            capacityLeft -= weightArr[undecidedObjects];
        }
    }

    return upperBound;
}

// In this function, we calculate the lower bound of the object `currentObject`
double calculateLowerBound(
    int currentObject, 
    int* profitArr, 
    int* weightArr, 
    int* partialSolutionArr, 
    int capacityLeft, 
    int numOfObjects
    ) {

    double lowerBound = 0;

    // These are the objects whose INCLUSION or EXCLUSION is decided in upper nodes of the current sub-tree.
    for(int decidedObjects = 1; decidedObjects <= currentObject; decidedObjects++) {       
            
        //? We check for INCLUSION or EXCLUSION of the object before changing the values of the bound, along with checking if there is space to accomodate the object in the knapsack
        if(weightArr[decidedObjects] > capacityLeft) {
            lowerBound -= (capacityLeft / (double)(weightArr[decidedObjects])) * profitArr[decidedObjects];
            capacityLeft = 0;

            break; //! Breaking the loop if no space is left in the knapsack
        }
        else if(partialSolutionArr[decidedObjects]) {
            lowerBound -= profitArr[decidedObjects];
            capacityLeft -= weightArr[decidedObjects];
        }
        else {
            continue;
        }

    }

    // These are the objects whose INCLUSION or EXCLUSION is yet to be decided, so we keep adding these objects to the bound until there is no space lieft 
    for(int undecidedObjects = currentObject + 1; undecidedObjects <= numOfObjects; undecidedObjects++) {

        //? We ONLY check if there is space to accomodate the object in the knapsack
        if(weightArr[undecidedObjects] > capacityLeft) {
            lowerBound -= (capacityLeft / (double)(weightArr[undecidedObjects])) * profitArr[undecidedObjects];
            capacityLeft = 0;
            break; //! Breaking the loop if no space is left in the knapsack
        }
        else {
            lowerBound -= profitArr[undecidedObjects];
            capacityLeft -= weightArr[undecidedObjects];
        }
    }

    return lowerBound;
}



int* branchAndBound(
    int currentObject, 
    int* solutionArr, 
    int numOfObjects
    ) {
    
    if( currentObject == (numOfObjects + 1) ) { // Base case of branch and bound function
        return solutionArr;
    }




}

void knapsack01() {
    int numOfObjects;
    int capacity;
    
    
    /*
    //* Code for taking inputs
    cout << "Number of objects : ";
    cin >> numOfObjects;
    
    int* profitArr = new int[numOfObjects + 1];
    int* weightArr = new int[numOfObjects + 1];

    cout << "Enter objects in the following form (profit weight):\n2 3\n\n";
    for(int index = 1; index <= numOfObjects; index++) {
        cout << "Object" << (index) << ": ";
        cin >> profitArr[index] >> weightArr[index];
    }
    
    cout << "Enter the capacity of the knapsack: ";
    cin >> capacity;
    */

    //* Values for testing the algorithm w/o input
    numOfObjects = 5;
    capacity = 8;
    int profitArr[6] = {0, 1, 2, 5, 6, 2};
    int weightArr[6] = {0, 2, 3, 4, 5, 2};

    int* untouchedSolutionArr = new int[numOfObjects + 1];

    int* solutionArr = branchAndBound(-1, untouchedSolutionArr, numOfObjects);

    cout << "\nSolutions in form of binary, where 0 means the object was not included and 1 means the object was included:\n(";


    for(int objectNum = 1; objectNum <= numOfObjects; objectNum++) {
        if(objectNum == numOfObjects) {
            printf("Ob%2d):\n", objectNum);
        }
        else {
            printf("Ob%2d, ", objectNum);
        }
    }

    for(int objectNum = 1; objectNum <= numOfObjects; objectNum++) {
        if(objectNum == numOfObjects) {
            printf("%4d):\n", solutionArr[objectNum]);
        }
        else {
            printf("%4d, ", solutionArr[objectNum]);
        }
    }
}

int main() {
    knapsack01();

    return 0;
}