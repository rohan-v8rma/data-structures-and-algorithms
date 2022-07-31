#include <iostream>
// Assuming stack contains integer elements
//TODO: Templatize the code.

struct stack {
    int size;
    int top; // This is a simple implementation where we just store the index position within the array, of the top-most element. If no element is present in the stack, value of top will be -1
    int *stackArray; // We allocate an array to this pointer depending on the size provided by the user. Although we will be dynamically allocating memory to this pointer upon user input, the size of the array assigned will be set only once throughout the program's runtime.
};

// Function for checking if the stack is empty. Truth value of function call can be checked.
int isEmpty(struct stack *stackPtr) {
    if( (stackPtr -> top) == -1) {
        return 1;
    }
    else {
        return 0;
    };
}; 

// Function for checking if the stack is full. Truth value of function call can be checked.
int isFull(struct stack *stackPtr) {
    if( ( (stackPtr -> top) + 1) == (stackPtr -> size) ) {
        return 1;
    }
    else {
        return 0;
    };
};

// Function for inserting an element in the stack
void stackPush (struct stack *stackPtr, int element) {
    if ( isFull(stackPtr) ) {
        std::cout << "Stack overflow. Element not inserted.\n";
    }
    else {
        ( stackPtr -> top )++;
        *( (stackPtr -> stackArray) + (stackPtr -> top) ) = element;
    };
};

// Function for removing and returning the top element
int stackPop (struct stack *stackPtr) {
    if ( isEmpty(stackPtr) ) {
        std::cout << "Stack underflow. Stack is empty.\n";
        return -1;
    }
    ( stackPtr -> top )--;
    return ( *( (stackPtr -> stackArray) + (stackPtr -> top) + 1 ) );
};

// Function that returns the topmost element of the stack.
int stackPeek (struct stack *stackPtr) {
    return ( *( (stackPtr -> stackArray) + (stackPtr -> top) ) );
};

// Function for creating a new stack. It returns a pointer to the stack.
struct stack* stackCreate (int size){
    struct stack* stackPtr;

    stackPtr = (struct stack*)( malloc( sizeof(struct stack) ) );
    stackPtr -> size = size;
    stackPtr -> top = -1;
    stackPtr -> stackArray = (int*)( malloc( size * sizeof(int) ) );

    return stackPtr;
}; 

int main() {
    struct stack* stackPtr = stackCreate(10);
    stackPush(stackPtr, 1);
    stackPush(stackPtr, 2);
    stackPush(stackPtr, 3);
    stackPush(stackPtr, 4);

    printf("Topmost element of stack is %d", stackPeek(stackPtr));
    printf("Popped %d from the stack", stackPop(stackPtr)); // If stack is empty, -1 will be shown as the popped value.


    return 0;
}