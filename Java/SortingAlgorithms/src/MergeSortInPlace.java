import java.util.Arrays;

public class MergeSortInPlace {
    public static void main(String[] args) {
        int[] arr = {21, 3, 4, 8, 5, 6, 12, 13};
        mergeSortInPlace(arr, 0 , arr.length);
        System.out.println( Arrays.toString( arr ) );
    }
    static void mergeSortInPlace(int[] unsortedArr, int start, int end) { // end is exclusive

        int subArrLen = end - start;

        if(subArrLen == 1) { // Base condition of recursive function, since when the sub-array has 1 element, we can't divide it any further.
            return;
        }
//        int middle = subArrLen / 2;
        /*
        We replaced the ABOVE assignment with the below one because the above assignment is only OK in the case where the sub-array is from 0 to end.
        Suppose the array is from 3 to 5, the middle index comes out to be (5 - 3) / 2, which is 1. Obviously, 1 doesn't come in between 3 and 5.
        The BELOW assignment gives us the average of 3 and 5, which is 4. This is the actual middle element of the sub-array.
        */
        int middle = (start + end) / 2;

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
