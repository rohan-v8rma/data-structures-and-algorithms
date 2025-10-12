// https://leetcode.com/problems/maximum-total-damage-with-spell-casting/

typedef long long l;

class Solution {
public:
    l recurse(int idx, vector<l>& powers, vector<l>& dp, map<l, l>& powersMap) {
        if(powers.size() <= idx) return 0ll;
        if(dp[idx] != -1) return dp[idx];

        // Over here we will run the pick, non-pick logic

        auto next = lower_bound(powers.begin(), powers.end(),  powers[idx] + 3);

        int nextIdx = next - powers.begin();

        l pick = powers[idx] * 1ll * powersMap[powers[idx]] + recurse(nextIdx, powers, dp, powersMap);

        l skip = recurse(idx + 1, powers, dp, powersMap);

        return dp[idx] = max(pick, skip);
    }

    long long maximumTotalDamage(vector<int>& power) {
        map<l, l> powersMap;

        for(int i = 0; i < power.size(); i++) {
            powersMap.emplace(power[i], 0);
            powersMap[power[i]]++;
        }

        vector<l> powers;

        for(const auto& pair: powersMap) {
            powers.push_back(pair.first);
        }

        vector<l> dp = vector(powers.size(), -1ll);

        return recurse(0, powers, dp, powersMap);
    }
};
