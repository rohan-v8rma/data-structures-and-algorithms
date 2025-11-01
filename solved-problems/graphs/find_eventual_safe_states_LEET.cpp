// https://leetcode.com/problems/find-eventual-safe-states

class Solution {
public:
    bool dfs(
        int node,
        vector<vector<int>>& graph,
        vector<bool>& visited,
        vector<bool>& terminal
    ) {
        // If we are visiting the node again, either the node has been proven a terminal node before, or it is in the current cycle of visiting, in which case since it leads to itself, it is not a terminal node
        if(visited[node]) {
            return terminal[node];
        }

        visited[node] = true;

        bool term = true;

        for(auto e: graph[node]) {
            term &= dfs(e, graph, visited, terminal);
        }

        return terminal[node] = term;
    }

    vector<int> eventualSafeNodes(vector<vector<int>>& graph) {
        int n = graph.size();

        vector<int> safe;

        vector<bool> visited = vector(n, false);
        vector<bool> terminal = vector(n, false);

        for(int i = 0; i < n; i++) {
            dfs(i, graph, visited, terminal);

            if(terminal[i]) {
                safe.push_back(i);
            }
        }

        return safe;
    }
};
