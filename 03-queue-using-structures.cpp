#include <iostream>

struct queue {
    int size;

    int front;
    int rear;

    int *queueArray;
};

struct queue* createQueue (int size) {
    struct queue* queuePtr;

    queuePtr = (struct queue*)( malloc( sizeof(struct queue) ) );
    queuePtr -> size = size;
    queuePtr -> front = -1;
    queuePtr -> rear = -1;
    queuePtr -> queueArray = (int*)( malloc( size * sizeof(int) ) );

    return queuePtr;
}; 

int isEmpty(struct queue* queuePtr) {
    if( (queuePtr -> front) == (queuePtr -> rear) ) {
        return 1;
    }
    else {
        return 0;
    };
}

int isFull(struct queue* queuePtr) {
    if( ( (queuePtr -> rear) - (queuePtr -> front) ) == (queuePtr -> size) ) {
        return 1;
    }
    else {
        return 0;
    };
}

void enQueue(struct queue* queuePtr, int element) {
    if( isFull(queuePtr) ) { // Here, we have already checked whether the queue is full or not.
        printf("Queue is full. Element not inserted\n");
    }
    else {
        if( (queuePtr -> rear) + 1 == queuePtr -> size) { // This is the condition where, due to dequeueing, the front of the queue is empty but the queue is full at the end, so we have to shift the elements to the front in order to make space for enqueueing.
            int insertIndex = 0;
            for(int index = (queuePtr -> front) ; index < (queuePtr -> rear) ; index++) {
                (queuePtr -> queueArray)[insertIndex] = (queuePtr -> queueArray)[index];
                insertIndex++;
            }
            int size = (queuePtr -> rear) - (queuePtr -> front);
            (queuePtr -> front) = -1;
            (queuePtr -> rear) = size - 1; // Since `(size - 1) - (-1) = size so size of the array remains same`
        };

        (queuePtr -> rear)++;
        *( (queuePtr -> queueArray) + (queuePtr -> rear) ) = element;
    };
}

int deQueue(struct queue* queuePtr) {
    if( isEmpty(queuePtr) ) { 
        printf("Queue is empty. Dequeueing failed\n");
        return -1;
    }
    else {
        (queuePtr -> front)++;
        return *( (queuePtr -> queueArray) + (queuePtr -> front)); // Since front anyways stores the index before the first element.
    };
    return -1;
}

void printQueue(struct queue* queuePtr) {
    if ( isEmpty(queuePtr) ) {
        printf("Queue is empty.");
    }
    else {
        int elementCt = 1;
        printf("From the front:\n");
        for(int index = ( (queuePtr -> front) + 1 ); index <= (queuePtr -> rear) ; index++) {
            printf("Element %d is %d\n", elementCt, (queuePtr -> queueArray)[index] );
            elementCt++;
        };
    };
}

int main() {
    struct queue* queuePtr = createQueue(5);
    for(int element =  1; element <= 5; element++) {
        enQueue(queuePtr, element);
    }
    std::cout << deQueue(queuePtr) << std::endl;
    printQueue(queuePtr);
    
}

