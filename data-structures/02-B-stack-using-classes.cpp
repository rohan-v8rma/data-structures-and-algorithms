#include <iostream>
// Assuming stack contains integer elements
//TODO: Templatize the code.

class Stack {
private:
    int size;
    int top; // This is a simple implementation where we just store the index position within the array, of the top-most element. If no element is present in the stack, value of top will be -1
    int *stackArray; // We allocate an array to this pointer depending on the size provided by the user. Although we will be dynamically allocating memory to this pointer upon user input, the size of the array assigned will be set only once throughout the program's runtime.

public:
    // Parameterized Constructor
    Stack(int size) {
        this -> size = size;
        top = -1;
        stackArray = (int*)( malloc( size * sizeof(int) ) );
    }; 

    // Default Constructor
    Stack() {
        size = 10;
        top = -1;
        stackArray = (int*)( malloc( size * sizeof(int) ) );
    }; 

    // Function for checking if the stack is empty. Truth value of function call can be checked.
    int isEmpty() {
        if( top == -1 ) {
            return 1;
        }
        else {
            return 0;
        };
    }; 

    // Function for checking if the stack is full. Truth value of function call can be checked.
    int isFull() {
        if( ( top + 1 ) == size ) {
            return 1;
        }
        else {
            return 0;
        };
    };

    // Function for inserting an element in the stack
    void stackPush (int element) {
        if ( isFull() ) {
            std::cout << "Stack overflow. Element not inserted.\n";
        }
        else {
            ( top )++;
            *( stackArray + top ) = element;
        };
    };

    // Function for removing and returning the top element
    int stackPop () {
        if ( isEmpty() ) {
            std::cout << "Stack underflow. Stack is empty.\n";
            return -1;
        }
        return ( *( stackArray + (top--) ) );
    };

    // Function that returns the topmost element of the stack.
    int stackPeek () {
        return ( *( stackArray + top ) );
    };
};



int main() {
    Stack stck(10);
    stck.stackPush(1);
    stck.stackPush(2);
    stck.stackPush(3);
    stck.stackPush(4);

    printf("Topmost element of stack is %d.\n", stck.stackPeek());
    printf("Popped %d from the stack.\n", stck.stackPop()); // If stack is empty, -1 will be shown as the popped value.


    return 0;
}