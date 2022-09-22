#include <iostream>
#include <limits.h>
using namespace ::std;

class Node
{

    friend void preOrderTraversal(Node* rootNode);
    friend void postOrderTraversal(Node* rootNode);
    friend void inOrderTraversal(Node* rootNode);
    
    friend Node* recursiveSearch(Node *rootPtr, int target);
    friend Node* iterativeSearch(Node *rootPtr, int target);
    
    friend Node* successor(Node *elementPtr);
    friend Node* predecessor(Node *elementPtr);

    friend void insert(Node* rootPtr, int element);
    friend void keyDelete(Node* rootPtr, int target);
    friend int main();

private:
    int key;
    Node *parent;
    Node *left;
    Node *right;

public:
    Node()
    {
        key = 0;
        parent = NULL;
        left = NULL;
        right = NULL;
    }

    Node(int key)
    {
        this->key = key;
        left = NULL;
        right = NULL;
    }

    Node(int key, Node *left, Node *right)
    {

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
    
    if(rootPtr == NULL) {
        return NULL;
    }
    else if (target == (rootPtr->key)) { // target element present
        return rootPtr;
    }
    else if ((target < (rootPtr->key))) {
        return recursiveSearch(rootPtr->left, target);
    }
    else if ((target > (rootPtr->key))) {
        return recursiveSearch(rootPtr->right, target);
    }

    return NULL;
}

Node *iterativeSearch(Node *rootPtr, int target) {
    while (true)
    {
        if(rootPtr == NULL) {
            return NULL;
        }
        else if (target == (rootPtr->key)) { // target element present
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

    return NULL;
}

//! Read inline explanations to understand how `successor` works.

Node* successor(Node* elementPtr)
{
    if(elementPtr == NULL) 
    { // Element not present in the tree, so no way of having successor
        return NULL;
    }

    if (elementPtr->right != NULL)
    { // If the element whose successor we need to find has a right sub-tree, the successor can be easily found by left traversing in its right sub-tree, in order to get the minimum element greater than the element whose successor we need.
        elementPtr = elementPtr->right;

        while (elementPtr->left != NULL)
        { // Finding the leftmost element in the right sub-tree.
            elementPtr = elementPtr->left;
        }

        return elementPtr;
    }

    /*
    We reach here only when the element doesn't have a right sub-tree.

    So, now we need to search in its parents.

    ? We actually need to find the FIRST ancestor of the element, in whose left sub-tree this element lies.

    Since, if the element lies in the right sub-tree, the element is obviously greater than the ancestor, WHICH IS NOT WHAT WE NEED.

    If the element is in an ancestor's left sub-tree, then the element is obviously less than the ancestor, meaning the ancestor is the next greatest element since the ancestor's right sub-tree would be having elements EVEN greater than the ancestor.
    */
    while (elementPtr->parent != NULL)
    {

        if (elementPtr == (elementPtr->parent)->left)
        {
            return (elementPtr->parent);
        }

        elementPtr = elementPtr->parent;
    }

    return NULL; // Ancestor not found.
}

Node *predecessor(Node *elementPtr)
{
    if(elementPtr == NULL) 
    { // Element not present in the tree, so no way of having predecessor
        return NULL;
    }


    if (elementPtr->left != NULL)
    { // If the element whose predecessor we need to find has a LEFT sub-tree, the predecessor can be easily found by right traversing in its left sub-tree, in order to get the maximum element lesser than the element whose predecessor we need.
        elementPtr = elementPtr->left;

        while (elementPtr->right != NULL)
        { // Finding the rightmost element in the left sub-tree.
            elementPtr = elementPtr->right;
        }

       return elementPtr;
    }

    /*
    We reach here only when the element doesn't have a left sub-tree.

    So, now we need to search in its parents.

    ? We actually need to find the FIRST ancestor of the element, in whose right sub-tree this element lies.

    Since, if the element lies in the left sub-tree, the element is obviously lesser than the ancestor, WHICH IS NOT WHAT WE NEED.

    If the element is in an ancestor's right sub-tree, then the element is obviously greater than the ancestor, meaning the ancestor is the next lowest element since the ancestor's left sub-tree would be having elements EVEN lesser than the ancestor.
    */
    while (elementPtr->parent != NULL)
    {

        if (elementPtr == (elementPtr->parent)->right)
        {
            return (elementPtr->parent);
        }

        elementPtr = elementPtr->parent;
    }

    return NULL; // Ancestor not found.
}

void insert(Node* rootPtr, int element) 
{
    
    if( element < (rootPtr -> key) ) 
    {
        if((rootPtr -> left) == NULL) 
        {
            rootPtr -> left = new Node(element);
            rootPtr -> left -> parent = rootPtr; // Setting the parent of the newly added node
        }
        else 
        {
            insert(rootPtr -> left, element);
        }
    }
    
    else if( element > (rootPtr -> key) ) 
    {
        if((rootPtr -> right) == NULL) 
        {
            rootPtr -> right = new Node(element);
            rootPtr -> right -> parent = rootPtr; // Setting the parent of the newly added node
        }
        else 
        {
            insert(rootPtr -> right, element);
        }
    }
    
    else 
    { 
        printf("Duplicate element, not inserted");   
    }
}

void keyDelete(Node* rootPtr, int target) {
    Node* targetPtr = iterativeSearch(rootPtr, target);
    
    

    if(targetPtr == NULL) {
        printf("Target element not present. Deletion not possible\n");
        return;
    }


    if( (targetPtr->left == NULL) && (targetPtr->right == NULL) ) 
    { // Case 1 (Element to be deleted has no children)
        
        if(targetPtr->parent->key == INT_MAX) // Checking whether this is the root node.
        { // No children and no parent so it is a trivial BST.
            printf("This tree has only one node. If that is deleted, this would no longer be a BST. So, deletion not possible\n");
        }
        
        //TODO: Try using free
        else { // Checking whether the target is the left or right child node of parent.
            
            if (targetPtr->parent->left == targetPtr) {
                targetPtr->parent->left = NULL;
            }
            else {
                targetPtr->parent->right = NULL;
            }
        }
        
        return;
    }
    
    
    // Case 2 (Element to be deleted has 1 child)
        
    else if(targetPtr->left != NULL) 
    {
        // We have placed the root element in the left sub-tree of the pseudo parent (OF THE ROOT NODE), so this if-condition will always be met.
        if (targetPtr->parent->left == targetPtr)  
        {
            targetPtr->parent->left = targetPtr -> left;
        }
        else 
        {
            targetPtr->parent->right = targetPtr -> left;
        }
    }
    else if(targetPtr->right != NULL) 
    {
        if (targetPtr->parent->left == targetPtr) 
        {
            targetPtr->parent->left = targetPtr -> right;
        }
        else 
        {
            targetPtr->parent->right = targetPtr -> right;
        }
    }    
    
    else 
    { // Case 3 (Element to be deleted has 2 children)
    
        
        // Getting pointers to both the successor and predecessor elements
        Node* sucPtr = successor(targetPtr);
        Node* prePtr = predecessor(targetPtr);
        
        if(sucPtr != NULL) {
            
            // First deleting the successor from the tree
            keyDelete(rootPtr, sucPtr->key); 
            
            // Then inserting the successor in place of the target, to avoid duplicate successor values.
            targetPtr->key = sucPtr->key; 
        }
        
        else if(prePtr != NULL) {
        
            keyDelete(rootPtr, prePtr->key);
        
            targetPtr->key = prePtr->key;
        }
        
        return;
    }
    
}

int main()
{

    //! Why couldn't we use this?
    // Node rootNode(0, Node(1, Node(3), Node(4)), Node(2, Node(5), Node(6)));
    //? We couldn't use this because we required pointers to these nodes, and these are just Rvalues so they don't have a defined storage location.

    //* If a node has only one child, it should be in the left sub-tree only
    //TODO Add support for child in right sub-tree also

    Node rootNode(15, new Node(8, new Node(6, new Node(3), new Node(7)), new Node(10, new Node(9), new Node(12))), NULL);
    rootNode.parent = new Node(INT_MAX); // Getting a parent for the root node.
    /*
          15
          /
         8
       /   \
      6    10
     / \   / \
    3   7 9  12

    */
    
    preOrderTraversal(&rootNode);
    printf("\n");
    postOrderTraversal(&rootNode);
    printf("\n");
    inOrderTraversal(&rootNode);
    printf("\n");

    insert(&rootNode, 11);
    insert(&rootNode, 13);

    preOrderTraversal(&rootNode);
    printf("\n");
    
    // Deleting a leaf node
    keyDelete(&rootNode, 9);
    
    preOrderTraversal(&rootNode);
    printf("\n");
    

    // * Deleting a node with 1 child (since 9 was removed, 10 has only 1 child)
    keyDelete(&rootNode, 10);
    
    preOrderTraversal(&rootNode);
    printf("\n");
    
    preOrderTraversal(&rootNode);
    printf("\n");

    if (recursiveSearch(&rootNode, 7))
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