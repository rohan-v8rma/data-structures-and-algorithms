#include <iostream>
using namespace::std;

class node { // Using classes helps us to keep our `value` and `next` pointer private

    friend node* partitionLinkedList(node* head, double value);

private:
    double value;
    node* next;
public:
    node() {
        value = -1;
        next = NULL;
    };
    
    node(double number) {
        this->value = number;
    };

    node(int elementCt) { //this helps in creating a linked list with `elementCt` number of elements
        node* tempPtr = this;
        for(int elementNo = 1; elementNo <= elementCt; elementNo++) {
            printf("Enter element %d : ", elementNo);
            scanf("%lf", &(tempPtr -> value));
            
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
        while(tempPtr != NULL) {
            printf("Element %d : %.0lf\n", elementNo, tempPtr -> value);
            tempPtr = tempPtr -> next;
            elementNo++;
        }
    }
};

node* partitionLinkedList(node* head, double value) {
    node* newNode = new node(value);
    node* returnList = newNode;

    node* temp;

    while(head != NULL) {
        
        if(head->value >= newNode->value) {
            temp = newNode->next;
            newNode->next = new node(head->value);
            newNode->next->next = temp;
        }
        else {
            temp = returnList;
            returnList = new node(head->value);

            returnList->next = temp;
        }

        head = head->next;

    }

    return returnList;
}

int main() {
    node* head = new node(5); // When `new` is used to allocate memory for a C++ class object, the object's constructor is called after the memory is allocated.

    head = partitionLinkedList(head, 22);

    head->display();

    return 0;
}