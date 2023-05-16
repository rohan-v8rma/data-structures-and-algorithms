#include <iostream>

class Queue {
    int size;

    int front;
    int rear;

    int *queueArray;

    public:

        // Parameterized Constructor
        Queue (int size) {            
            this -> size = size;
            front = -1;
            rear = -1;
            queueArray = (int*)( malloc( size * sizeof(int) ) );
        };

        // Default Constructor
        Queue () {            
            this -> size = 10;
            front = -1;
            rear = -1;
            queueArray = (int*)( malloc( size * sizeof(int) ) );
        }; 

        int isEmpty() {
            if(front == rear) {
                return 1;
            }
            else {
                return 0;
            };
        }

        int isFull() {
            if( ( rear - front )  == size ) {
                return 1;
            }
            else {
                return 0;
            };
        }

        void enQueue(int element) {
            if( isFull() ) { // Here, we have already checked whether the queue is full or not.
                printf("Queue is full. Element not inserted\n");
            }
            else {
                if( (rear + 1) == size) { // This is the condition where, due to dequeueing, the front of the queue is empty but the queue is full at the end, so we have to shift the elements to the front in order to make space for enqueueing.
                    int insertIndex = 0;

                    for(int index = (front + 1); index <= rear ; index++) {
                        queueArray[insertIndex] = queueArray[index];
                        insertIndex++;
                    }
                    
                    int size = rear - front;
                    front = -1;
                    rear = size - 1; // Since (`size` - 1) - (-1) = `size`, so size of the queue, calculated using the front and rear pointers remains same
                };

                rear++;
                *( queueArray + rear ) = element;
            };
        }

        int deQueue() {
            if( isEmpty() ) { 
                printf("Queue is empty. Dequeueing failed\n");
                return -1;
            }
            else {
                // We increment the front pointer before accessing the element that is now deQueued, since front anyways stores the index before the first element.
                return *( queueArray + (++front) ); 
            };
            return -1;
        }

        void printQueue() {
            if ( isEmpty() ) {
                printf("Queue is empty.");
            }
            else {
                int elementCt = 1;
                printf("From the front:\n");
                for(int index = ( front + 1 ); index <= (rear) ; index++) {
                    printf("Element %d is %d\n", elementCt, queueArray[index] );
                    elementCt++;
                };
            };
        }

};






int main() {
    Queue que(5);
    for(int element =  1; element <= 5; element++) {
        que.enQueue(element);
    }
    std::cout << que.deQueue() << std::endl;
    
    que.printQueue();
    
    return 0;
}

