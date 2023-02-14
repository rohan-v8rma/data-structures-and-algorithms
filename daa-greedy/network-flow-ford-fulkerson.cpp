#include <iostream>
#include <limits.h>
using namespace::std;

// We will assume the smallest numbered vertex is the source and the largest numbered vertex is the sink

//? Path matrix is a binary matrix used to tell us the route of the augmented path


int* allocateSpaceForArray(int numOfElements) {
    return new int[numOfElements];
}

// Function for dynamically allocating space for matrices
int** allocateSpaceForMatrix(int numOfVertices) {
    int** matrix = new int*[numOfVertices];
    for(int index = 0; index < numOfVertices; index++) {
        matrix[index] = allocateSpaceForArray(numOfVertices);
    }

    return matrix;
}

void allArrayPositionsZero(int* array, int numOfElements) {
    for(int index = 0; index < numOfElements; index++) {
        array[index] = 0;
    } 
}

void allMatrixPositionsZero(int** matrix, int numOfVertices) {
    for(int i = 0; i < numOfVertices; i++) {
        for(int j = 0; j < numOfVertices; j++) {
            matrix[i][j] = 0;
        }
    }
}

void printMatrix(int **matrix, int numOfVertices) {
    for(int i = 0; i < 10; i++) {
        for(int j = 0; j < 10; j++) {
            printf("%2d ", matrix[i][j]);
        }
        cout << endl;
    }
    cout << endl;
}

// This function uses a backtracking approach to find the augmented path
int findAugmentedPath(int fromVertex, int** pathMatrix, int** capacities, int* visited, int numOfVertices) {
    // Marking the fromVertex as visited upon entering the recursive call to avoid an infinite cycle of visiting the same vertices without reaching the sink vertex.
    visited[fromVertex] = 1;

    // Base case (Sink vertex reached)
    if(fromVertex == numOfVertices - 1) {
        return 1; // Augmented path found
    }
    
    //? Resolved problem of multiple augmented paths (multiple 1s in a single vertex' array) in a single iteration by removing the extra for-loop deciding the `fromVertex`, when it was already decided by the recursive call.
    
    //* Checking only `to` vertices since fromVertex is part of the recursive call
    // Checking backwards for faster finding of path
    for(int to = numOfVertices - 1; to >= 0; to--) {
    // for(int to = 0; to < numOfVertices; to++) {

        // If capacity is left, b/w `from` and `to` vertex. Also the `to` vertex is not visited.
        if( (capacities[fromVertex][to] != 0) && (visited[to] == 0) ) {

            // Building up the augmented path
            pathMatrix[fromVertex][to] = 1;

            // Augmented path found in lower recursive calls
            if(findAugmentedPath(to, pathMatrix, capacities, visited, numOfVertices)) {
                return 1;
            }

            // Backtracking since augmented path is not possible from the `to` vertex
            pathMatrix[fromVertex][to] = 0;
        }
    } 
    

    // Marking the fromVertex as unmarked since we weren't able to find augmented path by visiting it.
    visited[fromVertex] = 0;
    // If control reaches here, no augmented path was found, so we return 0
    return 0;
}

void printAugmentedPath(int** pathMatrix, int numOfVertices) {
    cout << "Augmented path: ";
    for(int from = 0; from < numOfVertices; from++) {
        for(int to = 0; to < numOfVertices; to++) {
            if(pathMatrix[from][to] == 1) {
                cout << char(from + 65) << "->";
                break;
            }
        }   
    }

    cout << char(numOfVertices - 1 + 65) << endl;
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
    
    cout << "Augmented capacity: " << augmentedCap << "\n\n";
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
}



void fordFulkerson(int numOfVertices, int numOfFlows) {
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

    int capOfSource = 0;
    // Total flow is equal to total outflow out of source
    for(int toVertex = 0; toVertex < numOfVertices; toVertex++) {
        capOfSource += capacities[0][toVertex];
    }    

    int** pathMatrix = allocateSpaceForMatrix(numOfVertices);
    allMatrixPositionsZero(pathMatrix, numOfVertices);

    // This will be the minimum of all the capacities along the augmented path
    int augmentedCap = 0;

    int* visited = allocateSpaceForArray(numOfVertices);
    allArrayPositionsZero(visited, numOfVertices);

    // Finding augmented path from the source vertex
    while(findAugmentedPath(0, pathMatrix, capacities, visited, numOfVertices)) {
        
        // After the call to `findAugmentedPath`, `pathMatrix` has been updated with the augmented path values
        
        // So, printing the augmented path and calculating the augmented capacity:
        // printMatrix(pathMatrix, numOfVertices);
        printAugmentedPath(pathMatrix, numOfVertices);        
        augmentedCap = findAugmentedCap(pathMatrix, capacities, numOfVertices);
        
        // Updating the capacities according to the augmented capacity
        updateCapacities(augmentedCap, pathMatrix, capacities, numOfVertices);

        // Resetting the path matrix after an iteration, since we will be finding a new augmented path for the next iteration
        allMatrixPositionsZero(pathMatrix, numOfVertices);

        // Resetting the visited array as well
        allArrayPositionsZero(visited, numOfVertices);        
    }

    int reducedCapOfSource = 0;
    // Total flow is equal to total outflow out of source
    for(int toVertex = 0; toVertex < numOfVertices; toVertex++) {
        reducedCapOfSource += capacities[0][toVertex];
    }
    
    cout << "Maximum total flow b/w source and sink is " << (capOfSource - reducedCapOfSource) << endl;

}



int main() {
    // 10 vertices, and 16 edges
    fordFulkerson(10, 16);
    
    return 0;
}