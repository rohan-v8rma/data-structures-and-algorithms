#include<iostream>
#define NO_OF_VERTICES 4
using namespace std;



class setup_of_dfs {
public:
    
    // Time variable that helps in classification of edges
    int time;
    
    // We store our DFS traversal, inside a traversal array
    int traversal_array[NO_OF_VERTICES], traversal_array_index = 0;
    int adjacencyMatrix[NO_OF_VERTICES][NO_OF_VERTICES] = 
    {
    {0,1,1,0},
    {0,0,1,0},
    {1,0,0,1},
    {0,0,0,1}
    };

    int visited[NO_OF_VERTICES];

    setup_of_dfs (){
        cout << "Linked List representation. Numbers on right side of arrow are all the vertices the left side vertex has edges with.\n\n";
        for (int fromVertex = 0; fromVertex < NO_OF_VERTICES; fromVertex++) {
            cout << fromVertex << " --> ";
            for (int toVertex = 0; toVertex < NO_OF_VERTICES; toVertex++) {
                if(adjacencyMatrix[fromVertex][toVertex] == 1){
                    cout << toVertex << ", ";
                }
            }
            cout << "\n";
        }
        
        cout << "Adjacency matrix\n\n";
        for (int fromVertex = 0; fromVertex < NO_OF_VERTICES; fromVertex++){
            for (int toVertex = 0; toVertex < NO_OF_VERTICES; toVertex++){
                cout << adjacencyMatrix[fromVertex][toVertex] << " ";
            }
            cout << "\n";
        }
    }
    void traverse_dfs_on_a_vertex(int fromVertex){
        visited[fromVertex] = 1;
        traversal_array[traversal_array_index++] = fromVertex;
        
        for (int toVertex = 0; toVertex < NO_OF_VERTICES; toVertex++){
            if(!visited[toVertex] && adjacencyMatrix[fromVertex][toVertex]){
                traverse_dfs_on_a_vertex(toVertex);
            }
        }
        
    }
    void dfs(){
        for (int vertex = 0; vertex < NO_OF_VERTICES; vertex++){
            visited[vertex] = 0;
        }

        for (int vertex = 0; vertex < NO_OF_VERTICES; vertex++){
            // In this we start from the vertex `0`, so we are essentially choosing it as our source vertex for our DFS
            // But we have to run traverse_dfs_on_a_vertex on other vertices as well to take care of two conditions
            //* 1. All edges are directed toward `0` so, no other vertices were visited.
            //* 2. The DFS traversal happened, but a certain vertex wasn't visited because no edges were directed towards it from other nodes, so we traverse it manually and classify all its edges
            //* This is the concept of restarting the DFS so that all vertices are visited.
            if(!visited[vertex]){
                traverse_dfs_on_a_vertex(vertex);
            }
        }
        cout << "\n";

        for(int index = 0; index < NO_OF_VERTICES; index++){
            cout << traversal_array[index] << " ";
        }
        cout << "\n";
    }

};

int main(){
    setup_of_dfs test;
    test.dfs();    
}