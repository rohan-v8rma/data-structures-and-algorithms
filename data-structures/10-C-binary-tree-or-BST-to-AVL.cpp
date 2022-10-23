#include "00-Array.h"
#include <iostream>

class Node {  

    friend Node* createAVLFromSortedArray(Array* arrayObjectPointer, int startIndex, int endIndex);
    
    friend void inOrderTraversal(Node* rootNode);
    friend void writeInOrderTraversalArray(Node* rootNode, Array* arrayObjectPointer);
    
    friend Node* BSTtoAVL(Node* rootNode);
    friend Node* binaryTreeToAVL(Node* rootNode);

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

Node* createAVLFromSortedArray(Array* arrayObjectPointer, int startIndex, int endIndex) {
    if(startIndex > endIndex) {
        return NULL;
    }
    int midIndex = (startIndex + endIndex) / 2;

    // Setting the middle element as root. Doing this recursively for all sub-arrays will result in an avl tree
    Node* root = new Node(arrayObjectPointer->getElement(midIndex));

    root->left = createAVLFromSortedArray(arrayObjectPointer, startIndex, midIndex - 1);

    root->right = createAVLFromSortedArray(arrayObjectPointer, midIndex + 1, endIndex);

    return root;
}

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

// To be used when the tree in question is already a binary search tree
Node* BSTtoAVL(Node* rootNode) {
    
    Array* newArrPtr = new Array;

    writeInOrderTraversalArray(rootNode, newArrPtr);
    
    rootNode = createAVLFromSortedArray(newArrPtr, 0, newArrPtr->getSize() - 1);

    return rootNode;
}

Node* binaryTreeToAVL(Node* rootNode) {
    Array* newArrPtr = new Array;

    writeInOrderTraversalArray(rootNode, newArrPtr);

    newArrPtr->sortArray(); // Only extra step compared to above function

    rootNode = createAVLFromSortedArray(newArrPtr, 0, newArrPtr->getSize() - 1);

    return rootNode;
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


    rootNode =  binaryTreeToAVL(rootNode);
    // At this point, the binary tree is now converted to an AVL tree.

    printf("\n");
    inOrderTraversal(rootNode);

    return 0;
}
