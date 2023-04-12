#include <bits/stdc++.h>
using namespace std;

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
        toVisit.erase(startingVertex);
        int currentCost = TSP(startingVertex, toVisit, costMatrix, numOfVertices, startingVertex);
        toVisit.insert(startingVertex);
        printf("Total cost when vertex %d is started from: %d\n", startingVertex, currentCost);
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