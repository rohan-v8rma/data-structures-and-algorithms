#include <iostream>
using namespace std;



//! Note that if the value of the prefix function at a particular index is 1, it means the first character of the pattern; not the first INDEX.
int* computePrefixFunction(string pattern) {
    int matchIndex = 0;

    int patternLength = pattern.length();

    int* prefixFunction = new int[patternLength];

    for(int index = 0; index < patternLength; index++) {
        if(index == 0) {
            
            // This is the value of the prefix function for a particular index when no match is found in the string 
            prefixFunction[index] = 0; 
            
        }
        else if(pattern[index] == pattern[matchIndex]) {

            // Adding 1 to the matchIndex since we use 1-indexing in string matching
            prefixFunction[index] = matchIndex + 1; 
            
            // Incrementing matchIndex for the next iteration.
            matchIndex++;
        }
        else {
            // If a match is not found, resetting the matchIndex
            matchIndex = 0;

            // Value of the prefix function for a particular index when no match is found in the string.
            prefixFunction[index] = 0;
        }
    }

    return prefixFunction;
}

void printText(string text) {
    int textLength = text.length();

    for(int index = 0; index < textLength; index++) {
        printf(" %2d |", index+1);
    }
    
    printf("\n");

    for(int index = 0; index < textLength; index++) {
        printf("  %c |", text[index]);
    }

    printf("\n");
}


void kmpStringMatching(string text, string pattern) {

    int textLength = text.length();
    int patternLength = pattern.length();

    int* prefixFunction = computePrefixFunction(pattern);

    // for(int index = 0; index < patternLength; index++) {
    //     cout << prefixFunction[index];
    // }

    // This represents the position just before the start of the pattern.
    int patternIndex = 0;

    // 1-based indexing in string matching
    for(int textIndex = 1; textIndex <= textLength; textIndex++) {
        
        // We subtract 1 for textIndex to convert to Cpp indexing
        while( (patternIndex > 0) && (text[textIndex - 1] != pattern[patternIndex]) ) {

            // The first condition takes care of an infinite loop which could be triggered since the prefix function of the first character is always 0.
            patternIndex = prefixFunction[patternIndex - 1];
            
        }   

        if(text[textIndex - 1] == pattern[patternIndex]) {
            patternIndex++;

            // Since we compared the last character of the pattern and then incremented the patternIndex, the patternIndex would now be equal to the patternLength
            if( patternLength == patternIndex ) {
                cout << "The pattern `" << pattern << "` appears at 1-based index (" << textIndex - patternLength + 1 << ") of the text:\n";

                printText(text);

                patternIndex = prefixFunction[patternIndex - 1];
            }
        }
        // This else if condition can only be reached if the the current indices don't match, so we have no option other than to continue;
        else if( patternIndex == 0 ) {
            continue;
        }
    }
}


int main () {
    kmpStringMatching("ababcabcabababd", "ababd");
    return 0;
}