#include <iostream>
using namespace::std;

void naiveStringMatching(string text, string pattern) {
    int textLength = text.length();
    int patternLength = pattern.length();

    int maxShift = textLength - patternLength; // Suppose pattern is length 5 and text is of length 7. Then the max shift for checking for the pattern is 2, such that there are 5 characters to check from.

    if(maxShift < 0) {
        cout << "Pattern is longer than the Text provided.";
        return;
    }

    for(int shift = 0; shift <= maxShift; shift++) {
        for(int index = 0; index < patternLength; index++) {
            cout << pattern[index] << " vs. " << text[shift + index] << endl;
            if(text[shift + index] != pattern[index]) {
                cout << "Comparison failed.";
                if(shift < maxShift) {
                    cout << " Increasing shift.\n";
                }
                break;
            }

            if(index == patternLength - 1) {
                printf("Pattern found at index (%d) of Text\n", shift);
            }
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

    naiveStringMatching(text, pattern);

    return 0;
}