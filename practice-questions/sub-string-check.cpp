#include<iostream>
#include<string.h>

int subCheck(std::string subString, std::string mainString) {
    
    int mainIndex = 0;
    int subIndex = 0;
    
    int lastMainStringIndex = (mainString.size() - 1);
    int lastSubStringIndex = (subString.size() - 1);


    // We keep this as the condition of the while loop as if mainIndex has gone beyond the bound of its highest value, we have nothing left to check. 
    // If subIndex has gone beyond the bound of its highest value, it means that all its index positions were checked without the subIndex getting reset back to 0, which means the first match of the subString has been found in the mainString. 
    while( !(mainIndex > lastMainStringIndex ) && !(subIndex > lastSubStringIndex ) ) { 
        
        if(mainString[mainIndex] == subString[subIndex]) {
            mainIndex++;
            subIndex++;
        }
        else {
            // If the subIndex is already incremented, we need to reset it back to 0, WITHOUT incrementing the mainIndex because it can be possible that an occurrence of the substring starts from the index of the mismatch. 
            //? Considering two strings 'super' and 'susuper', index 2 of main string will be a point of mismatch. Now, if we increment the mainIndex over here while resetting subIndex to 0, in the next iteration, the next index checked will be 3, which is 'u'. So we would miss the match of the substring present in the mainstring.
            if(subIndex != 0) { 
                subIndex = 0;
            }

            // If substring == 0, meaning not even a single character of the substring has matched so far with the mainstring, we increment the mainIndex to check for a match with the next mainstring character.
            else { 
                mainIndex++;
            };            
        };
    }

    // After the while loop, if the subIndex successfully reached greater than the last index, it means that all the index positions of the  substring were matched with the mainstring without the subIndex being reset back to 0. Even if the subIndex would been equal to the last index, it would have meant that the last index wasn't checked so the last character wasn't matched.
    if(subIndex > lastSubStringIndex) {
        return(( mainIndex - 1 ) - lastSubStringIndex); // Returning the index position of the occurrence of the substring in the mainstring
    }
    else {
        return -1; // Returning -1 when substring not found in mainstring
    }
}

int main() {
    std::string subString = "super";
    std::string mainString = "susuper";

    int returnVal = subCheck(subString, mainString);
    
    // This if condition not satisified for -1 i.e., when substring not found in mainstring.
    if (returnVal + 1) {
        std::cout << "The substring '" << subString << "' is present in '" << mainString << "' at the index " << returnVal << "\n";
    }
    else {
        std::cout << "The substring '" << subString << "' is NOT present in '" << mainString << "'\n";
    };
}