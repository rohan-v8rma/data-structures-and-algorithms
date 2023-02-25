#include <iostream>
using namespace ::std;

/* 
In this algorithm, we start from 0th object selection, which is the root node and doesn't represent any particular object. 

The objects are numbered from 1 to `numOfObjects`, getting us a total of numOfObjects.

The base case of the `branchAndBound()` function is when (currentObject == numOfObjects+1)
*/


//* Applying BUBBLE SORT to sort the arrays
void sortArrays(
    int* mappingArr, 
    int* profitArr, 
    int* weightArr, 
    int numOfObjects
    ) {
    
    double* densityArr = new double[numOfObjects + 1];
    for(int index = 1; index <= numOfObjects; index++) {
        densityArr[index] = profitArr[index] / (double)(weightArr[index]);
    }

    for(int pass = 0 ; pass < (numOfObjects - 1) ; pass++) {
        int swapCt = 0; // we reset the swap variable for the next pass.
        for(int comparison = 1 ; comparison <= ( (numOfObjects - 1) - pass ) ; comparison++) {
            if(densityArr[comparison] < densityArr[comparison + 1]) {
                
                swap(mappingArr[comparison], mappingArr[comparison + 1]);
                swap(densityArr[comparison], densityArr[comparison + 1]);
                swap(profitArr[comparison], profitArr[comparison + 1]);
                swap(weightArr[comparison], weightArr[comparison + 1]);

                swapCt++;            
            };
        }

        if(swapCt == 0) { // If no swaps occur in a particular pass, then it is clear that the array is already sorted and we need not make any more passes.
            break;
        };
    }       
}

int* MappedToUnmapped(
    int* mappedSolutionArr, 
    int* mappingArray, 
    int numOfObjects) {
        int* unmappedSolutionArr = new int[numOfObjects + 1];

        for(int index = 1; index <= numOfObjects; index++) {
            unmappedSolutionArr[mappingArray[index]] = mappedSolutionArr[index];
        }

        return unmappedSolutionArr;
}

