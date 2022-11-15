#include <iostream>

using namespace::std;

class Stack {
private:
    int size;
    int top; 
    int *stackArray;

public:
    // Parameterized Constructor
    Stack(int size) {
        this -> size = size;
        top = -1;
        stackArray = new int[size];
    }; 

    // Default Constructor
    Stack() {
        size = 10;
        top = -1;
        stackArray = new int[size];
    }; 

    // Function for checking if the stack is empty. Truth value of function call can be checked.
    int isEmpty() {
        if( top == -1 ) {
            return 1;
        }
        else {
            return 0;
        };
    }; 

    // Function for checking if the stack is full. Truth value of function call can be checked.
    int isFull() {
        if( ( top + 1 ) == size ) {
            return 1;
        }
        else {
            return 0;
        };
    };

    // Function for inserting an element in the stack
    void stackPush (int element) {
        if ( isFull() ) {
            std::cout << "Stack overflow. Element not inserted.\n";
        }
        else {
            ( top )++;
            *( stackArray + top ) = element;
        };
    };

    // Function for removing and returning the top element
    int stackPop () {
        if ( isEmpty() ) {
            std::cout << "Stack underflow. Stack is empty.\n";
            return -1;
        }
        return ( *( stackArray + (top--) ) );
    };

    // Function that returns the topmost element of the stack.
    int stackPeek () {
        return ( *( stackArray + top ) );
    };
};

class Queue {
int size;

int front;
int rear;

int *queueArray;

public:

    // Parameterized Constructor
    Queue (int size) {            
        this -> size = size;
        front = -1;
        rear = -1;
        queueArray = (int*)( malloc( size * sizeof(int) ) );
    };

    // Default Constructor
    Queue () {            
        this -> size = 10;
        front = -1;
        rear = -1;
        queueArray = (int*)( malloc( size * sizeof(int) ) );
    }; 

    int isEmpty() {
        if(front == rear) {
            return 1;
        }
        else {
            return 0;
        };
    }

    int isFull() {
        if( ( rear - front )  == size ) {
            return 1;
        }
        else {
            return 0;
        };
    }

    void enQueue(int element) {
        if( isFull() ) { // Here, we have already checked whether the queue is full or not.
            printf("Queue is full. Element not inserted\n");
        }
        else {
            if( (rear + 1) == size) { // This is the condition where, due to dequeueing, the front of the queue is empty but the queue is full at the end, so we have to shift the elements to the front in order to make space for enqueueing.
                int insertIndex = 0;

                for(int index = (front + 1); index <= rear ; index++) {
                    queueArray[insertIndex] = queueArray[index];
                    insertIndex++;
                }
                
                int size = rear - front;
                front = -1;
                rear = size - 1; // Since (`size` - 1) - (-1) = `size`, so size of the queue, calculated using the front and rear pointers remains same
            };

            rear++;
            *( queueArray + rear ) = element;
        };
    }

    int deQueue() {
        if( isEmpty() ) { 
            printf("Queue is empty. Dequeueing failed\n");
            return -1;            
        }

        // We increment the front pointer before accessing the element that is now deQueued, since front anyways stores the index before the first element.
            return *( queueArray + (++front) );     
    }

    void printQueue() {
        if ( isEmpty() ) {
            printf("Queue is empty.");
        }
        else {
            int elementCt = 1;
            printf("From the front:\n");
            for(int index = ( front + 1 ); index <= (rear) ; index++) {
                printf("Element %d is %d\n", elementCt, queueArray[index] );
                elementCt++;
            };
        };
    }

};


//**************BFS(breadth-first search) code**************//

void breadthFirstSearch(int sourceVertex, int noOfVertices, int** adjacencyMatrix) {
// void breadthFirstSearch(int sourceVertex, int noOfVertices, int adjacencyMatrix[4][4]) {
    Queue queue(noOfVertices - 1);
    int dequeuedElement;
    
    bool visited[noOfVertices - 1];
    for(int index = 0; index < noOfVertices; index++) {
        visited[index] = false;
    }    

    queue.enQueue(sourceVertex);
    visited[sourceVertex] = true;


    while( !queue.isEmpty() ) {
        
        
        dequeuedElement = queue.deQueue(); 
        printf("%d, ", dequeuedElement);
        
        // All edges from dequeuedElement to all other vertices first checked, and visited by enqueuing them. After that, in the next iteration of the while loop, we move to the next dequeued element, which is the adjacent element of the last dequeued element. So, this is effectively BFS.
        for(int toVertex = 0; toVertex < noOfVertices; toVertex++) {
            if( (adjacencyMatrix[dequeuedElement][toVertex] != 0) && (visited[toVertex] == false) ) {
                queue.enQueue(toVertex);
                visited[toVertex] = true; // As we check which adjacent vertices (represented in each iteration of the for-loop by toVertex) have NOT been visited. We enqueue them in the queue, and set their visited value as `true` so that duplicate vertices are not inserted into the queue.    

            }       
        }   
    }
}





