#include <iostream>
using namespace::std;

// Each node contains a value and an address pointing to the next node
class node { // Using classes helps us to keep our `value` and `next` pointer private

    friend node* reverseLinkedList(node* head);

private:
    int value;
    node* next;
public:
    node() {
        value = -1;
        next = NULL;
    };
    
    node(int elementCt) { // this helps in creating a linked list with `elementCt` number of elements
        node* tempPtr = this;
        for(int elementNo = 1; elementNo <= elementCt; elementNo++) {
            printf("Enter element %d : ", elementNo);
            scanf("%d", &(tempPtr -> value));
            
            if(elementNo == elementCt) {
                tempPtr -> next = NULL;    
            }
            else {
                tempPtr -> next = new node;
                tempPtr = tempPtr -> next;
            }
            
        };
    };
    
    void display() {
        node* tempPtr = this;
        int elementNo = 1;
        while( tempPtr ) { //? NULL has truth-value of 0, other pointer values have truth-value of 1
            printf("Element %d : %d\n", elementNo++, tempPtr -> value);
            tempPtr = tempPtr -> next;
        }
    }
};

// Recursive function for reversing linked list.
node* reverseLinkedList(node* head) {

    node* temp;

    node* reversedHead;

    if( !(head->next->next) ) { //! Base condition
        reversedHead = head->next;
        head->next = NULL; //? So that when we assign `head` to `next` of `reversedHead`, the elements after `head` aren't added to the reversed linked-list chain.
        
        reversedHead->next = head;
    }
    else {
        reversedHead = reverseLinkedList(head->next);
        head->next = NULL; //? So that when we assign `head` to `next` of `reversedHead`, the elements after `head` aren't added to the reversed linked-list chain.
        temp = reversedHead;
        while(temp->next) { // Reaching to the end of the reversedHead linked list using temp, and assigning the previous `head` to the end.
            temp = temp->next;
        }
        temp->next = head;
    }

    return reversedHead;

}


int main() {
    node* head = new node(5); // When `new` is used to allocate memory for a C++ class object, the object's constructor is called after the memory is allocated.
    node* reversedHead = reverseLinkedList(head);

    reversedHead -> display();
    return 0;
}

