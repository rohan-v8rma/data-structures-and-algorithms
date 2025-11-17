// https://leetcode.com/problems/count-distinct-integers-after-removing-zeros/

class Solution {
public:
    int getDigit(long long n, int d) {
        return (double)((long long)( n  / pow(10, d - 1) ) % 10);
    }
    
    long long countDistinct(long long n) {
        // Interestingly, log10(999999999999998) returns 15 but log(999999999999998) / log(10) returns 14.9999...
        // And (int)(log(1000000000000000) / log(10)) returns 14
        // So, always use to_string to calculate number of digits

        int nD = to_string(n).length();

        long long total = 0;

        for(int digits = 1; digits < nD; digits++) {
            total += pow(9, digits);
        }

        bool containsZeros = false;

        for(int d = nD; d >= 1; d--) {
            long long digit = getDigit(n, d);

            if(digit == 0) {
                containsZeros = true;
                break;
            };

            total += (digit - 1) * pow(9, d - 1);

            if(d == 1 && !containsZeros) {
                total += 1;
            }
        }

        return total;
    }
};
