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

int main() {
    int array[SIZE] = {5, 2, 4, 3, 1};
    
    int temp;

    for(int pass = 0; pass < (SIZE - 1); pass++) { // Passes need to be 1 less than the size of the array.
        for(int checkIndex = pass; checkIndex >= 0; checkIndex--) {
            if(array[checkIndex + 1] < array[checkIndex]) { // If we consider the first iteration of the inner loop, array[checkIndex + 1] is the element that needs to be sorted, which is outside the portion of the array that has already been sorted.
                temp = array[checkIndex];
                array[checkIndex] = array[checkIndex + 1];
                array[checkIndex + 1] = temp;       
            }
            else { // If the element that needs to be sorted is greater than or equal to the element that is next to it, then it means that the unsorted element has reached its correct place in the sorted array. So, we can move onto the next pass.
                break;
            }
            
        }
    }

    // Function for printing the array
    printArr(array, SIZE);

}