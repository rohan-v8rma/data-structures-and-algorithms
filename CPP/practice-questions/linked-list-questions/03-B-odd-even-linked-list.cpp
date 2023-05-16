#include <iostream>
using namespace::std;

//! This solution modifies the original linked list.
//

class node { // Using classes helps us to keep our `value` and `next` pointer private

    friend void oddEvenLinkedList(node* head);
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

void oddEvenLinkedList(node* head) {
    if(head == NULL) {
        printf("Linked list is empty.");    
        return;
    }

    int isHeadOdd = (head->value % 2);

    node* placeHolder = head; // For storing the address of the last odd node, which we will use to link the next odd node.
    node* headSubstitute = head; // For moving forward from placeholder and finding the next odd node to link. If even nodes are found, they are added to the second part of the list.
    //! NOTE: Odd and Even are interchangeable in the above explanations, depending on whether the head of the original list is odd or even.
    
    node* secondPartHead = NULL;
    node* secondPartPointer = NULL;

    headSubstitute = headSubstitute->next;

    while(headSubstitute != NULL) {
        if( (headSubstitute->value % 2) == isHeadOdd ) { 
            placeHolder->next = headSubstitute; // Suppose the list is like 1, 2, 3, 4, 5, 6.
            placeHolder = placeHolder->next; 
        }
        else if( (headSubstitute->value % 2) != isHeadOdd ) {
            if( !secondPartHead ) { // When head of second part is NULL.
                secondPartHead = headSubstitute;
                secondPartPointer = secondPartHead;
            }
            else {
                secondPartPointer->next = headSubstitute; // Attaching the new element to the end of the second part.
                secondPartPointer = secondPartPointer->next;
                
                //! NOTE: It is likely that the elements attached to headSubstitute are also attached to the second part now. To correct this, after the while loop, we will assign NULL to secondPartPointer->next.
            }
            
        }
        headSubstitute = headSubstitute -> next;
    }

    // cout << placeHolder->value;
    secondPartPointer->next = NULL; // To remove the extra elements attached to headSubstitute
    placeHolder->next = secondPartHead;
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
    
    oddEvenLinkedList(head);

    printf("\nAfter bringing odd and even elements together\n");
    head->display();

    return 0;
    
}