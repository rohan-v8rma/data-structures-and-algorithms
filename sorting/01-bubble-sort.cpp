#include <iostream>
#define SIZE 6

int main() {
    int array[SIZE] = {2, 8, 6 , 4, 10, 9};

    int temp;
    for(int pass = 0 ; pass < (SIZE - 1) ; pass++) {
        for(int comparison = 0 ; comparison < ( (SIZE - 1) - pass ) ; comparison++) {
            if(array[comparison] > array[comparison + 1]) {
                temp = array[comparison];
                array[comparison] = array[comparison + 1];            
                array[comparison + 1] = temp;            
            }
        }
    }

    for(int index = 0; index < SIZE; index++) {
        std::cout << array[index] << "\n";
    }

}