#include <iostream>
#define SIZE 5

// TODO: Figure out how to return and merge arrays, unlike in-place sorting, where the original array keeps getting modified instead of new arrays being created and merge
// Sort should be in ascending order from LEFT to RIGHT

int* merge(int* arr1, int* arr2) {
    
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

int* mergeSort(int* unsortedArr, int start, int end) { // end is EXCLUSIVE, meaning the sub-array is beginning from 'start' and upto but NOT including 'end'.
    
    int subArrLen = end - start;

    if( subArrLen == 1 ) { // Base condition for recursive function. Once there is only one element in the sub-array, we can't divide it any further.
        return;
    }

    int middle = (start + end) / 2;
    

    return merge(mergeSort(unsortedArr, start, middle), mergeSort(unsortedArr, middle, end));

    
}




int main() {
    int array[SIZE] = {5, 2, 4, 3, 1};

    mergeSortInPlace(array, 0, SIZE);
    
    // Loop for printing the array
    for(int index = 0; index < SIZE; index++) {
        std::cout << array[index] << "\n";
    }

}