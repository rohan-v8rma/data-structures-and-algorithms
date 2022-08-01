#include <iostream>

struct queue {
    int size;

    int front;
    int rear;

    int *queueArray;
};

struct queue* createQueue (int size){
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
            int newSize = (queuePtr -> rear) - (queuePtr -> front) + 1;
            (queuePtr -> front) = -1;
            (queuePtr -> rear) = newSize + 1;
        }

        (queuePtr -> rear)++;
        *( (queuePtr -> queueArray) + (queuePtr -> rear) ) = element;
    }
}

int deQueue(struct queue* queuePtr) {
    if( isEmpty(queuePtr) ) { 
        printf("Queue is empty. Dequeueing failed\n");
    }
    else {
        (queuePtr -> front)++;
        return *( (queuePtr -> queueArray) + (queuePtr -> front)); // Since front anyways stores the index before the first element.
    };
}

void printQueue(struct queue* queuePtr) {
    int elementCt = 1;
    for(int index = ( (queuePtr -> front) + 1 ); index <= (queuePtr -> rear) ; index++) {
        printf("Element %d is %d", elementCt, (queuePtr -> queueArray)[index] );
        elementCt++;
    };
}

int main() {
    struct queue* queuePtr = createQueue(5);
    
}

