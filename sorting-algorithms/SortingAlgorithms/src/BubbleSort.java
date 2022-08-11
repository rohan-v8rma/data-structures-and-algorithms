import java.lang.reflect.Array;
import java.util.Arrays;

public class BubbleSort {

    static int size = 5;

    public static void main(String[] args) {
        int[] array = {2, 1, 5, 4, 3};
//        int[] array = {1, 2, 3, 4, 5};
        bubbleSort(array);
        System.out.printf(Arrays.toString(array));
    }

    static void bubbleSort(int[] arr) { // for arrays, the original object is modified. Unlike strings, where a new object is created
        boolean swap;
        int temp;
        for(int pass = 0; pass < (size - 1); pass++) {

            swap = false;

            for(int comparison = 0; comparison < ( (size - 1) - pass ); comparison++) {
                if(arr[comparison] > arr[comparison + 1]) {
                    temp = arr[comparison];
                    arr[comparison] = arr[comparison + 1];
                    arr[comparison + 1] = temp;
                    swap = true;
                };
            }

            if(!swap) { // If no swaps occur in a particular pass, then it is clear that the array is already sorted and we need not make any more passes.
                break;
            };
        }
    }

}
