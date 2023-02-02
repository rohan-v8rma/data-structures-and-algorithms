#include <iostream>
using namespace std;

// This algorithm gives us the LENGTH of the longest possible substring

void LCS_Length(string str1, string str2) {
    
    // We need to store one integer per cell
    int** table = new int*[str1.length() + 1];
    for(int index = 0; index <= str1.length(); index++) {
        table[index] = new int[str2.length() + 1];
    }

    for(int assumedLenOf1 = 0; assumedLenOf1 <= str1.length(); assumedLenOf1++) {
        for(int assumedLenOf2 = 0; assumedLenOf2 <= str2.length(); assumedLenOf2++) {

            // When we assume the length of either string to be 0, there is no possibility of a common subsequence.
            if( (assumedLenOf1 == 0) || (assumedLenOf2 == 0) ) { 
                
                table[assumedLenOf1][assumedLenOf2] = 0; // 0-length i.e., empty string to denote no common subsequence.

            }

            // If assumed length is `n`, to get the character at the last index of a string, we need to access index `n - 1`
            else if( str1[assumedLenOf1 - 1] == str2[assumedLenOf2 - 1] ) {
                
                table[assumedLenOf1][assumedLenOf2] = table[assumedLenOf1 - 1][assumedLenOf2 - 1] + 1; // 1 character common at the end

            }
            else {
                int len1 = table[assumedLenOf1 - 1][assumedLenOf2];
                int len2 = table[assumedLenOf1][assumedLenOf2 - 1];

                // Assigning the subsequence of maximum length out of the 2 options
                table[assumedLenOf1][assumedLenOf2] = ( len1 > len2 )? len1 : len2;
            }
        }
    }

    cout << "\nThe LENGTH of longest common subsequence of the strings `" 
         << str1 << "` and `"
         << str2 << "` is : "
         << table[str1.length()][str2.length()] << endl;


}

int main() {
    LCS_Length("ZFKZK", "FZK");
    return 0;
}

