#include <iostream>
using na
{
    
} // na


int fibonacci(int elementNo) {
    cout << "Computing fibonacci term number (" << elementNo << ")";
    if(elementNo == 0 || elementNo == 1) {
        return elementNo;
    }


    // cout << "Computing fibonacci term number (" << elementNo << ")";
    return (fibonacci(elementNo - 1) + fibonacci (elementNo - 2));
}

int main() {
    cout << fibonacci(4);
}