#include <iostream>
#define SIZE 10
// Array rotation to the right

int main() {
    int d = 6;
    d %= SIZE;
    
    int *arr = (int*)malloc(SIZE * sizeof(int));
    // Looping for initializing the values of the array
    for(int index = 0; index < SIZE; index++) {
        *(arr + index) = (index + 1);
    };
    
    int temp[SIZE];
    int tempIndex = 0;
    for(int index = d; index < SIZE; index++) {
        *(temp + index) = *(arr + tempIndex);
        tempIndex++;
    };

    tempIndex = 0;

    for(int index = (SIZE - d); index < SIZE; index++) {
        *(temp + tempIndex) = *(arr + index);
        tempIndex++;
    };

    arr = temp;

    for(int index = 0; index < SIZE; index++) {
        printf("%d\n", *(arr + index));
    }

}