#include <iostream>
using namespace::std;

// We split the problem into smaller problems (recursion)

void towerOfHanoi(int diskNumber, char from_rod, char to_rod, char aux_rod) {
    if (diskNumber == 1) {
        printf("MOVED disk 1 from (rod %c) to (rod %c)\n", from_rod, to_rod);
        return;
    }

    towerOfHanoi(diskNumber - 1, from_rod, aux_rod, to_rod);
    
    printf("Moved disk %d from (rod %c) to (rod %c)\n", diskNumber, from_rod, to_rod);

    towerOfHanoi(diskNumber - 1, aux_rod, to_rod, from_rod);

}

int main() {
    char from_rod = 'A';
    char using_rod = 'B';
    char to_rod = 'C';

    towerOfHanoi(4, from_rod, to_rod, using_rod);

    return 0;
}