#include <iostream>

struct productDetails {
    int productId;
    double price;
};

// Each node contains a value and an address pointing to the next node
struct node {
    struct productDetails values;
    struct node* next = NULL; 
};

// Function for filling the elements of a Queue
struct node* createQueue(int elementCt) {
    
    struct node* head = (struct node*)(malloc(sizeof(struct node)));
    struct node* tempPtr;
    tempPtr = head;

    for(int index = 0; index < elementCt; index++) {
        printf("For element %d \n", index + 1);
        printf("product ID : ");
        scanf("%d", &( (tempPtr -> values).productId));
        printf("price : ");
        scanf("%lf", &( (tempPtr -> values).price));

        if (index == (elementCt - 1)) {
            tempPtr -> next = NULL; 
        }
        else {
            (tempPtr -> next) = (struct node*)(malloc(sizeof(struct node))); 
            tempPtr = tempPtr -> next;          
        };
    };

    return head;
}

// Function for printing the linked list
void printQueue(struct node* head) {
    struct node* tempPtr = head;
    int elementCt = 1;

    while (tempPtr != NULL) {
        printf("%d, ", ( (tempPtr -> values).productId));
        printf(" %lf\n", ( (tempPtr -> values).price));
        elementCt++;
             
        tempPtr = tempPtr -> next; 
    };
}

// Function for adding elements in the queue
void enQueue(struct node* head) {  

    struct node* tempPtr = head;

    while(tempPtr -> next != NULL) {
        tempPtr = tempPtr -> next;
    }
    tempPtr -> next = new struct node;
    tempPtr = tempPtr -> next;

    printf("For new element: \n");
    printf("product ID : ");
    scanf("%d", &( (tempPtr -> values).productId));
    printf("price : ");
    scanf("%lf", &( (tempPtr -> values).price));
}

// Function for dequeueing the elements of the queue
void deQueue(struct node* &head) {
    head = head -> next;
}

int main() {
    struct node* head;

    int elementCt;
    std::cout << "Enter element count : ";
    std::cin >> elementCt;

    head = createQueue(elementCt);

    
    enQueue(head);   
    
    printQueue(head);
    
    printf("After deletion : \n");
    deQueue(head);

    // Calling the function for printing the linked-list.
    printQueue(head);
}