#include <iostream>
#include <limits.h>
using namespace::std;

// We will assume the smallest numbered vertex is the source and the largest numbered vertex is the sink

//? Path matrix is a binary matrix used to tell us the route of the augmented path

// Function for dynamically allocating space for arrays
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

void printArray(int *array, int numOfVertices) {
    for(int i = 0; i < numOfVertices; i++) {        
        printf("%2d ", array[i]);
    }
    cout << endl;
}


bool isForwardPushPossible(int fromVertex, int* excessFlow, int* height, int numOfVertices, int** capacities, int** flowMatrix) {
    for(int toVertex = numOfVertices - 1; toVertex >= 0; toVertex--) {
        if( (excessFlow[fromVertex] > 0) && (height[fromVertex] > height[toVertex]) && (capacities[fromVertex][toVertex] - flowMatrix[fromVertex][toVertex] >= 0) ) {
            return true;
        }
    }
    return false;
}

bool isFlowInMiddle(int* excessFlow, int numOfVertices) {
    for(int index = 1; index < numOfVertices - 1; index++) {
        if(excessFlow[index] != 0) {
            return true;
        }
    }

    return false;
}

void parentRelabel(int childVertex, int* excessFlow, int* height, int numOfVertices, int** capacities, int** flowMatrix) {
    for(int parentVertex = 0; parentVertex < numOfVertices; parentVertex++) {
        if((flowMatrix[parentVertex][childVertex] > 0) && (height[parentVertex] <= height[childVertex]) ) {
            height[parentVertex] = height[childVertex] + 1;
            parentRelabel(parentVertex, excessFlow, height, numOfVertices, capacities, flowMatrix);
        };        
    }    
}

void relabelForReversePush(int vertex, int* excessFlow, int* height, int numOfVertices, int** capacities, int** flowMatrix) {
    if(excessFlow[vertex] && !isForwardPushPossible(vertex, excessFlow, height, numOfVertices, capacities, flowMatrix)) {
        int minimumParent = INT_MAX;
        for(int parentVartex = 0; parentVartex < numOfVertices; parentVartex++) {
            if((flowMatrix[vertex][parentVartex] < 0) && height[vertex] <= height[parentVartex]) {
                minimumParent = minimumParent > height[parentVartex] ? height[parentVartex] : minimumParent;
            }
        }
        height[vertex] = minimumParent + 1;
    }
}

void relabelIfNeeded(int vertex, int* excessFlow, int* height, int numOfVertices, int** capacities, int** flowMatrix) {
    if(excessFlow[vertex] == 0) {
        return;
    }

    int relabelHeight = INT_MAX; // Push back to source so need to be higher than source
    for(int toVertex = 0; toVertex < numOfVertices; toVertex++) {
        if(capacities[vertex][toVertex] - flowMatrix[vertex][toVertex] >= 0) { // Some space left
            if (height[vertex] > height[toVertex]) {
                relabelHeight = height[vertex];
                break;
            }
            // Getting the most minimum relabelling height.
            relabelHeight = relabelHeight > (height[toVertex] + 1) ? (height[toVertex] + 1) : relabelHeight;
        }
    }

    // A relabel happened
    if(relabelHeight != height[vertex]) {
        height[vertex] = relabelHeight;

        // Relabelling the parents
        parentRelabel(vertex, excessFlow, height, numOfVertices, capacities, flowMatrix);
    }  
    // A relabel didn't happen and excess flow is still left so we make the vertex taller than its minimum parent.
    relabelForReversePush(vertex, excessFlow, height, numOfVertices, capacities, flowMatrix);
}



void reversePush(int vertex, int* excessFlow, int* height, int numOfVertices, int** capacities, int** flowMatrix) {
    // Finding minimum parent
    int minimumParent = INT_MAX;
    for(int parentVertex = 0; parentVertex < numOfVertices; parentVertex++) {
        if(flowMatrix[vertex][parentVertex] < 0) {
            minimumParent = minimumParent > parentVertex ? parentVertex : minimumParent;
        }
    }

    if(height[vertex] <= height[minimumParent]) {
        relabelForReversePush(vertex, excessFlow, height, numOfVertices, capacities, flowMatrix);
    }

    int reverseCap = -1 * flowMatrix[vertex][minimumParent];

    int reversePush = reverseCap > excessFlow[vertex] ? excessFlow[vertex] : reverseCap;
    excessFlow[vertex] -= reversePush;
    excessFlow[minimumParent] += reversePush;

}


void push(int fromVertex, int* excessFlow, int* height, int numOfVertices, int** capacities, int** flowMatrix) {

    int flowBeingSent = 0;

    for(int toVertex = numOfVertices - 1; toVertex >= 0; toVertex--) {
        if( (excessFlow[fromVertex] > 0) && (height[fromVertex] > height[toVertex]) ) {
            int leftOverCap = capacities[fromVertex][toVertex] - flowMatrix[fromVertex][toVertex];

            flowBeingSent = excessFlow[fromVertex] > leftOverCap ? leftOverCap : excessFlow[fromVertex];
        }

        excessFlow[fromVertex] -= flowBeingSent; // Excess flow added to fromVertex
        excessFlow[toVertex] += flowBeingSent; // Excess flow added to toVertex
    }

    // More forward pushes are possible so we don't call reverse push yet
    if(isForwardPushPossible(fromVertex, excessFlow, height, numOfVertices, capacities, flowMatrix)) {
        return;
    }
    else {
        reversePush(fromVertex, excessFlow, height, numOfVertices, capacities, flowMatrix);
    }
}


void pushRelabelAlgorithm(int numOfVertices) {

    // This is for simplicity.
    int sourceVertex = 0;
    int destVertex = numOfVertices - 1;

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

    int* height = new int[numOfVertices];
    int* excessFlow = new int[numOfVertices];

    int** flowMatrix = allocateSpaceForMatrix(numOfVertices);
    allMatrixPositionsZero(flowMatrix, numOfVertices);

    height[sourceVertex] = numOfVertices;

    for(int index = 0; index < numOfVertices; index++) {
        if(capacities[sourceVertex][index]) {
            flowMatrix[sourceVertex][index]  = capacities[sourceVertex][index];
            flowMatrix[index][sourceVertex]  = -1 * capacities[index][sourceVertex];
            excessFlow[index] = capacities[sourceVertex][index];
        }
    }


    // int* visited = allocateSpaceForArray(numOfVertices);
    // allArrayPositionsZero(visited, numOfVertices);

    
    while(isFlowInMiddle(excessFlow, numOfVertices)) {
        for(int vertex = 1; vertex < (numOfVertices - 1); vertex++) {
            
            relabelIfNeeded(vertex, excessFlow, height, numOfVertices, capacities, flowMatrix);
            push(vertex, excessFlow, height, numOfVertices, capacities, flowMatrix);
            printArray(excessFlow, numOfVertices);            
        }
    }

    printf("Flow transferred from source to sink: %d\n", excessFlow[destVertex] - excessFlow[sourceVertex]);


    // int reducedCapOfSource = 0;
    // //? Calculating the residual capacity of the source, after all iterations
    // for(int toVertex = 0; toVertex < numOfVertices; toVertex++) {
    //     reducedCapOfSource += capacities[0][toVertex];
    // }
    
    // cout << "Maximum total flow b/w source and sink is " << (capOfSource - reducedCapOfSource) << endl;

}



int main() {
    // 10 vertices, and 16 edges
    pushRelabelAlgorithm(10);
    
    return 0;
}