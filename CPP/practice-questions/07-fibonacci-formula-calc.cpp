#include <iostream>
#include <math.h>

// This is an O(1) method of finding the Nth fibonacci number. This formula is obtained by solving the recurrence relation of the fibonacci series.

int fibo(int n) {  
    return (int) ( ( pow(5, -0.5) ) * ( pow( ( (1.0 + pow(5, 0.5) ) / 2 ) , n) - pow( ( (1.0 - pow(5, 0.5) ) / 2 ) , n) ) );
}

int main() {
    for(int term = 0; term <= 10; term++) {
        std::cout << fibo(term) << std::endl;
    }

    return 0;
}