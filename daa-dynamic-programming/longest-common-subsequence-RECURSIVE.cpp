#include <iostream>
using namespace::std;

string removeLastChar(string str) {
    return (str.substr(0, str.length() - 1));
}

// For function that returns the integral length of the Longest Common Subsequence.

int max(int num1, int num2) {
    return (num1 >= num2 ? num1 : num2); 
}


int longestCommonSubsequence(string str1, string str2) {
    if( (str1.length() == 0) || (str2.length() == 0) ) {
        return 0;
    }

    if(str1[str1.length() - 1] == str2[str2.length() - 1]) {
        return ( longestCommonSubsequence(removeLastChar(str1), removeLastChar(str2)) + 1 );
    }
    else {
        return ( max( longestCommonSubsequence( removeLastChar(str1), str2), longestCommonSubsequence(str1, removeLastChar(str2) ) ) );
    };

    return 0;
}

// For function that returns the string representation of the Longest Common Subsequence.

string maxLen(string str1, string str2) {
    return (str1.length() >= str2.length() ? str1 : str2); 
}

char lastChar(string str1) {
    return (str1[str1.length() - 1]);
}

string stringOfLCS(string str1, string str2) {
    if( (str1.length() == 0) || (str2.length() == 0) ) {
        return "";
    }

    if(str1[str1.length() - 1] == str2[str2.length() - 1]) {
        return ( stringOfLCS(removeLastChar(str1), removeLastChar(str2)) + lastChar(str1) );
    }
    else {
        return ( maxLen( stringOfLCS( removeLastChar(str1), str2), stringOfLCS(str1, removeLastChar(str2) ) ) );
    };

    return "";
}

int main() {

    string str1 = "FZK";
    string str2 = "ZFKZK";

    printf("Integral Length of LCS : %d\n", longestCommonSubsequence(str1, str2));
    cout << "String Representation of LCS : " << stringOfLCS(str1, str2) << endl;

    return 0;
}