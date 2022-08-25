import java.util.Arrays;

public class MergeSortInPlace {
    public static void main(String[] args) {
        int[] arr = {21, 3, 4, 8, 5, 6, 12, 13};
        mergeSortInPlace(arr, 0 , arr.length);
        System.out.println( Arrays.toString( arr ) );
    }
    static void mergeSortInPlace(int[] unsortedArr, int start, int end) { // end is exclusive

        int subArrLen = end - start;

        if(subArrLen == 1) { // Base condition of recursive function, where no elements are in the unsorted array
            return;
        }

        int middle = subArrLen / 2;

        mergeSortInPlace(unsortedArr, start, middle);
        mergeSortInPlace(unsortedArr, middle, end);

        mergeInPlace(unsortedArr, start, middle, end);
        
    }

    static void mergeInPlace(int[] arr, int start, int middle, int end) {
        int[] mergedArray = new int[end - start];

        int leftPtr = start;
        int rightPtr = middle;
        int mergePtr = 0;

        while( (leftPtr < middle) && (rightPtr < end) ) {
            if(arr[leftPtr] < arr[rightPtr]) {
                mergedArray[mergePtr++] = arr[leftPtr++];
            }
            else {
                mergedArray[mergePtr++] = arr[rightPtr++];
            }
        }
        while(leftPtr < middle) {
            mergedArray[mergePtr++] = arr[leftPtr++];
        }
        while (rightPtr < end) {
            mergedArray[mergePtr++] = arr[rightPtr++];
        }

        mergePtr = 0;


        // CHECKED
        // For changing the values in the sub-array, using the values of the mixed array.
        for(int index = start; index < end; index++) {
            arr[index] = mergedArray[mergePtr++];
        }
    }
}