// In this function, we calculate the upper bound of the object `currentObject`
int calculateUpperBound(
    int currentObject, 
    int currentObjIncludeBoolean,
    int* profitArr, 
    int* weightArr, 
    int* partialSolutionArr, 
    int capacityLeft, 
    int numOfObjects
    ) {

    partialSolutionArr[currentObject] = currentObjIncludeBoolean;

    int upperBound = 0;

    // These are the objects whose INCLUSION or EXCLUSION is decided in upper nodes of the current sub-tree.
    for(int decidedObjects = 1; decidedObjects <= currentObject; decidedObjects++) {       
            
        //? We check for INCLUSION or EXCLUSION of the object before changing the values of the bound, along with checking if there is space to accomodate the object in the knapsack. No fractions of objects are added.
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

    // These are the objects whose INCLUSION or EXCLUSION is yet to be decided, so we keep adding WHOLE objects to the bound until there is no space left. No fractions of objects are added
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
    
    // Removing the proposed INCLUSION or EXCLUSION for the current object, before returning the bound
    partialSolutionArr[currentObject] = 0;

    return upperBound;
}

// In this function, we calculate the lower bound of the object `currentObject`
double calculateLowerBound(
    int currentObject, 
    int currentObjIncludeBoolean,
    int* profitArr, 
    int* weightArr, 
    int* partialSolutionArr, 
    int capacityLeft, 
    int numOfObjects
    ) {

    partialSolutionArr[currentObject] = currentObjIncludeBoolean;

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

    // These are the objects whose INCLUSION or EXCLUSION is yet to be decided, so we keep adding these objects to the bound until there is no space left.
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

    // Removing the proposed INCLUSION or EXCLUSION for the current object, before returning the bound
    partialSolutionArr[currentObject] = 0;
    
    return lowerBound;
}



int* branchAndBound(
    int currentObject, 
    int* profitArr, 
    int* weightArr,
    int* solutionArr, 
    int capacity,
    int numOfObjects
    ) {
    
    if( currentObject == (numOfObjects + 1) ) { // Base case of branch and bound function
        return solutionArr;
    }

    // Calculating bounds for when current Object is included.
    double lBound1 = calculateLowerBound(currentObject, 1, profitArr, weightArr, solutionArr, capacity, numOfObjects);
    int uBound1 = calculateUpperBound(currentObject, 1, profitArr, weightArr, solutionArr, capacity, numOfObjects);

    
    // Calculating bounds for when current Object is NOT included.
    double lBound0 = calculateLowerBound(currentObject, 0, profitArr, weightArr, solutionArr, capacity, numOfObjects);
    int uBound0 = calculateUpperBound(currentObject, 0, profitArr, weightArr, solutionArr, capacity, numOfObjects);

    cout << "Object " << currentObject << endl;
    cout << uBound1 << " | " << lBound1 << endl;
    cout << uBound0 << " | " << lBound0 << endl;

    // Lower bound when object is included, is lesser, so we expand this node.
    if(lBound1 < lBound0) {
        solutionArr[currentObject] = 1;
    }
    // Lower bound of both INCLUSION and EXCLUSION is equal.
    else if(lBound1 == lBound0) {
        // When lower bound of both INCLUSION and EXCLUSION is equal, we compare the upper bounds.
        if(uBound1 < uBound0) {
            solutionArr[currentObject] = 1;
        }
        else {
            solutionArr[currentObject] = 0;
        }
    }
    // Lower bound when object is NOT included, is lesser, so we expand this node.
    else { 
        solutionArr[currentObject] = 0;
    }

    /*
    // Upper bound when object is included, is lesser, so we expand this node.
    if(uBound1 < uBound0) {
        solutionArr[currentObject] = 1;
    }
    // Upper bound of both INCLUSION and EXCLUSION is equal.
    else if(uBound1 == uBound0) {
        // When lower bound of both INCLUSION and EXCLUSION is equal, we compare the upper bounds.
        if(lBound1 < lBound0) {
            solutionArr[currentObject] = 1;
        }
        else {
            solutionArr[currentObject] = 0;
        }
    }
    // Lower bound when object is NOT included, is lesser, so we expand this node.
    else { 
        solutionArr[currentObject] = 0;
    }
    */


    return branchAndBound(currentObject + 1, profitArr, weightArr, solutionArr, capacity, numOfObjects);
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

    //? Working test case
    // numOfObjects = 4;
    // capacity = 15;
    // int profitArr[5] = {0, 10, 10, 12, 18};
    // int weightArr[5] = {0, 2, 4, 6, 9};
    
    //? Working test case
    // numOfObjects = 4;
    // capacity = 12;
    // int profitArr[5] = {0, 30, 28, 20, 24};
    // int weightArr[5] = {0, 5, 7, 4, 2};

    //! Non-functioning test case
    numOfObjects = 5;
    capacity = 8;
    int profitArr[6] = {0, 1, 2, 5, 6, 2};
    int weightArr[6] = {0, 2, 3, 4, 5, 2};

    int* mappingArr = new int[numOfObjects + 1];
    for(int index = 1; index <= numOfObjects; index++) {
        mappingArr[index] = index;
    }

    sortArrays(mappingArr, profitArr, weightArr, numOfObjects);
    
    for(int index = 1; index <= numOfObjects; index++) {
        cout << profitArr[index] << " ";
    }
    cout << endl;
    for(int index = 1; index <= numOfObjects; index++) {
        cout << weightArr[index] << " ";
    }
    cout << endl;
    for(int index = 1; index <= numOfObjects; index++) {
        cout << mappingArr[index] << " ";
    }
    cout << endl;
    

    int* newArr = MappedToUnmapped(mappingArr, mappingArr, numOfObjects);
    for(int index = 1; index <= numOfObjects; index++) {
        cout << newArr[index];
    }
    cout << endl;
    
    int* untouchedSolutionArr = new int[numOfObjects + 1];

    int* mappedSolutionArr = branchAndBound(1,  profitArr, weightArr, untouchedSolutionArr, capacity, numOfObjects);

    cout << "\nSolutions in form of binary, where 0 means the object was not included and 1 means the object was included:\n(";

    int* unmappedSolutionArr = MappedToUnmapped(mappedSolutionArr, mappingArr, numOfObjects);

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
            printf("%4d\n", unmappedSolutionArr[objectNum]);
        }
        else {
            printf("%4d, ", unmappedSolutionArr[objectNum]);
        }
    }
}

int main() {
    knapsack01();

    return 0;
}