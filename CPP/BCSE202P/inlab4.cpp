#include <iostream>
using namespace::std;

void printArr(int* arr, int size) {
    for(int index = 0; index < size; index++) {
        cout << *(arr+index) << endl;
    }
}


int bsearch(int* arr, int s, int e, int target) {
    if( s > e ) {
        return -1;
    }
    
    int middle = (s + e) / 2;
    
    if(arr[middle] == target) {
        return middle;
    }
    else if(arr[middle] > target) {
        bsearch(arr, s, middle-1, target);
    }
    else if(arr[middle] < target) {
        bsearch(arr, middle+1, e, target);
    };
}	 	  	 	 		     	      	      	  	  	 	

void bubble(int* arr, int size) {
    
    int swap = 0;
    
    int temp;
    
    for(int pass = 1; pass < size; pass++) {
        for(int checkIndex = 0; checkIndex < (size - pass); checkIndex++) {
            
            
            if(arr[checkIndex] > arr[checkIndex + 1]) {
                
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

}

void insertion(int* arr, int size) {
    
    int temp;
    
    for(int pass = 1; pass < size; pass++) {	 	  	 	 		     	      	      	  	  	 	
        for(int index = pass; index > 0; index--) { 
                      if(arr[index] < arr[index - 1]) {
                
                temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
            }
            else {
                break;
            }
        }
    }
    
}

void select(int* arr, int size) {
    
    
    int temp;
    
    int maxIndex = 0;
    
    for(int pass = size - 1; pass >= 0; pass--) {
        
        maxIndex = 0;
        
    
        for(int index = 1; index <= pass; index++) { 
        
            if(arr[maxIndex] < arr[index]) {
                maxIndex = index;
            }
        }

        if(maxIndex != pass) {
            temp = arr[pass];
            arr[pass] = arr[maxIndex];
            arr[maxIndex] = temp;
        }
        
    }

}


void merge(int* arr, int start, int middle, int end) {
    
    int mergedArr[end - start];
    
    int leftPtr = start;
    int rightPtr = middle;
    int mergePtr = 0;
    
    while( (leftPtr < middle) && (rightPtr < end) ) {
        if( (*(arr + leftPtr)) < (*(arr + rightPtr)) ) {
            mergedArr[mergePtr++] = *(arr + leftPtr++);
        }
        else {
            mergedArr[mergePtr++] = *(arr + rightPtr++);
        }
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

}

int merger(int* arr, int start, int end) {
    int subArrLen = end - start;
    
    if( subArrLen == 1 ) {
        return 0;
    }
    
     int middle = (start + end) / 2;
     
     merger(arr, start, middle);
     merger(arr, middle, end);
     merge(arr, start, middle, end);
}

void quick(int* arr, int start, int end) {
    
    
    if (end - start <= 0) {	 	  	 	 		     	      	      	  	  	 	
        return;
    }

    int pivot = (start + end) / 2;
    int pivotElement = arr[pivot];
    
    int temp;
    
    int left = start;
    int right = end;
    
    arr[pivot] = arr[right];
    arr[right] = pivotElement;
    right--;
    
    while (left <= right) {
        while( (left <= end) && (arr[left] < pivotElement) ) {
            left++;      
            right--;
        }

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
    
    
}


int main() {

    int arr [5][6] = {
        {46, 15, 3, 41, 2, 1 },
        {46, 15, 3, 41, 2, 1 },
        {46, 15, 3, 41, 2, 1 },
        {46, 15, 3, 41, 2, 1 },
        {46, 15, 3, 41, 2, 1 }
    };
    
    
    

    bubble(arr[0], 6);
    

    printArr(arr[0], 6);
    
    

    insertion(arr[1], 6);
    

    printArr(arr[1], 6);
    

    select(arr[2], 6);
    

    printArr(arr[2], 6);


    quick(arr[4], 0, 6-1);
    

    printArr(arr[4], 6);


    merger(arr[3], 0, 6); 


    printArr(arr[3], 6);
    


    int keyFind = 1;
    
    cout << bsearch(arr[1], 0, 5, keyFind) << endl;
    
    
    return 0;
}	 	  	 	 		     	      	      	  	  	 	