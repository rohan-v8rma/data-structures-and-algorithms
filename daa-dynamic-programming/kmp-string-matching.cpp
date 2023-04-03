#include <iostream>
using namespace::std;

// 0 based indexing

void computePi(string pattern, int* piArray) {
    int patternLength = pattern.length();

    int resetIndex = -1;

    piArray[0] = resetIndex;

    // Starting from second character of the pattern
    for(int index = 1; index < patternLength; index++) {
        if(pattern[index] == pattern[resetIndex + 1]) {
            resetIndex++;
        }
        else {
            resetIndex = -1;
        }
        piArray[index] = resetIndex;
    }
}

void KMPStringMatching(string text, string pattern) {
    int textLength = text.length();
    int patternLength = pattern.length();

    if( (textLength - patternLength) < 0 ) {
        cout << "Pattern is longer than the Text provided.\n";
        return;
    }

    int* piArray = new int[patternLength];

    computePi(pattern, piArray);

    int currentPatternIndex = -1;

    int textIndex = 0;

    int maxShift = textLength - patternLength;

    // Optimized the condition of KMP, reducing the number of while-loop iterations.
    while(textIndex - (currentPatternIndex + 1) <= maxShift) {
        cout << text[textIndex] << " vs. " << pattern[currentPatternIndex + 1] << endl;
        if( text[textIndex] == pattern[currentPatternIndex + 1] ) {
            currentPatternIndex++;
            textIndex++;
        }
        else if(currentPatternIndex != -1) {// We can still move back the pattern index and check
            currentPatternIndex = piArray[currentPatternIndex];
            printf("Check failed. Pattern index reset to (%d).\n\n", currentPatternIndex);
        }
        else { // No change of moving back so we increment the textIndex
            textIndex++;
            printf("Check failed and further reset not possible. Incrementing textIndex...\n\n");
        }
        
        if(currentPatternIndex == patternLength - 1) {
            printf("Pattern found at index (%d) of Text.\n\n", textIndex - patternLength);
            currentPatternIndex = piArray[currentPatternIndex]; // Moving back the pattern index
        }
    }

}

int main() {
    string pattern;
    cout << "Enter the pattern: ";
    cin >> pattern;

    string text;
    cout << "Enter the text in which the pattern has to be found: ";
    cin >> text;

    // pattern = "0001";
    // text = "000010001010001";

    KMPStringMatching(text, pattern);

    return 0;
}