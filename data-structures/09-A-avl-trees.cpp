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
    
    friend Node* balanceBST(Node* node);

    friend Node* insertNode(Node* node, int element);

    friend Node* minimumNodeFinder(Node* treePtr);
    friend Node* deleteNode(Node* node, int element);

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
    if(rootPtr == NULL) {
        return rootPtr;
    }

    Node* newRoot = rootPtr -> left;    
    
    if( newRoot == NULL) { //! To prevent segmentation fault and since there is no newRoot
        return rootPtr;
    } 
    
    // Assigning the right branch of the left child node of rootPtr (rootPtr -> left -> right), which is about to become root itself, to the left of rootPtr. Note that it can be NULL as well, which is okay, since we anyways need to remove the old left branch of rootPtr, other there would be duplicate branches in the tree.
    rootPtr->left = newRoot->right;     
    // Updating height of rootPtr after adding a branch to it.
    rootPtr->height = 1 + getMax(getHeight(rootPtr->left), getHeight(rootPtr->right));
    
    // Assigning the old rootPtr to the right sub-tree of the NEW root (which is 'rootPtr -> left' i.e., 'newRoot')
    newRoot -> right = rootPtr; 
    // Updating height of newRoot after adding a branch to it.
    newRoot->height = 1 + getMax(getHeight(newRoot->left), getHeight(newRoot->right));

    return newRoot;
}

