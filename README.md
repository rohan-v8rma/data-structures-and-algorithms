- [Linked List](#linked-list)
  - [Linked List Implementation in CPP](#linked-list-implementation-in-cpp)
      - [More about `NULL`](#more-about-null)
- [Stack Abstract Data Type](#stack-abstract-data-type)
  - [Stack Operations](#stack-operations)
- [Applications of Stacks](#applications-of-stacks)
- [Stack Implementation](#stack-implementation)
- [Queue](#queue)

# Linked List

There is no need for contiguous memory like in an array.

Each node consists of 2 things:
- Value stored in node. 
- Pointer pointing to memory of next node.

Head of linked list points to the first node.

The last node of the linked list points to a null value.

## Linked List Implementation in CPP

We define a `struct` containing an number as well as a pointer to the same `struct` (Self Referential structure).

We create a pointer of this `struct` type which is the 'head' pointer of the linked list.

The pointer of the last node is assigned the defined constant [`NULL`](#more-about-null), used as an end-marker for the linked list.

#### More about `NULL`

`NULL` is a defined constant that can be implicitly/explicitly type-casted to any pointer type. 

It is a pointer that doesn't point to any valid data object. 

Considering this code:
```cpp
#include <iostream>

int main() {
    int* integer = NULL;
    std::cout << integer << std::endl;
    printf("%p", integer);
    
    return 0;
}
```
The output of the following code will be:
```
0
(nil)
```

It is always a good practice to assign the pointer NULL to a pointer variable in case you do not have exact address to be assigned. 

This is done at the time of variable declaration. 

A pointer that is assigned `NULL` is called a null pointer.

# Stack Abstract Data Type

Last-in-First-Out

Abstract data type

An Abstract Data Type specifies:
- Data stored
- Operations on the data
- Error conditions associated with operations



## Stack Operations

push()
pop()
create()
isempty()
isfull()

Before inserting an element in the stack, we check `isfull()`
Before deleting an element from the stack, we check `isempty()`

# Applications of Stacks

reversing
backtracking
function calls
java bytecode is evaluated on virtual stack based processor

# Stack Implementation

We can start entering elements from the end or the beginning of the array (ONLY ONE, DON'T BE GREEDY).
We need a top pointer along with a stack to point to the 'top of the stack' or the last filled element.

Stack implementation using Linked List

# Queue

- The `front` index points to the oldest element in the queue
- The `rear` index points to the newest element in the queue

- front is actually the first element of the array
- rear is actually the last element of the array


When the queue is empty, `front = -1`.

When the queue has one element, both front and rear are 0.