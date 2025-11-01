// https://leetcode.com/problems/is-graph-bipartite
// ../../images/graphs-bipartite-bfs-and-dfs.png

class Solution {
public:
    bool dfs(int i, int cannotBeThisColor, vector<vector<int>>& graph, vector<int>& colors) { 
        // color in alternate way. if it is possible, then it is bipartite
        if(colors[i] && cannotBeThisColor) {
            return colors[i] != cannotBeThisColor;
        }

        if(colors[i]) return true;

        if(cannotBeThisColor) {
            colors[i] = 3 - cannotBeThisColor;
        }
        else {
            colors[i] = 1;
        }

        bool bipartite = true;

        for(auto e: graph[i]) {
            bipartite &= dfs(e, colors[i], graph, colors);
        }

        return bipartite;
    }

    bool isBipartite(vector<vector<int>>& graph) {
        // 0 is uncolored, 1 is color 1, 2 is color 2

        int n = graph.size();

        vector<int> colors = vector(n, 0);

        bool bipartite = true;

        for(int i = 0; i < n; i++) {
            bipartite &= dfs(i, 0, graph, colors);
        }

        return bipartite;
    }
};
