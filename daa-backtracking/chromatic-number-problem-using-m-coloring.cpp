#include <iostream>
using namespace std;

/* 
The following approach to graph coloring is different from the previous two. 

This is because we had no limitations on the number of available colors in the last 2 approaches, but here we are limited to M colors. We need to determine whether M colors are sufficient for coloring the graph.
*/

#define N 14

bool coloringPossible(int vertexNum, int colorTBU, bool graph[N][N], int n, int* colorArray) {
    for(int toVertex = 0; toVertex < n; toVertex++) {
        
        if( (graph[vertexNum][toVertex] == 1) && (colorArray[toVertex] == colorTBU) ) {
            // We don't have an `if` condition checking whether `vertexNum == toVertex` because `vertexNum` isn't colored yet, so the second condition would automatically take care of that.    
            return false;
        }    
    }
    
    // If the control of the program reaches here, coloring using `colorTBU` is possible; so we return `true`.
    return true;
}

bool colorVertex(int vertexNum, bool graph[N][N], int m, int n, int* colorArray) {
    
    if(vertexNum == n) { // We've colored all the vertices (0 to n-1) without backtracking; so upon reaching the nth non-existent vertex, we return true to signify that it is possible to color the give graph using m colors
        return true;
    }
    
    for(int color = 0; color < m; color++) {
        if(coloringPossible(vertexNum, color, graph, n, colorArray)) {
            //! Imagine this as a tree structure where; before following a particular branch of the tree in our attempt to find a solution, we first check whether the branch we are about to select has a dead end. Dead end means the color we are trying to use would violate the constraints of the graph coloring problem.

            colorArray[vertexNum] = color; // Since coloring is possible, we use that color.

            if(colorVertex(vertexNum + 1, graph, m, n, colorArray)) {
                
                //? Printing the coloring result, at the top-most level of the space tree, when solution is found (returning `true` below).
                if(vertexNum == 0) {
                    printf("Vertex | Color\n");
                    for(int vertex = 0; vertex < n; vertex++) {
                        // Printing the vertices as letters.
                        printf("%4c   | %3d\n", vertex + 65, colorArray[vertex]);    
                    }
                }

                return true; // it was possible to color the child using `m` colors, so solution found; so returning `true`.
            }          
        }
    }
    
    colorArray[vertexNum] = -1; //! Removing the color of the vertex as a backtracking step
    
    //? We need to remove the color from this child node since returning `false` means backtracking is occurring due to solution not being found in this branch.
    //? This is because; upon recoloring a parent node above it, the faultily colored child node (in the case color was not removed from the child node) will result in wrong information by the `coloringPossible()` function that it is not possible to color the parent node using that color, when in actuality it is ok to use that color because we have backtracked from the point at which the child node was colored using the same color.
    
    if(vertexNum == 0) { // About to backtrack from the topmost level of the space tree, meaning no solution to the m-coloring problem was found.
        printf("It is not possible to color the given graph using `%d` colors\n", m);
    }
    return false; // Backtracking.

}

// Function to determine if graph can be coloured with at most M colours such that no two adjacent vertices of graph are coloured with same colour.
bool mColoringProblem(bool graph[N][N], int m, int n) {
    
    int* colorArray = new int[n];
    
    for(int index = 0; index < n; index++) {
        colorArray[index] = -1; // -1 means the vertex is uncolored.
    }
    
    return colorVertex(0, graph, m, n, colorArray);
    // your code here
}

void findChromaticNumber(bool graph[N][N], int numOfVertices) {
    for(int numOfColors = 1; numOfColors <= numOfVertices; numOfColors++) {
        if(mColoringProblem(graph, numOfColors, numOfVertices)) {
            printf("Chromatic number of the graph is %d\n", numOfColors);
            break;
        }
    }

}

int main() {
    bool adjacencyMatrix[N][N] = {
        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1}, 
        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0}, 
        {0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0}, 
        {0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0}, 
        {0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0}, 
        {0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1}, 
        {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1}, 
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0}
    };

    findChromaticNumber(adjacencyMatrix, 14);

    return 0;
}