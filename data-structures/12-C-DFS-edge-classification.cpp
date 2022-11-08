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

    int visited[NO_OF_VERTICES], start_time[NO_OF_VERTICES], end_time[NO_OF_VERTICES];

    setup_of_dfs (){
        time = 0;
        cout << "List representation" << endl << endl;
        for (int fromVertex = 0; fromVertex < NO_OF_VERTICES; fromVertex++) {
            cout << fromVertex << " --> ";
            for (int toVertex = 0; toVertex < NO_OF_VERTICES; toVertex++) {
                if(adjacencyMatrix[fromVertex][toVertex] == 1){
                    cout << toVertex << " ";
                }
            }
            cout << endl;
        }
        
        cout << "Adjacency matrix" << endl << endl;
        for (int fromVertex = 0; fromVertex < NO_OF_VERTICES; fromVertex++){
            for (int toVertex = 0; toVertex < NO_OF_VERTICES; toVertex++){
                cout << adjacencyMatrix[fromVertex][toVertex] << " ";
            }
            cout << endl;
        }
    }
    void traverse_dfs_on_a_vertex(int fromVertex){
        visited[fromVertex] = 1;
        traversal_array[traversal_array_index++] = fromVertex;
        start_time[fromVertex] = end_time[fromVertex] = time++;
        // The end_time of the fromVertex is initialized over here because we are comparing the end_time in the if-else conditions in the below for-loop.

        for (int toVertex = 0; toVertex < NO_OF_VERTICES; toVertex++){
            if(!visited[toVertex] && adjacencyMatrix[fromVertex][toVertex]){
                printf("Tree edge: %d --> %d\n", fromVertex, toVertex);
                traverse_dfs_on_a_vertex(toVertex);
            }
            else{
                if(start_time[fromVertex] > start_time[toVertex] && end_time[fromVertex] < end_time[toVertex]){
                    printf("Back edge: %d --> %d\n", fromVertex, toVertex);
                }
                else if(start_time[fromVertex] < start_time[toVertex] && end_time[fromVertex] > end_time[toVertex]){
                    printf("Forward edge: %d --> %d\n", fromVertex, toVertex);
                }
                else if(start_time[fromVertex] > start_time[toVertex] && end_time[fromVertex] > end_time[toVertex] && adjacencyMatrix[fromVertex][toVertex]){
                    printf("Cross edges: %d --> %d\n", fromVertex, toVertex);
                }
            }

            // The end_time of the fromVertex is dynamic, and is changing with each iteration of the for-loop. At the last iteration of the for loop, the end_time will be finalized.
            end_time[fromVertex] = time++;
        }
        
    }
    void dfs(){
        for (int vertex = 0; vertex < NO_OF_VERTICES; vertex++){
            visited[vertex] = 0;
            start_time[vertex] = 0;
            end_time[vertex] = 0;
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
        cout << endl;

        for(int index = 0; index < NO_OF_VERTICES; index++){
            cout << traversal_array[index] << " ";
        }
    }

};

int main(){
    setup_of_dfs test;
    test.dfs();    
}