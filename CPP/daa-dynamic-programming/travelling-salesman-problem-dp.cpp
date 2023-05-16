#include <bits/stdc++.h>
using namespace std;

struct Solution {
    int *visited;
    int currentCost;
};

// Assuming that 1 is our starting vertex
int TSP(int vertexVisited, set<int> verticesToVisit, int** costMatrix, int numOfVertices, int originVertex) {
    if(verticesToVisit.empty()) {
        return costMatrix[vertexVisited][originVertex];
    }
    else {
        int minCost = INT_MAX;
        for(auto& it: verticesToVisit) {
            set<int> clonedSet(verticesToVisit);
            clonedSet.erase(it);
            int costOfVisitingIt = costMatrix[vertexVisited][it] + TSP(it, clonedSet, costMatrix, numOfVertices, originVertex);
            minCost = minCost > costOfVisitingIt ? costOfVisitingIt : minCost;
        }
        return minCost;
    }
}

void setupTSP(int** costMatrix, int numOfVertices) {
    set<int> toVisit;
    for(int index = 0; index < numOfVertices; index++) {
        toVisit.insert(index);
    }

    for(int startingVertex = 0; startingVertex < numOfVertices; startingVertex++) {
        set<int> removedStartVertex(toVisit);
        removedStartVertex.erase(startingVertex);
        // This is because we need to visit a minimum of 2 vertices, by traversing an edge.
        Solution** table = new Solution*[numOfVertices - 2 + 1 ];

        for(int verticesVisited = 2; verticesVisited <= numOfVertices; verticesVisited++) {
            table[verticesVisited - 2] = new Solution[numOfVertices];
            for(int index = 0; index < numOfVertices; index++) {
                table[verticesVisited - 2][index].currentCost = INT_MAX;
                table[verticesVisited - 2][index].visited = new int[numOfVertices];
                // table[verticesVisited - 2][index].visited[startingVertex] = 1;
            }
        }
        // Inside this loop, the table serves as a memoization of costs intended with the purpose of getting back to the current `startingVertex`.
        // The table's purpose changes every loop.
        
        for(int verticesVisited = 2; verticesVisited <= numOfVertices; verticesVisited++) {
            for(auto& afterSource: removedStartVertex) {
                
                // In the first iteration, we have no previous values to check so we just append the values from the costMatrix into the table.
                if(verticesVisited == 2) {
                    // Going back to source vertex
                    //TODO: Test this with directed asymmetric graphs
                    // table[verticesVisited - 2][afterSource].currentCost = costMatrix[startingVertex][afterSource];
                    table[verticesVisited - 2][afterSource].currentCost = costMatrix[afterSource][startingVertex];
                    table[verticesVisited - 2][afterSource].visited[startingVertex] = 1;
                }
                // In the last iteration of the dynamic programming approach, we visit the last unvisited vertex from the `afterSource` vertex
                else if(verticesVisited == numOfVertices) {
                    int visitInThisIteration = -1;
                    for(int index = 0; index < numOfVertices; index++) {
                        table[verticesVisited - 2][afterSource].visited[index] = table[verticesVisited - 3][afterSource].visited[index];
                        if(table[verticesVisited - 3][afterSource].visited[index] == 0) {
                            visitInThisIteration = index;
                        }
                    }   
                    table[verticesVisited - 2][afterSource].currentCost = costMatrix[afterSource][visitInThisIteration] + table[verticesVisited - 3][afterSource].currentCost + costMatrix[visitInThisIteration][startingVertex];
                }
                else {
                    int minCost = INT_MAX;
                    set<int> clonedSet(removedStartVertex);
                    clonedSet.erase(afterSource);
                    int visitInThisIteration;
                    for(int index = 0; index < numOfVertices; index++) {
                        if(table[verticesVisited - 3][afterSource].visited[index] == 1) {
                            continue;
                        }
                        int currentCost = costMatrix[afterSource][index] + table[verticesVisited - 3][index].currentCost;
                        if(minCost > currentCost) {
                            minCost = currentCost;
                            visitInThisIteration = index;
                        }
                    }
                    table[verticesVisited - 2][afterSource].currentCost = minCost;
                    for(int index = 0; index < numOfVertices; index++) {
                        table[verticesVisited - 2][afterSource].visited[index] = table[verticesVisited - 3][visitInThisIteration].visited[index];
                    }
                    table[verticesVisited - 2][afterSource].visited[visitInThisIteration] = 1;
                    
                    // for(auto& secondAfterSource: clonedSet) {
                    //     // Cost of getting from afterSource to secondAfterSource, then the cost of getting from secondAfterSource back to source.
                    //     // 
                    //     int currentCost = costMatrix[afterSource][secondAfterSource] + table[verticesVisited - 3][secondAfterSource];
                    //     // int currentCost = costMatrix[secondAfterSource][afterSource] + table[verticesVisited - 3][afterSource];
                    //     minCost = minCost > currentCost ? currentCost : minCost;
                    // }
                    // table[verticesVisited - 2][afterSource] = minCost;
                    
                }
                table[verticesVisited - 2][afterSource].visited[afterSource] = 1;
                // cout << table[verticesVisited - 2][afterSource] << " ";
                
            }  
            
            for(int index = 0; index < numOfVertices; index++) {
                cout << table[verticesVisited - 2][index].currentCost << " ";
                for(int innerIndex = 0; innerIndex < numOfVertices; innerIndex++) {
                    cout << table[verticesVisited - 2][index].visited[innerIndex] << " ";
                }
                cout << endl;
            }
            cout << endl;
        }


        int leastPossibleCost = INT_MAX;
        for(int index = 0; index < numOfVertices; index++) {
            if(startingVertex == index) {
                continue;
            }
            int currentCost = table[numOfVertices - 2][index].currentCost;
            leastPossibleCost = leastPossibleCost > currentCost ? currentCost : leastPossibleCost;

        }
        printf("Total cost when vertex %d is started from: %d\n", startingVertex, leastPossibleCost);
    }

    


    

    
}


int main() {
    int numOfCities = 4;

    int** costMatrix = new int*[numOfCities];

    for(int index = 0; index < numOfCities; index++) {
        costMatrix[index] = new int[numOfCities];
    }

    costMatrix[0][0] = 0;
    costMatrix[0][1] = 20;
    costMatrix[0][2] = 42;
    costMatrix[0][3] = 25;

    costMatrix[1][0] = 20;
    costMatrix[1][1] = 0;
    costMatrix[1][2] = 30;
    costMatrix[1][3] = 34;

    costMatrix[2][0] = 42;
    costMatrix[2][1] = 30;
    costMatrix[2][2] = 0;
    costMatrix[2][3] = 10;

    costMatrix[3][0] = 25;
    costMatrix[3][1] = 34;
    costMatrix[3][2] = 10;
    costMatrix[3][3] = 0;


    // int costMatrix[4][4] = {
    //     {0, 20, 42, 25,},
    //     {20, 0, 30, 34,},
    //     {42, 30, 0, 10,},
    //     {25, 34, 10, 0,},
    // };

    
    

    setupTSP(costMatrix, numOfCities);

    // int* visited = new int[numOfCities];
    // for(int index = 0; index < numOfCities; index++) {
    //     visited[index] = 0;
    // }




    // vector<vector<int>> hello;
    // vector<int> row;
    // row.push_back(29);
    // hello.push_back(row);
    // cout << hello.at(0).at(0);

}