#include <iostream>
#include <vector>
using namespace::std;


int main() {
    /*
    Inputs would always be in the form:
    5 3     (number-of-vertices number-of-edges)
    1 5     (to from edge-weight)
    2 3 
    3 4
    */

    // Number of vertices
    const int n = 3;


    //? For 1-based numbering of vertices; we leave the 0th index blank

    //* Adjacency matrix for undirected graph
    int matrix_1_numbering[n + 1][n + 1] = {
        {0,0,0,0},
        {0,0,1,0},
        {0,1,0,1},
        {0,0,1,0},
    };
    //! In the case of directed graph, the matrix need not be symmetrical across the principal diagonal like the matrix above.

    //* Adjacency list for undirected graph
    vector<int> list_1_numbering[n+1];

    list_1_numbering[1].push_back(2);
    list_1_numbering[2].push_back(1);
    
    list_1_numbering[2].push_back(3);
    list_1_numbering[3].push_back(2);

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
