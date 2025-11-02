// https://leetcode.com/contest/weekly-contest-474/problems/minimum-time-to-complete-all-deliveries/

class Solution {
public:
    int gcd(int a, int b) {
        if(b == 0) return a;
        if(a < b) return gcd(b, a);

        return gcd(b, a % b);
    }
    
    long long minimumTime(vector<int>& d, vector<int>& r) {

        long long deliveries = (long long)d[0] + d[1];

        long long lower_bound = deliveries;
        long long upper_bound = (long long)INT_MAX * 100;
        
        long long total = (long long)INT_MAX * 100;

        int numGcd = gcd(r[0], r[1]);

        // These will tell us when common breaks come
        long long numLcm = (r[0] * r[1]) / numGcd;

        while(lower_bound <= upper_bound) {
            long long newTotal = (upper_bound + lower_bound) / 2;

            long long commonBreaks = newTotal / numLcm;

            // Exclusive rest time of 1
            long long restTime1 = (newTotal / r[0]) - commonBreaks;

            // Exclusive rest time of 2
            long long restTime2 = (newTotal / r[1]) - commonBreaks;

            // time left after all the common breaks (no action possible is removed)
            long long timeLeft = newTotal - commonBreaks;

            // So either the rest time of 1 will be larger or the deliveries of 2 will be more
            timeLeft -= max(restTime1, (long long)d[1]);
            timeLeft -= max(restTime2, (long long)d[0]);
            
            if(timeLeft >= 0) {
                total = min(total, newTotal);
                upper_bound = newTotal - 1;
            }
            else {
                lower_bound = newTotal + 1;
            }
        }

        return total;
    }
};©leetcode