//***************DFS(depth-first search) code******************//

void depthFirstSearch(int sourceVertex, int noOfVertices, int** adjacencyMatrix) {
    Stack stack(noOfVertices - 1);
    int poppedElement;

    bool visited[noOfVertices - 1];    
    for(int index = 0; index < noOfVertices; index++) {
        visited[index] = false;
    }    


    stack.stackPush(sourceVertex);

    while(!stack.isEmpty()) {
        
        poppedElement = stack.stackPop();
        printf("%d, ", poppedElement);

        // Taking the case of the first element, it is popped from the stack, and all its adjacent elements are found using for-loop and pushed onto the stack, one-by-one. In the next iteration, the last element pushed onto the stack is popped and its children are found and pushed onto the stack. In the next to next iteration, one of the children's adjacent elements are found. This way Depth Traversal is happening, since the elements instead of getting queued are getting pushed onto stack and popped, resulting in the last child of the first adjacent node getting popped, instead of the other adjacent element of the source vertex getting popped.
        for(int toVertex = 0; toVertex < noOfVertices; toVertex++) {

            if( (adjacencyMatrix[poppedElement][toVertex] != 0) && (visited[toVertex] == false) ){
                
                if(visited[toVertex] == false) {
                    stack.stackPush(toVertex);
                    visited[toVertex] = true; // As we check which adjacent vertices (represented in each iteration of the for-loop by toVertex) have NOT been visited. We push them into the stack, and set their visited value as `true` so that duplicate vertices are not pushed onto the stack, resulting in a vertex getting visited twice in DFS.
                }
                
            }
        }
    }
}




int main() {

    int noOfVertices;
    cout << "Enter the number of vertices : ";
    cin >> noOfVertices;

    // int noOfVertices = 4;

    int** adjacencyMatrix = new int*[noOfVertices - 1];

    // int adjacencyMatrix[4][4] = {{0,1,1,0},{0,0,1,0},{1,0,0,1},{0,0,0,1}};

    for(int index = 0; index < noOfVertices; index++) {
        adjacencyMatrix[index] = new int[noOfVertices - 1];
    }
    
    for(int fromVertex = 0; fromVertex < noOfVertices; fromVertex++) {
        for(int toVertex = 0; toVertex < noOfVertices; toVertex++) {
            printf("Enter 1 if there is an edge from  VERTEX `%d` to VERTEX `%d`. Else enter 0 : ", fromVertex, toVertex);
            scanf("%d", &adjacencyMatrix[fromVertex][toVertex]);
        }
    }

    printf("THE ADJACENCY MATRIX IS\n");

    for(int fromVertex = 0; fromVertex < noOfVertices; fromVertex++) {
        for(int toVertex = 0; toVertex < noOfVertices; toVertex++) {
            printf("%d, ", adjacencyMatrix[fromVertex][toVertex]);
        }
        printf("\n");
    }

    char continueChoice;

    do {
        int choice;
        cout << "\nMENU";
        cout << "\n1.B.F.S";
        cout << "\n2.D.F.S";
        cout << "\nEnter your choice : ";
        cin >> choice;
        
        int sourceVertex;
        cout << "Enter the source vertex : ";
        cin >> sourceVertex;

        switch(choice) {
            case 1 : 
                breadthFirstSearch(sourceVertex, noOfVertices, adjacencyMatrix);
                break;
            case 2:
                depthFirstSearch(sourceVertex, noOfVertices, adjacencyMatrix);
                break;
        }
        
        cout << "\n\nPress 'y' to continue OR any other key to end the program : ";
        cin >> continueChoice;

    } while( (continueChoice == 'y') || (continueChoice == 'Y') );

}






