#include <iostream>
#define NO_OF_VERTICES 5

using namespace::std;

int findParent(int vertex, int parentOf[NO_OF_VERTICES]) {
    if(parentOf[vertex] == vertex) {
        return vertex;
    }
    
    return findParent(parentOf[vertex], parentOf);

    // TODO: Implement path compression and change rank with path compression
    // return ( parentOf[vertex] = findParent(vertex, parentOf) );
}

void Union(int vertex1, int vertex2, int rankOf[NO_OF_VERTICES], int parentOf[NO_OF_VERTICES]) {
    
    vertex1 = findParent(vertex1, parentOf);
    vertex2 = findParent(vertex2, parentOf);

    

    if(rankOf[vertex1] == rankOf[vertex2]) {
        parentOf[vertex2] = parentOf[vertex1];
        rankOf[vertex1]++;
    }
    else if(rankOf[vertex1] > rankOf[vertex2]) {
        parentOf[vertex2] = parentOf[vertex1];
    }
    else if(rankOf[vertex1] < rankOf[vertex2]) {
        parentOf[vertex1] = parentOf[vertex2];
    }

}

void initializeSetArrays(int* parent, int* rank, int noOfVertices) {
    for(int index = 0; index < noOfVertices; index++) {
        parent[index] = index;
        rank[index] = 0;
    }
}

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

void swap(int edgeIndex1, int edgeIndex2, Edge** edges) {
    Edge* temp = edges[edgeIndex1];

    edges[edgeIndex1] = edges[edgeIndex2];
    edges[edgeIndex2] = temp;
}

void sortEdgeArray(Edge** edgesArray) {
    
    int edgesArraySize = 0;
    
    Edge* tempPtr = edgesArray[edgesArraySize];
    while(tempPtr != NULL) {
        edgesArraySize++;
        tempPtr = edgesArray[edgesArraySize];
    }

    for(int pass = 1; pass < edgesArraySize; pass++) { // passes need to be one less than size of the array
        for(int checkIndex = pass; checkIndex >= 1; checkIndex--) { // We are keeping boundary of `checkIndex` as 1 so that when we subtract 1 from it for checking before swapping, the index doesn't go out of bounds and stays at 0.
            if(edgesArray[checkIndex]->weight < edgesArray[checkIndex - 1]->weight) {
                swap(checkIndex - 1, checkIndex, edgesArray);
            }
        }
    }

    // Displaying the sorted array.
    // for(int index = 0; index < edgesArraySize; index++) {
    //     cout << edgesArray[index];
    // }
    
}

void kruskalAlgorithm(Edge** originalEdgesArray, int numOfVertices) {
    cout << "\nUsing Kruskal's algorithm to creating a Minimum Spanning Tree....\n\n";

    int parentOf[NO_OF_VERTICES];
    int rankOf[NO_OF_VERTICES];
    initializeSetArrays(parentOf, rankOf, NO_OF_VERTICES);
    
    // Sorting the edges array
    sortEdgeArray(originalEdgesArray);

    Edge* minimumSpanningTree[numOfVertices - 1];
    int edgesIncluded = 0;
    int edgesArrayIndex = 0;
    Edge* currentEdgePointer;

    
    while(edgesIncluded != numOfVertices - 1) {
                
        currentEdgePointer = originalEdgesArray[edgesArrayIndex];

        if(findParent(currentEdgePointer->vertex1, parentOf) == findParent(currentEdgePointer->vertex2, parentOf)) {
            cout << currentEdgePointer << " NOT included because it creates a cycle.\n";
        }        
        else {
            Union(currentEdgePointer->vertex1, currentEdgePointer->vertex2, rankOf, parentOf);

            minimumSpanningTree[edgesIncluded++] = currentEdgePointer;
        }

        edgesArrayIndex++;
    }

    int costOfMst = 0;

    cout << "\nEdges included : \n";

    for(int index = 0; index < numOfVertices - 1; index++) {
        cout << minimumSpanningTree[index] << "\n";
        costOfMst += minimumSpanningTree[index]->weight;
    }

    cout << "\nCost of MST: " << costOfMst << "\n";
}


int main() {

    Edge* edges[50]; // It is better to use an array of `Edge` pointers because we can easily identify the end of the array as soon as the pointer to the array elements becomes NULL.

    edges[0] = new Edge(1, 3, 8);
    edges[1] = new Edge(1, 4, 5);
    edges[2] = new Edge(0, 3, 6);
    edges[3] = new Edge(0, 1, 2);
    edges[4] = new Edge(3, 4, 9);
    edges[5] = new Edge(1, 2, 3);
    edges[6] = new Edge(2, 4, 7);
    edges[7] = new Edge(2, 0, 4);

    // swap(0, 1, edges);
    // sortEdgeArray(edges);

    // if(edges[8] == NULL) {
    //     printf("%p", edges[8]);    
    // }

    // int index = 0;
    // Edge* tempPtr = edges[index];
    
    // while(tempPtr != NULL) {
    //     cout << *tempPtr;
    //     tempPtr = edges[++index];
    // }

    kruskalAlgorithm(edges, NO_OF_VERTICES);
    return 0;
}