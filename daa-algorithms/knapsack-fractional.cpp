#include <iostream>
using namespace::std;

void printArr(int size, float* arr) {
    printf("{");
    for(int index = 0; index < size; index++) {
        printf("%.0f, ", arr[index]);
    }
    printf("}\n");
}

// void swapNumbers(float* array, int index1, int index2) {
//     float temp = array[index1];
//     array[index1] = array[index2];
//     array[index2] = temp;
// }

void insertionSortBoth(int size, float* weightArr, float* profitArr) {
    for(int passes = 1; passes < (size); passes++) {
        for(int pass = passes; pass >= 1; pass--) {
            
            // This results in the most dense items towards the start of the array
            if( (profitArr[pass] / weightArr[pass]) > (profitArr[pass-1] / weightArr[pass-1]) ) {
                swap(profitArr[pass], profitArr[pass - 1]);
                swap(weightArr[pass], weightArr[pass - 1]);   
            }
        }
    }
}

int main() {
    float leftoverKnapsackCapacity;
    int numOfItems;
    float* itemWeight;
    float* itemProfit;
    float* includeFraction;

    cout << "How many items in knapsack? : ";
    cin >> numOfItems;

    cout << "Knapsack weight capacity? : ";
    cin >> leftoverKnapsackCapacity;

    itemWeight = new float[numOfItems];
    itemProfit = new float[numOfItems];
    includeFraction = new float[numOfItems];

    // Initializing the item arrays with random values.
    for(int index = 0; index < numOfItems; index++) {
        itemProfit[index] = (index + 5) * (index + 7);
        itemWeight[index] = (index + 1) * (index + 3);
        
        // Because no items included yet.
        includeFraction[index] = 0;
    }

    // Call to insertion sort.
    insertionSortBoth(numOfItems, itemWeight, itemProfit);

    // printArr(numOfItems, itemProfit);
    // printArr(numOfItems, itemWeight);
    

    for(int index = 0; index < numOfItems; index++) {
        
        if(itemWeight[index] < leftoverKnapsackCapacity) {
            includeFraction[index] = 1;
            leftoverKnapsackCapacity -= itemWeight[index];
        }

        else {

            // Fraction decided by the amount of space left in knapsack and weight of item.
            includeFraction[index] = leftoverKnapsackCapacity / itemWeight[index];
            leftoverKnapsackCapacity = 0;
            break;
        }
    }

    // Variable for calculating total knapsack value
    float knapsackValue = 0;

    printf("\nContents of knapsack : \n\n");

    for(int index = 0; index < numOfItems; index++) {
        if(includeFraction[index] == 0) {
            break;
        }
        
        printf("Density of Object %d : %0.2f\n", index + 1, itemProfit[index]/itemWeight[index]);
        printf("%0.2f of Object %d included.\n\n", includeFraction[index], index + 1);

        knapsackValue += includeFraction[index] * itemProfit[index];
    }
    
    printf("Total knapsack value : %0.2f\n", knapsackValue);
    printf("Knapsack capacity left : %0.2f\n", leftoverKnapsackCapacity);

    // printArr(numOfItems, itemWeight);
    // printArr(numOfItems, itemProfit);

    return 0;
}