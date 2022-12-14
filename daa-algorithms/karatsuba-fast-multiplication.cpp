#include <iostream>
#include <math.h>

using namespace::std;

int digitCt(int num) {

    int numOfDigits = 0;

    if(num == 0) {
        numOfDigits++;
    }
    else {
        while(num != 0) {
            numOfDigits++;
            num /= 10;
        }
    }

    return numOfDigits;
}

int isOdd(int num) {
    return (num % 2); 
    // O represents false (is even)
    // 1 represents true (is odd)
}

int maxEven(int length1, int length2) {
    if(length1 >= length2) {
        if(isOdd(length1)) { // length1 is odd so we need to return even
            return (length1 + 1);
        }
        
        return length1;

    }

    if(isOdd(length2)) { // length2 is odd so we need to return even
        return (length2 + 1);
    }

    return length2;
}


// Suppose we provide 2 n-bit numbers as arguments.
// Base case when both num1 and num2 have digitCt = 1.
int karatsubaFastMultiplication(int num1, int num2) {
    int highestDigitCt = maxEven(digitCt(num1), digitCt(num2)); // We need an even digit count.
    
    // Highest Digit Count can only be multiples of 2, starting from 2
    // 2, 4, 6, 8, 10...

    if( highestDigitCt == 2 ) { // Base condition that contains no recursive calls.
        /*
        Possible cases:
        - Two 2-digit numbers 
        - One 1-digit AND 1 2-digit number
        - Two 1-digit numbers
        */ 
        int numSplit =  pow(10, (highestDigitCt / 2));        
        

        int a1 = (num1 / numSplit);
        int a2 = (num1 % numSplit);
        int b1 = (num2 / numSplit);
        int b2 = (num2 % numSplit);
        cout << "Numbers are: ";
        cout << a1 << a2 << "," << b1 << b2 << endl;
        
        int A = a1 * b1;
        int B = a2 * b2;
        int C = (a1 + a2) * (b1 + b2);
        int D = C - A - B;
        
        return ( (pow(10, highestDigitCt) * A) + ((numSplit) * D) + B );
    }    

    else {
        int numSplit =  pow(10, (highestDigitCt / 2));        

        int a1 = (num1 / numSplit);
        int a2 = (num1 % numSplit);
        int b1 = (num2 / numSplit);
        int b2 = (num2 % numSplit);
        

        // 3 Recursive Calls
        int A = karatsubaFastMultiplication(a1, b1); // Recursive Call 1
        int B = karatsubaFastMultiplication(a2, b2); // Recursive Call 2
        int C = karatsubaFastMultiplication((a1 + a2), (b1 + b2)); // Recurive Call 3
        int D = C - A - B;

        return ( (pow(10, highestDigitCt) * A) + ((numSplit) * D) + B );
    }

    return 0;

}

int main() {
    printf("Result is : %d\n\n", karatsubaFastMultiplication(12, 12));
    printf("Result is : %d\n\n", karatsubaFastMultiplication(4, 12));
    printf("Result is : %d\n\n", karatsubaFastMultiplication(0, 0));

    printf("Result is : %d\n\n", karatsubaFastMultiplication(3454, 123));

    return 0;
}