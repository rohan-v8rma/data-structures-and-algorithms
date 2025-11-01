// https://leetcode.com/problems/network-delay-time

// class Solution {
// public:
//     int networkDelayTime(vector<vector<int>>& times, int n, int k) {
//         vector<vector<vector<int>>> adj = vector(n + 1, vector(0, vector<int>()));

//         for(auto time: times) {
//             adj[time[0]].push_back({time[1], time[2]});
//         }

//         vector<int> distance = vector(n + 1, INT_MAX);

//         // Loop to print the element
//         priority_queue<
//             pair<int,int>,
//             vector<pair<int,int>>,
//             greater<pair<int,int>>
//         > pq;

//         pq.push(make_pair(0, k));

//         while(!pq.empty()) {
//             pair<int, int> currNode = pq.top();
//             pq.pop();

//             if(distance[currNode.second] <= currNode.first) {
//                 continue;
//             }

//             distance[currNode.second] = currNode.first;

//             for(auto adjElement: adj[currNode.second]) {
//                 pq.push(make_pair(currNode.first + adjElement[1], adjElement[0]));
//             }
//         }

//         int maxOutOfAllNodes = 0;

//         for(int i = 1; i <= n; i++) {
//             maxOutOfAllNodes = max(maxOutOfAllNodes, distance[i]);
//         }

//         if(maxOutOfAllNodes == INT_MAX) return -1;

//         return maxOutOfAllNodes;
//     }
// };

// this solution prevents entry of known sub-optimal paths, into the priority queue.

class Solution {
public:
    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        vector<vector<vector<int>>> adj = vector(n + 1, vector(0, vector<int>()));

        for(auto time: times) {
            adj[time[0]].push_back({time[1], time[2]});
        }

        vector<int> distance = vector(n + 1, INT_MAX);

        // Loop to print the element
        priority_queue<
            pair<int,int>,
            vector<pair<int,int>>,
            greater<pair<int,int>>
        > pq;

        pq.push(make_pair(0, k));
        distance[k] = 0;

        while(!pq.empty()) {
            pair<int, int> currNode = pq.top();
            pq.pop();

            if(distance[currNode.second] < currNode.first) continue;

            for(auto adjElement: adj[currNode.second]) {
                if(distance[adjElement[0]] > currNode.first + adjElement[1]) {
                    pq.push(make_pair(currNode.first + adjElement[1], adjElement[0]));
                    distance[adjElement[0]] = currNode.first + adjElement[1];
                }
            }
        }

        int maxOutOfAllNodes = 0;

        for(int i = 1; i <= n; i++) {
            maxOutOfAllNodes = max(maxOutOfAllNodes, distance[i]);
        }

        if(maxOutOfAllNodes == INT_MAX) return -1;

        return maxOutOfAllNodes;
    }
};
