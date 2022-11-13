#include <iostream>
#include <limits.h>
using namespace::std;

//* This code can be used for building a Max Heap.

//* It also performs heap sort in ascending order, since larger elements are deleted and placed at the end of the heap array, resulting in the array having the smallest element at the front and the largest element at the end.

class MaxHeap {

public:
    int capacity;
    int lastIndex = -1; // Represents the index till which the elements of the Heap are confined.
    int* heap;

    MaxHeap(int capacity = 10) {
        this -> capacity = capacity;
        heap = new int[capacity];
    }
    
    //* It is better to have smaller functions like these as member functions because member functions are implicitly inline, which makes them faster.
    int getLeftChildIndex(int parentIndex) { 
        return ( (2 * parentIndex) + 1 );
    }
    
    int getRightChildIndex(int parentIndex) { 
        return ( (2 * parentIndex) + 2 );
    }

    int getParentIndex(int childIndex) {
        return( (childIndex - 1) / 2 );
    }

    int hasLeftChild(int index) {
        if( getLeftChildIndex(index) <= lastIndex ) {
            return 1;
        }
        
        return 0;
    }
    
    int hasRightChild(int index) {
        if( getRightChildIndex(index) <= lastIndex ) {
            return 1;
        }
        
        return 0;

    }

    int hasParent(int index) {
        if( getParentIndex(index) >= 0 ) {
            return 1;
        }

        return 0;

    }

    int getLeftChild(int index) {
        return heap[getLeftChildIndex(index)];
    }

    int getRightChild(int index) {
        return heap[getRightChildIndex(index)];
    }

    int parent(int index) {
        return heap[getParentIndex(index)];
    }

    void swap(int index_1, int index_2) {
        if(index_1 != index_2) {
            int temp = heap[index_1];
            heap[index_1] = heap[index_2];
            heap[index_2] = temp;
        }

        return;

    }

    void ensureCapacity() {
        if (lastIndex + 1 == this->capacity) {
            int* temp = new int[2 * this->capacity];
            
        
            for(int index = 0; index < this->capacity; index++) {
                temp[index] = this->heap[index];
            }

            // Uninitialized positions
            for(int index = this->capacity; index < (2 * this->capacity); index++) {
                temp[index] = INT_MAX;
            }

            this->capacity *= 2;
            heap = temp;
        }
        
        return;
    }

    void insert(int element) {
        ensureCapacity();
        
        int index = ++lastIndex;
        // Inserting new key at the new last index
        heap[index] = element;

        // In the first condition of while loop, we check whether the element has a parent to compare or not. Only then do we compare it with its parent element. If parent element is greater, we need to replace it since this is a min heap.
        while( hasParent(index) && ( parent(index) < heap[index] ) ) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
        //? We shouldn't call heapify in this because heapify compares an element with its children, with no regard for its parents. Here, we are inserting the new element at the bottom of the minHeap, so we need to compare the elemnt with its parents until it reaches its correct position.
    }

};

//? This function has O(log(N)) time complexity, because it makes comparisons in constant time so best case time complexity would be O(1). But in worst case, it performs log(N) recursive calls (over the full height of the tree) 
void heapify(int *arr, int size, int indexToHeapify) {
    
    int maxIndex;

    maxIndex = indexToHeapify;
    
    int leftChildIndex = (2 * indexToHeapify + 1);
    int rightChildIndex = (2 * indexToHeapify + 2); 

    if( ( leftChildIndex <= (size - 1) ) && ( arr[maxIndex] < arr[leftChildIndex] ) ) { // left child
        maxIndex = leftChildIndex;
    }

    if( ( (rightChildIndex) <= (size - 1) ) && ( arr[maxIndex] < arr[rightChildIndex] ) ) { // right child
        maxIndex = rightChildIndex;
    }

    int temp;
    if(indexToHeapify != maxIndex) {
        temp = arr[indexToHeapify];
        arr[indexToHeapify] = arr[maxIndex];
        arr[maxIndex] = temp;

        //! Whenever a swap is done, we check whether the index with which we replaced, still satisfies heap property w.r.t. its children. That is the reason for this recursive call.
        heapify(arr, size, maxIndex); 
    }

}

//! It is better to have more complicated functions like this one, as friend functions instead of member functions, because member functions are implicitly inline.
void deleteRoot(MaxHeap* heapObj) {
    if(heapObj->lastIndex == -1) {
        printf("Heap empty. No deletion possible.");
        return;
    }

    int index = 0;
    int smallestIndex;

    // Swapping the element at the top of heap with the lastIndex, at the same time decreasing lastIndex, effectively removing the top-most element from the heap.
    heapObj->swap(index, (heapObj->lastIndex)--);

    //! No elements left in heap, so we don't need to go through the below while loop.
    if(heapObj->lastIndex == -1) { // Meaning all elements of min-heap deleted, so the heap array contaings all ex-elements of heap sorted in descending order.            
        return; //? Returned from here.
    }
    //* We only need to call heapify on the first index, because that is the index where are changing. If a swap occurs in this heapify call, there will be more recursive calls to heapify the sub-trees that need heapifying.  
    heapify(heapObj->heap, (heapObj->lastIndex) + 1, index);        
    //? We added 1 because size is always `the last index + 1`.
}

//? Time complexity of this function is O(N.log(N)) because it calls heapify N times (for each element in the array). Assuming worst case time complexity of heapify, which is log(N).
MaxHeap* buildMaxHeap(int *arr, int size) {
    
    int minIndex;

    for(int index = size - 1; index >= 0; index--) {
        heapify(arr, size, index);
        // Here, we are calling heapify on every element of the array.
    }

    MaxHeap* returnHeap = new MaxHeap();
    returnHeap->heap = arr;
    returnHeap->lastIndex = size - 1;

    return returnHeap;
}

//? Note that this display function could have been made a member function and much simpler with LESSER POINTER ARITHMETIC, but to isolate its variables (by not making it implicitly inline, as a member function), we made it a friend function.
void displayInAscendingOrder(MaxHeap* heapObj) {
    MaxHeap copy = *heapObj;

    int endOfSorted = copy.lastIndex;

    for(int index = 0; index <= copy.lastIndex; index++) {
        deleteRoot(&copy);
    }

    for(int index = 0; index <= endOfSorted; index++) {
        printf("%d, ", copy.heap[index]);
    }
    printf("\n");
}

int main() {
    MaxHeap* heapVar = new MaxHeap;

    heapVar->insert(7);
    heapVar->insert(6);
    heapVar->insert(5);
    heapVar->insert(4);
    heapVar->insert(3);

    for(int index = 0; index < 5; index++) {
        printf("%d, ", heapVar->heap[index]);
    }
    printf("\n");
    displayInAscendingOrder(heapVar);
    

    
    int size = 7;
    int arr[size] = {25, 41, 2, 15, 32, 31, 10};

    MaxHeap* heapVar2 = buildMaxHeap(arr, size);

    
    for(int index = 0; index < size; index++) {
        printf("%d, ", heapVar2->heap[index]);
    }
    printf("\n");

    displayInAscendingOrder(heapVar2);


    return 0;
}