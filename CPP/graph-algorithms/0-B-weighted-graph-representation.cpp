#include <iostream>
#include <vector>
#include <limits.h>
using namespace::std;


int main() {
    /*
    Inputs would always be in the form:
    5 3     (number-of-vertices number-of-edges)
    1 5 2   (to from edge-weight)
    2 3 1 
    3 4 3
    */


    // Number of vertices
    const int n = 3;



    //? For 1-based numbering of vertices; we leave the 0th index blank

    //* Adjacency matrix for undirected weighted graph
    // THe only difference in matrix for weighted graphs is instead of binary values, we can store edge weights when edges exist. And infinity (INT_MAX) when edges does not exist.
    int matrix_1_numbering[n + 1][n + 1];

    for(int i = 1; i <= n; i++) {
        for(int j = 1; j <= n; j++) {
            matrix_1_numbering[i][j] = INT_MAX; // Initializing the matrix with infinite values.
        }   
    }

    // Edge between 1 and 2, with weight 3
    matrix_1_numbering[1][2] = 3;
    matrix_1_numbering[2][1] = 3;

    // Edge between 2 and 3, with weight 5
    matrix_1_numbering[2][3] = 5;
    matrix_1_numbering[3][2] = 5;

    //! In the case of directed graph, the matrix need not be symmetrical across the principal diagonal like the matrix above.

    //* Adjacency list for undirected weighted graph
    // THe only difference in list for weighted graphs is that instead of just the vertex to which edge exists; we also store the edge weight.
    vector<int[2]> list_1_numbering[n+1];

    // Edge between 1 and 2, with weight 3
    list_1_numbering[1].push_back({2, 3});
    list_1_numbering[2].push_back({1, 3});
    
    // Edge between 2 and 3, with weight 5
    list_1_numbering[2].push_back({3, 5});
    list_1_numbering[3].push_back({2, 5});

    //! In the case of directed graph, it is not necessary if edge exists from 1 to 2, that edge will also exist from 2 to 1.



    //? For 0-based numbering of vertices; we leave the 0th index blank

    //* Adjacency matrix for undirected graph
    int matrix_0_numbering[n][n] = {
        {0,1,0},
        {1,0,1},
        {0,1,0},
    };
    //! In the case of directed graph, the matrix need not be symmetrical across the principal diagonal like the matrix above.

    //* Adjacency list for undirected graph
    vector<int> list1[n];

    list1[0].push_back(1);
    list1[1].push_back(0);
    
    list1[1].push_back(2);
    list1[2].push_back(1);

    //! In the case of directed graph, it is not necessary if edge exists from 1 to 2, that edge will also exist from 2 to 1.

}
