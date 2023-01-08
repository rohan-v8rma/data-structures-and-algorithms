#include <iostream>
using namespace::std;

// N represents the number of Vertices.
const int N = 11;

//? This is a simplified case of M-coloring problem without any backtracking; since number of colors is equal to number of vertices, so each leaf node is a guaranteed solution.
// Let the color numbers start from 0. Uncolored vertices have color as -1.
// In this way, the color numbers range from 0 to `N - 1` (4, in this case).

//! Note how the current test case is solved using 5 colors, but would fail if there was a constraint of just 4 colors. 
//* In the constraint case, the below algorithm won't be able to find a solution, but a backtracking algorithm would.


int colorVertex(int vertexNumber, int colorToBeUsed, int adjacencyMatrix[N][N], int colorArray[N]) {
    
    // `toVertex` represents the vertices in the graph which can have 
    for(int toVertex = 0; toVertex < N; toVertex++) {
        if ( (adjacencyMatrix[vertexNumber][toVertex] == 1) && (colorArray[toVertex] == colorToBeUsed) ) {
            // ( (There is an edge from `vertexNumber` to `toVertex`) && 
            // (The `toVertex` is COLORED using the same color as the one we wish to use, which is NOT viable in graph coloring problem) )

            // Returning 0 represents backtracking.
            return 0;
        }
    }

    // If control of the program reaches here, it means no adjacent vertices of `vertexNumber` have the same color as the one we wish to use.
    colorArray[vertexNumber] = colorToBeUsed;
    
    // Now, trying to color the next vertex, provided it exists.
    if(vertexNumber + 1 <= N) {
            for(int potentialColor = 0; potentialColor < N; potentialColor++) {
            
            if(colorVertex(vertexNumber + 1, potentialColor, adjacencyMatrix, colorArray)) {
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
   
    int colorArray[N]; // -1 means the vertices (represented by the index positions) aren't colored yet.

    for(int index = 0; index < N; index++) {
        colorArray[index] = N;
    }
    // Since no vertices are colored yet, we can choose to color the 0th vertex with the `0` color. Or any other color
    colorVertex(0, 0, adjacencyMatrix, colorArray);
    // Now, the coloring of other vertices will happen internally using recursion and backtracking.


    printf("Vertex | Color\n");
    for(int vertex = 0; vertex < N; vertex++) {
        // Printing the vertices as letters.
        printf("%4c   | %3d\n", vertex + 65, colorArray[vertex]);    
    }
}


int main() {

    // Creating a symmetrical adjacency matrix to represent an undirected graph
    // int adjacencyMatrix[N][N] = {
    //     {1, 0, 0, 1, 0},
    //     {0, 1, 1, 0, 0},
    //     {0, 1, 0, 1, 1},
    //     {1, 0, 1, 0, 1},
    //     {0, 0, 1, 1, 0},
    // };

    int adjacencyMatrix[N][N] = {
        {0,0,1,1,0,0,1,1,0,0,1},
        {0,0,1,0,0,1,0,0,1,0,0},
        {1,1,0,0,0,0,0,1,1,0,0},
        {1,0,0,0,0,1,1,0,0,1,1},
        {0,0,0,0,0,0,0,0,1,1,1},
        {0,1,0,1,0,0,0,1,0,1,1},
        {1,0,0,1,0,0,0,1,0,1,1},
        {1,0,1,0,0,1,1,0,1,0,1},
        {0,1,1,0,1,0,0,1,0,0,0},
        {0,0,0,1,1,1,1,0,0,0,1},
        {1,0,0,1,1,1,1,1,0,1,0},
    };
    graphColoring(adjacencyMatrix);

    return 0;
}