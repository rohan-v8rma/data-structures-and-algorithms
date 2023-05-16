#include "00-Array.h"
#include <iostream>

class Node {  

    friend Node* createAVLFromSortedArray(Array* arrayObjectPointer, int startIndex, int endIndex);

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

