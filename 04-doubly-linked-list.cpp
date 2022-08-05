#include <iostream>

class Node {
    private:
        int value;
        Node* next;
        Node* previous;
    public:

        Node() {
            value = 0;
            next = NULL;
            previous = NULL;
        };

        Node(int value, Node* next, Node* previous) {
            this -> value = value;
            this -> next = next;
            this -> previous = previous;
        };
    
    friend Node* createDoublyLinkedList(int elementCt);
    friend void printDoublyLinkedList(Node *head);
};

Node* createDoublyLinkedList(int elementCt) {
    Node* head = new Node();

    Node* tempPtr = head;
    Node* beforePtr = new Node(); 
    beforePtr -> next = tempPtr; // beforePtr helps us assign a value to the `previous` pointer.

    for(int index = 0; index < elementCt; index++) {
        
        printf("Enter element %d : ", index + 1);
        scanf("%d", &(tempPtr -> value));
        printf("%d\n", (tempPtr -> value) );

        if(tempPtr == head) { // Since the default constructor of the Node sets the `next` and `previous` to NULL already, we need not set it explicitly to NULL  
            // tempPtr -> previous = NULL;
            continue; 
        }
        else {
            (tempPtr -> previous) = beforePtr; // 
        };

        if ( index == (elementCt - 1) ) { // Since the default constructor of the Node sets the `next` and `previous` to NULL already, we need not set it explicitly to NULL
            // (tempPtr -> next) = NULL;
            continue;
        }
        else {
            (tempPtr -> next) = new Node();

            tempPtr = (tempPtr -> next);
            beforePtr = (beforePtr -> next);
        };
    }

    return head;
};

void printDoublyLinkedList(Node* head) {
    
    int elementCt = 0;

    while(true) {
        printf("Element %d is : %d\n", ++elementCt, (head -> value) );
        
        if( (head -> next) == NULL ) { 
            break;
        }
        else {
            head = head -> next;
        };

    };

    while(true) {
        printf("Element %d is : %d\n", elementCt--, (head -> value) );
        
        if( (head -> previous) == NULL ) {
            break;
        }
        else {
            head = head -> previous;
        };
    };

    
}

int main() {
    int size = 5; 
    Node* head = createDoublyLinkedList(size);

    printDoublyLinkedList(head);
    

    return 0;
}