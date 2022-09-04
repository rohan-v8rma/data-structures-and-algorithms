#include <iostream>

void printArr(int* arr, int length) {
    printf("Elements of the array:\n");

    for(int index = 0; index < length; index++) {
        printf("%d\n", *(arr + index));
    }
}

int maxFinder(int* arr, int length) {
    int maxElement = -1;
    for(int index = 0; index < length; index++) {
        if( *(arr + index) > maxElement) {
            maxElement = *(arr + index);
        }
    }

    return maxElement;    
}

void countSort(int* arr, int length) {
    int maxElement = maxFinder(arr, length);

    if(maxElement < 0) {
        printf("max element is less than 0, not possible to form element of 0 or negative length");
        return;
    }

    int countArr[maxElement + 1];
    
    // Initializing all indices of the count array to 0
    for(int index = 0; index <= maxElement; index++) {
        countArr[index] = 0;
    }

    // Incrementing the corresponding index in the count array
    for(int index = 0; index < length; index++) {
        countArr[*(arr + index)] += 1;
    }

    int countArrPtr = 0;
    int mainArrPtr = 0;
    
    while(countArrPtr <= maxElement) { 
        while(countArr[countArrPtr]-- != 0) {
            *(arr + mainArrPtr++) = countArrPtr;
        }
        countArrPtr++;
    }

}

int main() {
    int length = 8;
    int arr[length] = {7, 7, 4, 3, 2, 5, 10, 1};
    
    printArr(arr, length);
    countSort(arr, length);
    printArr(arr, length);

    return 0;
}