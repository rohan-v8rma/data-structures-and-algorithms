/*
Approach--->
Keep traversing the graph... If you find an edge pointing to already visited node
except the parent then cycle is present
*/

#include<iostream>
#include<bits/stdc++.h>
using namespace std;

bool isCycle(int source, vector<vector<int>> adj, vector<bool> visited, int parent){

    visited[source] = true;
    
    vector<int> :: iterator it;
    for(it=adj[source].begin(); it!=adj[source].end(); it++){
        if(*it != parent){
            // Means it does not count cycle to graph such as lets say 1 to 2 then 2 to 1
            if(visited[*it]){
                return true;
            }
            if(!visited[*it] && isCycle(*it, adj, visited, source)){
                return true;
            }
        }
    }
    return false;
}

int main(){
    int n;
    cout<<"Enter the number of nodes: "<<endl;
    cin>>n;

    int m;
    cout<<"Enter the number of edges: "<<endl;
    cin>>m;

    bool cornor_case = false;
    if(n==2){
        // No of nodes are 2
        cornor_case = true;
    }

    if(cornor_case == false){

        vector<vector<int>> adj(n);
        vector<bool> visited(n, false);

        for(int i=0; i<m; i++){
            int x, y;
            cout<<"Enter the starting and ending value of edges: "<<endl;
            cin>>x>>y;

            adj[x].push_back(y);
            adj[y].push_back(x);
        }

        bool cycle = false;

        for(int i=0; i<n; i++){

            vector<bool> visited(n, false); // We keep this visited array initialization inside the for loop, so that each time a check for a cycle is started, we get a fresh 'visited' array.

            if(!visited[i] && isCycle(i, adj, visited, -1)){
                cycle = true;
            }
        }
        if(cycle){
            cout<<"Cycle is present"<<endl;
        }else{
            cout<<"Cycle is not present"<<endl;
        }
    }

    if(cornor_case){
        cout<<"Cycle is not present as it conflicts with the rule for a set of nodes to contain a cycle."<<endl;
    }


    return 0;
}