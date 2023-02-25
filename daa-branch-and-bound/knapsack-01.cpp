#include <iostream>
using namespace ::std;

/* 
The objects are numbered from 1 to `numOfObjects`, getting us a total of numOfObjects.

The base case of the `branchAndBound()` function is when (currentObject == numOfObjects+1)
*/

//! This supports weights and profits upto 3 digits. Above that, print formatting should be modified.
void printObjectDetails(int* profitArr, int* weightArr, int numOfObjects) {
    cout << "\n";

    for(int object = 0; object <= numOfObjects; object++) {
        if(object == 0) {
            cout << "Object No. | ";
        }
        else {
            printf("%3d | ", object);
        }
    }
    cout << "\n\n";
    
    for(int object = 0; object <= numOfObjects; object++) {
        if(object == 0) {
            cout << "Profit Val | ";
        }
        else {
            printf("%3d | ", profitArr[object]);
        }
    }
    cout << "\n";
    
    for(int object = 0; object <= numOfObjects; object++) {
        if(object == 0) {
            cout << "Weight Val | ";
        }
        else {
            printf("%3d | ", weightArr[object]);
        }
    }
    cout << "\n";
    cout << endl;

}


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

// This function also prints total knapsack profit and weight, along with mapping the solution back to the original order.
int* MappedToUnmapped(
    int* mappedSolutionArr, 
    int* profitArr,
    int* weightArr,
    int* mappingArray, 
    int capacityLeft,
    int numOfObjects
    ) {
        int originalCapacity = capacityLeft;
        int* unmappedSolutionArr = new int[numOfObjects + 1];

        int totalProfit = 0;
        for(int index = 1; index <= numOfObjects; index++) {
            if(mappedSolutionArr[index]) {
                totalProfit += profitArr[index];
            }
        }

        cout << "\nTotal Knapsack Profit: " << totalProfit;
        /* 
        It is possible that by exploring nodes using the HEURISTIC Approach, we reach an impossible solution (total weight exceeding the capacity of the knapsack). This is just a characteristic of BnB approach. 
        
        So, when converting the Mapped to Unmapped array, we need to make the acquired solution viable.
        */
        for(int object = 1; object <= numOfObjects; object++) {

            // The object is actually included in the mapped solution AND it is possible to add it WHOLE in the knapsack
            if(mappedSolutionArr[object] && weightArr[object] <= capacityLeft) {
                capacityLeft -= weightArr[object];
            }
            else {
                mappedSolutionArr[object] = 0;
            }
        }

        cout << "\nTotal Knapsack Weight: " << originalCapacity - capacityLeft << endl;
        
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

    for(int object = 1; object <= numOfObjects; object++) {

        if( 
            // These are the objects whose INCLUSION or EXCLUSION is decided in upper nodes of the current sub-tree.
            ( (object <= currentObject) && partialSolutionArr[object] ) 
            || 
            // These are the objects whose INCLUSION or EXCLUSION is yet to be decided, so we keep adding WHOLE objects to the bound value until there is no space left.
            (object > currentObject) 
        ) {
            //? We check if there is space to accomodate the WHOLE object in the knapsack. No fractions of objects are added in UPPER BOUND.
            if(weightArr[object] > capacityLeft) {
                break; //! Breaking the loop if no space is left in the knapsack
            }
            else {
                upperBound -= profitArr[object];
                capacityLeft -= weightArr[object];
            }            
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

    for(int object = 1; object <= numOfObjects; object++) {

        if( 
            // These are the objects whose INCLUSION or EXCLUSION is decided in upper nodes of the current sub-tree.
            ( (object <= currentObject) && partialSolutionArr[object] ) 
            || 
            // These are the objects whose INCLUSION or EXCLUSION is yet to be decided, so we keep adding WHOLE objects to the bound value until there is no space left.
            (object > currentObject) 
        ) {
            //? We check if there is space to accomodate the WHOLE object in the knapsack. Fractions of objects are allowed to be added in LOWER BOUND.
            if(weightArr[object] > capacityLeft) {
                lowerBound -= (capacityLeft / (double)(weightArr[object])) * profitArr[object];
                capacityLeft = 0;
                break; //! Breaking the loop since no space is left in the knapsack
            }
            else {
                lowerBound -= profitArr[object];
                capacityLeft -= weightArr[object];  
            }
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

    //! This only works if the original array provided is already sorted in decreasing order of density. To make this consistent otherwise, the code needs to be modified.
    cout << "Object " << currentObject;
    cout << "\nINCLUDED:\n";
    cout << "Upper Bound: " << uBound1;
    cout << ", Lower Bound: " << lBound1;
    cout << "\nNOT INCLUDED:\n";
    cout << "Upper Bound: " << uBound0;
    cout << ", Lower Bound: " << lBound0;
    cout << "\n\n";

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
    numOfObjects = 4;
    capacity = 15;
    int profitArr[5] = {0, 10, 10, 12, 18};
    int weightArr[5] = {0, 2, 4, 6, 9};

    //! Non-functioning test case (DUE TO HEURISTIC APPROACH)
    // numOfObjects = 5;
    // capacity = 8;
    // int profitArr[6] = {0, 1, 2, 5, 6, 2};
    // int weightArr[6] = {0, 2, 3, 4, 5, 2};
    cout << "\nKnapsack Capacity: " << capacity << "\n";
    printObjectDetails(profitArr, weightArr, numOfObjects);

    int* mappingArr = new int[numOfObjects + 1];
    for(int index = 1; index <= numOfObjects; index++) {
        mappingArr[index] = index;
    }

    sortArrays(mappingArr, profitArr, weightArr, numOfObjects);

    int* untouchedSolutionArr = new int[numOfObjects + 1];

    int* mappedSolutionArr = branchAndBound(1,  profitArr, weightArr, untouchedSolutionArr, capacity, numOfObjects);

    int* unmappedSolutionArr = MappedToUnmapped(mappedSolutionArr, profitArr, weightArr, mappingArr, capacity, numOfObjects);

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