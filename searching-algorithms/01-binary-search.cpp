#include <iostream>

int binSearch(int* arr, int startIndex, int endIndex, int target) {
    int middleIndex = ( startIndex + endIndex ) / 2;
    
    // if ( (startIndex > endIndex) || ( (startIndex == endIndex) && (target != arr[startIndex]) ) ) { // The second condition in the OR is to prevent endless recursive calls when startIndex == endIndex and target is not equal to the middleIndex. If we hadn't placed this condition, there would be no changes in the start and end indices in the recursive calls since middleIndex would always be equal to start and end index.
    //     return -1;
    // };

    if (startIndex > endIndex) { // As we increase the start index and decrease the end index in the process of finding the target element, if the target is not present in the array, a point will be reached when startIndex will be greater than endIndex.   
        return -1;
    }

    if (arr[middleIndex] > target) {
        endIndex = middleIndex - 1; // Since we have already seen that the value at middleIndex is more than target, so we can keep endIndex one less than the middleIndex. 
    }
    else if (arr[middleIndex] < target) {
        startIndex = middleIndex + 1; // Since we have already seen that the value at middleIndex is less than target, so we can keep startIndex one more than the middleIndex. 
    }
    else {
        return middleIndex;
    };

    return binSearch(arr, startIndex, endIndex, target);
}

int main() {
    int arr[6] = {1, 2, 5, 6, 9, 100};
    std::cout << binSearch(arr, 0, 2, 2) << std::endl;
}