#include <iostream>

void descending(int n) {
    if(n == 0) {
        return;
    }

    printf("%d\n", n); // Here, the number is printed before the recursive call. So, this results in descending order of printing

    descending(n - 1);
}

void ascending(int n) {
    if(n == 0) {
        return;
    }

    ascending(n - 1);

    printf("%d\n", n); // Here, the number is printed after the recursive call. So, the printing takes place as the stack frame of main is emptied. This leads to printing occuring after the base case is hit. This gives us ascending order of printing.
}

int main() {
    ascending(5);
    
    printf("\n");
    
    descending(5);
}