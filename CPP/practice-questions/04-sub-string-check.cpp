#include<iostream>
#include<string.h>

/*
If we used WHILE loop, for the edge case where substring is `susuper` and the main string is `sususuper`, the first non-matching character would be found at the index 4 of the mainstring, after which the check would again start from index 4, with `super` left in the main string. 
We should have started our check again from index 2 in order to find the substring match. So, in the case of using WHILE loop, we would have missed the occurrence of the substring.

THIS IS WHY WE ARE USING NESTED FOR LOOP.
*/

int subCheck(std::string subString, std::string mainString) {

    int mainStringLength = mainString.size();
    int subStringLength = subString.size();

    // Two for loops seem inefficient but this is the only for taking care of all edge cases.
    for(int mainIndex = 0; mainIndex < mainStringLength; mainIndex++) {
        for(int subIndex = 0; subIndex < subStringLength; subIndex++) {
            if(subString[subIndex] != mainString[mainIndex + subIndex]) { // If a non-matching character is found in the main string, we break the inner for loop and start checking from the next character of the main string.
                break;
            }
            else if(subIndex == (subStringLength - 1) ) { // When the last index also doesn't break the loop, it means that the substring is fully matched, so we will return the mainIndex, which is wear the occurrence of the substring starts.
                return mainIndex;
            };
        }
    }
    return -1;
}

int main() {
    std::string subString = "susuper";
    std::string mainString = "sususuper";

    int returnVal = subCheck(subString, mainString);
    
    // This if condition not satisified for -1 i.e., when substring not found in mainstring.
    if (returnVal + 1) {
        std::cout << "The substring '" << subString << "' is present in '" << mainString << "' at the index " << returnVal << "\n";
    }
    else {
        std::cout << "The substring '" << subString << "' is NOT present in '" << mainString << "'\n";
    };
}