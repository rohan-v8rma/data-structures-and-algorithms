#include <iostream>

// Each node contains a value and an address pointing to the next node
class node {

    friend class circularSinglyLinkedList;

private:
    int value;
    node* next;
public:
    node() {
        value = -1;
        next = NULL;
    };
};

/* 
? In the case circular linked list, we needed a separate class for it, unlike regular linked list, because we need to store the address of the last element as well, to prevent an infinite loop.

! So, a class which stores just the value of the node and the next pointer is NOT sufficient.
*/
class circularSinglyLinkedList {

public:

    node* head;
    node* last; //? This address helps in demarcating the end of the circular linked list.
    
    circularSinglyLinkedList(int elementCt) { // this helps in creating a linked list with `elementCt` number of elements
        this->head = new node;
        node* tempPtr = head;
        for(int elementNo = 1; elementNo <= elementCt; elementNo++) {
            printf("Enter element %d : ", elementNo);
            scanf("%d", &(tempPtr -> value));
            
            if(elementNo == elementCt) {
                tempPtr -> next = head;
                this->last = tempPtr;
            }
            else {
                tempPtr -> next = new node;
                tempPtr = tempPtr -> next;
            }
            
        };
    };

    void display() {
        node* tempPtr = this->head;
        int elementNo = 1;
        while( true ) { //? NULL has truth-value of 0, other pointer values have truth-value of 1
            printf("Element %d : %d\n", elementNo++, tempPtr -> value);
            
            if( tempPtr == last) { /// To prevent an infinite loop.
                break;
            }
            
            tempPtr = tempPtr -> next;    
            
            
        }
    }  
};

int main() {
    circularSinglyLinkedList* first = new circularSinglyLinkedList(5);
        
    first->display();

    return 0;
}

