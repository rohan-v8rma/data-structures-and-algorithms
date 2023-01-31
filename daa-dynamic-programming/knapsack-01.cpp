#include <iostream>
using namespace std;

// This algorithm supports formatting for double digit weights and profits. Printing part would have to be modified for higher values

int max(int n1, int n2) {
    return ( (n1 > n2) ? n1 : n2);
}

void visualizeTabulation(int* profitArr, int* weightArr, int** table, int numOfObjects, int capacity) {
    for(int n = 0; n <= numOfObjects; n++) {
        if( n == 0 ) {
            cout << " pi | wi | ";
        }
        else {
            printf(" %2d | %2d | ", profitArr[n], weightArr[n]);        
        }

        for(int w = 0; w <= capacity; w++) {
            printf("%3d | ", table[n][w]);
        }
        cout << "\n";
    }
}

void printSolutions(int objToSelect, int capLeft, int* solutionArr, int** table, int numOfObjects, int* profitArr, int* weightArr) {

    // The base case will be reached in any scenario because the 0th row is initialized with 0 values.
    if( objToSelect == 0) {
        for(int index = 1; index <= numOfObjects; index++) {
            printf("%d, ", solutionArr[index]);
        }
        printf("\n");
        return;
    }

    // The current object is not selected.
    if(table[objToSelect][capLeft] == table[objToSelect - 1][capLeft]) {
        solutionArr[objToSelect] = 0;
        printSolutions(objToSelect - 1, capLeft, solutionArr, table, numOfObjects, profitArr, weightArr);
    }
    else if(capLeft - weightArr[objToSelect] >= 0) {

        // The current object is selected.
        if( table[objToSelect][capLeft] == (table[objToSelect - 1][capLeft - weightArr[objToSelect]] + profitArr[objToSelect]) ) {
            solutionArr[objToSelect] = 1;
            printSolutions(objToSelect - 1, capLeft - weightArr[objToSelect], solutionArr, table, numOfObjects, profitArr, weightArr);
        }
    }

    solutionArr[objToSelect] = 0;
}

void knapsack01() {
    int numOfObjects;
    
    //* Code for taking input of the details of the objects
    // cout << "Number of objects : ";
    // cin >> numOfObjects;

    // cout << "Enter objects in the following form (profit weight):\n2 3\n\n";

    // int* profitArr = new int[numOfObjects + 1];
    // int* weightArr = new int[numOfObjects + 1];

    // for(int index = 1; index <= numOfObjects; index++) {
    //     cout << Object << (index + 1) :;
    //     cin >> profitArr[index] >> weightArr[index];
    // }


    int capacity;
    //* Code for taking input of the capacity of knapsack
    // cout << "Enter the capacity of the knapsack: ";
    // cin >> capacity;


    //* Values for testing the algorithm w/o input
    numOfObjects = 5;
    capacity = 8;
    int profitArr[6] = {0, 1, 2, 5, 6, 2};
    int weightArr[6] = {0, 2, 3, 4, 5, 2};


    int** profitValues = new int*[numOfObjects + 1];
    for(int index = 0; index <= numOfObjects; index++) {
        profitValues[index] = new int[capacity + 1];
    }


    // n is till where the set of objects extends
    // w is the assumed capacity of the knapsack
    for(int n = 0; n <= numOfObjects; n++) {
        for(int w = 0; w <= capacity; w++) {

            if( n == 0 || w == 0 ) { // No objects are considered, so profit cannot be non-zero
                profitValues[n][w] = 0;
            }
            else if( w - weightArr[n] >= 0 ) { // If the current assumed knapsack capacity allows including the current object
                profitValues[n][w] = max(profitValues[n - 1][w], profitValues[n - 1][w - weightArr[n]] + profitArr[n]);
            }
            else { // As a last resort, we just take the profit value of the previous set of objects, of the same knapsack capacity
                profitValues[n][w] = profitValues[n - 1][w];
            }
            
        }
    }

    visualizeTabulation(profitArr, weightArr, profitValues, numOfObjects, capacity);


    int solutionArr[numOfObjects + 1];
    for(int index = 0; index <= numOfObjects; index++) {
        solutionArr[index] = 0;
    }
    cout << "\nSolutions in form of binary, where 0 means the object was not included and 1 means the object was included:\n(";


    for(int objectNum = 1; objectNum <= numOfObjects; objectNum++) {
        if(objectNum == numOfObjects) {
            printf("Ob%2d):\n", objectNum);
        }
        else {
            printf("Ob%2d, ", objectNum);
        }
    }
    printSolutions(numOfObjects, capacity, solutionArr, profitValues, numOfObjects, profitArr, weightArr);
}

int main() {
    knapsack01();

    return 0;
}