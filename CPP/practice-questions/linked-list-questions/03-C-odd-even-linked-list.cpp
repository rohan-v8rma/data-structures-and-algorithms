#include <iostream>
using namespace::std;

//? This solution makes a new linked list, instead of modifying the original one.
//* Most easily understandable solution.

class node { // Using classes helps us to keep our `value` and `next` pointer private

    friend node* oddEvenLinkedList(node* head);
    friend int main();
private:
    int value;
    node* next;
public:
    node() {
        value = -1;
        next = NULL;
    };
    
    node(int number) {
        this->value = number;
    };

    void display() {
        node* tempPtr = this;
        int elementNo = 1;
        while(tempPtr != NULL) {
            printf("Element %d : %d\n", elementNo, tempPtr -> value);
            tempPtr = tempPtr -> next;
            elementNo++;
        }
    }
};

node* oddEvenLinkedList(node* head) {
    if(head == NULL) {
        printf("Linked list is empty.");
        return head;
    }

    node* returnList = new node(head->value); // Pointer for storing the head of the list to be returned. 
    node* firstPartPointer = returnList; // Pointer for adding elements to the first part.
    node* secondPartHead = NULL; // Pointer for storing the head of the second part of the linked list, which we will connect to the tail of the first part.
    node* secondPartPointer; // Pointer for adding elements to the second part.
    
    int isHeadOdd = (head->value % 2);

    head = head->next;

    while(head != NULL) {
        if( (head->value % 2) == isHeadOdd ) {
            firstPartPointer->next = new node(head->value);
            firstPartPointer = firstPartPointer->next;
        }
        else if( (head->value % 2) != isHeadOdd ) {
            if( !secondPartHead ) { // When head of second part is NULL.
                secondPartHead = new node(head->value);
                secondPartPointer = secondPartHead;
            }
            else {
                secondPartPointer->next = new node(head->value);
                secondPartPointer = secondPartPointer->next;
            }
        }
        head = head -> next;
    }
    firstPartPointer->next = secondPartHead;

    return returnList;
}

int main() {

    node* head = new node(1);

    int size = 5;

    node* temp = head;
    for(int element = 2; element < (size + 2); element++) {
        temp->next = new node(element);
        temp = temp->next;
    }

    head->display();
    
    node* newList = oddEvenLinkedList(head);

    printf("\nAfter bringing odd and even elements together\n");
    newList->display();

    return 0;
    
}