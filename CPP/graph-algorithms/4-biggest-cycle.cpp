#include <iostream>
#define NO_OF_VERTICES 5

using namespace::std;

/*
In the case of Kruskal's algorithm, we use 2 arrays to implement the concept of disjoint sets.

The first array is the `parentOf` array, which is used to find the "PARENT OF" a specific vertex

The second array is the `rank` array, which helps decide which tree should be merged into which tree, so as to keep depth of the tree at a minimum. This allows the `findParent` operation to take less time.

*/

int findParent(int vertex, int parentOf[NO_OF_VERTICES]) {
    if(parentOf[vertex] == vertex) {
        return vertex;
    }
    
    return findParent(parentOf[vertex], parentOf);

}

int findParent(int vertex, int parentOf[NO_OF_VERTICES], int* cycleSize) {
    *cycleSize += 1;
    if(parentOf[vertex] == vertex) {
        return vertex;
    }

    return findParent(parentOf[vertex], parentOf, cycleSize);

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
    int vertex1;
    int vertex2;

    static int numOfEdges;
    
    Edge() {
        vertex1 = 0;
        vertex2 = 0;
        numOfEdges++;
    }

    Edge(int _vertex1, int _vertex2) {
        vertex1 = _vertex1;
        vertex2 = _vertex2;
        numOfEdges++;
    }


};

int Edge::numOfEdges = 1;

ostream& operator << (ostream& out, Edge* edgeValues) {
    out << "Edge FROM ("; 
    out << edgeValues->vertex1;
    out << ") TO (";
    out << edgeValues->vertex2;
    out << ")";

    return out;
}

void swap(int edgeIndex1, int edgeIndex2, Edge** edges) {
    Edge* temp = edges[edgeIndex1];

    edges[edgeIndex1] = edges[edgeIndex2];
    edges[edgeIndex2] = temp;
}


void biggestCycle(Edge** originalEdgesArray) {
    cout << "\nUsing Kruskal's algorithm to creating a Minimum Spanning Tree....\n\n";

    int parentOf[NO_OF_VERTICES];
    int rankOf[NO_OF_VERTICES];
    initializeSetArrays(parentOf, rankOf, NO_OF_VERTICES);
    
    // Sorting the edges array
    // sortEdgeArray(originalEdgesArray);

    int edgesIncluded = 0;
    int edgesArrayIndex = 0;
    Edge* currentEdgePointer;
    
    int maxCycleSize = 0;
    
    while(edgesArrayIndex + 1 != Edge::numOfEdges) {
        int* potentialCycleSize1 = new int(0);               
        int* potentialCycleSize2 = new int(0);               

        int currentCycleSize;
        currentEdgePointer = originalEdgesArray[edgesArrayIndex];

        if(findParent(currentEdgePointer->vertex1, parentOf, potentialCycleSize1) == findParent(currentEdgePointer->vertex2, parentOf, potentialCycleSize2)) {
            currentCycleSize = *potentialCycleSize1 + *potentialCycleSize2; // +1 for parent of both
            cout << currentEdgePointer << " forms a cycle of size " << currentCycleSize << endl;

            if(maxCycleSize < currentCycleSize) {
                maxCycleSize = currentCycleSize;
            }

            cout << "Biggest cycle size uptil this point has been " << maxCycleSize << endl;
        }        
        else {
            Union(currentEdgePointer->vertex1, currentEdgePointer->vertex2, rankOf, parentOf);
        }
        edgesArrayIndex++;
        delete potentialCycleSize1;
        delete potentialCycleSize2;
    }

}


int main() {
    
    Edge::numOfEdges = 0;
    
    Edge* edges[50]; // It is better to use an array of `Edge` pointers because we can easily identify the end of the array as soon as the pointer to the array elements becomes NULL.

    edges[0] = new Edge(1, 3);
    edges[1] = new Edge(1, 4);
    edges[2] = new Edge(0, 3);
    edges[3] = new Edge(0, 1);
    edges[4] = new Edge(3, 4);
    edges[5] = new Edge(1, 2);
    edges[6] = new Edge(2, 4);
    edges[7] = new Edge(2, 0);

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

    biggestCycle(edges);
    return 0;
}