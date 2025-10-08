// https://leetcode.com/problems/avoid-flood-in-the-city

class Solution {
  public:
    vector<int> avoidFlood(vector<int>& rains) {
        vector<int> result;

        set<int> sorted_clear_days;

        map<int, int> rain_day;

        int n = rains.size();

        for(int i = 0; i < n; i++) {
            if(rains[i] == 0) {
                result.push_back(1);
                sorted_clear_days.insert(i);
            }
            else {
                // It rained on this day, so no clearing possible
                result.push_back(-1);

                if(rain_day.count(rains[i])) {
                    int pos_of_previous_rains_i = rain_day[rains[i]];

                    // Getting the first clear day after the lake was filled.
                    // Because consider the case: 2, 0, 1, 0, 2, 1
                    // So, if we use the second 0 to empty 2, then 1 will not be emptied
                    // So, it is better to empty the lake as soon as it is filled
                    // Suppose 1 had occured even before first 2, then the first 0 had already been consumed, then we would have had to take the second 0 to clear 2
                    // We use upper bound so that we get the least index > pos_of_previous_rains_i
                    auto it = sorted_clear_days.upper_bound(pos_of_previous_rains_i);

                    // A 0, after the lake filling and before the lake flooding was not found.
                    if(it == sorted_clear_days.end()) {
                        return {};
                    }

                    result[*it] = rains[i];

                    sorted_clear_days.erase(it);
                }

                rain_day[rains[i]] = i;
            }
        }

        return result;
    }
};
