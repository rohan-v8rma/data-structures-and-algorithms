#include <iostream>
using namespace::std;

class Node {
    
    friend void preOrderTraversal(Node rootNode);
    friend void postOrderTraversal(Node rootNode);
    friend void inOrderTraversal(Node rootNode);

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

int main() {

    //! Why couldn't we use this?
    // Node rootNode(0, Node(1, Node(3), Node(4)), Node(2, Node(5), Node(6)));
    //? We couldn't use this because we required pointers to these nodes, and these are just Rvalues so they don't have a defined storage location.


    Node rootNode(0, new Node(1, new Node(3), new Node(4)), new Node(2, new Node(5), new Node(6)));
    
    preOrderTraversal(rootNode);
    printf("\n");
    postOrderTraversal(rootNode);
    printf("\n");
    inOrderTraversal(rootNode);
    printf("\n");

    return 0;
}