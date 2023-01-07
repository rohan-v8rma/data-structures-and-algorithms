#include <iostream>
using namespace::std;

// N represents the number of Vertices.
const int N = 5;

// Let the color numbers start from 0. Uncolored vertices have color as -1.
// In this way, the color numbers range from 0 to `N - 1` (4, in this case).

void colorVertex(int vertexNumber, int colorNumber[N], int adjacencyMatrix[N][N], int possibleColors[N][N]) {
    
    // `toVertex` represents the vertices in the graph which can have 
    for(int toVertex = 0; toVertex < N; toVertex++) {
        if ( (adjacencyMatrix[vertexNumber][toVertex] == 1) && (colorNumber[toVertex] != -1) ) {
            // ( (There is an edge from `vertexNumber` to `toVertex`) && (The `toVertex` is COLORED) )
            
            // NOTE: The case where `vertexNumber` == `toVertex` is avoided over here because `toVertex` represents only COLOR vertices, but `vertexNumber` isn't colored yet.

            possibleColors[vertexNumber][colorNumber[toVertex]] = 0; 
            // Removing the color of the `toVertex` from the list of possible colors for `vertexNumber`
        }
    }

    int colorToBeUsed = 0;
    while(possibleColors[vertexNumber][colorToBeUsed] == 0) {
        colorToBeUsed++;
    }
    colorNumber[vertexNumber] = colorToBeUsed;
}

void graphColoring(int adjacencyMatrix[N][N]) {
    
    // We initialize the values at all indices with 1, because the indices which are (0 through 4); are the potential colors. 1 means that the color can be used to color the vertex; 0 means that it can't.
    
    // Worst case scenario is that all vertices are different colors, which is why we have 5 possible colors.

    int possibleColors[N][N] = {
        {1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1}
    };

    int colorNumber[N] = {-1, -1, -1, -1, -1};

    printf("Vertex | Color\n");
    for(int vertex = 0; vertex < N; vertex++) {
        colorVertex(vertex, colorNumber, adjacencyMatrix, possibleColors);
        printf("%4d   | %3d\n", vertex, colorNumber[vertex]);    
    }

    
}


int main() {

    // Creating a symmetrical adjacency matrix to represent an undirected graph
    int adjacencyMatrix[N][N] = {
        {1, 0, 1, 1, 0},
        {0, 1, 1, 0, 0},
        {1, 1, 0, 1, 1},
        {1, 0, 1, 0, 1},
        {0, 0, 1, 1, 0},
    };

    graphColoring(adjacencyMatrix);

    return 0;
}