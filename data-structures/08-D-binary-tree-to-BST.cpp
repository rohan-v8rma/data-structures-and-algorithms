#include <iostream>
using namespace::std;

// Converting binary tree to BST.

class Array {
private:
    int capacity = 10;
    int size = 0;
    int* arrayPointer;
public:
    Array() {
        arrayPointer = new int[capacity];
    }

    Array(int capacity) {
        arrayPointer = new int[capacity];
    }

    void ensureCapacity() {
        if(size == capacity) {
            int* tempPointer = new int[2 * capacity];

            for(int index = 0; index < capacity; index++) {
                tempPointer[index] = arrayPointer[index];
            }
            
            arrayPointer = tempPointer;
            capacity *= 2;
        }
    }

    void insertElement(int element) {
        ensureCapacity();

        // Suppose array has no elements, then size is 0. So, we insert an element at 0th index position, then increment the size variable.
        arrayPointer[size++] = element;
    }

    int deleteElementFromFront() {
        int returnVal = arrayPointer[0];

        for(int index = 1; index < size; index++) {
            arrayPointer[index - 1] = arrayPointer[index];
        }
        
        size--;
        
        return returnVal;
    }

    void sortArray() {
        for(int index = 1; index < size; index++) {
            int tempIndex = index;
            int tempVar;
            while( (tempIndex >= 1) && (arrayPointer[tempIndex - 1] > arrayPointer[tempIndex]) ) {
                tempVar = arrayPointer[tempIndex - 1];
                arrayPointer[tempIndex - 1] = arrayPointer[tempIndex];
                arrayPointer[tempIndex] = tempVar;
                tempIndex--;
            }
        }
        // Array is sorted
    }
};

class Node {
    friend void inOrderTraversal(Node* rootNode);

    friend void writeInOrderTraversalArray(Node* rootNode, Array* arrayObjectPointer);
    
    friend void overwriteInOrderElements(Node* rootNode, Array* arrayObjectPointer);

    friend void binaryToBST(Node* rootNode);

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


    Array* newArrPtr = new Array;

    writeInOrderTraversalArray(rootNode, newArrPtr);
    newArrPtr->sortArray();


    overwriteInOrderElements(rootNode, newArrPtr);

    printf("\n");
    inOrderTraversal(rootNode);

    return 0;
}