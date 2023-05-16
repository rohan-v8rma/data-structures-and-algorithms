#include <iostream>
using namespace::std;

string towerOfHanoi_DP() {
    for(int index = 0)
}

// Important to pass an array of appropriate size.

int fibonacci(int elementNum, int* arr) {
    for(int index = 0; index <= elementNum; index++) {
        if(index == 0 || index == 1) {
            arr[index] = index;
        }
        else {
            arr[index] = arr[index - 1] + arr[index - 2];
        }
    }

    return arr[elementNum];
}

int main() {
    const int elementNum = 30;

    int arr[elementNum + 1];


    cout << fibonacci(elementNum, arr) << endl;

    cout << 
}