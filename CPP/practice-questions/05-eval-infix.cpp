#include <iostream>
#include <string>
#include <math.h>

template <class T> class stack {
public:
    int top;
    int size;
    T *stackArray;

    stack() {
        size = 100;
    }
    stack(int size) {            
        this -> size = size;
        top = -1;
        stackArray = (T*)( malloc( size * sizeof(T) ) );
    }; 
    

    int isEmpty() {
        if(top == -1) {
            return 1;
        }   
        else {
            return 0;
        };
    }

    int isFull() {
        if( ( (top) + 1) == (size) ) {
            return 1;
        }
        else {
            return 0;
        };
    }
    void stackPush (float element) {
        if ( isFull() ) {
        std::cout << "Stack overflow. Element not inserted.\n";
        }
        else {
            ( top )++;
            *(stackArray + top) = element;
        };
    };

    T stackPop () {
        if ( isEmpty() ) {
            std::cout << "Stack underflow. Stack is empty.\n";
            return -1;
        }
        return ( *( stackArray + top-- ) );

    };
};
   

int precedOpCheck(char op) {
    switch(op) {
        case '^':
            return 4;
            break;
        case '/':
        case '*':
        case '%':
            return 3;
            break;
        case '+': 
        case '-':
            return 2;
            break;
        default:
            return 0; // This default case will help us verify whether a certain character is an operator or not.
            break;
    };    
};

float operation(char op, float digit1, float digit2) {

    switch(op) {
        case '^': 
            return pow(digit1, digit2);
            break;
        case '/': 
            return ( digit1 / digit2 );
            break;
        case '*': 
            return (digit1 * digit2);
            break;
        case '%': 
            return ((int)digit1 % (int)digit2);
            break;
        case '+': 
            return (digit1 + digit2);
            break;
        case '-': 
            return (digit1 - digit2);
            break;
        default:
            return -1;
            break;
    }
}

float evaluatingInfix(std::string infix) {

    int infixLength = infix.length();

    stack<char> operatorStack(50);

    stack<float> operandStack(50);

    char current;

    
    for(int index = 0; index < infixLength ; index++) { 
        
        current = infix[index];
        // std::cout << current << std::endl;

        if( ( ( (int)(current - '0') ) >= 0 ) && ( ( (int)(current - '0') ) <= 9 ) ) {
            operandStack.stackPush((float)(current - '0') );
        }
        else if(current == '(') {
            operatorStack.stackPush(current);
        }
        else if(current == ')') {
            while((operatorStack.stackArray)[operatorStack.top] != '(') {
                operandStack.stackPush(operation(operatorStack.stackPop(), operandStack.stackPop(), operandStack.stackPop()) );
            }
            operatorStack.stackPop();
        }
        else if( precedOpCheck(current) ) {
            if( precedOpCheck(*( (operatorStack.stackArray) + (operatorStack.top) )) < precedOpCheck(current) ) {
                operatorStack.stackPush(current);
            }
            else {
                while( precedOpCheck( *( (operatorStack.stackArray) + (operatorStack.top) ) ) >= precedOpCheck(current) ) {
                    operandStack.stackPush(operation(operatorStack.stackPop(), operandStack.stackPop(), operandStack.stackPop()));
                };
                operatorStack.stackPush(current);
            };
        };
    }

    return operandStack.stackPop();
}

int main() {
    //* Test Expression
    std::string infix = "(7/2/5 - 1*4 + (6^2) * 1 + 3)";
    //? Expected Output : 35.7

    float value1 = evaluatingInfix(infix);
    std::cout << value1 << std::endl;
    
    float value2 = (7.0/2/5 - 1*4 + pow(6, 2) * 1 + 3);
    std::cout << value2 << std::endl;
    return 0;
}
