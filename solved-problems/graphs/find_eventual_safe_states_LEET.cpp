// https://leetcode.com/problems/find-eventual-safe-states

class Solution {
public:
    // DFS
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

    // // BFS

    // vector<int> eventualSafeNodes(vector<vector<int>>& graph) {
    //     int n = graph.size();

    //     // Measure out degrees to find terminal nodes and make a reverse graph that go from leaf nodes to parents so that we can see which nodes are terminal nodes or safe nodes

    //     vector<int> out_degrees = vector(n, 0);
    //     vector<vector<int>> reverseGraph = vector(n, vector(0, 0));

    //     for(int i = 0; i < n; i++) {
    //         for(auto e: graph[i]) {   
    //             out_degrees[i]++;
    //             reverseGraph[e].push_back(i);
    //         }
    //     }

    //     queue<int> q;

    //     for(int i = 0; i < n; i++) {
    //         if(out_degrees[i] == 0) {
    //             q.push(i);
    //         }
    //     }

    //     vector<int> safe;

    //     while(!q.empty()) {
    //         int safeElement = q.front();
    //         q.pop();
    //         safe.push_back(safeElement);

    //         for(auto e: reverseGraph[safeElement]) {
    //             out_degrees[e]--;
    //             if(out_degrees[e] == 0) {
    //                 q.push(e);
    //             }
    //         }
    //     }

    //     sort(safe.begin(), safe.end());

    //     return safe;
    // }
};
