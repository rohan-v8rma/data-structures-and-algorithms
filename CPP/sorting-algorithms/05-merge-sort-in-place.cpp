#include <iostream>
#define SIZE 5

// Sort should be in ascending order from LEFT to RIGHT

// Function for printing the array
void printArr(int *arr, int size) {

    std::cout << "{";

    for(int index = 0; index < size; index++) {
        std::cout << *(arr + index);
        if(index + 1 != size) {
            std::cout << ", ";
        }
        
    }
    std::cout << "}\n";
}

void mergeInPlace(int* arr, int start, int middle, int end) { // end is EXCLUSIVE, meaning the sub-array is beginning from 'start' and upto but NOT including 'end'.
    
    int subArrLen = end - start;

    int mergedArr[subArrLen];
    
    int leftPtr = start;
    int rightPtr = middle;
    int mergedPtr = 0;

    while( (leftPtr < middle) && (rightPtr < end) ) {
        if( arr[leftPtr] < arr[rightPtr] ) {
            mergedArr[mergedPtr++] = arr[leftPtr++];
        }        
        else {
            mergedArr[mergedPtr++] = arr[rightPtr++];
        }
    }
    
    while(leftPtr < middle) {
        mergedArr[mergedPtr++] = arr[leftPtr++];
    } 
    
    while (rightPtr < end) {
        mergedArr[mergedPtr++] = arr[rightPtr++];
    }

    mergedPtr = 0;
    
    // Loop for changing the values in the unsorted sub-array, using the values of the MERGED array.
    for(int index = start; index < end; index++) {
        arr[index] = mergedArr[mergedPtr++];
    }
}

void mergeSortInPlace(int* unsortedArr, int start, int end) { // end is EXCLUSIVE, meaning the sub-array is beginning from 'start' and upto but NOT including 'end'.
    
    int subArrLen = end - start;

    if( subArrLen == 1 ) { // Base condition for recursive function. Once there is only one element in the sub-array, we can't divide it any further.
        return;
    }

    int middle = (start + end) / 2; 
    /*
    Because we obtain `middle` in this way, there is NO way that the the BASE-CONDITION stated above gets bypassed. 
    
    * Taking the case where have a 3-element array and start is 0, end is 3. Middle will be obtained as 1.

    ? So the first sub-array will have start - 0, end - 1 : meaning 1 element in the sub-array
    ! BASE-CONDITION HIT
    ? And the second sub-array will have start - 1, end - 3 : meaning 2 elements in the sub-array
      BASE-CONDITION not HIT

    * Taking the above 2-element array, where start is 1, end is 3. Middle will be obtained as 2.

    ? So the first sub-array will have start - 1, end - 2 : meaning 1 element in the sub-array
    ! BASE-CONDITION HIT
    ? And the second sub-array will have start - 2, end - 3 : meaning 1 element in the sub-array
    ! BASE-CONDITION HIT

    So, it is NOT possible that directly the length of the sub-array becomes 0, BYPASSING the base-case.
    */

    mergeSortInPlace(unsortedArr, start, middle);
    mergeSortInPlace(unsortedArr, middle, end);

    mergeInPlace(unsortedArr, start, middle, end);
}




int main() {
    int array[SIZE] = {5, 2, 4, 3, 1};

    mergeSortInPlace(array, 0, SIZE);
    
    // Function for printing the array
    printArr(array, SIZE);

}