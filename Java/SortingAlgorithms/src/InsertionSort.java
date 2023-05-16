import java.util.Arrays;

public class InsertionSort {
    static int size = 5;

    public static void main(String[] args) {
        int[] array = {2, 1, 5, 4, 3};
//        int[] array = {1, 2, 3, 4, 5}; Check whether already sorted array is detected and loop is broken
        insertionSort(array);
        System.out.printf(Arrays.toString(array));
    }

    static void insertionSort(int[] array) {

        int temp;

        for(int pass = 0; pass < (size - 1); pass++) { // Passes need to be 1 less than the size of the array.
            for(int checkIndex = pass; checkIndex >= 0; checkIndex--) {
                if(array[checkIndex + 1] < array[checkIndex]) { // If we consider the first iteration of the inner loop, array[checkIndex + 1] is the element that needs to be sorted, which is outside the portion of the array that has already been sorted.
                    temp = array[checkIndex];
                    array[checkIndex] = array[checkIndex + 1];
                    array[checkIndex + 1] = temp;
                }
                else { // If the element that needs to be sorted is greater than or equal to the element that is next to it, then it means that the unsorted element has reached its correct place in the sorted array. So, we can move onto the next pass.
                    break;
                }

            }
        }
    }
}
