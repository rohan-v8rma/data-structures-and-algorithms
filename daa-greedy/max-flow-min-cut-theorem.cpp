#include <iostream>
#include <math.h>
#include <limits.h>
#include <vector>
using namespace std;

/* 
Assumption is that `0` is the source vertex and `numOfVertices - 1` is the sink vertex.

So:
- in-degree of vertex `0` should be 0
- out-degree of vertex `numOfVertices - 1` should be 0 

*/
void printSet(vector<char> set) {
    cout << "{";
    for(auto element: set) {
        cout << element << ",";
    }
    cout << "}\n";
}


int cutCapacityCalculation(vector<int> S_set_numbered, vector<int> T_set_numbered, int** capacityMatrix) {\
    int cutCapacity = 0;

    for(auto& from: S_set_numbered) {
        for(auto& to: T_set_numbered) {
            // No condition for checking because when an edge doesn't exist, capacity is 0 so we can add that as well
            cutCapacity += capacityMatrix[from][to];
        }
    }

    return cutCapacity;
}

void cutSelection(int vertexToBePlaced, int* cuttingArray, int** capacityMatrix, int numOfVertices, int cutDetails[3]) {
    
    if(cutDetails[1] == cutDetails[2]) { // Max amount of cuts tested
        return;
    } 

    // Reached the base case where the set is selected for all vertices, except source and sink; whose sets are fixed.
    if(vertexToBePlaced == (numOfVertices - 1)) {
        vector<char> S_set;
        vector<int> S_set_numbered;
        vector<char> T_set;
        vector<int> T_set_numbered;

        for(int index = 0; index < numOfVertices; index++) {

            if(cuttingArray[index] == 0) {
                S_set.push_back(char(index+65));
                S_set_numbered.push_back(index);
            }
            else if(cuttingArray[index] == 1) {
                T_set.push_back(char(index+65));
                T_set_numbered.push_back(index);
            }
        }   
        // Printing the current cut
        cout << "\nS : ";
        printSet(S_set);
        cout << "T : ";
        printSet(T_set);

        int currentCutCapacity = cutCapacityCalculation(S_set_numbered, T_set_numbered, capacityMatrix);
        if(currentCutCapacity < cutDetails[0]) {
            if(cutDetails[0] == INT_MAX) {
                printf("This is the first cut tested with a cut capacity of (%d)\n", currentCutCapacity);
            }
            else {
                printf("The current cut capacity of (%d) is less than the minimum reached till now which is (%d)\n", currentCutCapacity, cutDetails[0]);
            }
            
            printf("Updating the minimum cut capacity...\n");
            cutDetails[0] = currentCutCapacity;
        }
        else if(cutDetails[0] <= currentCutCapacity) {
            printf("The current cut capacity of (%d) is more than or equal to the minimum reached till now which is (%d)\n", currentCutCapacity, cutDetails[0]);
            printf("Moving on without updating...\n");
        }
        
        // 1 more cut tested
        cutDetails[1] += 1;

        return;
    }

    // Placing the vertex in the first set
    cuttingArray[vertexToBePlaced] = 0;
    cutSelection(vertexToBePlaced + 1, cuttingArray, capacityMatrix, numOfVertices, cutDetails);

    // Placing the vertex in the second set
    cuttingArray[vertexToBePlaced] = 1;
    cutSelection(vertexToBePlaced + 1, cuttingArray, capacityMatrix, numOfVertices, cutDetails);

}


int** allocateSpaceForMatrix(int numOfVertices) {
    int** matrix = new int*[numOfVertices];
    for(int index = 0; index < numOfVertices; index++) {
        matrix[index] = new int[numOfVertices];
    }

    return matrix;
}


void allMatrixPositionsZero(int** matrix, int numOfVertices) {
    for(int i = 0; i < numOfVertices; i++) {
        for(int j = 0; j < numOfVertices; j++) {
            matrix[i][j] = 0;
        }
    }
}   

// Provide numOfCutsToBeTested as 0 to test all possible cuts.

void maxFlowMinCutProblem(int numOfVertices, int numOfFlows, int numOfCutsToBeTested=0) {

    if (numOfCutsToBeTested == 0) {
        // We are keeping first and last vertex in separate sets, that is why we subtracted 2
        numOfCutsToBeTested = pow(2, numOfVertices - 2);
    }

    // This matrix stores the initial and later on residual capacities of paths b/w vertices
    int** capacityMatrix = allocateSpaceForMatrix(numOfVertices);

    // Initializing all to be 0 before adding the capacities
    allMatrixPositionsZero(capacityMatrix, numOfVertices);

    //* Taking input of the non-zero capacities
    // int from;
    // int to;
    // int capacity;
    // for(int flowNum = 0; flowNum < numOfFlows; flowNum++) {
    //     cin >> from >> to >> capacity;
    //     capacityMatrix[from][to] = capacity;
    // }

    //* Test input
    capacityMatrix[0][1] = 12;
    capacityMatrix[0][2] = 3; 
    capacityMatrix[0][3] = 20;

    capacityMatrix[1][4] = 6; 
    capacityMatrix[1][6] = 5;

    capacityMatrix[2][4] = 4; 
    capacityMatrix[2][5] = 4;

    capacityMatrix[3][5] = 5; 
    capacityMatrix[3][8] = 10;

    capacityMatrix[4][6] = 3;
    capacityMatrix[4][7] = 3;

    capacityMatrix[5][7] = 5;
    capacityMatrix[5][8] = 3;

    capacityMatrix[6][9] = 13;

    capacityMatrix[7][9] = 10;

    capacityMatrix[8][9] = 12;

    int* cuttingArray = new int[numOfVertices];

    // Source vertex dedicated as part of first set
    cuttingArray[0] = 0;
    // Sink vertex dedicated as part of second set
    cuttingArray[numOfVertices - 1] = 1;

    /* 
    In between these 2 vertices, there are the vertices `2` to `numOfVertices - 1`

    So, a total of (numOfVertices - 2) vertices

    Number of possible cuts = 2^(numOfVertices - 2)
    Meaning either vertex being part of the either the first or the second set
    */

   int numOfCutsAlreadyTested = 0;

    int minCutCapacity = INT_MAX;

    int cutDetails[3] = {minCutCapacity, numOfCutsAlreadyTested, numOfCutsToBeTested};

    // Selecting a cut for the second vertex
    cutSelection(1, cuttingArray, capacityMatrix, numOfVertices, cutDetails);

    cout << "\nMax possible flow from source to sink is : " << cutDetails[0] << endl;
}
int main() {
    // We are testing 8 cuts as we need to test atleast 4 cuts
    maxFlowMinCutProblem(10, 16, 8);


    return 0;
}