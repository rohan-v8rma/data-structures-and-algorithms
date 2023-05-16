#include <iostream>
using namespace std;

#define N 14

int colorsUsedInCurrentSolution(int* colorArray, int numOfVertices) {
    int colorsUsed = -1;

    for(int index = 0; index < numOfVertices; index++) {
        if(colorArray[index] > colorsUsed) {
            colorsUsed = colorArray[index];
        }
    }

    colorsUsed++; // Since color number starts from 0.

    return colorsUsed;
}

void updateChromaticNumber(int* colorArray, int numOfVertices, int* chromaticNumber) {
    int colorsUsed = colorsUsedInCurrentSolution(colorArray, numOfVertices);
    
    if(*chromaticNumber > colorsUsed) {
        *chromaticNumber = colorsUsed;
        return;
    }
}

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

void colorVertex(int vertexNum, bool graph[N][N], int n, int* colorArray, int* chromaticNumber) {
    
    if(vertexNum == n) { // We've colored all the vertices (0 to n-1) without backtracking; so upon reaching the nth non-existent vertex, we modify the chromatic number variable if the colors used in this solution are less than the stored chromatic number. 

        updateChromaticNumber(colorArray, n, chromaticNumber);
        return; // Backtracking
    }
    
    int color = 0;
    
    /* 
    We have used a while-loop in this problem to change the loop condition during runtime. 
    
    * Let us understand what this does by taking an example:
    If in the lower levels of the space tree, we find a solution which 4 colors are used to color the graph. So now, the new chromatic number is 4. 
    
    Now, we need to look for solutions which use 3 colors if possible because we already know that the chromatic number is established as 4.

    Which is why we change the condition of the loop dynamically, as solutions are found.

    ? We need the second condition in the while loop since it is possible that the current node is being colored by a number less than the chromatic number but it is possible that some earlier nodes were colored using higer colors. So, we check that using `colorsUsedinCurrentSolution` Function. 

    */
    while( color < (*chromaticNumber - 1) && colorsUsedInCurrentSolution(colorArray, n) < (*chromaticNumber - 1)) {    

        if(coloringPossible(vertexNum, color, graph, n, colorArray)) {

            colorArray[vertexNum] = color; // Since coloring is possible, we use that color.

            colorVertex(vertexNum + 1, graph, n, colorArray, chromaticNumber);       
        }

        color++;
    }
    
    colorArray[vertexNum] = -1; //! Removing the color of the vertex as a backtracking step
    
    //? We need to remove the color from this child node since returning `false` means backtracking is occurring due to solution not being found in this branch.
    //? This is because; upon recoloring a parent node above it, the faultily colored child node (in the case color was not removed from the child node) will result in wrong information by the `coloringPossible()` function; that it is not possible to color the parent node using that color, when in actuality it is ok to use that color because we have backtracked from the point at which the child node was colored using the same color.
    
    return; // Backtracking.
}

// Function for finding the chromatic number of a graph
void findChromaticNumber(bool graph[N][N], int n) {
    int* colorArray = new int[n];
    
    for(int index = 0; index < n; index++) {
        colorArray[index] = -1; // -1 means the vertex is uncolored.
    }
    
    int* chromaticNumVal = new int;
    *chromaticNumVal = n + 1; // It is impossible to have a chromatic number more than the number of vertices, so this is our starting value.

    colorVertex(0, graph, n, colorArray, chromaticNumVal);

    printf("Chromatic number of the graph is %d\n", *chromaticNumVal);
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