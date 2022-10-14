#include <iostream>
#include <string>

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
        void stackPush (int element) {
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
            (top )--;
            return ( *( stackArray + top + 1 ) );
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

std::string inToPost(std::string infix) {

    std::string postfix = "";
    int infixLength = infix.length();

    stack<char> opStack(50);
    char current;
    
    for(int index = 0; index < infixLength ; index++) {
        current = infix[index];
        
        if(current == '(') {
            opStack.stackPush(current);
        }
        else if(current == ')') {
            while((opStack.stackArray)[opStack.top] != '(') {
                postfix += opStack.stackPop();
            }
            opStack.stackPop();
        }
        else if( precedOpCheck(current) ) {
            if( precedOpCheck(opStack.stackArray[opStack.top]) < precedOpCheck(current) ) {
                opStack.stackPush(current);
            }
            else if( ( precedOpCheck( opStack.stackArray[opStack.top] ) == 4 ) && ( precedOpCheck( opStack.stackArray[opStack.top] ) == precedOpCheck(current) ) ) {
                // When we have the current operator as '^' and the one on the top of the stack also as '^'. In this condition, the precedence of the current operator is higher due to its right associativity. So it will be pushed into the operator stack.
                opStack.stackPush(current);
            }
            else {
                while( precedOpCheck( opStack.stackArray[opStack.top] ) >= precedOpCheck(current) ) {
                    postfix += opStack.stackPop();
                };
                opStack.stackPush(current);

            };
        }
        else if( ( (65 <= (int)(current)) && (90 >= (int)(current)) ) || ( (97 <= (int)(current)) && (122 >= (int)(current)) )) {
            postfix += current;
        };
    }

    return postfix;
}

std::string reverseString(std::string str) {

    std::string reverse = "";
   
    for(int index = str.length() - 1; index >= 0; index--) {
        if(str[index] == '(') {
            reverse += ')';
        }
        else if(str[index] == ')') {
            reverse += '(';
        }
        else {
            reverse += str[index];
        }
    }

    return reverse;
}

std::string inToPre1(std::string infix) { 
    /* 
    In this infix to prefix convertor function, we reverse the infix string and then use the inToPost function on the reversed string. 
    
    Since it would require multiple passes for implementing an actual infix to prefix converter, as we would have to require prior knowledge on what operators come ahead, it is just easier to reverse the infix string, apply postfix conversion and reverse the output string once again.
    */

    std::string reverse = reverseString(infix);

    std::string temp = inToPost(reverse);

    std::string prefix = reverseString(temp);

    return prefix;

}

std::string inToPre2(std::string infix) {
    /*
    In this infix to prefix converter function, we just iterate over the infix expression from right to left, applying infix to postfix conversion rules, and at the end, reverse the output before returning it.
    */

    std::string reverse = "";
    int infixLength = infix.length();

    stack<char> opStack(50);
    char current;
    
    // We just replace right brackets with left brackets and vice versa in the for loop used for iterating over the characters of the infix expression, since we are iterating over the infix expression from right to left, unlike in `inToPost`

    for(int index = infixLength - 1; index >= 0; index--) {
        current = infix[index];
        
        if(current == ')') { 
            opStack.stackPush(current);
        }
        else if(current == '(') {
            while((opStack.stackArray)[opStack.top] != ')') {
                reverse += opStack.stackPop();
            }
            opStack.stackPop();
        }
        else if( precedOpCheck(current) ) {
            if( precedOpCheck(opStack.stackArray[opStack.top]) < precedOpCheck(current) ) {
                opStack.stackPush(current);
            }
            else if( ( precedOpCheck( opStack.stackArray[opStack.top] ) == 4 ) && ( precedOpCheck( opStack.stackArray[opStack.top] ) == precedOpCheck(current) ) ) {
                // When we have the current operator as '^' and the one on the top of the stack also as '^'. In this condition, the precedence of the current operator is higher due to its right associativity. So it will be pushed into the operator stack.
                opStack.stackPush(current);
            }
            else { // for left associative operators
                while( precedOpCheck( opStack.stackArray[opStack.top] ) >= precedOpCheck(current) ) {
                    reverse += opStack.stackPop();
                };
                opStack.stackPush(current);

            };
        }
        else if( ( (65 <= (int)(current)) && (90 >= (int)(current)) ) || ( (97 <= (int)(current)) && (122 >= (int)(current)) )) {
            reverse += current;
        };
    }

    return reverseString(reverse);
}


int main() {
    //* Test Expression 1
    // std::string infix = "(K + L - M*N + (O^P) * W/U/V * T + Q)";
    //? Expected Postfix Output : KL+MN∗−OP^W∗U/V/T∗+Q+
    //! Expected Prefix Output : +K-L+*MN+*^OP/W/U*VTQ

    //* Test Expression 2
    // std::string infix = "(A + B / C * (D + E ^ Z) - F)";
    //? Expected Postfix Output : ABC/DEZ^+*+F-
    //! Expected Prefix Output : +A-/B*C+D^EZF

    //* Test Expression 2
    std::string infix = "(B - C * A + (D * E - F) / G)";
    //? Expected Postfix Output : BCA*-DE*F-G/+
    //! Expected Prefix Output : -B+*CA/-*DEFG
    
    // std::string infix = "(K^L-M^N+(O^P^T))";

    std::string postfix = inToPost(infix);
    std::cout << postfix << std::endl;

    std::string prefix1 = inToPre1(infix);
    std::cout << prefix1 << std::endl;

    std::string prefix2 = inToPre2(infix);
    std::cout << prefix2 << std::endl;

    return 0;
}
