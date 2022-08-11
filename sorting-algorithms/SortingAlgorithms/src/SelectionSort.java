import java.util.Arrays;

public class SelectionSort {
    static int size = 5;

    public static void main(String[] args) {
        int[] array = {2, 1, 5, 4, 3};
        selectionSort(array);
        System.out.printf(Arrays.toString(array));
    }

    static void selectionSort(int[] array) {
        // Code for sorting goes here and change the function name.
        int maxIndex, temp;

        // Loop for making passes
        for(int pass = 0; pass < (size - 1); pass++) {
            maxIndex = 0;
            // Loop for finding the index position of the next largest element
            for(int index = 1; index < ((size) - pass); index++) { // We start from index 1 because by default we have assigned the maxIndex to be 0.
                if(array[index] > array[maxIndex]) {
                    maxIndex = index;
                };
            }
            // Swap operation of the element of the next largest value with the last-most unsorted element.
//            if (maxIndex != (size - 1 - pass) ) { // if the position of the maxIndex is already at the end, we need not swap it.
            temp = array[maxIndex];
            array[maxIndex] = array[size - 1 - pass];
            array[size - 1 - pass] = temp;
//            };
        }

    }
}
