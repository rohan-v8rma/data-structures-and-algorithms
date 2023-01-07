#include <iostream>
#include <math.h>

using namespace::std;

// Function for counting the number of digits
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

// Function for determining whether a number is odd or not (Used with lengths of provided numbersd)
int isOdd(int num) {
    return (num % 2); 
    // O represents false (is even)
    // 1 represents true (is odd)
}

// Used for determining the highest even length out of the two numbers to be multiplied.
int maxEven(int length1, int length2) {
    if(length1 >= length2) {
        if(isOdd(length1)) { // length1 is odd so we need to return even
            return (length1 + 1);
        }
        
        return length1; // length1 not odd, so we return it as it is
    }

    if(isOdd(length2)) { // length2 is odd so we need to return even
        return (length2 + 1);
    }

    return length2;
}


// Suppose we provide 2 n-bit numbers as arguments.
// Base case when both num1 and num2 have digitCt = 1.
long int karatsubaFastMultiplication(int num1, int num2) {

    // We need an even digit count.
    int highestDigitCt = maxEven(digitCt(num1), digitCt(num2)); 
    
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
        

        long int a1 = (num1 / numSplit);
        long int a2 = (num1 % numSplit);
        long int b1 = (num2 / numSplit);
        long int b2 = (num2 % numSplit);
        // cout << "Numbers are: ";
        // cout << a1 << a2 << "," << b1 << b2 << endl;
        
        long int A = a1 * b1;
        long int B = a2 * b2;
        long int C = (a1 + a2) * (b1 + b2);
        long int D = C - A - B;
        
        return ( (pow(10, highestDigitCt) * A) + ((numSplit) * D) + B );
    }    

    else {
        long int numSplit =  pow(10, (highestDigitCt / 2));        

        long int a1 = (num1 / numSplit);
        long int a2 = (num1 % numSplit);
        long int b1 = (num2 / numSplit);
        long int b2 = (num2 % numSplit);
        

        // 3 Recursive Calls

        // Recursive Call 1
        long int A = karatsubaFastMultiplication(a1, b1); 

        // Recursive Call 2
        long int B = karatsubaFastMultiplication(a2, b2); 

        // Recurive Call 3
        long int C = karatsubaFastMultiplication((a1 + a2), (b1 + b2)); 
        long int D = C - A - B;

        return ( (pow(10, highestDigitCt) * A) + ((numSplit) * D) + B );
    }

    return 0;

}

int main() {
    // Multiplications of numbers with even number of digits.
    printf("\nResult is : %ld\n\n", karatsubaFastMultiplication(12, 12));
    
    // Mismatching number of digits.
    printf("Result is : %ld\n\n", karatsubaFastMultiplication(4, 12));

    printf("(Rohan Verma - 21BCE0498)\n\n");

    // Multiplication of zeros for verification.
    printf("Result is : %ld\n\n", karatsubaFastMultiplication(0, 0));


    // Multiplication of two 7 digit numbers. (Used `ld` format specifier for displaying correct output)
    printf("Result is : %ld\n\n", karatsubaFastMultiplication(1234567, 1234567));

    return 0;
}