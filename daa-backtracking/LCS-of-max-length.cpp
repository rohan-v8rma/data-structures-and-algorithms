#include <iostream>
#include <set>
using namespace std;

/*
This algorithm gives us the longest common subsequence as well as all subsequences that have length less than the LCS.
Due to this condition, only 1 subsequence of max possible length will be returned. 

Let us take an example to understand what this means: 

Suppose we have two strings: ABCD and ABDC.
There will be 2 longest subsequences in this case: `ABC` and `ABD`
But, because of the given constraints in this algorithm, we will identify only 1 out of these.
*/
void LCS_all(string str1, string str2) {
    
    // Set has been used since we need to store multiple strings, denoting multiple subsequences. But, we need to avoid duplicates 
    set<string>** table = new set<string>*[str1.length() + 1];
    for(int index = 0; index <= str1.length(); index++) {
        table[index] = new set<string>[str2.length() + 1];
    }

    for(int assumedLenOf1 = 0; assumedLenOf1 <= str1.length(); assumedLenOf1++) {
        for(int assumedLenOf2 = 0; assumedLenOf2 <= str2.length(); assumedLenOf2++) {

            // When we assume the length of either string to be 0, there is no possibility of a common subsequence.
            if( (assumedLenOf1 == 0) || (assumedLenOf2 == 0) ) { 
                
                table[assumedLenOf1][assumedLenOf2].insert(""); // Adding an empty string to denote no common subsequence.
        
            }

            // If assumed length is `n`, to get the character at the last index of a string, we need to access index `n - 1`
            else if( str1[assumedLenOf1 - 1] == str2[assumedLenOf2 - 1] ) {
                
                for(auto& it : table[assumedLenOf1 - 1][assumedLenOf2 - 1]) { // Getting all subsequences of the diagonally previous lengths of strings
                    table[assumedLenOf1][assumedLenOf2].insert(it + str1[assumedLenOf1  - 1]);
                }

            }

            // The 2 for-loops below are for cases when the last character of strings of the assumed length is not same
            else {
                // Reducing the assumed length of the FIRST string by 1 character
                for(auto& it: table[assumedLenOf1 - 1][assumedLenOf2]) {
                    table[assumedLenOf1][assumedLenOf2].insert(it);
                }

                // Reducing the assumed length of the SECOND string by 1 character
                for(auto& it: table[assumedLenOf1][assumedLenOf2 - 1]) {
                    table[assumedLenOf1][assumedLenOf2].insert(it);
                }
            }
            
        }
    }

    set<string>* allSubsequences = new set<string>();
    string LCS = "";

    // Adding the subsequences into another set in order to avoid duplicates.
    for(int i = 0; i <= str1.length(); i++) {
        for(int j = 0; j <= str2.length(); j++) {

            // For iterating over all subsequences.
            for(auto& it: table[i][j]) {
                (*allSubsequences).insert(it);
                
                if(LCS.length() < it.length()) {
                    LCS = it;
                }
            }

        }
    }

    cout << "\nThe LCS of the strings `" 
         << str1 << "` and `"
         << str2 << "` is : "
         << LCS << endl;

    printf("\nValue of k (length of longest common subsequence) is : %ld\n", LCS.length());

    cout << "\nThe rest of the subsequences, having length less than `k`"
         << "` are given below.\n" 
         << "Subsequences are in alphabetical order.\n" // This is due to property of ordered set.
         << endl;

    int count = 1;
    for(auto& it: *allSubsequences) {
        
        // This ensures that the LCS that has been found as well as other subsequences that have the length == k, which is the length of the LCS; are excluded from the output.
        if(it.length() < LCS.length() ) { 
            printf("Subsequence %2d : ", count);
            count++;

            if(it.length() > 0) {    
                cout << it << endl;
            }
            else {
                cout << "(EMPTY STRING)" << endl;
            }
        }
    }
}

int main() {
    LCS_all("ACGTTCGA", "BCTBBCA");
    return 0;
}