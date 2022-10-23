#include <iostream>
#include "00-Array.h" // User-defined header-file containing Array class
using namespace::std;

// Converting binary tree to BST.

class Node {
    friend void inOrderTraversal(Node* rootNode);

    friend void writeInOrderTraversalArray(Node* rootNode, Array* arrayObjectPointer);
    
    friend void overwriteInOrderElements(Node* rootNode, Array* arrayObjectPointer);

    friend void binaryTreeToBST(Node* rootNode);

    friend int main();

private:
    int key;
    Node *left;
    Node *right;

public:
    Node() {
        key = 0;
        left = NULL;
        right = NULL;
    }

    Node(int key) {
        this->key = key;
        left = NULL;
        right = NULL;
    }

    Node(int key, Node *left, Node *right) {

        this->key = key;

        // Objects are created and stored somewhere in memory so we can get their address and store it in the `left` and `right` pointer
        this->left = left;
        this->right = right;

    }
};

void inOrderTraversal(Node* rootNode) {
    if( !(rootNode) ) { //? (!NULL) has truth-value of 1, other pointer values have truth-value of 0 with `!`
        return;
    }

    inOrderTraversal(rootNode->left);   

    printf("%d, ", rootNode->key);

    inOrderTraversal(rootNode->right);
}

void writeInOrderTraversalArray(Node* rootNode, Array* arrayObjectPointer) {
    if(rootNode == NULL) {
        return;
    }

    writeInOrderTraversalArray(rootNode->left, arrayObjectPointer);   

    arrayObjectPointer->insertElement(rootNode->key);
    
    writeInOrderTraversalArray(rootNode->right, arrayObjectPointer);   

    // Control reaches to the outermost return statement, only when all the above statements have been executed. This means that all elements are inserted into the Array object.
}

void overwriteInOrderElements(Node* rootNode, Array* arrayObjectPointer) {
    if(rootNode == NULL) {
        return;
    }

    overwriteInOrderElements(rootNode->left, arrayObjectPointer);   

    rootNode->key = arrayObjectPointer->deleteElementFromFront();    
    
    overwriteInOrderElements(rootNode->right, arrayObjectPointer);   

    // Control reaches to the outermost return statement, only when all the above statements have been executed. This means that all elements are inserted into the Array object.
    return;
}


void binaryTreeToBST(Node* rootNode) {
    Array* newArrPtr = new Array;

    writeInOrderTraversalArray(rootNode, newArrPtr);
    newArrPtr->sortArray();

    overwriteInOrderElements(rootNode, newArrPtr);
}

int main() {

    Node* rootNode = new Node(22, NULL, new Node(8, new Node(16, new Node(3), new Node(7)), new Node(10, new Node(9), new Node(12))));

    /*
    22
      \
       8
      / \
     /   \
    16   10
    /\   /\
   3  7 9  12
    */
    inOrderTraversal(rootNode);

    binaryTreeToBST(rootNode);
    // At this point, the binary tree is now converted to a binary SEARCH tree.

    printf("\n");
    inOrderTraversal(rootNode);

    return 0;
}