#include <iostream>
using namespace std;

// This algorithm gives us the STRING REPRESENTATION of the longest possible substring

void LCS_String(string str1, string str2) {
    
    // We need to store one string per cell
    string** table = new string*[str1.length() + 1];
    for(int index = 0; index <= str1.length(); index++) {
        table[index] = new string[str2.length() + 1];
    }

    for(int assumedLenOf1 = 0; assumedLenOf1 <= str1.length(); assumedLenOf1++) {
        for(int assumedLenOf2 = 0; assumedLenOf2 <= str2.length(); assumedLenOf2++) {

            // When we assume the length of either string to be 0, there is no possibility of a common subsequence.
            if( (assumedLenOf1 == 0) || (assumedLenOf2 == 0) ) { 
                
                table[assumedLenOf1][assumedLenOf2] = ""; // Adding an empty string to denote no common subsequence.

            }

            // If assumed length is `n`, to get the character at the last index of a string, we need to access index `n - 1`
            else if( str1[assumedLenOf1 - 1] == str2[assumedLenOf2 - 1] ) {
                
                table[assumedLenOf1][assumedLenOf2] = table[assumedLenOf1 - 1][assumedLenOf2 - 1] + str1[assumedLenOf1  - 1];

            }
            else {
                string sub1 = table[assumedLenOf1 - 1][assumedLenOf2];
                string sub2 = table[assumedLenOf1][assumedLenOf2 - 1];

                // Assigning the subsequence of maximum length out of the 2 options
                table[assumedLenOf1][assumedLenOf2] = ( sub1.length() > sub2.length() )? sub1 : sub2;
            }
        }
    }

    cout << "\nThe longest common subsequence of the strings `" 
         << str1 << "` and `"
         << str2 << "` is : "
         << table[str1.length()][str2.length()] << endl;


}

int main() {
    LCS_String("ZFKZK", "FZK");
    return 0;
}