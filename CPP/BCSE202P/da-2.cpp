#include <iostream>

#define SIZE 6

struct studentRec {
    int regno;
    std::string name;
    float cgpa;
};

void printStruct(studentRec* arr, int size) {
    
    for(int index = 0; index < size; index++) {
        printf("%d\n", arr[index].regno);
    }
    printf("\n");
}

// This function returns the index position of the target element. If the target element is not present, it returns -1.
int binSearch(studentRec* arr, int s, int e, int target) {
    if( s > e ) {
        return -1;
    }
    
    int middle = (s + e) / 2;
    
    if(arr[middle].regno == target) {
        return middle;
    }
    else if(arr[middle].regno > target) {
        binSearch(arr, s, middle-1, target);
    }
    else if(arr[middle].regno < target) {
        binSearch(arr, middle+1, e, target);
    };
}

void bubbleSort(studentRec* arr, int size) {	 	  	 	 		     	      	      	  	  	 	
    
    int swap = 0;
    int comparisons = 0;
    
    studentRec temp;
    
    for(int pass = 1; pass < size; pass++) {
        for(int checkIndex = 0; checkIndex < (size - pass); checkIndex++) {
            
            comparisons++; // Incrementing the comparisons variable should be outside the if condition. Because inside the if condition, 
            
            if(arr[checkIndex].regno > arr[checkIndex + 1].regno) {
                
                temp = arr[checkIndex];
                arr[checkIndex] = arr[checkIndex + 1];
                arr[checkIndex + 1] = temp;
                swap++;
            }
            
        }
        
        if(swap == 0) {
            break;
        }
    }
    
    printf("Number of comparisons in bubble sort : %d\n", comparisons);
}

void insertionSort(studentRec* arr, int size) {
    
    int comparisons = 0;
    studentRec temp;
    
    for(int pass = 1; pass < size; pass++) {
        for(int index = pass; index > 0; index--) {	 	  	 	 		     	      	      	  	  	 	
            
            comparisons++;
            
            // Moving the index as the unsorted element moves index by index towards its correct position
            if(arr[index].regno < arr[index - 1].regno) {
                
                temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
            }
            else {
                break;
            }
        }
    }
    
    printf("Number of comparisons in insertion sort : %d\n", comparisons);
}

void selectionSort(studentRec* arr, int size) {
    
    int comparisons = 0;
    
    studentRec temp;
    
    int maxIndex = 0;
    
    for(int pass = size - 1; pass >= 0; pass--) {
        
        maxIndex = 0;
        
        // Loop for finding index of max element
        for(int index = 1; index <= pass; index++) { 
            // We start with index 1 because we take 0th index as max by default
            
            comparisons++;
            
            if(arr[maxIndex].regno < arr[index].regno) {	 	  	 	 		     	      	      	  	  	 	
                maxIndex = index;
            }
        }
        
        if(maxIndex != pass) { // We need not replace if maxIndex and pass are same
            temp = arr[pass];
            arr[pass] = arr[maxIndex];
            arr[maxIndex] = temp;
        }
        
    }

    
    printf("Number of comparisons in selection sort : %d\n", comparisons);
}


// Return type is integer since this is a recursive function, so it would be easier to return the number of comparisons.
int merge(studentRec* arr, int start, int middle, int end) {
    int comparisons = 0;
    
    studentRec mergedArr[end - start];
    
    int leftPtr = start;
    int rightPtr = middle;
    int mergePtr = 0;
    
    while( (leftPtr < middle) && (rightPtr < end) ) {
        if( (*(arr + leftPtr)).regno < (*(arr + rightPtr)).regno ) {
            mergedArr[mergePtr++] = *(arr + leftPtr++);
        }
        else {
            mergedArr[mergePtr++] = *(arr + rightPtr++);
        }
        comparisons++;
    }	 	  	 	 		     	      	      	  	  	 	
    
    while(leftPtr < middle) {
        mergedArr[mergePtr++] = *(arr + leftPtr++);
    }
    
    while(rightPtr < end) {
        mergedArr[mergePtr++] = *(arr + rightPtr++);
    }
    
    mergePtr = 0;
    
    for(int index = start; index < end; index++) {
        *(arr + index) = mergedArr[mergePtr++];
    }
    
    return comparisons;
}

