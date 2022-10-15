#include <iostream>

class DEqueue {
    int maxSize;

    int front;
    int rear;

    int *queueArray;

    public:
        DEqueue() { // Default constructor creates queue of 10 elements
            maxSize = 10;
            front = -1;
            rear = -1;
            queueArray = (int*)( malloc( maxSize * sizeof(int) ) );
        }

        DEqueue(int maxSize) { // Parameterized constructor creates queue of `size` elements
            this -> maxSize = maxSize;
            front = -1;
            rear = -1;
            queueArray = (int*)( malloc( (this -> maxSize) * sizeof(int) ) );
        }

        int isEmpty() {
            if( front == rear ) {
                return 1;
            }
            else {
                return 0;
            };
        }

        int isFull() {
            if( ( rear - front ) == maxSize ) {
                return 1;
            }
            else {
                return 0;
            };
        }

        void printQueue() {
            if ( isEmpty() ) { // We need not mention the object upon which isEmpty is to be called, since `printQueue` is a member function
                printf("Queue is empty.");
            }
            else {
                int elementCt = 1;
                
                printf("\nFrom the front:\n");
                
                for(int index = (front + 1); index <= rear ; index++) {
                    printf("Element %d is %d\n", elementCt, queueArray[index] );
                    elementCt++;
                };
            };

            printf("\n");
        }

        //* Regular Queue Operations

        void removeFrontDeadSpace() { // Function for removing empty space from the front of the array.
            int insertIndex = 0;
                    
            for(int index = front + 1 ; index <= rear ; index++) { // We keep the starting index as `front + 1` because the front ptr anyways stores an index before the start of the array. For example, if array starts from index 0, the front ptr will have value `-1`.
                queueArray[insertIndex++] = queueArray[index];
            }

            int size = rear - front; // This is the current size of the queue
            front = -1;
            rear = size - 1; // Since (`size` - 1) - (-1) = `size`, so size of the queue, calculated using the front and rear pointers remains same
        }

        void enQueueRear(int element) {
            if( isFull() ) { // Here, we have already checked whether the queue is full or not.
                printf("Queue is full. Element not inserted from the rear.\n");
            }
            else {
                if( (rear + 1) == maxSize ) { // This is the condition where, due to dequeueing, the front of the queue is empty but the queue is full at the end, so we have to shift the elements to the front in order to make space for enqueueing from the rear.
                    removeFrontDeadSpace();
                };

                // Incrementing the rear pointer, BEFORE inserting the new element.
                queueArray[++rear] = element;
            };
        }

        int deQueueFront() {
            if( isEmpty() ) { 
                printf("Queue is empty. Dequeueing failed\n");
                return -1;
            }
            else {
                return queueArray[++front]; // Since front anyways stores the index before the first element.
            };

            return -1;
        }

        //* Double-Ended Queue Operations

        void removeRearDeadSpace() { // Function for removing empty space from the end of the array.
            int insertIndex = maxSize - 1;
                    
            for(int index = rear ; index > front ; index--) {
                queueArray[insertIndex--] = queueArray[index];
            }

            int size = rear - front; // This is the current size of the queue

            rear = maxSize - 1;
            
            front = rear - size; // Since `(maxSize - 1) - ( (maxSize - 1) - `size` ) = `size`, so size of the queue, calculated using the front and rear pointers remains same
            
        }

        void enQueueFront(int element) {
            if( isFull() ) { // Here, we have already checked whether the queue is full or not.
                printf("Queue is full. Element not inserted from the front.\n");
            }
            else {
                if( front == -1 ) { // This is the condition where the queue is full from the front but the rear of the queue is empty, so we have to shift the elements to the rear in order to make space for enqueueing from the front.
                    removeRearDeadSpace();
                };

                // Since front already points to an index before the start of the index, so we decrement it AFTER inserting the new element.
                queueArray[front--] = element;
            };
        }
        
        int deQueueRear() {
            if( isEmpty() ) { 
                printf("Queue is empty. Dequeueing failed\n");
                return -1;
            }
            else {
                return queueArray[rear--]; // Since front anyways stores the index before the first element.
            };

            return -1;
        }

};


int main() {
    DEqueue queuePtr(10);

    for(int element =  1; element <= 5; element++) {
        queuePtr.enQueueRear(element);
    }
    
    // Printing queue after adding 5 elements
    queuePtr.printQueue();

    
    queuePtr.enQueueRear(0);

    // Printing queue after enqueueing an element from the rear (regular operation)
    queuePtr.printQueue();
    
    queuePtr.enQueueFront(6);
    
    // Printing queue after enqueueing an element from the front (DE-queue operation)
    queuePtr.printQueue();

    std::cout << "De-queued element from rear is : " << queuePtr.deQueueRear() << std::endl;

    // Printing queue after dequeueing an element from the rear (DE-queue operation)
    queuePtr.printQueue();

    std::cout << "De-queued element from front is : " << queuePtr.deQueueFront() << std::endl;

    // Printing queue after dequeueing an element from the front (regular operation)
    queuePtr.printQueue();

    return 0;
}

