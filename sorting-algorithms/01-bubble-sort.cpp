#include <iostream>
#define SIZE 6

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
    int array[SIZE] = {2, 8, 6 , 4, 10, 9};
    int temp, swap;

    for(int pass = 0 ; pass < (SIZE - 1) ; pass++) {
        swap = 0; // we reset the swap variable for the next pass.
        for(int comparison = 0 ; comparison < ( (SIZE - 1) - pass ) ; comparison++) {
            if(array[comparison] > array[comparison + 1]) {
                temp = array[comparison];
                array[comparison] = array[comparison + 1];            
                array[comparison + 1] = temp;

                swap++;            
            };
        }

        if(swap == 0) { // If no swaps occur in a particular pass, then it is clear that the array is already sorted and we need not make any more passes.
            break;
        };
    }

    // Function for printing the array
    printArr(array, SIZE);

}