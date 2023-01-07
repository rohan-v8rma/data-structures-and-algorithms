#include <iostream>
using namespace::std;

// return-type is also long long int.
long long int fibonacciNumber(int nthTerm) {
    
    long long int* fibonacciArr = new long long int[nthTerm + 1];

    for(int index = 0; index <= nthTerm; index++) {
        
        cout << "Computing fibonacci term number (" << index << ")";

        if(index == 0) {
            fibonacciArr[index] = 0;
        }
        else if(index == 1) {
            fibonacciArr[index] = 1;
        }
        else {
            fibonacciArr[index] = fibonacciArr[index - 1] + fibonacciArr[index - 2];
        }        
    } 

    long long int returnVal = fibonacciArr[nthTerm];
    // Freeing memory using array pointer deletion operator so that out-of-memory error does not occur
    delete[] fibonacciArr;

    return returnVal; 
}

int main() {
    for(int termNumber = 0; termNumber < 75; termNumber++) {
        printf("Term number %2d : %lld\n", termNumber, fibonacciNumber(termNumber));
    }

    return 0;
}