// Return type is integer since this is a recursive function, so it would be easier to return the number of comparisons.
int mergeSort(studentRec* arr, int start, int end) { // end is exclusive, for simple splitting using the middle element
    int subArrLen = end - start;
    
    if( subArrLen == 1 ) {
        return 0;
    }
    
     int middle = (start + end) / 2;
     
     return(mergeSort(arr, start, middle) + mergeSort(arr, middle, end) + merge(arr, start, middle, end));
}

// Return type is integer since this is a recursive function, so it would be easier to return the number of comparisons.
int quickSort(studentRec* arr, int start, int end) { // end is inclusive
    
    int comparisons = 0;
    
    if (end - start <= 0) {	 	  	 	 		     	      	      	  	  	 	
        return comparisons;
    }

    int pivot = (start + end) / 2;
    studentRec pivotElement = arr[pivot];
    
    studentRec temp;
    
    int left = start;
    int right = end;
    
    arr[pivot] = arr[right];
    arr[right] = pivotElement;
    right--; // Moving `right` pointer ahead of the pivot element
    
    while (left <= right) {
        while( (left <= end) && (arr[left].regno < pivotElement.regno) ) {
            left++;
            comparisons++; // for the last comparison, which doesn't execute the body of the while loop
        }
        comparisons++;
        
        while( (right >= start) && (arr[right].regno > pivotElement.regno) && (left <= right) ) {
            right--;
            comparisons++;
        }
        comparisons++; // for the last comparison, which doesn't execute the body of the while loop
        
        if(left < right) {
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
            
        }	 	  	 	 		     	      	      	  	  	 	
    }
    
    temp = arr[left];
    arr[left] = arr[end];
    arr[end] = temp;
    
    return (comparisons + quickSort(arr, start, right) + quickSort(arr, ++left, end));
    
    
}


int main() {
    // studentRec : {regno, name, cgpa}
    studentRec arr [5][SIZE] = {
        { {46, "a", 9.5}, {15, "b", 9.3}, {3, "c", 9.9}, {41, "d", 9.5}, {2, "e", 9.3}, {1, "f", 9.9} },
        { {46, "a", 9.5}, {15, "b", 9.3}, {3, "c", 9.9}, {41, "d", 9.5}, {2, "e", 9.3}, {1, "f", 9.9} },
        { {46, "a", 9.5}, {15, "b", 9.3}, {3, "c", 9.9}, {41, "d", 9.5}, {2, "e", 9.3}, {1, "f", 9.9} },
        { {46, "a", 9.5}, {15, "b", 9.3}, {3, "c", 9.9}, {41, "d", 9.5}, {2, "e", 9.3}, {1, "f", 9.9} },
        { {46, "a", 9.5}, {15, "b", 9.3}, {3, "c", 9.9}, {41, "d", 9.5}, {2, "e", 9.3}, {1, "f", 9.9} }
    };
    
    
    
    // Calling BUBBLE sort
    bubbleSort(arr[0], SIZE);
    
    // Printing the registration numbers
    printStruct(arr[0], SIZE);
    
    
    // Calling INSERTION sort
    insertionSort(arr[1], SIZE);
    
    // Printing the registration numbers
    printStruct(arr[1], SIZE);
    
    // Calling SELECTION sort
    selectionSort(arr[2], SIZE);
    
    // Printing the registration numbers
    printStruct(arr[2], SIZE);

    // Calling QUICK sort
    printf("Number of comparisons in quick sort : %d\n", quickSort(arr[3], 0, SIZE-1));
    
    // Printing the registration numbers
    printStruct(arr[3], SIZE);

    // Calling MERGE sort
    printf("Number of comparisons in merge sort : %d\n", mergeSort(arr[4], 0, SIZE)); 
    // Here, SIZE is the end index instead of `size` argument, but since `end` argument is exclusive, we need not subtract 1 from it
    
    // Printing the registration numbers
    printStruct(arr[4], SIZE);
    
    // Calling BINARY SEARCH

    int keyFind = 1;
    
    printf("Target element present at %d\n", binSearch(arr[1], 0, 5, keyFind));
    
    
    return 0;
}	 	  	 	 		     	      	      	  	  	 	