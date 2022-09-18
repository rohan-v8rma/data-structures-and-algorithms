#include <iostream>
using namespace::std;

class Node {
    
    friend void preOrderTraversal(Node rootNode);
    friend void postOrderTraversal(Node rootNode);
    friend void inOrderTraversal(Node rootNode);
    friend int recursiveSearch(Node* rootPtr, int target);
    friend int iterativeSearch(Node* rootPtr, int target);

    private:
        int value;
        Node* left;
        Node* right;
    public:      
        Node() {
            value = 0;
            left = NULL;
            right = NULL;
        }

        Node(int value) {
            this -> value = value;
            left = NULL;
            right = NULL;
        }

        Node(int value, Node* left, Node* right) {
            
            this -> value = value;

            // Objects are created and stored somewhere in memory so we can get their address and store it in the `left` and `right` pointer
            this -> left = left; 
            this -> right = right;
        }
};

// Visit the root node, then the left node, then the right node
void preOrderTraversal(Node rootNode) {

    printf("%d, ", rootNode.value);

    if(rootNode.left != NULL) {
        preOrderTraversal(*(rootNode.left));    
    };
    
    if(rootNode.right != NULL) {
        preOrderTraversal(*(rootNode.right));    
    };
}

// Visit the left node, the right node, then the root node.
void postOrderTraversal(Node rootNode) {
    
    if(rootNode.left != NULL) {
        postOrderTraversal(*(rootNode.left));    
    };
    
    if(rootNode.right != NULL) {
        postOrderTraversal(*(rootNode.right));    
    };

    printf("%d, ", rootNode.value);
}

// Visit the left node, then the root node, then the right node.
void inOrderTraversal(Node rootNode) {
    
    if(rootNode.left != NULL) {
        inOrderTraversal(*(rootNode.left));    
    };

    printf("%d, ", rootNode.value);

    if(rootNode.right != NULL) {
        inOrderTraversal(*(rootNode.right));    
    };

}


int recursiveSearch(Node* rootPtr, int target) {

    if(target == (rootPtr -> value)) { // target element present
        return 1;
    
    }
    // If target less than root, but left is NULL, then no chance of target element being present, so 0 is returned
    else if( (target < (rootPtr -> value)) && ((rootPtr -> left) != NULL) ) { 
        
        return recursiveSearch(rootPtr -> left, target);

    }
    // If target greater than root, but right is NULL, then no chance of target element being present, so 0 is returned
    else if( (target > (rootPtr -> value)) && ((rootPtr -> right) != NULL) ) {
        
        return recursiveSearch(rootPtr -> right, target);
        
    }
    
    return 0;
}

int iterativeSearch(Node* rootPtr, int target) {

    while(true) {
        if(target == (rootPtr -> value)) { // target element present
            return 1;
        }
        else if( (target < (rootPtr -> value)) && ((rootPtr -> left) != NULL) ) { 
        
            rootPtr = rootPtr -> left;
            continue;
        }
        else if( (target > (rootPtr -> value)) && ((rootPtr -> right) != NULL) ) {
        
            rootPtr = rootPtr -> right;
            continue;
        }
        else {
            break;
        }
    }

    return 0;
    
}

int main() {

    //! Why couldn't we use this?
    // Node rootNode(0, Node(1, Node(3), Node(4)), Node(2, Node(5), Node(6)));
    //? We couldn't use this because we required pointers to these nodes, and these are just Rvalues so they don't have a defined storage location.


    Node rootNode(8, new Node(6, new Node(3), new Node(7)), new Node(10, new Node(9), new Node(12)));
    
    preOrderTraversal(rootNode);
    printf("\n");
    postOrderTraversal(rootNode);
    printf("\n");
    inOrderTraversal(rootNode);
    printf("\n");


    if(recursiveSearch(&rootNode, 12)) {
        cout << "Key present\n";
    }
    else {
        cout << "Key NOT present\n";
    }

    return 0;
}