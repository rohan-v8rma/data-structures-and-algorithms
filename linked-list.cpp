#include <iostream>

// Each node contains a value and an address pointing to the next node
struct node {
    int value;
    struct node* next = NULL; // This default value doesn't affect the work/non-working code in any way
};

// Function for filling the elements of a linked list
struct node* createLinkedList(int elementCt) {
    
    struct node* head = (struct node*)(malloc(sizeof(struct node))); // Note that the left side of this assignment just creates and allocates memory for a pointer (8 bytes). We use malloc on the right side in order to allocate memory for the 'node' structure variable the pointer will point to.
    struct node* tempPtr;
    tempPtr = head; // Since we have assigned addresses stored in head to tempPtr, tempPtr can be incremented and used to assign values for nodes, without affecting the address `head` is pointing to

    for(int index = 0; index < elementCt; index++) {
        printf("Enter element %d : ", index + 1);
        scanf("%d", &(tempPtr -> value));
        // printf("%d\n", tempPtr -> value);

        if (index == (elementCt - 1)) {
            tempPtr = NULL;
        }
        else {
            //! Not working, where we first change tempPtr and then allocate memory for the new node
            // tempPtr = tempPtr -> next; // We dereference tempPtr and access the pointer to the next node. We assign this value to the tempPtr itself.          
            
            // printf("%p\n", tempPtr); // Observe how (nil) is shown everytime.
            
            // tempPtr = (struct node*)(malloc(sizeof(struct node))); // This statement allocates memory for the value that the next pointers of the nodes will point to
            
            //* Working code, where we first allocate memory in next, then change tempPtr
            
            (tempPtr -> next) = (struct node*)(malloc(sizeof(struct node))); 
            tempPtr = tempPtr -> next;          

            // tempPtr = new struct node; // another way of allocating memory for the new node.
        };
    };

    return head;
}

//Function for printing the linked list
void printLinkedList(struct node* head) {
    struct node* tempPtr = head;
    int elementCt = 1;

    while (tempPtr != NULL) { // this condition helps us stop printing when the tempPtr points to the NULL terminator of the linked list.
        
        std::cout << "Element "<< elementCt << " is : " << (tempPtr->value) << "\n";
        elementCt++;
             
        tempPtr = tempPtr -> next; // this moves the tempPtr to the next node.
    };
}
int main() {
    struct node* head;

    int elementCt;
    std::cout << "Enter element count : ";
    std::cin >> elementCt;

    head = createLinkedList(elementCt);
    printLinkedList(head);
}