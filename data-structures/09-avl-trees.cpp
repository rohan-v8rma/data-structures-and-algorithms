#include <iostream>
#include <limits.h>
using namespace ::std;

/*
NOTE: Height of a node is the number of edges to a leaf node. 
      
      In the case of a leaf node, that would be 0 since it is already a leaf node.
*/

int getMax(int num1, int num2) {
    return ((num1 > num2) ? num1 : num2);
}

class Node {

    friend void preOrderTraversal(Node* rootNode);
    friend void inOrderTraversal(Node* rootNode);

    friend Node* rightRotate(Node* rootPtr);
    friend Node* leftRotate(Node* rootPtr);

    friend int getHeight(Node* node);
    friend int getBalanceFactor(Node* node);

    friend Node* insertNode(Node* rootPtr, int element);

    friend int main();

private:
    int key;
    int height;
    Node *left;
    Node *right;

public:
    Node() {
        key = 0;
        height = 0;
        left = NULL;
        right = NULL;
    }

    Node(int key) { // This is a leaf node, since both left and right sub-trees are NULL.
        this->key = key;
        height = 0; 
        // We are considering height of leaf node as 0, according to the logic explained at the top.
        left = NULL;
        right = NULL;
    }

    Node(int key, Node *left, Node *right) {

        // this->parent = NULL;

        this->key = key;
        // Objects are created and stored somewhere in memory so we can get their address and store it in the `left` and `right` pointer
        this->left = left;
        this->right = right;

        height = 1 + getMax(((left == NULL) ? -1 : left->height), ((right == NULL) ? -1 : right->height));
        
    }
};

// Visit the root node, then the left node, then the right node
void preOrderTraversal(Node* rootNode) {
    if(rootNode == NULL) {
        return;
    }

    printf("%d, ", rootNode->key);

    preOrderTraversal(rootNode->left);
    
    preOrderTraversal(rootNode->right);
    
}

// Visit the left node, then the root node, then the right node.
void inOrderTraversal(Node* rootNode){
    if(rootNode == NULL) {
        return;
    }

    inOrderTraversal(rootNode->left);   

    printf("%d, ", rootNode->key);

    inOrderTraversal(rootNode->right);
}

int getHeight(Node* node) {
    if(node == NULL) {
        return -1;
        // Since leaf nodes have height of 0, NULL nodes that are child nodes of leaf nodes should have height even less, so they have height as -1.
    }
    else {
        return (node->height);
    }
}


Node* rightRotate(Node* rootPtr) {

    Node* newRoot = rootPtr -> left;    
    
    if( (newRoot->right) != NULL) {
        rootPtr->left = newRoot->right; // Assigning the right branch of the left child node of rootPtr (rootPtr -> left -> right), which is about to become root itself, to the left of rootPtr.
        
        // Updating height of rootPtr after adding a branch to it.
        rootPtr->height = 1 + getMax(getHeight(rootPtr->left), getHeight(rootPtr->right));
    }
    
    newRoot -> right = rootPtr; // Assigning the old rootPtr to the right sub-tree of the NEW root (which is 'rootPtr -> left' i.e., 'newRoot')

    // Updating height of newRoot after adding a branch to it.
    newRoot->height = 1 + getMax(getHeight(newRoot->left), getHeight(newRoot->right));

    return newRoot;
}

Node* leftRotate(Node* rootPtr) {
    
    Node* newRoot = rootPtr -> right;
    
    if( (newRoot->left) != NULL) {
        rootPtr->right = newRoot->left; // Assigning the left branch of the right child node of rootPtr (rootPtr -> right -> left), which is about to become root itself, to the right of rootPtr.
        
        // Updating height of rootPtr after adding a branch to it.
        rootPtr->height = 1 + getMax(getHeight(rootPtr->left), getHeight(rootPtr->right));
    }
    
    newRoot -> left = rootPtr; // Assigning the old rootPtr to the left sub-tree of the NEW root (which is 'rootPtr -> right' i.e., 'newRoot')

    // Updating height of newRoot after adding a branch to it.
    newRoot->height = 1 + getMax(getHeight(newRoot->left), getHeight(newRoot->right));

    return newRoot;
}

int getBalanceFactor(Node* node) {
    if(node == NULL) {
        return 0;
    }
    else {
        return ( getHeight(node->left) - getHeight(node->right) );
    }
}

Node* insertNode(Node* node, int element) {

    // 1. Performing regular recursive BST insertion
    if(node == NULL) {
        return new Node(element); // this will have height = 0;
    }

    if( element < (node -> key) ) {
        node->left = insertNode(node->left, element);
    }
    else if( element > (node -> key) ) {
        node->right = insertNode(node->right, element);
    }
    else {
        printf("Duplicate element. NOT inserted.");
        return node; // No need to go any further since no insertion took place
    }
    
    // 2. Updating height of the current node
    node->height = 1 + getMax(getHeight(node->left), getHeight(node->right));
    // We don't perform a simple incrementation because it is possible that before insertion the left sub-tree had a height of 4 and right sub-tree had a height of 5. An element is inserted in the left sub-tree but still the height of the parent node remains same. 

    int balanceFactor = getBalanceFactor(node);

    // If this node becomes unbalanced, then
    // there are 4 cases
 
    // Left Left Case (since balance factor is greater than 1 {left sub-tree larger, so right rotation needed} & element to be inserted is less than key of LEFT child node, meaning it would have been inserted as the LEFT child {in the previous nested recursive call} of the LEFT child node of the parent node.)
    if (balanceFactor > 1 && element < node->left->key) {
        return rightRotate(node);
    }
        
    // Right Right Case (since balance factor is less than -1 {right sub-tree larger, so left rotation need} & element to be inserted is greate than key of RIGHT child node, meaning it would have been inserted as the RIGHT child {in the previous nested recursive call} of the RIGHT child node of the parent node.)
    if (balanceFactor < -1 && element > node->right->key) {
        return leftRotate(node);
    }

    // Left Right Case
    if (balanceFactor > 1 && element > node->left->key) {
        node->left = leftRotate(node->left);

        return rightRotate(node);
    }
 
    // Right Left Case
    if (balanceFactor < -1 && element < node->right->key) {
        node->right = rightRotate(node->right);

        return leftRotate(node);
    }
        
    return node; 
}

int main() {

    //! Why couldn't we use this?
    // Node rootNode(0, Node(1, Node(3), Node(4)), Node(2, Node(5), Node(6)));
    //? We couldn't use this because we required pointers to these nodes, and these are just Rvalues so they don't have a defined storage location.

    Node* rootNode = new Node(15, new Node(8, new Node(6, new Node(3), new Node(7)), new Node(10, new Node(9), new Node(12))), NULL);
    // rootNode->parent = new Node(INT_MAX); // Getting a parent for the root node.
    /*
          15
          /
         8
       /   \
      6    10
     / \   / \
    3   7 9  12
    */


    preOrderTraversal(rootNode);
    printf("\n");
    // inOrderTraversal(rootNode);
    // printf("\n");

    rootNode = rightRotate(rootNode);

    preOrderTraversal(rootNode);
    printf("\n");

    rootNode = leftRotate(rootNode);
    
    preOrderTraversal(rootNode);
    printf("\n");

    return 0;
}