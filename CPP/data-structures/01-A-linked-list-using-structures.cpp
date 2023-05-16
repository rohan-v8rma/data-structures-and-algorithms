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

    for(int elementNo = 1; elementNo <= elementCt; elementNo++) {
        printf("Enter element %d : ", elementNo);
        scanf("%d", &(tempPtr -> value));
        // printf("%d\n", tempPtr -> value);

        if (elementNo == elementCt) {
            tempPtr -> next = NULL; //the next pointer of the last element will point to NULL
        }
        else {
            //! Not working, where we first change tempPtr and then allocate memory for the new node. This is
            
            //? It is not working since we are changing the value stored in tempPtr, and it is a local variable, which is not being returned so the changes aren't reflected.
            
            // tempPtr = tempPtr -> next; // We dereference tempPtr and access the pointer to the next node. We assign this value to the tempPtr itself.          
                   
            // tempPtr = (struct node*)(malloc(sizeof(struct node))); // This statement allocates memory for the value that the next pointers of the nodes will point to
            
            //* Working code, where we first allocate memory in next, then change tempPtr
            
            //? It is working because we are de-referencing tempPtr (arrow operator dereferences, then accesses) and accessing the members of the structure it is pointing to. So we are not making changes to the local variable.

            // This is because we need to allocate space for 
            
            (tempPtr -> next) = (struct node*)(malloc(sizeof(struct node))); 
            tempPtr = tempPtr -> next;          

            // tempPtr = new struct node; // another way of allocating memory for the new node.
        };
    };

    return head;
}

// Function for printing the linked list
void printLinkedList(struct node* head) { // We pass the head pointer by-reference since in most cases when we are passing pointers, we are dereferencing them and modifying the value stored at the address they point to. Here, we are potentially changing the value of the head pointer itself, which is why if we pass-by-value, the changes won't be reflected. 
    struct node* tempPtr = head;
    int elementCt = 1;

    while (tempPtr != NULL) { // this condition helps us stop printing when the tempPtr points to the NULL terminator of the linked list.
        
        std::cout << "Element "<< elementCt << " is : " << (tempPtr->value) << "\n";
        elementCt++;
             
        tempPtr = tempPtr -> next; // this moves the tempPtr to the next node.
    };
}

// Function for adding elements in the linked list 
void addElement(struct node* &head) {  // We are passing head by reference so that whatever changes happen to the head pointer are reflected outside

    int addIndex; // let 0
    printf("Index position of node: ");
    scanf("%d", &addIndex);

    struct node* tempPtr = head;
    struct node* beforePtr = new struct node;
    beforePtr -> next = tempPtr;    

    for(int index = 0; index < addIndex; index++) {
        tempPtr = tempPtr -> next;
        beforePtr = beforePtr -> next;
    };
    // printf("%p\n", beforePtr);
    // printf("%p\n", tempPtr);
    tempPtr = new struct node;
    // printf("%p\n", beforePtr);
    // printf("%p\n", tempPtr);
    printf("Value of node: ");
    scanf("%d", &(tempPtr -> value));

    if (addIndex > 0) {
        tempPtr -> next = beforePtr -> next;
        beforePtr -> next = tempPtr;
    }
    else {
        tempPtr -> next = head;
        head = tempPtr;    
    }; // this if else block is need since if the inserted element is to be at 0th index, head has to point directly to the next element, instead of the new element getting pointed to by some other element.
}

// Function for deleting elements from the linked list
void delElement(struct node* &head) {
    int delIndex;
    printf("Index position of node: ");
    scanf("%d", &delIndex);
    if(delIndex == 0){
        head = head -> next;
    }
    else {
        struct node* tempPtr = head;

        for(int index = 0; index < (delIndex - 1); index++) { // if delIndex is 1, we need not move `tempPtr` from `head`, we can simply assign tempPtr -> next = (tempPtr -> next) -> next; 
            tempPtr = tempPtr -> next;
        }
        tempPtr -> next = (tempPtr -> next) -> next;
    }
}

int main() {
    struct node* head;

    int elementCt;
    std::cout << "Enter element count : ";
    std::cin >> elementCt;

    head = createLinkedList(elementCt);

    // Calling the function for adding an element.
    addElement(head);

    // Calling the function for deleting an element.
    // delElement(head);

    // Calling the function for printing the linked-list.
    printLinkedList(head);
}