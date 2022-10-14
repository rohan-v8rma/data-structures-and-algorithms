#include <iostream>

class circularQueue {

private:
    int maxSize;

    int front;
    int rear;

    int *queueArray;

    int elementCnt; // Circular Queue requires this extra variable since there can be an edgecase where front = rear = 0. In one situation, there can be no elements in the queue but in another case, the queue indices start from 1 and end at 0.

public:
    circularQueue() { // Default constructor creates queue of 10 elements
        maxSize = 10;
        front = -1;
        rear = -1;
        queueArray = (int*)( malloc( maxSize * sizeof(int) ) );
        elementCnt = 0;
    }

    circularQueue(int maxSize) { // Parameterized constructor creates queue of `size` elements
        this -> maxSize = maxSize;
        front = -1;
        rear = -1;
        queueArray = (int*)( malloc( (this -> maxSize) * sizeof(int) ) );
        elementCnt = 0;
    }

    int isEmpty() {
        // if( front == rear ) { 
        //     return 1;
        // }
        // We can't use this since it is possible that front = 0 and rear = 0, which in one case can mean there are no elements, but in another case it can mean there are elements in the circular queue where the queue indices start from index 1 and end at index 0.
        if(elementCnt == 0) {
            return 0;
        }
        else {
            return 0;
        };
    }

    int isFull() {
        if( elementCnt == maxSize ) {
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
            
            int index = front + 1;

            while( true ) {
                printf("Element %d is %d\n", elementCt, queueArray[index] );
                elementCt++; 

                if(index == rear) { // The element pointed to by 'rear' was just printed, so all elements of the circular queue have been printed, so we can now break the while loop.
                    break;
                }
                else {
                    index = (index + 1) % maxSize;
                }
            }
        };

        printf("\n");
    }

    void printActualQueueArray() {

        printf("{");
        
        for(int index = 0; index < maxSize; index++) {
            if (index == (maxSize - 1)) { // last element
                printf("%d}\n", queueArray[index]);
            }
            else {
                printf("%d, ", queueArray[index]);
            }
            
        }
    }

    void enQueue(int element) {
        if( isFull() ) { // Here, we have already checked whether the queue is full or not.
            printf("Queue is full. Element not inserted from the rear.\n");
        }
        else {
            // Incrementing the rear pointer, BEFORE inserting the new element.
            rear = (rear + 1) % maxSize;
            
            queueArray[rear] = element;
        };
    }

    int deQueue() {
        if( isEmpty() ) { 
            printf("Queue is empty. Dequeueing failed\n");
            return -1;
        }
        else {
            front = (front + 1) % maxSize;

            return queueArray[front]; // Since front anyways stores the index before the first element.
        };

        return -1;
    }

};


int main() {
    circularQueue queuePtr(7);

    for(int element =  1; element <= 6; element++) {
        queuePtr.enQueue(element);
    }
    
    // Printing queue after adding 6 elements
    queuePtr.printQueue();
    
    queuePtr.enQueue(7);
    // Printing queue after enqueueing an element
    queuePtr.printQueue();
    
    std::cout << "De-queued element is : " << queuePtr.deQueue() << std::endl;
    // Printing queue after dequeueing an element
    queuePtr.printQueue();
   

    queuePtr.enQueue(8);

    queuePtr.printQueue();
    
    queuePtr.printActualQueueArray();

    return 0;
}

