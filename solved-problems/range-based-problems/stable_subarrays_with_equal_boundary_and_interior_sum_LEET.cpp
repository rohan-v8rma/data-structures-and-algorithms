// https://leetcode.com/contest/weekly-contest-473/problems/stable-subarrays-with-equal-boundary-and-interior-sum/

class Solution {
public:
    // BRUTEFORCE
    // long long countStableSubarrays(vector<int>& capacity) {
    //     long long ct = 0;

    //     for(int i = 0; i < capacity.size(); i++) {
    //         long long sum = 0;
    //         for(int j = i + 2; j < capacity.size(); j++) {
    //             sum += capacity[j - 1];
    //             if(sum == capacity[i] && sum == capacity[j]) {
    //                 ct++;
    //             }
    //         }
    //     }

    //     return ct;
    // }

    // OPTIMAL
    long long countStableSubarrays(vector<int>& capacity) {
        vector<long long> pre = vector(capacity.size(), 0ll);

        long long sum = 0;

        for(int i = 0; i < capacity.size(); i++) {
            sum += capacity[i];
            pre[i] = sum;
        }

        map<vector<long long>, int> m;

        long long stableCt = 0;

        for(int i = capacity.size() - 2; i >= 0; i--) {
            // This is the key of the count of indices that have matching value to current element, and the sum of elements in between is equal to the element itself. Which is determined by the current prefix value + 2 * capacity.
            vector<long long> stableMatching = {capacity[i], pre[i] + 2 * capacity[i]};
            if(m.find(stableMatching) != m.end()) {
                stableCt += m[stableMatching];
            }

            // We add the previous element now because we don't want 2 adjacent 0s to be taken as stable array
            vector<long long> prevElementPreSum = {capacity[i + 1], pre[i + 1]};

            if(m.find(prevElementPreSum) == m.end()) {
                m[prevElementPreSum] = 1;
            }
            else {
                m[prevElementPreSum]++;
            }
        }

        return stableCt;
    }
};
