#include <iostream>
#define SIZE 7
using namespace::std;

// In this algorithm, the pivot element is moved to the end, and then the comparisons take place. This is a bit inefficient because of the two extra swapping operations along with the conditions for keeping the 'left' and 'right' pointers within bounds.

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

void quickSort(int* unsortedArr, int start, int end) { // end is inclusive

    if(end - start <= 0) { // If the sub-array has one element, there is nothing to swap in the array. Also, the array can faultily have less than 1 element, which is also a case that requires no action
        return;
    }

    // Selecting the pivot element
    int pivot = (start + end) / 2;
    int pivotElement = unsortedArr[pivot];

    int temp;

    // Dynamic indices for comparison of elements
    int left = start;
    int right = end;

    // Swapping the pivot element to the last indice
    unsortedArr[pivot] = unsortedArr[right];
    unsortedArr[right] = pivotElement;
    right--; // Moving `right` pointer ahead of the pivot element.

    while(left <= right) {
        while( (left <= end) && (unsortedArr[left] < pivotElement) ) { 
            /* 
            The first condition is to ensure 'left' pointer doesn't go out of bounds. The second condition is for stopping the 'left' pointer as soon as an element greater than or equal to the pivot is found. 
            
            The 'left' pointer anyways won't go out of bounds because the pivotElement is shifted to the end anyways, so the second condition will be violated before 'left' can go out of bounds.
            */
            left++;
        }
        while( (right >= start) && (unsortedArr[right] > pivotElement) && (left <= right) ) { 
            /* 
            The first condition is to ensure 'right' pointer doesn't go out of bounds. The second condition is for stopping the 'right' pointer as soon as an element greater than or equal to the pivot is found. The third condition is for stopping the 'right' pointer as soon as the 'right' pointer crosses the 'left' pointer. 
            
            If the 'right' pointer crosses the 'left' pointer, it means all element to the right of the 'right' pointer are greater than or equal to the pivot. So, we can replace the element just to the right of the 'right' pointer, which is pointed to by the 'left' pointer.

            It is necessary that the 'right' pointer crosses the 'left' pointer, as it gives us confirmation that all elements to the right of the 'right' pointer, including the element pointed to by the 'left' pointer, are greater than or equal to pivot element, meaning it would be okay if we swapped the 'left' pointer element, with the end element, which is the where the pivot is temporarily placed.
            */
            right--;
        }

        if (left < right) { // If the above WHILE loops which increment the 'left' and 'right' pointer end, and the 'left' pointer is still less than the 'right' pointer, we should swap the elements pointed to by the 'left' and 'right' pointer, because it is a VIOLATION of the condition that all elements bigger than pivot should be on the right of it and all elements small than pivot should be on the left of it.
            temp = unsortedArr[left];
            unsortedArr[left] = unsortedArr[right];
            unsortedArr[right] = temp;
            left++;
            right--;
        }
    }
    // The above WHILE loop ends when the right bound crosses the left bound. At this point, all elements to the left of the left bound are less than the pivot and all elements including the left bound as well as to its right are greater than or equal to the pivot, so we can replace the left bound with the last element, which is the pivot.


    temp = unsortedArr[left];
    unsortedArr[left] = unsortedArr[end];
    unsortedArr[end] = temp;

    quickSort(unsortedArr, start, right); // At the end, the 'right' ptr is adjacent to the 'left' ptr, where the 'left' ptr is to its right.
    quickSort(unsortedArr, ++left,  end); // The 'left' ptr is the pivot itself, so we need to call quickSort on the sub-array after the pivot, which is why we use ++left.
}

int main() {
    int arr[SIZE] = {1, 2, 4, 5, 10, 7, 3};
    // int arr[SIZE] = {1, 2, 4, 5, 10, 7, 3};
    quickSort(arr, 0, SIZE-1);
    
    // function for printing the array
    printArr(arr, SIZE);
}

    
