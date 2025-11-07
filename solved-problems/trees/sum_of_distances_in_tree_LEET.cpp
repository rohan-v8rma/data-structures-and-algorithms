// https://leetcode.com/problems/sum-of-distances-in-tree/

class Solution {
public:
    int dfs1(int parent, int node, vector<vector<int>>& adj, vector<int>& subTreeCt) {
        int dist = 0;

        subTreeCt[node] = 1;

        for(int adjNode: adj[node]) {
            if(adjNode != parent) {
                dist += dfs1(node, adjNode, adj, subTreeCt) + subTreeCt[adjNode];
                subTreeCt[node] += subTreeCt[adjNode];
            }
        }

        return dist;
    }

    void dfs2(int dist, int n, int parent, int node, vector<vector<int>>& adj, vector<int>& distances, vector<int>& subTreeCt) {
        distances[node] = dist - subTreeCt[node] + (n - subTreeCt[node]);

        for(int adjNode: adj[node]) {
            if(parent != adjNode) {
                dfs2(distances[node], n, node, adjNode, adj, distances, subTreeCt);
            }
        }
    }

    vector<int> sumOfDistancesInTree(int n, vector<vector<int>>& edges) {
        vector<vector<int>> adj = vector(n, vector(0, 0));

        for(vector<int> edge: edges) {
            adj[edge[0]].push_back(edge[1]);
            adj[edge[1]].push_back(edge[0]);
        }

        vector<int> subTreeCt = vector(n, 0);

        int dist = dfs1(-1, 0, adj, subTreeCt) + n;

        vector<int> distances = vector(n, 0);

        dfs2(dist, n, -1, 0, adj, distances, subTreeCt);

        return distances;
    }
};
