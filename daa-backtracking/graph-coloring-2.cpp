#include <iostream>
using namespace::std;

// N represents the number of Vertices.
const int N = 5;

// Let the color numbers start from 0. Uncolored vertices have color as -1.
// In this way, the color numbers range from 0 to `N - 1` (4, in this case).

//! Imagine this as a tree structure where; before following a particular branch of the tree in our attempt to find a solution, we first check whether the branch we are about to select has a dead end. Dead end means the color we are trying to use would violate the constraints of the graph coloring problem.

int colorVertex(int vertexNumber, int colorToBeUsed, int adjacencyMatrix[N][N], int colorNumber[N]) {
    
    // `toVertex` represents the vertices in the graph which can have 
    for(int toVertex = 0; toVertex < N; toVertex++) {
        if ( (adjacencyMatrix[vertexNumber][toVertex] == 1) && (colorNumber[toVertex] == colorToBeUsed) ) {
            // ( (There is an edge from `vertexNumber` to `toVertex`) && 
            // (The `toVertex` is COLORED using the same color as the one we wish to use, which is NOT viable in graph coloring problem) )

            // Returning 0 represents backtracking.
            return 0;
        }
    }

    // If control of the program reaches here, it means no adjacent vertices of `vertexNumber` have the same color as the one we wish to use.
    colorNumber[vertexNumber] = colorToBeUsed;
    
    // Now, trying to color the next vertex, provided it exists.
    if(vertexNumber + 1 <= N) {
            for(int potentialColor = 0; potentialColor < N; potentialColor++) {
            
            if(colorVertex(vertexNumber + 1, potentialColor, adjacencyMatrix, colorNumber)) {
                // If the `potentialColor` was used to color the vertex, i.e. 1 was returned, we break the loop of trying more colors.
                break;
            }
            else {
                // If 0 was returned we continue.
                continue;
            }
        }
    }
    

    return 1;
}

void graphColoring(int adjacencyMatrix[N][N]) {


    // We initialize the values at all indices with 1, because the indices which are (0 through 4); represent the potential colors. 1 means that the color can be used to color the vertex; 0 means that it can't.
    
    // Worst case scenario is that all vertices are different colors, which is why we have 5 possible colors.
   
    int colorNumber[N] = {-1, -1, -1, -1, -1};

    // Since no vertices are colored yet, we can choose to color the 0th vertex with the `0` color. Or any other color
    colorVertex(0, 0, adjacencyMatrix, colorNumber);
    // Now, the coloring of other vertices will happen internally using recursion and backtracking.


    printf("Vertex | Color\n");
    for(int vertex = 0; vertex < N; vertex++) {
        // Printing the vertices as letters.
        printf("%4c   | %3d\n", vertex + 65, colorNumber[vertex]);    
    }
}


int main() {

    // Creating a symmetrical adjacency matrix to represent an undirected graph
    int adjacencyMatrix[N][N] = {
        {1, 0, 0, 1, 0},
        {0, 1, 1, 0, 0},
        {0, 1, 0, 1, 1},
        {1, 0, 1, 0, 1},
        {0, 0, 1, 1, 0},
    };

    graphColoring(adjacencyMatrix);

    return 0;
}