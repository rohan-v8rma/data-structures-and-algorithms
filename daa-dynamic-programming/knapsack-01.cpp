#include <iostream>
using namespace std;

// This algorithm supports formatting for double digit weights and profits. Printing part would have to be modified for higher values

int max(int n1, int n2) {
    return ( (n1 > n2) ? n1 : n2);
}

void visualizeTabulation(
    int* profitArr, 
    int* weightArr, 
    int** table, 
    int capacity,
    int numOfObjects) {
    for(int n = 0; n <= numOfObjects; n++) {
        if( n == 0 ) {
            cout << "\n pi | wi || ";
        }
        else {
            printf(" %2d | %2d || ", profitArr[n], weightArr[n]);        
        }

        for(int w = 0; w <= capacity; w++) {
            printf("%3d | ", table[n][w]);
        }
        cout << "\n";
    }
}

void printSolutions(
    int objToSelect, 
    int* profitArr, 
    int* weightArr,
    int* solutionArr, 
    int** table, 
    int capacityLeft,
    int numOfObjects
    ) {

    // The base case will be reached in any scenario because the 0th row is initialized with 0 values.
    if( objToSelect == 0 ) {
        int totalProfit = 0;
        int totalWeight = 0;

        for(int object = 1; object <= numOfObjects; object++) {
            if(solutionArr[object]) {
                totalProfit += profitArr[object];
                totalWeight += weightArr[object];
            }            

            printf("%d, ", solutionArr[object]);
        }
        printf("\n\n");
        cout << "Total profit of Knapsack: " << totalProfit << endl;
        cout << "Total weight of Knapsack: " << totalWeight << endl;

        return;
    }

    // The current object is not selected.
    if(table[objToSelect][capacityLeft] == table[objToSelect - 1][capacityLeft]) {
        solutionArr[objToSelect] = 0;
        printSolutions(objToSelect - 1, profitArr, weightArr, solutionArr, table, capacityLeft, numOfObjects);
    }
    else if(capacityLeft - weightArr[objToSelect] >= 0) {

        // The current object is selected.
        if( table[objToSelect][capacityLeft] == (table[objToSelect - 1][capacityLeft - weightArr[objToSelect]] + profitArr[objToSelect]) ) {
            solutionArr[objToSelect] = 1;
            printSolutions(objToSelect - 1, profitArr, weightArr, solutionArr, table, capacityLeft - weightArr[objToSelect], numOfObjects);
        }
    }

    solutionArr[objToSelect] = 0;
}

void knapsackBnB() {
    int numOfObjects;
    int capacity;
    

    //* Code for taking inputs
    // cout << "Number of objects : ";
    // cin >> numOfObjects;
    
    // int* profitArr = new int[numOfObjects + 1];
    // int* weightArr = new int[numOfObjects + 1];

    // cout << "Enter objects in the following form (profit weight):\n2 3\n\n";
    // for(int index = 1; index <= numOfObjects; index++) {
    //     cout << "Object" << (index) << ": ";
    //     cin >> profitArr[index] >> weightArr[index];
    // }
    
    // cout << "Enter the capacity of the knapsack: ";
    // cin >> capacity;
    

    //* Values for testing the algorithm w/o input
    // numOfObjects = 5;
    // capacity = 8;
    // int profitArr[6] = {0, 1, 2, 5, 6, 2};
    // int weightArr[6] = {0, 2, 3, 4, 5, 2};

    numOfObjects = 7;
    capacity = 15;
    int profitArr[8] = {0, 10, 5, 15, 7, 6, 18, 3};
    int weightArr[8] = {0, 2, 3, 5, 7, 1, 4, 1};


    int** profitTable = new int*[numOfObjects + 1];
    for(int index = 0; index <= numOfObjects; index++) {
        profitTable[index] = new int[capacity + 1];
    }


    // n is till where the set of objects extends
    // w is the assumed capacity of the knapsack
    for(int n = 0; n <= numOfObjects; n++) {
        for(int w = 0; w <= capacity; w++) {

            if( n == 0 || w == 0 ) { // No objects are considered, so profit cannot be non-zero
                profitTable[n][w] = 0;
            }
            else if( w - weightArr[n] >= 0 ) { // If the current assumed knapsack capacity allows including the current object
                profitTable[n][w] = max(profitTable[n - 1][w], profitTable[n - 1][w - weightArr[n]] + profitArr[n]);
            }
            else { // As a last resort, we just take the profit value of the previous set of objects, of the same knapsack capacity
                profitTable[n][w] = profitTable[n - 1][w];
            }
            
        }
    }

    visualizeTabulation(profitArr, weightArr, profitTable, capacity, numOfObjects);


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
    printSolutions(numOfObjects, profitArr, weightArr, solutionArr, profitTable, capacity, numOfObjects);
}

int main() {
    knapsackBnB();

    return 0;
}