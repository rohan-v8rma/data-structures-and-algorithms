// https://leetcode.com/contest/biweekly-contest-168/problems/minimum-operations-to-transform-array/

class Solution {
public:
    static bool lastInBetween(int num1, int num2, int last) {
        return (min(num1, num2) <= last && max(num1, num2) >= last);
    }

    // for sure we have to bring the first N elements equivalent
    // so increment and decrement will for sure happen over there
    // we can only minimize by minimizing the last element such that we only append.
    
    long long minOperations(vector<int>& nums1, vector<int>& nums2) {
        int last = nums2[nums2.size() - 1];

        // +1 for appending it
        long long minOpsForLast = abs(nums1[0] - last) + 1;

        long long totalOps = 0;

        for(int i = 0; i < nums1.size(); i++) {
            if(lastInBetween(nums1[i], nums2[i], last)) {
                // no need for increment and decrement operations
                // straight away append the element while during the increment or decrement comes to be equal to the last element.
                minOpsForLast = 1;
            }
            // So both nums1[i] and nums2[i] are to one side of last
            else {
                minOpsForLast = min(
                    minOpsForLast, 
                    (long long)min(
                        // so if nums1 is closer, we append and then increment
                        abs(nums1[i] - last) + 1,
                        // if nums2 is closer, we increment and then append and then again increment
                        abs(nums2[i] - last) + 1
                    )
                );
            }

            totalOps += abs(nums1[i] - nums2[i]);
        }

        return totalOps + minOpsForLast;
    }
};©leetcode
