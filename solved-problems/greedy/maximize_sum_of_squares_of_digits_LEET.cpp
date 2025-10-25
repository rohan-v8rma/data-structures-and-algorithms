// https://leetcode.com/contest/biweekly-contest-168/problems/maximize-sum-of-squares-of-digits/

class Solution {
public:
  string maxSumOfSquares(int num, int sum) {
    // num is number of digits
    // sum is the sum of the digits

    // so we need to divide such that the squares of the digits are max
    // suppose sum is 10
    // if we take 91, 9^2 + 1^2 = 81
    // if we take 82, 8^2 + 2^2 = 68
    // so we have to aim to take maximum 9s
    long long sqSum = 0;

    string s = "";

    for(int i = 0; i < num; i++) {
      int digit = min(sum, 9);

      sqSum += pow(digit, 2);

      s += (char)(digit + '0');

      sum -= digit;
    }

    if(sum > 0) return "";


    return s;
  }
};
