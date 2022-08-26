#include <iostream>
#define SIZE 5

// Sort should be in ascending order from LEFT to RIGHT

// TODO : will having a function for these operations have any performance impact.
// int returnMaxIndex(int arr[SIZE]);
// int swap(int index1, int index2, int arr);

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
        // Swap operation of the element of the next largest value with the last-most unsorted element.
        if (maxIndex != (SIZE - 1 - pass) ) { // if the position of the maxIndex is already at the end, we need not swap it.
            temp = array[maxIndex];
            array[maxIndex] = array[SIZE - 1 - pass];
            array[SIZE - 1 - pass] = temp;
        };
    }

    // Function for printing the array
    printArr(array, SIZE);

}