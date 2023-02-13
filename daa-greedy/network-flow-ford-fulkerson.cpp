#include <iostream>
#include <limits.h>
using namespace::std;

// We will assume the smallest numbered vertex is the source and the largest numbered vertex is the sink

//? Path matrix is a binary matrix used to tell us the route of the augmented path

int sols = 0;

// Function for dynamically allocating space for matrices
int** allocateSpaceForMatrix(int numOfVertices) {
    int** matrix = new int*[numOfVertices];
    for(int index = 0; index < numOfVertices; index++) {
        matrix[index] = new int[numOfVertices];
    }

    return matrix;
}

void allPositionsZero(int** matrix, int numOfVertices) {
    for(int i = 0; i < numOfVertices; i++) {
        for(int j = 0; j < numOfVertices; j++) {
            // cout << "hello";
            matrix[i][j] = 0;
        }
    }
}

void printMatrix(int **matrix, int numOfVertices) {
    for(int i = 0; i < 10; i++) {
        for(int j = 0; j < 10; j++) {
            cout << matrix[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
}

int findAugmentedPath(int fromVertex, int** pathMatrix, int** capacities, int numOfVertices) {
    
    // Base case (Sink vertex reached)
    if(fromVertex == numOfVertices - 1) {
        // printMatrix(pathMatrix, numOfVertices);
        return 1; // Augmented path found
    }

    for(int from = fromVertex; from < numOfVertices; from++) {
    
        // Checking backwards for faster finding of path
        for(int to = numOfVertices - 1; to >= 0; to--) {
        // for(int to = 0; to < numOfVertices; to++) {

            if(sols == 2) {
                cout << from << " " << to << endl;
            } 

            // If capacity is left, b/w from and to vertex
            if(capacities[from][to] != 0) {

                // Building up the augmented path
                pathMatrix[from][to] = 1;

                // Augmented path found in lower recursive calls
                if(findAugmentedPath(to, pathMatrix, capacities, numOfVertices)) {
                    return 1;
                }

                // Backtracking since augmented path is not possible from the `to` vertex
                pathMatrix[from][to] = 0;
            }
        }
    }

    // If control reaches here, no augmented path was found, so we return 0
    return 0;
}

int findAugmentedCap(int** pathMatrix, int** capacities, int numOfVertices) {
    int augmentedCap = INT_MAX;
    for(int from = 0; from < numOfVertices; from++) {
        for(int to = 0; to < numOfVertices; to++) {
            if(pathMatrix[from][to] == 1) {
                augmentedCap = (augmentedCap < capacities[from][to] ) ? augmentedCap : capacities[from][to];
            }
        }
    }
    
    cout << augmentedCap << endl;
    return augmentedCap;
}

void updateCapacities(int augmentedCap, int** pathMatrix, int** capacities, int numOfVertices) {
    for(int from = 0; from < numOfVertices; from++) {
        for(int to = 0; to < numOfVertices; to++) {
            if(pathMatrix[from][to] == 1) {
                // Since flow is being sent `from` -> `to`, the capacity of that path reduces.
                capacities[from][to] -= augmentedCap;
                // The path in the opposite direction now has the capability of sending back the flow it has received, thereby getting an increase in capacity.
                capacities[to][from] += augmentedCap;
            }
        }
    }
    printMatrix(pathMatrix, numOfVertices);
    printMatrix(capacities, numOfVertices);
    sols++;
}



void fordFulkerson(int numOfVertices, int numOfFlows) {
    int** capacities = allocateSpaceForMatrix(numOfVertices);

    // Initializing all to be 0 before adding the capacities
    allPositionsZero(capacities, numOfVertices);

    //* Taking input of the non-zero capacities
    // int from;
    // int to;
    // int capacity;
    // for(int flowNum = 0; flowNum < numOfFlows; flowNum++) {
    //     cin >> from >> to >> capacity;
    //     capacities[from][to] = capacity;
    // }

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


    int** pathMatrix = allocateSpaceForMatrix(numOfVertices);
    allPositionsZero(pathMatrix, numOfVertices);

    // This will be the minimum of all the capacities along the augmented path
    int augmentedCap = 0;

    // Finding augmented path from the source vertex
    while(findAugmentedPath(0, pathMatrix, capacities, numOfVertices)) {
        // After the call to `findAugmentedPath`, `pathMatrix` has been updated with the augmented path values
        augmentedCap = findAugmentedCap(pathMatrix, capacities, numOfVertices);
        // Updating the capacities according to the augmented capacity
        updateCapacities(augmentedCap, pathMatrix, capacities, numOfVertices);

        // Resetting the path matrix after an iteration
        allPositionsZero(pathMatrix, numOfVertices);
    }

    int totalFlow = 0;
    // Total flow is equal to total outflow out of source
    for(int toVertex = 0; toVertex < numOfVertices; toVertex++) {
        totalFlow += capacities[0][toVertex];
    }
    
    cout << "Maximum total flow b/w source and sink is " << totalFlow;
    

}

int main() {
    // int** capacities = allocateSpaceForMatrix(10);
    // allPositionsZero(capacities, 10);

    // int** pathMatrix = allocateSpaceForMatrix(10);
    // allPositionsZero(pathMatrix, 10);
    

    // capacities[0][1] = 12;
    // capacities[0][2] = 3; 
    // capacities[0][3] = 20;

    // capacities[1][4] = 6; 
    // capacities[1][6] = 5;

    // capacities[2][4] = 4; 
    // capacities[2][5] = 4;

    // capacities[3][5] = 5; 
    // capacities[3][8] = 10;

    // capacities[4][6] = 3;
    // capacities[4][7] = 3;

    // capacities[5][7] = 5;
    // capacities[5][8] = 3;

    // capacities[6][9] = 13;

    // capacities[7][9] = 10;

    // capacities[8][9] = 12;

    // printMatrix(capacities, 10);

    // findAugmentedPath(0, pathMatrix, capacities, 10);

    // printMatrix(pathMatrix, 10);

    fordFulkerson(10, 16);
    
    return 0;
}