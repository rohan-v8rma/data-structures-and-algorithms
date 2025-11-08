// https://leetcode.com/problems/get-watched-videos-by-your-friends

class Solution {
public:
    vector<string> watchedVideosByFriends(vector<vector<string>>& watchedVideos, vector<vector<int>>& friends, int id, int level) {
        unordered_map<string, int> videoWatchCt;

        int n = friends.size();

        priority_queue<
            vector<int>,
            vector<vector<int>>,
            greater<vector<int>>
        > pq;

        pq.push({0, id});

        vector<bool> visited = vector(n, false);

        while(!pq.empty()) {
            vector<int> node = pq.top();
            pq.pop();

            if(visited[node[1]]) {
                continue;
            }

            visited[node[1]] = true;

            if(node[0] > level) {
                break;
            }
            else if(node[0] == level) {
                for(string watchedVideo: watchedVideos[node[1]]) {
                    videoWatchCt[watchedVideo]++;
                }
            }

            for(int frnd: friends[node[1]]) {
                pq.push({node[0] + 1, frnd});
            }
        }

        vector<string> watched;

        map<int, set<string>> ctToAlpha;

        for(auto it = videoWatchCt.begin(); it != videoWatchCt.end(); it++) {
            ctToAlpha[it -> second].insert(it -> first);
        }


        for(auto it = ctToAlpha.begin(); it != ctToAlpha.end(); it++) {
            for(string vid: it -> second) {
                watched.push_back(vid);
            }
        }

        return watched;
    }
};
