#include <iostream>

// Each node contains a value and an address pointing to the next node
class node { // Using classes helps us to keep our `value` and `next` pointer private
    private:
        int value;
        node* next;
    public:
        node() {
            value = -1;
            next = NULL;
        };
        
        node(int elementCt) { //this helps in creating a linked list with `elementCt` number of elements
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
            while(tempPtr != NULL) {
                printf("Element %d : %d\n", elementNo, tempPtr -> value);
                tempPtr = tempPtr -> next;
            }
        }
};
int main() {
    node* head = new node(5); // When `new` is used to allocate memory for a C++ class object, the object's constructor is called after the memory is allocated.
    
    head -> display();

    return 0;
}

