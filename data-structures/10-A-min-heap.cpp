#include <iostream>
#include <limits.h>
using namespace::std;

class MinHeap {

    public:
        int capacity;
        int lastIndex = -1; // Represents the index till which the elements of the Heap are confined.
        int* heap;
    
    public:
        MinHeap(int capacity = 10) {
            this -> capacity = capacity;
            heap = new int[capacity];
            
            // Uninitialized positions
            for(int index = 0; index < capacity; index++) {
                heap[index] = INT_MAX;
            }
        }
        
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

        int leftChild(int index) {
            return heap[getLeftChildIndex(index)];
        }

        int rightChild(int index) {
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
            while( hasParent(index) && ( parent(index) > heap[index] ) ) {
                swap(index, getParentIndex(index));
                index = getParentIndex(index);
            }

        }

        void deleteRoot() {
            if(lastIndex == -1) {
                printf("Heap empty. No deletion possible.");
                return;
            }

            int index = 0;
            int smallesIndex;

            // Swapping the element at the top of heap with the lastIndex, at the same time decreasing lastIndex, effectively removing the top-most element from the heap.
            swap(index, lastIndex--);

            //! No elements left in heap, so we don't need to go through the below while loop.
            if(lastIndex == -1) { // Meaning all elements of min-heap deleted, so the heap array contaings all ex-elements of heap sorted in descending order.
                int index = 0;
                while(heap[index] != INT_MAX) {
                    printf("%d, ", heap[index]);
                    index++;
                }
                printf("\n");
                
                return; //? Returned from here.
            }

            smallesIndex = index;

            while(true) {
                if( hasLeftChild(index) && (leftChild(index) < heap[smallesIndex]) ) {
                    smallesIndex = getLeftChildIndex(index);
                }

                // smallesIndex can be 0, or it can be leftChildIndex resulting from the previous if-condition.
                if( hasRightChild(index) && (rightChild(index) < heap[smallesIndex]) ) {
                    smallesIndex = getRightChildIndex(index);
                }
                
                if(smallesIndex == index) {
                    break;
                }
                
                swap(index, smallesIndex);

                index = smallesIndex;
            }
            
        }
};

MinHeap heapify(int* arr, int size) {
    MinHeap newHeap;
    
    for(int index = 0; index < size; index++) {
        newHeap.insert(arr[index]);
    }

    return newHeap;
}

int main() {
    MinHeap heapVar;

    heapVar.insert(7);
    heapVar.insert(6);
    heapVar.insert(5);
    heapVar.insert(4);
    heapVar.insert(3);

    heapVar.deleteRoot();
    heapVar.deleteRoot();
    heapVar.deleteRoot();
    heapVar.deleteRoot();
    heapVar.deleteRoot();
    
    int size = 7;
    int arr[size] = {25, 41, 2, 15, 32, 31, 10};

    MinHeap heapVar2;

    heapVar2 = heapify(arr, size);

    heapVar2.deleteRoot();
    heapVar2.deleteRoot();
    heapVar2.deleteRoot();
    heapVar2.deleteRoot();
    heapVar2.deleteRoot();
    heapVar2.deleteRoot();
    heapVar2.deleteRoot();


    return 0;
}