// https://leetcode.com/problems/the-time-when-the-network-becomes-idle/

class Solution {
public:
    int networkBecomesIdle(vector<vector<int>>& edges, vector<int>& patience) {
        int n = patience.size();

        vector<int> distance = vector(n, INT_MAX);
        vector<vector<int>> adj = vector(n, vector(0, 0));

        for(auto edge: edges) {
            adj[edge[0]].push_back(edge[1]);
            adj[edge[1]].push_back(edge[0]);
        }

        // Use Djikstra to find single source shortest path

        priority_queue<
            pair<int, int>,
            vector<pair<int, int>>,
            greater<pair<int, int>>
        > pq;

        pq.push(make_pair(0, 0));

        while(!pq.empty()) {
            auto [dist, node] = pq.top();
            pq.pop();

            if(dist > distance[node]) continue;

            distance[node] = dist;

            for(auto adjE: adj[node]) {
                if(dist + 1 < distance[adjE]) {
                    distance[adjE] = dist + 1;
                    pq.push(make_pair(distance[adjE], adjE));
                }
            }
        }

        int timeWhenIdle = 0;

        for(int i = 1; i < n; i++) {
            int RTT = distance[i] * 2;

            // So the server will keep retrying until the second just before 
            // it gets the first message it sent out, back.
            // so RTT - 1 is the seconds till that time
            // we divide it by patience to see how many retries went out in 
            // that period. and multiply the whole number by patience to see 
            // when the last retry message originated
            int lastRetry = ((RTT - 1) / patience[i]) * patience[i];

            // If retry was not sent out, then the server will be idle 1 
            // second after RTT completes
            // Otherwise, it would be idle 1 second after the RTT of the last 
            // retry

            timeWhenIdle = max(
                timeWhenIdle,
                max(RTT, lastRetry + RTT) + 1
            );
        }

        return timeWhenIdle;
    }
};
