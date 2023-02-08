#include <iostream>
#include <set>
using namespace std;


// Finding the unique subsequences of the LCS by effectively using backtracking
void findAllSubsequences(string LCS, int charToSelect, string stringSoFar, set<string>* allSubsequences) {
    if(charToSelect == LCS.length() - 1) {
        allSubsequences->insert(stringSoFar + "");
        allSubsequences->insert(stringSoFar + LCS[charToSelect]);
    }
    else {
        findAllSubsequences(LCS, charToSelect + 1, stringSoFar + "", allSubsequences);
        findAllSubsequences(LCS, charToSelect + 1, stringSoFar + LCS[charToSelect], allSubsequences);
        return; // Backtracking
    }
}

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

    string LCS = table[str1.length()][str2.length()];

    cout << "\nThe longest common subsequence of the strings `" 
         << str1 << "` and `"
         << str2 << "` is : "
         << LCS << endl;

    set<string>* allSubsequences = new set<string>;

    findAllSubsequences(LCS, 0, "", allSubsequences);

    cout << "\nThe rest of the subsequences are : \n";
    for(auto& it: *allSubsequences) {
        if(it.length() < LCS.length()) {
            cout << it << endl;
        }
        
    }
}

int main() {
    LCS_String("ACGTTCGA", "BCTBBCA");
    return 0;
}