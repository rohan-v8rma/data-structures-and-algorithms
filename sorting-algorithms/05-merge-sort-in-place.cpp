#include <iostream>
#define SIZE 5

// Sort should be in ascending order from LEFT to RIGHT

void mergeInPlace(int* arr, int start, int middle, int end) {
    
    int subArrLen = end - start;

    int mergedArr[subArrLen];
    
    int leftPtr = start;
    int rightPtr = middle;
    int mergedPtr = 0;

    while( (leftPtr < middle) && (rightPtr < end) ) {
        if( *(arr + leftPtr) < *(arr + rightPtr) ) {
            mergedArr[mergedPtr++] = *(arr + leftPtr++);
        }        
        else {
            mergedArr[mergedPtr++] = *(arr + rightPtr++);
        }
    }
    
    while(leftPtr < middle) {
        mergedArr[mergedPtr++] = *(arr + leftPtr++);
    } 
    
    while (rightPtr < end) {
        mergedArr[mergedPtr++] = *(arr + rightPtr++);
    }

    mergedPtr = 0;

    // Loop for changing the values in the unsorted sub-array, using the values of the MERGED array.
    for(int index = start; index < end; index++) {
        *(arr + index) = mergedArr[mergedPtr++];
    }
}

void mergeSortInPlace(int* unsortedArr, int start, int end) { // end is EXCLUSIVE, meaning the sub-array is beginning from 'start' and upto but NOT including 'end'.
    
    int subArrLen = end - start;

    if( subArrLen == 1 ) { // Base condition for recursive function. Once there is only one element in the sub-array, we can't divide it any further.
        return;
    }

    int middle = (start + end) / 2;
    

    mergeSortInPlace(unsortedArr, start, middle);
    mergeSortInPlace(unsortedArr, middle, end);

    mergeInPlace(unsortedArr, start, middle, end);
}




int main() {
    int array[SIZE] = {5, 2, 4, 3, 1};

    mergeSortInPlace(array, 0, SIZE);
    
    // Loop for printing the array
    for(int index = 0; index < SIZE; index++) {
        std::cout << array[index] << "\n";
    }

}