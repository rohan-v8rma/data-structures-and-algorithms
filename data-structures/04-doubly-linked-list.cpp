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
    friend void addElement(Node* &head);
};

Node* createDoublyLinkedList(int elementCt) {
    Node* head = new Node();

    Node* tempPtr = head;
    Node* beforePtr = new Node(); 
    beforePtr -> next = tempPtr; // beforePtr helps us assign a value to the `previous` pointer.

    for(int index = 0; index < elementCt; index++) {
        
        printf("Enter element %d : ", index + 1);
        scanf("%d", &(tempPtr -> value) );

        if(tempPtr == head) { // Since the default constructor of the Node sets the `next` and `previous` to NULL already, we need not set it explicitly to NULL  
            // tempPtr -> previous = NULL; 
        }
        else {
            (tempPtr -> previous) = beforePtr; // 
        };

        if ( index == (elementCt - 1) ) { // Since the default constructor of the Node sets the `next` and `previous` to NULL already, we need not set it explicitly to NULL
            // (tempPtr -> next) = NULL;
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

void addElement(Node* &head) {
    int addIndex; // let 0
    printf("Index position of node: ");
    scanf("%d", &addIndex);

    Node* tempPtr = head;
    Node* beforePtr = new Node();
    beforePtr -> next = tempPtr;    

    for(int index = 0; index < addIndex; index++) {
        tempPtr = tempPtr -> next;
        beforePtr = beforePtr -> next;
    };
    
    tempPtr = new Node();

    printf("Value of node: ");
    scanf("%d", &(tempPtr -> value));

    if (addIndex > 0) {
        tempPtr -> next = beforePtr -> next;
        tempPtr -> previous = beforePtr;
        (beforePtr -> next) -> previous = tempPtr; // It is important that we assign the previous pointer of `beforePtr's next` to tempPtr, before changing the value of `beforePtr's next` (see next line) which would lead to unexpected behaviour. 
        beforePtr -> next = tempPtr;
        
    }
    else {
        tempPtr -> next = head;
        head -> previous = tempPtr;
        head = tempPtr;    
    }; // this if else block is need since if the inserted element is to be at 0th index, head has to point directly to the next element, instead of the new element getting pointed to by some other element.
}

int main() {
    int size = 3; 
    Node* head = createDoublyLinkedList(size);

    printDoublyLinkedList(head);
    
    addElement(head);

    printDoublyLinkedList(head);

    return 0;
}