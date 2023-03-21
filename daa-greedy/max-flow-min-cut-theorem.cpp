#include <iostream>
#include <vector>
using namespace std;

/* 
Assumption is that `1` is the source vertex and `numOfVertices` is the sink vertex.

So:
- in-degree of vertex `1` should be 0
- out-degree of vertex `numOfVertices` should be 0 

*/
void printSet(vector<char> set) {
    cout << "{";
    for(auto element: set) {
        cout << element << ",";
    }
    cout << "}\n";
}

void cutSelection(int vertexToBePlaced, int* cuttingArray, int numOfVertices) {
    if(vertexToBePlaced == numOfVertices) {
        vector<char> S_set;
        vector<int> S_set_numbered;
        vector<char> T_set;
        vector<int> T_set_numbered;

        for(int index = 1; index <= numOfVertices; index++) {

            if(cuttingArray[index] == 0) {
                S_set.push_back(char(index+64));
                S_set_numbered.push_back(index);
            }
            else if(cuttingArray[index] == 1) {
                T_set.push_back(char(index+64));
                T_set_numbered.push_back(index);
            }

            // printf("%2d,", cuttingArray[index]);
        }   
        // printf("\n");
        cout << "\nS : ";
        printSet(S_set);
        cout << "T : ";
        printSet(T_set);


        return;
        // Print the cut of the graph here
    }

    // Placing the vertex in the first set
    cuttingArray[vertexToBePlaced] = 0;
    cutSelection(vertexToBePlaced + 1, cuttingArray, numOfVertices);

    // Placing the vertex in the second set
    cuttingArray[vertexToBePlaced] = 1;
    cutSelection(vertexToBePlaced + 1, cuttingArray, numOfVertices);

}


int** allocateSpaceForMatrix(int numOfVertices) {
    int** matrix = new int*[numOfVertices];
    for(int index = 0; index < numOfVertices; index++) {
        matrix[index] = new int[numOfVertices];
    }

    return matrix;
}

// void allArrayPositionsZero(int* array, int numOfElements) {
//     for(int index = 0; index < numOfElements; index++) {
//         array[index] = 0;
//     } 
// }

void allMatrixPositionsZero(int** matrix, int numOfVertices) {
    for(int i = 0; i < numOfVertices; i++) {
        for(int j = 0; j < numOfVertices; j++) {
            matrix[i][j] = 0;
        }
    }
}   


void maxFlowMinCutProblem(int numOfVertices, int numOfFlows) {
    // This matrix stores the initial and later on residual capacities of paths b/w vertices
    int** capacities = allocateSpaceForMatrix(numOfVertices);

    // Initializing all to be 0 before adding the capacities
    allMatrixPositionsZero(capacities, numOfVertices);

    //* Taking input of the non-zero capacities
    // int from;
    // int to;
    // int capacity;
    // for(int flowNum = 0; flowNum < numOfFlows; flowNum++) {
    //     cin >> from >> to >> capacity;
    //     capacities[from][to] = capacity;
    // }

    //* Test input
    capacities[0][1] = 12;
    capacities[0][2] = 3; 
    capacities[0][3] = 20;

    capacities[1][4] = 6; 
    capacities[1][6] = 5;

    capacities[2][4] = 4; 
    capacities[2][5] = 4;

    capacities[3][5] = 5; 
    capacities[3][8] = 10;

    capacities[4][6] = 3;
    capacities[4][7] = 3;

    capacities[5][7] = 5;
    capacities[5][8] = 3;

    capacities[6][9] = 13;

    capacities[7][9] = 10;

    capacities[8][9] = 12;

    // Taking numOfVertices + 1 so that indexing starts from 1, for simplicity
    int* cuttingArray = new int[numOfVertices + 1];

    // Source vertex dedicated as part of first set
    cuttingArray[1] = 0;
    // Sink vertex dedicated as part of second set
    cuttingArray[numOfVertices] = 1;

    /* 
    In between these 2 vertices, there are the vertices `2` to `numOfVertices - 1`

    So, a total of (numOfVertices - 2) vertices

    Number of possible cuts = 2^(numOfVertices - 2)
    Meaning either vertex being part of the either the first or the second set
    */

    // Selecting a cut for the second vertex
    cutSelection(2, cuttingArray, numOfVertices);


}
int main() {
    maxFlowMinCutProblem(10, 16);


    return 0;
}