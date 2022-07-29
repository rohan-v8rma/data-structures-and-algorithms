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
            for(int index = 0; index < elementCt; index++) {
                printf("Enter element %d : ", index + 1);
                scanf("%d", &(tempPtr -> value));
                tempPtr = tempPtr -> next;
                tempPtr = new node;
            };
        };
};
int main() {
    node* head = new node(5); // When `new` is used to allocate memory for a C++ class object, the object's constructor is called after the memory is allocated.
    return 0;
}

