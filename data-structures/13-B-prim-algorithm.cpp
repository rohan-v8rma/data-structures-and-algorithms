#include <iostream>
#include <limits.h>
using namespace::std;

#define NO_OF_VERTICES 5 // We have defined and used this macro because every array is of this size

/*

In the case of Prim's algorithm, we use 3 arrays

The first array is the `includedInMst` array, which has boolean values indicating whether a particular VERTEX has been included yet in the MST or not. All values are initialized to false.

The second array is the `key` array, which has indices/VERTICES as keys and its values represents the edge weight to the VERTEX. This array is initalized with the first value as 0, meaning that it is the first VERTEX that we would be including the MST. The other values are initialized with INT_MAX, and once the first VERTEX is visited, these values are changed to the edge weight between the 0th vertex and the new vertex.

The third array is the `connectedTo` array which again has indices/VERTICES as keys and its values help us determine the vertex at the other end of the edge connected to the key VERTEX. All values of this array are initialized with -1. These values are updated when the edge weights are updated in the second array.

*/

class Edge {

    friend ostream& operator << (ostream& out, Edge edgeValues);

public:
    int weight;
    int vertex1;
    int vertex2;
    
    Edge() {
        vertex1 = 0;
        vertex2 = 0;
        weight = 0;
    }

    Edge(int _vertex1, int _vertex2, int _weight) {
        vertex1 = _vertex1;
        vertex2 = _vertex2;
        weight = _weight;
    }
};

ostream& operator << (ostream& out, Edge* edgeValues) {
    out << "Edge FROM ("; 
    out << edgeValues->vertex1;
    out << ") TO (";
    out << edgeValues->vertex2;
    out << ") OF WEIGHT (";
    out << edgeValues->weight;
    out << ")";

    return out;
}

void initializeAlgoArrays(bool* includedInMst, int* edgeWeight, int* connectedTo) {
    for(int index = 0; index < NO_OF_VERTICES; index++) {
        includedInMst[index] = false;
        edgeWeight[index] = INT_MAX;
        connectedTo[index] = -1;

        if(index == 0) {
            edgeWeight[index] = 0;
        }
    }
}

void primAlgorithm(int adjacencyList[NO_OF_VERTICES][NO_OF_VERTICES]) {
    bool includedInMst[NO_OF_VERTICES];
    int edgeWeight[NO_OF_VERTICES];
    int connectedTo[NO_OF_VERTICES];

    int noOfVerticesIncluded = 0;
    int totalCostOfMst = 0;

    initializeAlgoArrays(includedInMst, edgeWeight, connectedTo);

    //including 0th vertex
    int includeVertex = 0;

    while(noOfVerticesIncluded != NO_OF_VERTICES) {
        noOfVerticesIncluded++; // Incrementing the number of vertices included
        includedInMst[includeVertex] = true;
        
        totalCostOfMst += edgeWeight[includeVertex]; // Adding the edge weight to cost of the MST
        
        edgeWeight[includeVertex] = INT_MAX; // Since we don't have to include an already included vertex again, we don't care about its edge weight. We make it INFINITY, so that other vertices can be CHOSEN as the vertex to be included. It is useful for every case because includeVertex has the lowest edge weight in each subsequent step, so this removes that edgeWeight, and allows selection of the next lowest edge weight.


        // Checking the edge weights of all the neighbours the included vertex
        for(int toVertex = 0; toVertex < NO_OF_VERTICES; toVertex++) {
            if(includeVertex == toVertex) {
                continue;
            }

            // For this to work properly, the adjacency list has to be symmetrical (because spanning trees has non-directed edges)
            if(includedInMst[toVertex] == false) { // We change the edge weights for vertices not included in MST, letting vertices that have been included keep edge weights of INFINITY.

                if(adjacencyList[includeVertex][toVertex] < edgeWeight[toVertex]) { // If the new edge weight from the adjacency list is less than the pre-existing edge weight, then only do we store it.
                    edgeWeight[toVertex] = adjacencyList[includeVertex][toVertex];
                    
                    connectedTo[toVertex] = includeVertex; // Changing the connectedTo value from -1 to the `includeVertex`
                }
            }
            
        }

        // Making the edge weight of the already included vertex as INFINITY above is useful over here, when edgeWeight of other NOT included vertices are compared with the includeVertex iteratively.
        for(int index = 0; index < NO_OF_VERTICES; index++) {
            if(includedInMst[index] == false) {
                if(edgeWeight[index] < edgeWeight[includeVertex]) {
                    includeVertex = index;
                }
            }
        }
    }


    cout << "Total cost of MST : " << totalCostOfMst << "\n";
}

void initializeAdjacencyList(int adjacencyList[NO_OF_VERTICES][NO_OF_VERTICES]) {
    for(int fromVertex = 0; fromVertex < NO_OF_VERTICES; fromVertex++) {
        for(int toVertex = 0; toVertex < NO_OF_VERTICES; toVertex++) {
            adjacencyList[fromVertex][toVertex] = INT_MAX;
        }
    }
}

void addUndirectedEdge(int adjacencyList[NO_OF_VERTICES][NO_OF_VERTICES], int vertex1, int vertex2, int weight) {
    
    // Adding edge in both directions
    adjacencyList[vertex1][vertex2] = weight;
    adjacencyList[vertex2][vertex1] = weight;
}

int main() {
    int adjacencyList[NO_OF_VERTICES][NO_OF_VERTICES];

    initializeAdjacencyList(adjacencyList);

    addUndirectedEdge(adjacencyList, 0, 1, 2);
    addUndirectedEdge(adjacencyList, 1, 2, 3);
    addUndirectedEdge(adjacencyList, 1, 4, 5);
    addUndirectedEdge(adjacencyList, 2, 4, 7);
    addUndirectedEdge(adjacencyList, 0, 3, 6);
    addUndirectedEdge(adjacencyList, 1, 3, 8);


    primAlgorithm(adjacencyList);

    return 0;
}