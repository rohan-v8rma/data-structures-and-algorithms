#include <iostream>
using namespace::std;

class Node {

    friend void preOrderTraversal(Node rootNode);
    friend void postOrderTraversal(Node rootNode);
    friend void inOrderTraversal(Node rootNode);

    friend Node* createXpTreeFromPostfix(string postfix);

    friend int main();

private:
    char key;
    Node *parent;
    Node *left;
    Node *right;

public:
    Node() {
        key = ' ';
        parent = NULL;
        left = NULL;
        right = NULL;
    }

    Node(char key) {
        this->key = key;
        left = NULL;
        right = NULL;
    }

    Node(char key, Node *left, Node *right) {

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
void preOrderTraversal(Node rootNode) {

    printf("%c", rootNode.key);

    if (rootNode.left != NULL) {
        preOrderTraversal(*(rootNode.left));
    };

    if (rootNode.right != NULL) {
        preOrderTraversal(*(rootNode.right));
    };
}

// Visit the left node, the right node, then the root node.
void postOrderTraversal(Node rootNode) {

    if (rootNode.left != NULL) {
        postOrderTraversal(*(rootNode.left));
    };

    if (rootNode.right != NULL) {
        postOrderTraversal(*(rootNode.right));
    };

    printf("%c", rootNode.key);
}

// Visit the left node, then the root node, then the right node.
void inOrderTraversal(Node rootNode) {

    if (rootNode.left != NULL) {
        inOrderTraversal(*(rootNode.left));
    };

    printf("%c", rootNode.key);

    if (rootNode.right != NULL) {
        inOrderTraversal(*(rootNode.right));
    };
}

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
   
Node* createXpTreeFromPostfix(string postfix) {

    stack<Node*> nodeStack(50);

    char current;

    Node *n1, *n2;

    for(int index = 0; index < postfix.length(); index++) {
        current = postfix[index];

        if( ( (65 <= (int)(current)) && (90 >= (int)(current)) ) || ( (97 <= (int)(current)) && (122 >= (int)(current)) )) {
            nodeStack.stackPush(new Node(current));
        }
        else if(current == '-' || current == '+' || current == '/' || current == '*' || current == '^') {
            n1 = nodeStack.stackPop();
            n2 = nodeStack.stackPop(); // This was inserted first, so technically this should be right child
            nodeStack.stackPush(new Node(current, n2, n1));
        }
    }

    return nodeStack.stackPop();
}


int main() {
    
    //! It is important to give the infix expression enclosed in brackets.
    // string infix = "((A + B) * (C + D))";
    // string infix = "(K + L - M*N + (O^P) * W/U/V * T + Q)";
    string postfix = "BCA*-DE*F-G/+";

    Node* xpTree = createXpTreeFromPostfix(postfix);
    
    inOrderTraversal(*xpTree);
    printf("\n");
    preOrderTraversal(*xpTree);
    printf("\n");
    postOrderTraversal(*xpTree);
    printf("\n");

    return 0;
}
