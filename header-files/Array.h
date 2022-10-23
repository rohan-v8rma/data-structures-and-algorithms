class Array {
private:
    int capacity = 10;
    int size = 0;
    int* arrayPointer;
public:
    Array() {
        arrayPointer = new int[capacity];
    }

    Array(int capacity) {
        arrayPointer = new int[capacity];
    }

    void ensureCapacity() {
        if(size == capacity) {
            int* tempPointer = new int[2 * capacity];

            for(int index = 0; index < capacity; index++) {
                tempPointer[index] = arrayPointer[index];
            }
            
            arrayPointer = tempPointer;
            capacity *= 2;
        }
    }

    void insertElement(int element) {
        ensureCapacity();

        // Suppose array has no elements, then size is 0. So, we insert an element at 0th index position, then increment the size variable.
        arrayPointer[size++] = element;
    }

    int deleteElementFromFront() {
        int returnVal = arrayPointer[0];

        for(int index = 1; index < size; index++) {
            arrayPointer[index - 1] = arrayPointer[index];
        }
        
        size--;
        
        return returnVal;
    }

    void sortArray() {
        for(int index = 1; index < size; index++) {
            int tempIndex = index;
            int tempVar;
            while( (tempIndex >= 1) && (arrayPointer[tempIndex - 1] > arrayPointer[tempIndex]) ) {
                tempVar = arrayPointer[tempIndex - 1];
                arrayPointer[tempIndex - 1] = arrayPointer[tempIndex];
                arrayPointer[tempIndex] = tempVar;
                tempIndex--;
            }
        }
        // Array is sorted
    }
};