# INDEX

- [INDEX](#index)
- [Recursion](#recursion)
  - [How to understand & approach problems](#how-to-understand--approach-problems)
  - [Recursion Tree](#recursion-tree)
  - [Types of Recurrence Relation](#types-of-recurrence-relation)
    - [Linear Recurrence Relation](#linear-recurrence-relation)
    - [Divide & Conquer Recurrence Relation](#divide--conquer-recurrence-relation)
  - [Tail-end recursive functions](#tail-end-recursive-functions)
    - [Factorial calculator using regular vs. tail recursion](#factorial-calculator-using-regular-vs-tail-recursion)
  - [How function calls work in languages](#how-function-calls-work-in-languages)
  - [Complexity Analysis in Recursive Algorithms](#complexity-analysis-in-recursive-algorithms)
    - [Space Complexity (Auxiliary Space in ACTUALITY)](#space-complexity-auxiliary-space-in-actuality)
- [Dynamic Programming](#dynamic-programming)
- [Arrays](#arrays)
- [Abstract Data Types](#abstract-data-types)
  - [Classification of Abstract Data Types](#classification-of-abstract-data-types)
- [Linked List](#linked-list)
  - [Linked List Implementation in CPP](#linked-list-implementation-in-cpp)
      - [More about `NULL`](#more-about-null)
- [Stack Abstract Data Type](#stack-abstract-data-type)
  - [Stack Operations](#stack-operations)
  - [Applications of Stacks](#applications-of-stacks)
    - [Infix to Postfix/Prefix](#infix-to-postfixprefix)
    - [Reversing Data](#reversing-data)
    - [Backtracking](#backtracking)
    - [Function calls](#function-calls)
  - [Stack Implementation in CPP](#stack-implementation-in-cpp)
- [Queue](#queue)
  - [Queue operations](#queue-operations)
  - [Queue implementation in CPP](#queue-implementation-in-cpp)
  - [Double-Ended Queue](#double-ended-queue)
    - [Input Restricted DE-Queue](#input-restricted-de-queue)
    - [Output Restricted DE-Queue](#output-restricted-de-queue)
- [Time Complexity](#time-complexity)
- [Space Complexity](#space-complexity)
  - [What is Auxiliary Space?](#what-is-auxiliary-space)
  - [Using Auxiliary Space as a criteria instead of Space Complexity.](#using-auxiliary-space-as-a-criteria-instead-of-space-complexity)
- [Sorting Algorithms](#sorting-algorithms)
  - [Stable vs. Unstable Sorting Algorithms](#stable-vs-unstable-sorting-algorithms)
  - [In-place Sorting Algorithms](#in-place-sorting-algorithms)
  - [Bubble Sort (Sinking Sort OR Exchange Sort)](#bubble-sort-sinking-sort-or-exchange-sort)
    - [Time Complexity](#time-complexity-1)
      - [Best case - O(N)](#best-case---on)
      - [Worst case - $O(N^2)$](#worst-case---on2)
  - [Selection Sort](#selection-sort)
    - [Why use Selection Sort?](#why-use-selection-sort)
    - [Time Complexity](#time-complexity-2)
      - [Best case - $O(N^2)$ & Worst case - $O(N^2)$](#best-case---on2--worst-case---on2)
  - [Insertion Sort](#insertion-sort)
    - [Why use Insertion Sort?](#why-use-insertion-sort)
    - [Time Complexity](#time-complexity-3)
      - [Best case - $O(N)$](#best-case---on-1)
      - [Worst case - $O(N^2)$](#worst-case---on2-1)
  - [Merge Sort](#merge-sort)
    - [Steps followed in Merge Sort](#steps-followed-in-merge-sort)
    - [Time Complexity of Merge Sort](#time-complexity-of-merge-sort)
      - [Solving using Recursion Tree Method](#solving-using-recursion-tree-method)
      - [Solving Recurrence Relation using Akra-Bazzi Theorem](#solving-recurrence-relation-using-akra-bazzi-theorem)
      - [Best case - $O(N.\log{N})$  & Worst case - $O(N.\log{N})$](#best-case---onlogn---worst-case---onlogn)
    - [Space Complexity of Merge Sort](#space-complexity-of-merge-sort)
      - [In-place Merge Sort](#in-place-merge-sort)
      - [Not in-place Merge Sort](#not-in-place-merge-sort)
  - [Quick Sort](#quick-sort)
    - [What is Pivot?](#what-is-pivot)
    - [Picking a Pivot](#picking-a-pivot)
    - [Time Complexity](#time-complexity-4)
      - [Recurrence Relation](#recurrence-relation)
      - [Best case - $O(N.\log N)$](#best-case---onlog-n)
      - [Worst case - $O(N^2)$](#worst-case---on2-2)
    - [Why use Quick Sort?](#why-use-quick-sort)
  - [Hybrid Sorting Algorithms](#hybrid-sorting-algorithms)
- [Searching Algorithms](#searching-algorithms)
- [Binary Search](#binary-search)
  - [Time Complexity](#time-complexity-5)
    - [Recurrence Relation](#recurrence-relation-1)
    - [Best case - $O(1)$](#best-case---o1)
    - [Worst case - $O(\log_{2}N)$](#worst-case---olog_2n)
- [Rotated Binary Search](#rotated-binary-search)
- [Tips & Tricks for DSA](#tips--tricks-for-dsa)
  - [Calculating the no. of digits in a number](#calculating-the-no-of-digits-in-a-number)
  - [Calculating `N`th Fibonacci number (regular recursion vs. recurrence relation formula)](#calculating-nth-fibonacci-number-regular-recursion-vs-recurrence-relation-formula)
    - [Regular Recursion](#regular-recursion)
    - [Recurrence Relation Formula](#recurrence-relation-formula)
- [TODO](#todo)

# Recursion

- It helps us in solving bigger/complex problems in a simpler way.
- You can convert recursion solution into iteration and vice versa.
  
  So, solve complex problems using recursion and the convert into recursion to get a more optimized answer.
- Space complexity is higher. For example, if suppose we print 1000 numbers using recursion, 1000 function calls will go into the stack memory, so space complexity will be O(N). 

  On the other hand, if we print 1000 numbers using loops, the task will be done in constant space complexity O(1) because only a loop variable will be required for storage and it will be updated with each iteration.

## How to understand & approach problems

- Identify if you can break down the problem into smaller problems.
- Write down the recurrence relation if needed. 
- Draw the recursion tree.
- About the tree:
  - See the flow of functions, how they are getting in stack.
  - Identify and focus on left tree calls and right tree calls.    
- Draw the tree and pointers again & again using pen and paper.
- Use a debugger to see the flow.
- See how and what type of values are returned at each step. 
- See at which step, the function call actually finally returns a value.

## Recursion Tree

<!-- TODO -->

## Types of Recurrence Relation

### Linear Recurrence Relation

Fibonacci Recurrence Relation
```
Fibo(N) = Fibo(N - 1) + Fibo(N - 2)
``` 

Here, the argument is getting reduced LINEARLY, which is why it is referred to as Linear Recurrence Relation.

This is quite inefficient because the argument is getting reduced very slowly and at a constant rate. In comparison, in Divide & Conquer Recurrence Relations, division/multiplication by a factor results in exponential change which is much faster and efficient.

### Divide & Conquer Recurrence Relation

Divide-and-conquer algorithms consist of:
1. Dividing the problem into smaller sub-problems. 
2. Solving those sub-problems
3. Combining the solutions for those smaller sub-problems to solve the original problem
   
NOTE that the sub-problems should be of the same type as the main problem. 
   
For example, if the main problem is of SORTING an array, the sub-problem can ONLY be SORTING a part of the array.

Recurrence Relation for Binary Search
```
Search(N) = O(1) + Search(N/2)
```
Here, when we try to search for an element using Binary Search, a comparison takes place (in constant time) between the element to be found and the element in the middle of the array, which explains the **O(1)** term.

After it is determined whether the element to be found is greater or lesser than the middle point, a `Search` operation is again initiated at either the first or second half of the array, which explains the **Search(N/2)** term.

Since the search space is DIVIDED by a factor, it is referred to as a Divide & Conquer Recurrence Relation.

## Tail-end recursive functions
 
The tail recursion is basically using the recursive function as the last statement of the function. So when nothing is left to do after coming back from the recursive call, that is called tail recursion. We will see one example of tail recursion.

For example, if suppose we return (2 + function call), that 2 will be stored somewhere in memory when stack frame of the function, from which the value is being returned, is destroyed. 

So instead, we find a way to pass that 2 somehow into the recursive function call.

### Factorial calculator using regular vs. tail recursion

Factorial calculator using regular recursion:
```cpp
#include <iostream>

int fact(int n) {
  if (n == 1) {
    return 1;
  };

  return fact(n - 1) * n;
}
```

In this case, suppose n = 5, the recursive call will make the return value occupy more and more memory until the base condition (n = 1) is reached.

This is how the return value looks with changing **n**.
```
( fact(4) * 5 );
( ( fact(3) * 4 ) * 5 );
( ( ( fact(2) * 3 ) * 4 ) * 5 );
( ( ( ( fact(1) * 2 ) * 3 ) * 4 ) * 5 );
( ( ( ( 1 * 2 ) * 3 ) * 4 ) * 5 );
( ( ( 2 * 3 ) * 4 ) * 5 );
( ( 6 * 4 ) * 5 );
( 24 * 5 );
120;
```

Factorial calculator using tail-end recursion:
```cpp
#include <iostream>

int fact(int n, int result) {
  if(n == 1) {
    return result;
  }
  return fact(n-1, result * n);
}
```

In this case, suppose we take n = 5 and a = 1, the return value looks something like this:
```
fact(5-1,  1 * 5);
fact(4-1,  5 * 4);
fact(3-1, 20 * 3);
fact(2-1, 60 * 2);
120;
```
Here, as we can see, only the parameters of the function that is getting returned are getting changed, until the base condition (n = 1) is reached. So space complexity in the memory stack is O(1). 

## How function calls work in languages

While the function is not finished executing, it will remain in stack.

When a function finishes executing, it is removed from the stack and the flow of the program is returned to the point where the function was called.

## Complexity Analysis in Recursive Algorithms

### Space Complexity (Auxiliary Space in ACTUALITY)

![](/images/recursive-space-complexity.jpg)

As the recursive functions are calling itself again and again, addresses are added into stack. So, if the function is called **N** times recursively, it will take $O(N)$ auxiliary space.

# Dynamic Programming

Dynamic Programming is a technique in computer programming that helps to efficiently solve a class of problems that have overlapping subproblems and optimal substructure property.

If any problem can be divided into sub-problems, which in turn are divided into smaller sub-problems, and if there are overlapping among these subproblems, then the solutions to these subproblems can be saved for future reference. 

In this way, efficiency of the CPU can be enhanced. This method of solving a solution is referred to as dynamic programming.

# Arrays

An array is a homogenous data structure: all elements are of the same type. Also, the elements of an array are stored in adjacent memory locations. 

Because each cell has the same type (and thus the same size), and because the cells are adjacent in memory, it is possible to quickly calculate the address of any array cell, given the address of the first cell, which is why arrays are good for RANDOM ACCESS of elements.

Say we allocate memory for an array of `N` elements (the total number of cells of the array must be defined beforehand), where the elements of the array are of a type that has a size of `b` bytes (e.g. a C++ int has a size of 4 bytes), and the resulting array is allocated starting at memory address `x`. 

Using 0-based indexing:
- the element at index `i = 0` is at memory address `x`
- the element at index `i = 1` is at memory address `x + b`
- the element at index `i` is at memory address `x + bi`. 

Below is the same example array, where the number inside each cell is the index of that cell, and the number below each cell is the memory address at which that cell begins.

![](images/array.png)

# Abstract Data Types

An Abstract Data Type specifies:
- Data stored
- Operations on the data
- Error conditions associated with operations

## Classification of Abstract Data Types

![](./images/adt-classification.png)

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

Stack is an ADT that manages data elements linearly but provides access to only one end i.e., data element can be inserted and removed from one end only.

It is a Last-in-First-Out data structure.

It consists of a an array whose size is set by the user and can't be changed along with a top pointer to point to the 'top of the stack' or the last filled element. 

## Stack Operations

- `create()` - 
- `push()` - for adding an element onto the top of the stack.
- `pop()` - for deleting an element from the top of stack and returning the element. -1 is returned when `pop()` fails.
- `peek()` - access the element at the top of the stack.
- `isempty()` - for checking whether the stack is empty before trying to pop. 
- `isfull()` - for checking whether the stack is full before trying to add an element.

## Applications of Stacks

### Infix to Postfix/Prefix



### Reversing Data

We can use stacks to reverse data. (example: files, strings).  Very useful for finding palindromes.

### Backtracking

It is an algorithmic technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point in time.

### Function calls

Stacks are used to implement function calls by creating a **stack frame** in memory where local variables are stored.

  As the scopes of the variables end, they are one-by-one popped out from the stack, with the return address at the bottom, after which the stack frame is removed from memory.
  
  Most compilers implement function calls by using a stack.
- Arithmetic expression evaluation: An important application of stacks is in parsing.
  
  In high level languages, infix notation can't be used to evaluate expressions. A common technique is to convert a infix notation into postfix notation, then evaluating it.

TODO: Understand java bytecode is evaluated on virtual stack based processor

## Stack Implementation in CPP

We can start entering elements from the end or the beginning of the array.

We usually implement the `top` pointer in a simple manner where `top` is just an integer with a value of the index of topmost element. The value of `top` is kept as -1 if the stack is empty. 

Stack implementation using Linked List

# Queue

- The `front` pointer points to the oldest element in the queue
- The `rear` pointer points to the newest element in the queue

- front is actually the first element of the array
- rear is actually the last element of the array

## Queue operations

- `isEmpty()` 
- `isFull()`
- `enQueue()`
- `deQueue()`
- `printQueue()`

## Queue implementation in CPP

It is opposite to the implementation of stacks using arrays. In stacks, the beginning of the array represented the bottom of the stack for easy pushing and popping.

Here the beginnning of the array is considered as the front of the queue over here.

The implementation of the `front` and `rear` pointers is usually kept simple by keeping them as integers storing values of indices.

Initially, `front` = -1 and `rear` = -1.

When one element is queued, front is -1 and rear is 0.

Whenever `front` = `rear`, queue is empty.


- Using arrays: Here, enqueueing and dequeueing is an O(1) operation but when the queue isn't full and an element has to be enqueued then shifting has to take place which is an O(n) operation.
- Using linked list: Here, both enqueueing and dequeueing is an O(1)
- Using other ADTs

## Double-Ended Queue

A double-ended queue is similar to a [Queue ADT](#queue) with the added functionality of being able to enqueue and dequeue from both ends of the queue.

DE-queues can further be classified into two types:

### Input Restricted DE-Queue

In restricted INPUT DE-Queue, enqueueing from front is NOT permitted, however dequeueing from end is permitted.

### Output Restricted DE-Queue

In restricted OUTPUT DE-Queue, dequeueing from end is NOT permitted, however enqueueing from front is permitted.

# Time Complexity 

![Time-Complexity-1.png](./images/Time-Complexity-1.png)

![Time-Complexity-2.png](./images/Time-Complexity-2.png)

![Time-Complexity-3.png](./images/Time-Complexity-3.png)

![Time-Complexity-4.png](./images/Time-Complexity-4.png)

# Space Complexity 

Space Complexity of an algorithm is the total space taken by an algorithm withm respect to the input size. Space complexity includes both Auxiliary space and space used by input.

## What is Auxiliary Space?

Auxiliary space is the extra space or the temporary space used by an algorithm.

## Using Auxiliary Space as a criteria instead of Space Complexity.

We can't really do anything about the input we are taking, but we can choose what algorithm to use, depending on which takes up the least amount of memory.

For example, if we want to compare standard sorting algorithms on the basis of space, the auxiliary space would be a better criteria than Space Complexity. Merge Sort uses O(n) auxiliary space, Insertion sort and Heap Sort use O(1) auxiliary space. 

Space complexity of all these sorting algorithms is O(n) though.

# Sorting Algorithms

An in-place algorithm is an algorithm that does not need an extra space and produces an output in the same memory that contains the data by transforming the input 'in-place'. 

However, a small constant extra space used for variables is allowed.

## Stable vs. Unstable Sorting Algorithms

![](./images/stable-and-unstable-sorting.png)

**Stable sorting algorithms** preserve the relative order of equal elements, while **unstable sorting algorithms** don't. 

In other words, stable sorting maintains the position of two equals elements relative to one another.

## In-place Sorting Algorithms

An in-place sorting algorithm uses constant space for producing the output (modifies the given array only). 

It sorts the list only by modifying the order of the elements within the list. 

For example, [Insertion Sort](#insertion-sort) and [Selection Sort](#selection-sort) are in-place sorting algorithms as they do not use any additional space for sorting the list. 

A typical implementation of [Merge Sort](#merge-sort) is not in-place, also the implementation for counting sort is not an in-place sorting algorithm

## Bubble Sort (Sinking Sort OR Exchange Sort)

This sorting technique is just like bubbles in a water column, coming up one by one. 

![Bubble Sort](./images/bubble-sort.ppm)

In bubble sort, with each subsequent pass, the next largest element is settled at the end of the array.

In each subsequent pass, we reduce our domain of comparison by 1 because 1 additional element is placed in its sorted position after each pass.

It is a [stable sorting algorithm](#stable-vs-unstable-sorting-algorithms) as well as an [in-place sorting algorithm](#in-place-sorting-algorithms).

### Time Complexity

#### Best case - O(N)

When the array is already sorted, only one pass is run, which includes **(N-1)** comparisons.

So, it takes minimum amount of time (order of N).

This is implemented using a `swap` variable which keeps track of how many swaps occur in a single pass. 
```cpp
if(swap == 0) { 
    break;
}
else { 
    swap = 0;
};
```
- If no swaps occur in a particular pass, then it is clear that the array is already sorted and we need not make any more passes.
- Else, we reset the swap variable for the next pass.

#### Worst case - $O(N^2)$

When the array is already sorted, but in reverse order.

![](/images/bubble-sort-worst.jpg)

## Selection Sort

The selection sort algorithm sorts an array by repeatedly finding the maximum element (considering ascending order) from unsorted part and putting it at the end. 

The algorithm maintains two subarrays in a given array.

- The subarray which is already sorted. 
- Remaining subarray which is unsorted.

It is an [unstable sorting algorithm](#stable-vs-unstable-sorting-algorithms) as well as an [in-place sorting algorithm](#in-place-sorting-algorithms).

### Why use Selection Sort?

- The good thing about selection sort is that never makes more than $O(N^2)$ swaps so it can be useful when memory write is a costly operation.
- It performs well for smaller values of N. 

### Time Complexity

The average time complexity is $O(N^2)$.

![](/images/selection-sort-avg.jpg)

#### Best case - $O(N^2)$ & Worst case - $O(N^2)$

In both the Best and Worst case, all the comparisons will still be made for finding the index position of the next largest element so the time complexity will be almost the same as the average case.

Minor difference can be no time spent in swap operations in the Best Case and all possible swap operations performed in Worst Case.

## Insertion Sort

Insertion sort is a simple sorting algorithm that works similar to the way you sort playing cards in your hands. The array is virtually split into a sorted and an unsorted part. Values from the unsorted part are picked and placed at the correct position in the sorted part.

Putting the first index position of the unsorted array into the correct position in the sorted array. 

The problem encountered here is that we need to shift elements to the right, in order to insert the element that has to be sorted into the correct position.

After every pass, a larger and larger portion of the array would be sorted.

It is a [stable sorting algorithm](#stable-vs-unstable-sorting-algorithms) as well as an [in-place sorting algorithm](#in-place-sorting-algorithms).

### Why use Insertion Sort?

- It is adaptive as steps get reduced if array is already sorted.
- It is used for smaller values of N. 
- It works well when array is partially sorted.
- It takes part in hybrid sorting algorithms.

### Time Complexity

#### Best case - $O(N)$

When the array is already sorted, only comparison takes place per pass. Number of passes is **(N-1)** so total comparisons are also **(N-1)**

So, it takes minimum amount of time (order of N).

![](/images/insertion-sort.jpg)

#### Worst case - $O(N^2)$

When the array is already sorted, but in reverse order.

![](/images/insertion-sort-worst.jpg)

## Merge Sort

### Steps followed in Merge Sort

- Divide array into 2 parts
- Get both parts sorted via recursion
- Merge the sorted parts

### Time Complexity of Merge Sort

#### Solving using Recursion Tree Method

![](./images/merge-sort-recursion-tree-1.jpg)
![](./images/merge-sort-recursion-tree-2.jpg)

#### Solving Recurrence Relation using Akra-Bazzi Theorem

![](./images/merge-sort-recurrence-relation-solve.jpg)

#### Best case - $O(N.\log{N})$  & Worst case - $O(N.\log{N})$

The time complexity of MergeSort is $O(N.\log{N})$ in all the 3 cases (worst, average and best) as the merge sort algorithm always divides the array into two halves and takes linear time to merge two halves.

### Space Complexity of Merge Sort

![](./images/merge-sort-space-complexity-common.jpg)

#### In-place Merge Sort

![](images/merge-sort-inplace-space-comp.jpg)

#### Not in-place Merge Sort

![](images/merge-sort-notinplace-space-comp-1.jpg)
![](images/merge-sort-notinplace-space-comp-2.jpg)

## Quick Sort

It is a [Divide and Conquer](#divide--conquer-recurrence-relation) algorithm. 

It uses the concept of a [**pivot**](#what-is-pivot) for sorting.

It is an [unstable sorting algorithm](#stable-vs-unstable-sorting-algorithms) as well as an [in-place sorting algorithm](#in-place-sorting-algorithms).

### What is Pivot?

Quick Sort works by selecting an element called a pivot and placing the pivot element at the correct position after each pass.

Once an element is chosen as a pivot:
- All elements lesser than the pivot will be on the LHS of the pivot.
- All elements greater than the pivot will be on the RHS of the pivot.

It is NOT necessary that the two parts of the array (LHS and RHS of pivot) are sorted. Only the pivot is at its correct position within the array.

### Picking a Pivot

There are many different versions of quick sort that pick [pivot](#what-is-pivot) in different ways. 

- Always pick the first element as a pivot.
- Always pick the last element as a pivot (implemented below)
- Pick a random element as a pivot.
- Pick median as the pivot.

We use 4 variables in quick sort:
- `start` : This is the starting index of the partitioned array.
- `end` : This is the ending index of the partitioned array.
- `left` : This is a dynamic variable used to swap elements which are greater than the pivot, but are present on the LHS of the pivot.
- `right` : This is a dynamic variable used to swap elements which are less than the pivot, but are present on the RHS of the pivot.

### Time Complexity

#### Recurrence Relation

![](/images/quick-sort-recurrence-relation.jpg)

#### Best case - $O(N.\log N)$

![](/images/quick-sort-best-case-1.jpg)
![](/images/quick-sort-best-case-2.jpg)

#### Worst case - $O(N^2)$

![](/images/quick-sort-worst-case-1.jpg)
![](/images/quick-sort-worst-case-2.jpg)


### Why use Quick Sort?

- An advantage Quick Sort has over [Merge Sort](#merge-sort) is that if an array is already sorted, Merge Sort will still run till the base condition (the sub-array has only 1 element) is reached, and merge the sub-arrays back to the original array by comparing the elements of the sub-arrays. 
  
  Here, that will NOT happen. The only process happening will be incrementation of the left and right pointers.
  
  TODO: How?

- For arrays, Quick Sort is preferred over Merge Sort, because Merge Sort takes O(N) extra space.

  But, in the case of linked lists, memory allocation is random so Merge Sort is preferred over Quick Sort. 
  TODO: Why?

## Hybrid Sorting Algorithms

The sorting algorithm used by the in-built `sort()` method in Python is Tim Sort which is a combination of [Merge Sort](#merge-sort) and [Insertion Sort](#insertion-sort) (works well with partially sorted arrays).

TODO : Understand in detail

# Searching Algorithms

# Binary Search

## Time Complexity

### Recurrence Relation

<!-- TODO: Do in notebok and add picture -->
![]()

### Best case - $O(1)$

This is when the target element is found in the very FIRST pass.

<!-- TODO: Do in notebok and add picture -->
![]()

### Worst case - $O(\log_{2}N)$

This is when the target element is found in the very LAST pass.

<!-- TODO: Do in notebok and add picture -->
![]()


# Rotated Binary Search

TODO

# Tips & Tricks for DSA

## Calculating the no. of digits in a number

We can make use of the function: 

$f(x) = floor( \log_{10}x ) + 1$

where x is the number whose digits we want to count.

This is because looking at the value of $\log_{10}x$, 

$x = 1$, 
- $\log_{10}1 = 0$
- $\log_{10}1 + 1 = 1$

$x = 7$
- $\log_{10}7 = 0.845...$
- $floor( \log_{10}7 ) + 1 = 1$

$x = 10$
- $\log_{10}10 = 1$
- $\log_{10}10 + 1 = 2$

$x = 47$
- $\log_{10}47 = 1.672...$
- $floor( \log_{10}47 ) + 1 = 2$

$x = 100$
- $\log_{10}100 = 2$, 
- $\log_{10}100 + 1 = 3$

The value of $\log_{10}x$ increases by an integer only when the number crosses an integer power of 10, like $10^0 = 1, 10^1 = 10, 10^2 = 100$, etc... 

This is also when an extra digit is added to the number.

So, this is an ideal method for calculating the number of digits of a number.


## Calculating `N`th Fibonacci number (regular recursion vs. recurrence relation formula)

### Regular Recursion

![](/images/fibonacci-example.jpg)

### Recurrence Relation Formula

See the code [here](practice-questions/07-fibonacci-formula-calc.cpp).

![](/images/fibonacci-recurrence-relation-1.jpg)
![](/images/fibonacci-recurrence-relation-2.jpg)

# TODO 
