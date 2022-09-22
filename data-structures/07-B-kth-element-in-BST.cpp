#include <iostream>
#include <limits.h>
using namespace ::std;

class Node {

    friend Node* kthSmallest(Node* rootNode, int kth_minimum, int &count);
    friend Node* kthLargest(Node* rootNode, int kth_maximum, int &count);

    friend Node* successor(Node *elementPtr);
    friend Node* predecessor(Node *elementPtr);

    friend int main();

private:
    int key;
    Node *parent;
    Node *left;
    Node *right;

public:
    Node() {
        key = 0;
        parent = NULL;
        left = NULL;
        right = NULL;
    }

    Node(int key) {
        this->key = key;
        left = NULL;
        right = NULL;
    }

    Node(int key, Node *left, Node *right) {

        this->parent = NULL;

        this->key = key;

        // Objects are created and stored somewhere in memory so we can get their address and store it in the `left` and `right` pointer
        this->left = left;
        this->right = right;

        if(this-> left != NULL) { 
            this->left->parent = this;
        }
        
        if(this-> right != NULL) {
            this->right->parent = this;
        }
        
    }
};


// Count variable passed by reference to function and its recursive calls, so it is common for all function calls
Node* kthSmallest(Node* rootNode, int kth_minimum, int &count) {
    
    if(rootNode == NULL) {
        return NULL;
    }

    // Searching for kth smallest in left sub-tree
    Node* searchResultFromLeftTree = kthSmallest(rootNode->left, kth_minimum, count);   

    // We don't directly return the result from the search in left sub-tree because there is a chance that if it is NULL, the kth smallest element is in the right sub-tree
    if(searchResultFromLeftTree != NULL) {
        return searchResultFromLeftTree;
    };

    // Modifying the count variable which is common for all function calls.
    if(count < kth_minimum) {
        count++;
    }
    else if(count == kth_minimum) {
        return rootNode;
    }

    // We directly return this because if the kth smallest element is not even found in the right sub-tree, then we are ok with returning NULL, since the element can't be anywhere else.
    return kthSmallest(rootNode->right, kth_minimum, count);
}

//? Reverse in order traversal
Node* kthLargest(Node* rootNode, int kth_maximum, int &count) {
    
    if(rootNode == NULL) {
        return NULL;
    }

    // Searching for kth largest in right sub-tree
    Node* searchResultFromRightTree = kthLargest(rootNode->right, kth_maximum, count);   

    // We don't directly return the result from the search in right sub-tree because there is a chance that if it is NULL, the kth largest element is in the left sub-tree
    if(searchResultFromRightTree != NULL) {
        return searchResultFromRightTree;
    };

    // Modifying the count variable which is common for all function calls.
    if(count < kth_maximum) {
        count++;
    }
    else if(count == kth_maximum) {
        return rootNode;
    }

    // We directly return this because if the kth largest element is not even found in the left sub-tree, then we are ok with returning NULL, since the element can't be anywhere else.
    return kthLargest(rootNode->left, kth_maximum, count);
}


int main() {

    Node* rootNode = new Node(15, new Node(8, new Node(6, new Node(3), new Node(7)), new Node(10, new Node(9), new Node(12))), NULL);

    rootNode -> parent = new Node(INT_MAX); // Getting a parent for the root node.
    /*
          15
          /
         8
       /   \
      6    10
     / \   / \
    3   7 9  12

    */
    int count = 1;

    cout << (kthSmallest(rootNode, 2, count) -> key) << endl;

    count = 1;
    cout << (kthLargest(rootNode, 2, count) -> key) << endl;
    
    

    

    return 0;
}