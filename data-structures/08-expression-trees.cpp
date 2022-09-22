#include <iostream>
using namespace::std;

class Node
{

    friend void preOrderTraversal(Node rootNode);
    friend void postOrderTraversal(Node rootNode);
    friend void inOrderTraversal(Node rootNode);

    friend Node* xpTreeCreate(string infix);

    friend int main();

private:
    char key;
    Node *parent;
    Node *left;
    Node *right;

public:
    Node()
    {
        key = ' ';
        parent = NULL;
        left = NULL;
        right = NULL;
    }

    Node(char key)
    {
        this->key = key;
        left = NULL;
        right = NULL;
    }

    Node(char key, Node *left, Node *right)
    {

        this->parent = NULL;

        this->key = key;

        // Objects are created and stored somewhere in memory so we can get their address and store it in the `left` and `right` pointer
        this->left = left;
        this->right = right;

        this->left->parent = this;
        this->right->parent = this;
    }
};

// Visit the root node, then the left node, then the right node
void preOrderTraversal(Node rootNode)
{

    printf("%c", rootNode.key);

    if (rootNode.left != NULL)
    {
        preOrderTraversal(*(rootNode.left));
    };

    if (rootNode.right != NULL)
    {
        preOrderTraversal(*(rootNode.right));
    };
}

// Visit the left node, the right node, then the root node.
void postOrderTraversal(Node rootNode)
{

    if (rootNode.left != NULL)
    {
        postOrderTraversal(*(rootNode.left));
    };

    if (rootNode.right != NULL)
    {
        postOrderTraversal(*(rootNode.right));
    };

    printf("%c", rootNode.key);
}

// Visit the left node, then the root node, then the right node.
void inOrderTraversal(Node rootNode)
{

    if (rootNode.left != NULL)
    {
        inOrderTraversal(*(rootNode.left));
    };

    printf("%c", rootNode.key);

    if (rootNode.right != NULL)
    {
        inOrderTraversal(*(rootNode.right));
    };
}

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
        void stackPush (T element) {
            if ( isFull() ) {
            cout << "Stack overflow. Element not inserted.\n";
            }
            else {
                ( top )++;
                *(stackArray + top) = element;
            };
        };

        T stackPop () {

            return ( *( stackArray + top-- ) );

        };

};
   
Node* xpTreeCreate(string infix) {

    stack<char> operatorStack(50);

    stack<Node*> nodeStack(50);

    char current;


    Node *n1, *n2;
    char op;

    for(int index = 0; index < infix.length(); index++) { 
        
        current = infix[index];
        // cout << current << endl;

        if( ( ( (int)(current) ) >= 65 ) && ( ( (int)(current) ) <= 90 ) || ( ( (int)(current) ) >= 97 ) && ( ( (int)(current) ) <= 122 ) ) {
            nodeStack.stackPush(new Node(current));
        }
        else if(current == '(') {
            operatorStack.stackPush(current);
        }
        else if(current == ')') {
            while((operatorStack.stackArray)[operatorStack.top] != '(') {
                n1 = nodeStack.stackPop();
                n2 = nodeStack.stackPop();
                op = operatorStack.stackPop();

                nodeStack.stackPush(new Node(op, n2, n1));
            }
            
            operatorStack.stackPop(); // Popping '('
        }
        else if( precedOpCheck(current) ) {
            if( precedOpCheck(*( (operatorStack.stackArray) + (operatorStack.top) )) < precedOpCheck(current) ) {
                operatorStack.stackPush(current);
            }
            else {
                while( precedOpCheck( *( (operatorStack.stackArray) + (operatorStack.top) ) ) >= precedOpCheck(current) ) {
                    // operandStack.stackPush(operation(operatorStack.stackPop(), operandStack.stackPop(), operandStack.stackPop()));
                    n1 = nodeStack.stackPop();
                    n2 = nodeStack.stackPop();

                    nodeStack.stackPush(new Node(operatorStack.stackPop(), n2, n1));
                };
                operatorStack.stackPush(current);
            };
        };
    }


    return nodeStack.stackPop();
}


int main() {
    
    //! It is important to give the infix expression enclosed in brackets.
    string infix = "((A + B) * (C + D))";

    Node* xpTree = xpTreeCreate(infix);
    
    inOrderTraversal(*xpTree);
    printf("\n");
    preOrderTraversal(*xpTree);
    printf("\n");

    return 0;
}