Node* leftRotate(Node* rootPtr) {
    if(rootPtr == NULL) {
        return rootPtr;
    }
    
    Node* newRoot = rootPtr -> right;
    
    if(newRoot == NULL) { //! To prevent segmentation fault and since there is no newRoot
        return rootPtr;
    }
    
    // Assigning the left branch of the right child node of rootPtr (rootPtr -> right -> left), which is about to become root itself, to the right of rootPtr. Note that it can be NULL as well, which is okay, since we anyways need to remove the old right branch of rootPtr, other there would be duplicate branches in the tree
    rootPtr->right = newRoot->left; 

    // Updating height of rootPtr after adding a branch to it.
    rootPtr->height = 1 + getMax(getHeight(rootPtr->left), getHeight(rootPtr->right));

    // Assigning the old rootPtr to the left sub-tree of the NEW root (which is 'rootPtr -> right' i.e., 'newRoot')
    newRoot -> left = rootPtr; 
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


// TODO: Not working with a normal bst. Only working as a helper
Node* balanceBST(Node* node) {
    if(node == NULL) {
        return node;
    }

    node->height = 1 + getMax(getHeight(node->left), getHeight(node->right));
    // We don't perform a simple incrementation because it is possible that before insertion the left sub-tree had a height of 4 and right sub-tree had a height of 5. An element is inserted in the left sub-tree but still the height of the parent node remains same. 

    int balanceFactor = getBalanceFactor(node);

    // If this node becomes unbalanced, then
    // there are 4 cases
 
    // Left Left Case (since balance factor is greater than 1 {left sub-tree larger, so right rotation needed} & balance factor of the left sub-tree is greater than or equal to 0 meaning there are more/equal nodes in the left sub-tree OF THE LEFT SUB-TREE compared to right sub-tree of the child node, so we can apply right rotation directly, unlike LR case.
    if (balanceFactor > 1 && getBalanceFactor(node->left) >= 0) {
        return rightRotate(node);
    }
        
    // Right Right Case (since balance factor is less than -1 {right sub-tree larger, so left rotation need} & balance factor of the right sub-tree is less than 0 meaning there are more/equal nodes in the right sub-tree OF THE RIGHT SUB-TREE compared to left sub-tree of the child node, so we can apply left rotation directly, unlike RL case.
    if (balanceFactor < -1 && getBalanceFactor(node->right) < 0) {
        return leftRotate(node);
    }

    // Left Right Case
    if (balanceFactor > 1 && getBalanceFactor(node->left) < 0) {
        
        node->left = leftRotate(node->left);        

        return rightRotate(node);
    }
 
    // Right Left Case
    if (balanceFactor < -1 && getBalanceFactor(node->right) >= 0) {
        node->right = rightRotate(node->right);

        return leftRotate(node);
    }    

    
    return node; // -1 <= balanceFactor <= 1
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
    
    return balanceBST(node); 
}

Node* minimumNodeFinder(Node* treePtr) {
    
    while(treePtr != NULL && treePtr->left != NULL) { // Sequence of conditions is important in this case because if treePtr is actually NULL and we try to access 'left', we will get segmentation fault.
        
        treePtr = treePtr -> left; //? This is perfectly fine to do and won't mutate the original pointer since we are not derefencing the pointer before assignment.
    }
        
    return treePtr;
}

Node* deleteNode(Node* node, int element) {
    if(node == NULL) {
        return node;
    }

    if(element < node->key) {
        node->left = deleteNode(node->left, element);
    }
    else if(element > node->key) {
        node->right = deleteNode(node->right, element);
    }
    else { // Node to be deleted found

        //? Node with 0 children
        if(node -> left == NULL && node -> right == NULL) {
            
            return NULL; // Instead of returning node, which would mean that the element would stay in the BST, we returned NULL which means the element would no longer be in BST.

        }

        //* Node with 1 child
        else if(node -> left == NULL) { // The left sub-tree is not present but right sub-tree is. So, instead of returning node, we directly return the pointer to its right sub-tree, removing the link to node.
            
            Node* temp = node -> right;
            
            delete node; //? de-allocating the memory allocated to node
            
            return temp;

        }
        //* Node with 1 child
        else if(node -> right == NULL) { // The right sub-tree is not present but left sub-tree is. So, instead of returning node, we directly return the pointer to its left sub-tree, removing the link to node.
            
            Node* temp = node -> left;
            
            delete node; //? de-allocating the memory allocated to node

            return temp;

        }

        //! Node with 2 child nodes
        Node* nodeSuccessor = minimumNodeFinder(node->right); // This won't be NULL in any case, since 'node' will DEFINITELY have 2 children, as cases of 0 and 1 children have been taken care of above.
        node->key = nodeSuccessor->key;

        //? Deleting the node we just used to replace value of 'node'
        //! It is logically OK to delete this key from RIGHT sub-tree, because, at this point, this key is unique in the right sub-tree of 'node' (NOT unique in the entire node BST, because node->key is also same)
        deleteNode(node->right, nodeSuccessor->key); 
        // This will go on recursively, if the successor's keep having 2 children, otherwise the above conditions will be satisfied
    }
    
    return balanceBST(node);
}

int main() {

    //! Why couldn't we use this?
    // Node rootNode(0, Node(1, Node(3), Node(4)), Node(2, Node(5), Node(6)));
    //? We couldn't use this because we required pointers to these nodes, and these are just Rvalues so they don't have a defined storage location.

    Node* rootNode = new Node(15, new Node(10, new Node(6, new Node(3),new Node(8, new Node(7), new Node(9))), NULL), NULL);
    // rootNode->parent = new Node(INT_MAX); // Getting a parent for the root node.
    /*
          15
          /
        10
       /  
      6   
     / \  
    3   8
       / \
      7   9
    */

    printf("Original BST:\n");
    preOrderTraversal(rootNode);
    printf("\n");

    // Right rotation of BST
    printf("Right rotation of BST:\n");
    rootNode = rightRotate(rootNode);
    preOrderTraversal(rootNode);
    printf("\n");

    // Left rotation of BST
    printf("Left rotation of BST (back to Original):\n");
    rootNode = leftRotate(rootNode);
    preOrderTraversal(rootNode);
    printf("\n");

    // Balancing the BST
    // printf("Balanced BST (now AVL tree):\n");
    // rootNode = balanceBST(rootNode);
    // preOrderTraversal(rootNode);
    // printf("\n");  
    
    // Inserting an element in AVL tree
    printf("Inserted 11 in AVL tree:\n");
    rootNode = insertNode(rootNode, 11);
    preOrderTraversal(rootNode);
    printf("\n");

    // Deleting a node from AVL tree
    printf("Deleted 11 from AVL tree (back to Original AVL):\n");
    rootNode = deleteNode(rootNode, 11);

    preOrderTraversal(rootNode);
    printf("\n");

    return 0;
}