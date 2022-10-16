#include <iostream>
using namespace::std;

//? This solution makes a new linked list, instead of modifying the original one.
//* Very rudimentary solution that uses 3 while loops.


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

    node* returnList = new node(head->value);
    node* firstPartPointer = returnList;
    node* secondPartHead;


    int isHeadOdd = ( (head->value) % 2 );

    head = head->next; // Keeping position of original head unchanged.

    while(head != NULL) {
        if( (head->value % 2) == (isHeadOdd) ) { //next element is also odd
            firstPartPointer = returnList;
            while( (firstPartPointer->next != NULL) && (( firstPartPointer->next->value % 2) == (isHeadOdd) ) ) {
                firstPartPointer = firstPartPointer->next;
            }
            secondPartHead = firstPartPointer->next;

            firstPartPointer->next = new node(head->value);
            firstPartPointer->next->next = secondPartHead;
        }
        else if( (head->value % 2) != (isHeadOdd) ) { // next element is not odd
            firstPartPointer = returnList;
            while(firstPartPointer->next != NULL) {
                firstPartPointer = firstPartPointer->next;
            }
            firstPartPointer->next = new node(head->value);
        }

        head = head -> next;
    }

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