#include <iostream>
#define SIZE 5

// Sort should be in ascending order from LEFT to RIGHT

// int returnMaxIndex(int arr[SIZE]) {
//     for(int )
// }

int main() {
    int array[SIZE] = {5, 2, 4, 3, 1};
    int maxIndex, temp;

    // Loop for making passes
    for(int pass = 0; pass < (SIZE - 1); pass++) {
        maxIndex = 0;
        // Loop for finding the index position of the next largest element
        for(int index = 1; index < ((SIZE) - pass); index++) { // We start from index 1 because by default we have assigned the maxIndex to be 0.
            if(array[index] > array[maxIndex]) {
                maxIndex = index;
            };            
        }
        // Swap operation of the element of the next largest value with the lastmost unsorted element.
        if (maxIndex != (SIZE - 1 - pass) ) { // if the position of the maxIndex is already at the end, we need not swap it.
            temp = array[maxIndex];
            array[maxIndex] = array[SIZE - 1 - pass];
            array[SIZE - 1 - pass] = temp;
        };
    }

    // Loop for printing th array
    for(int index = 0; index < SIZE; index++) {
        std::cout << array[index] << "\n";
    }

}