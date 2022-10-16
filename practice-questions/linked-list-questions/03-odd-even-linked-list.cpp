#include <iostream>
using namespace::std;

//? This solution makes a new linked list, instead of modifying the original one.

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

    node* returnList = new node(head->value);
    node* temp = returnList;
    node* temp1;


    int isHeadOdd = ( (head->value) % 2 );

    head = head->next; // Keeping position of original head unchanged.

    while(head != NULL) {
        if( (head->value % 2) == (isHeadOdd) ) { //next element is also odd
            temp = returnList;
            while( (temp->next != NULL) && (( temp->next->value % 2) == (isHeadOdd) ) ) {
                temp = temp->next;
            }
            temp1 = temp->next;
            temp->next = new node(head->value);
            temp->next->next = temp1;
        }
        else if( (head->value % 2) != (isHeadOdd) ) { // next element is not odd
            temp = returnList;
            while(temp->next != NULL) {
                temp = temp->next;
            }
            temp->next = new node(head->value);
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