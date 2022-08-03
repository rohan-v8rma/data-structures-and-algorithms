#include <iostream>
#include <string>

struct stack {
    int top;
    int size = 100;
    char *stackArray;
};

// Function for checking if the stack is empty. Truth value of function call can be checked.if()
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
char stackPop (struct stack *stackPtr) {
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
    stackPtr -> stackArray = (char*)( malloc( size * sizeof(int) ) );

    return stackPtr;
}; 


int precedence(char op) {
    if(op == '^') {
        return 4;
    }
    else if( (op == '/') || (op == '*') || (op == '%')) {
        return 3;
    }
    else if( (op == '+') || (op == '-') ) {
        return 2;
    };
    return 0;
};

std::string inToPost(std::string infix) {

    std::string postfix = "";
    int infixLength = infix.length();

    struct stack* opStack = stackCreate(50);
    char current;
    
    for(int index = 0; index < infixLength ; index++) {
        current = infix[index];
        
        if(current == '(') {
            stackPush(opStack, current);
        }
        else if(current == ')') {
            while((opStack->stackArray)[opStack -> top] != '(') {
                postfix += stackPop(opStack);
            }
            stackPop(opStack);
        }
        else if( (current == '^') ||  (current == '*') ||  (current == '/') ||  (current == '+') ||  (current == '-') ||  (current == '%')) {
            if( precedence(*( (opStack ->stackArray) +(opStack -> top) )) < precedence(current) ) {
                stackPush(opStack, current);
            }
            else {
                while( precedence( *( (opStack ->stackArray) + (opStack -> top) ) ) >= precedence(current) ) {
                    postfix += stackPop(opStack);
                };
                stackPush(opStack, current);

            };
        }
        else if( ( (65 <= (int)(current)) && (90 >= (int)(current)) ) || ( (97 <= (int)(current)) && (122 >= (int)(current)) )) {
            postfix += current;
        };
    }

    return postfix;
}

int main() {
    std::string infix = "";
    while( (infix[0] != '(') && (infix[-1] != ')') ) {
        printf("Please give the infix expression enclosed in brackets. Ensure there is no mismatch in brackets\n");
        printf("Enter the infix expression : ");
        getline(std::cin, infix); //for considering spaces
    };

    std::string postfix = inToPost(infix);
    std::cout << postfix << std::endl;
    return 0;
}
