#include <iostream>
#include <limits.h>
using namespace ::std;

class Node {

    friend void preOrderTraversal(Node* rootNode);
    friend void postOrderTraversal(Node* rootNode);
    friend void inOrderTraversal(Node* rootNode);
    
    friend Node* recursiveSearch(Node *rootPtr, int target);
    friend Node* iterativeSearch(Node *rootPtr, int target);

    friend Node* minimumNodeFinder(Node* rootPtr);

    friend Node* insertNode(Node* rootPtr, int element);
    friend Node* deleteNode(Node* rootPtr, int element);

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

// Visit the root node, then the left node, then the right node
void preOrderTraversal(Node* rootNode) {
    if(rootNode == NULL) {
        return;
    }

    printf("%d, ", rootNode->key);

    preOrderTraversal(rootNode->left);
    
    preOrderTraversal(rootNode->right);
    
}

// Visit the left node, the right node, then the root node.
void postOrderTraversal(Node* rootNode) {
    if(rootNode == NULL) {
        return;
    }

    postOrderTraversal(rootNode->left);
        
    postOrderTraversal(rootNode->right);
    
    printf("%d, ", rootNode->key);
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

Node* recursiveSearch(Node *rootPtr, int target) {
    
    if(rootPtr != NULL) {
        if (target == (rootPtr->key)) { // target element present
            return rootPtr;
        }
        else if ((target < (rootPtr->key))) {
            return recursiveSearch(rootPtr->left, target);
        }
        else if ((target > (rootPtr->key))) {
            return recursiveSearch(rootPtr->right, target);
        }
    }
    
    return NULL;
        
}

Node *iterativeSearch(Node *rootPtr, int target) {

    while (true) {
        if(rootPtr != NULL) {
            if (target == (rootPtr->key)) { // target element present
                return rootPtr;
            }
            else if (target < (rootPtr->key)) {
                rootPtr = rootPtr->left;
                continue;
            }
            else if (target > (rootPtr->key)) {
                rootPtr = rootPtr->right;
                continue;
            }
        }
        else {
            return NULL;
        }
    }

    return NULL;
}

//? Recursive insertion function
Node* insertNode(Node* rootPtr, int element) {
    
    if (rootPtr == NULL) { // Either the BST was empty OR we have reached a the empty sub-tree of a leaf where the element will fit.
        return (new Node(element));
    }

    if( element < (rootPtr -> key) ) {
        rootPtr->left = insertNode(rootPtr->left, element);
        rootPtr->left->parent = rootPtr; // Important to assign parent as well. This won't give segmentation fault, because insert will never be NULL, it will either be a new Node or a sub-tree ALWAYS.
    }
    else if( element > (rootPtr -> key) ) {
        rootPtr->right = insertNode(rootPtr->right, element);
        rootPtr->right->parent = rootPtr; // Important to assign parent as well. This won't give segmentation fault, because insert will never be NULL, it will either be a new Node or a sub-tree ALWAYS.
    }
    else {
        printf("Duplicate element. NOT inserted.");
    }
    
    return rootPtr; // For the cases where a new Node wasn't added, but we still need to return the root to reflect the changes in the top-most BST.
}

Node* minimumNodeFinder(Node* treePtr) {
    
    while(treePtr != NULL && treePtr->left != NULL) { // Sequence of conditions is important in this case because if treePtr is actually NULL and we try to access 'left', we will get segmentation fault.
        
        treePtr = treePtr -> left; //? This is perfectly fine to do and won't mutate the original pointer since we are not derefencing the pointer before assignment.
    }
        
    return treePtr;
}

//? Recursive deletion function
Node* deleteNode(Node* rootPtr, int element) {
    if(rootPtr == NULL) {
        return NULL;
    }

    if(element < rootPtr->key) {
        rootPtr->left = deleteNode(rootPtr->left, element);
    }
    else if(element > rootPtr->key) {
        rootPtr->right = deleteNode(rootPtr->right, element);
    }
    else { // when element == rootPtr->key, this is the element to be deleted.
        
        //? Node with 0 children
        if(rootPtr -> left == NULL && rootPtr -> right == NULL) {
            
            return NULL; // Instead of returning rootPtr, which would mean that the element would stay in the BST, we returned NULL which means the element would no longer be in BST.

        }

        //* Node with 1 child
        else if(rootPtr -> left == NULL) { // The left sub-tree is not present but right sub-tree is. So, instead of returning rootPtr, we directly return the pointer to its right sub-tree, removing the link to rootPtr.
            
            rootPtr->right->parent = rootPtr->parent;
            
            delete rootPtr; //? de-allocating the memory allocated to rootPtr
            
            return (rootPtr->right);

        }
        //* Node with 1 child
        else if(rootPtr -> right == NULL) { // The right sub-tree is not present but left sub-tree is. So, instead of returning rootPtr, we directly return the pointer to its left sub-tree, removing the link to rootPtr.
            
            rootPtr->left->parent = rootPtr->parent;
            
            delete rootPtr; //? de-allocating the memory allocated to rootPtr

            return (rootPtr->left);

        }

        //! Node with 2 children

        Node* rootSuccessor = minimumNodeFinder(rootPtr->right); // This won't be NULL in any case, since rootPtr will DEFINITELY have 2 children, as cases of 0 and 1 children have been taken care of above.
        rootPtr->key = rootSuccessor->key;

        // No need to modify parents, because the relative position of the node is staying same, just its value changed

        //? Deleting the node we just used to replace rootPtr's value
        deleteNode(rootPtr->right, rootSuccessor->key);
        // This will go on recursively, if the successor's keep having 2 children, otherwise the above conditions will be satisfied
    }
}


int main() {

    //! Why couldn't we use this?
    // Node rootNode(0, Node(1, Node(3), Node(4)), Node(2, Node(5), Node(6)));
    //? We couldn't use this because we required pointers to these nodes, and these are just Rvalues so they don't have a defined storage location.

    //* If a node has only one child, it should be in the left sub-tree only
    //TODO Add support for child in right sub-tree also

    Node* rootNode = new Node(15, new Node(8, new Node(6, new Node(3), new Node(7)), new Node(10, new Node(9), new Node(12))), NULL);
    rootNode->parent = new Node(INT_MAX); // Getting a parent for the root node.
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
    postOrderTraversal(rootNode);
    printf("\n");
    inOrderTraversal(rootNode);
    printf("\n");

    rootNode = insertNode(rootNode, 11);
    rootNode = insertNode(rootNode, 13);

    preOrderTraversal(rootNode);
    printf("\n");
    
    // Deleting a leaf node
    rootNode = deleteNode(rootNode, 9);
    
    preOrderTraversal(rootNode);
    printf("\n");
    

    // * Deleting a node with 1 child (since 9 was removed, 10 has only 1 child)
    rootNode = deleteNode(rootNode, 10);
    
    preOrderTraversal(rootNode);
    printf("\n");
    
    preOrderTraversal(rootNode);
    printf("\n");

    if (recursiveSearch(rootNode, 7))
    {
        cout << "Key present\n";
    }
    else
    {
        cout << "Key NOT present\n";
    }


    //TODO: hello   

    

    return 0;
}