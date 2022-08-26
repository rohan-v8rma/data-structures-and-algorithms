#include <iostream>
using namespace::std;
#define SIZE 7

//TODO: Figure out how exactly this works.

//? This algorithm is also faulty as the pivot element is not reaching its correct index position after reach pass. Try taking the example {1, 2, 4, 5, 3}. The middle element 4, when taken as pivot, would be at last position after first pass, when it should be second-last index. BUT IT DOES WORK and is MORE EFFICIENT since shifting of elements is less.

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

void quickSort(int* unsortedArr, int start , int end) {
    
    if( (end - start) <= 0 ) {
        return;
    }

    int pivot = (start + end) / 2;
    int pivotElement = unsortedArr[pivot];

    int left = start;
    int right = end;

    int temp;

    while(left <= right) {

        while(unsortedArr[left] < pivotElement) {  // There is no need to check out of bounds condition over here since pivotElement will definitely be present in the unsortedArr, stopping the left pointer.
            left++; 
        };
        while(unsortedArr[right] > pivotElement) { // There is no need of `&& (left <= right)` condition over here, because if `left` pointer stops somewhere, it means, that element is greater than or equal to pivotElement, and all elements to its LEFT are less than pivotElement. So, the 'right' pointer will automatically stop JUST to the left of 'left' pointer.
            right--; 
        };

        // if(left < right) . We removed this for the case when both left and right are at the pivot element, so the replacement operation has no meaning BUT we need to perform the increment operation here, since the above while loops won't run, but we need to recursively call quickSort again, which shouldn't be including the pivot element.

        if(left <= right) { // If 'left' and 'right' pointer stop before CROSSING each other, it means there is a violation of the condition that elements to the left of the pivot should be less than it and all elements to right of the pivot should be greater than it. So we must swap the elements pointed to by the 'left' and 'right' ponter in order to rectify this violation
            temp = unsortedArr[right];
            unsortedArr[right] = unsortedArr[left];
            unsortedArr[left] = temp;
            left++;
            right--;            
        }
    }
    

    quickSort(unsortedArr, left, end);
    quickSort(unsortedArr, start, right);
}

int main() {
    int arr[] = {1, 2, 4, 5, 10, 7, 3};
    quickSort(arr, 0, SIZE-1);
    
    // Function for printing the array
    printArr(arr, SIZE);
}


