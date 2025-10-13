// https://leetcode.com/problems/remove-k-digits

class Solution {
public:
    string removeKdigits(string num, int k) {
        stack<char> d;
        int n = num.size();

        for(int i = 0; i < n; i++) {
            while(k > 0 && !d.empty()) {
                char top = d.top();

                if(top > num[i]) {
                    d.pop();
                    k--;
                }
                else {
                    break;
                }
            }

            // No point in storing leading zeros
            if(!d.empty() || num[i] != '0') {
                d.push(num[i]);
            }
        }

        // If k > 0, we know for sure this is a monotonically increasing series
        // Because all opportunities to make 142 as 12 have been exhuasted and still k is left.        

        // So we remove the largest numbers at the back, until k == 0
        // Because suppose we have 129 and we can remove 1 digit.
        // 29, 19, 12. It is in our benefit to remove the largest number

        while(!d.empty() && k > 0) {
            d.pop();
            k--;
        }

        string newNum = "";

        // Creating the number

        while(!d.empty()) {
            // This was causing memory limit exceeded
            // newNum = d.top() + newNum;
            // Reason: This creates a new string every iteration (O(n²) copies), causing memory limit exceeded.
            newNum.push_back(d.top());
            d.pop();
        }

        reverse(newNum.begin(), newNum.end());

        if(newNum == "") return "0";

        return newNum;
    }
};
