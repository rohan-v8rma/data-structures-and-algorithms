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

        int[] mergedArray = new int[subArrLen];

        int leftPtr = start;
        int rightPtr = middle;
        int mergePtr = 0;

        while( (leftPtr < middle) && (rightPtr < end) ) {
            if(unsortedArr[leftPtr] < unsortedArr[rightPtr]) {
                mergedArray[mergePtr++] = unsortedArr[leftPtr++];
            }
            else {
                mergedArray[mergePtr++] = unsortedArr[rightPtr++];
            }
        }
        while(leftPtr < middle) {
            mergedArray[mergePtr++] = unsortedArr[leftPtr++];
        }
        while (rightPtr < end) {
            mergedArray[mergePtr++] = unsortedArr[rightPtr++];
        }

        mergePtr = 0;

        // CHECKED
        // For changing the values in the sub-array, using the values of the mixed array.
        for(int index = start; index < end; index++) {
            unsortedArr[index] = mergedArray[mergePtr++];
        }


    }

    static int[] merge(int[] left, int[] right) {
        int[] sortedArr = new int[left.length + right.length];

        int minSize = (left.length < right.length) ? left.length : right.length;

        int leftIndex = 0;
        int rightIndex = 0;
        int mainIndex = 0;

        
        while( (leftIndex < left.length) && (rightIndex < right.length) ) { // While both the left and the right arrays have elements left over, we will compare the elements of the left and right arrays.

                if(left[leftIndex] < right[rightIndex]) {
                    sortedArr[mainIndex++] = left[leftIndex++];
                }
                else {
                    sortedArr[mainIndex++] = right[rightIndex++];
                }

        }
        
        while (leftIndex < left.length) { // If only the LEFT array has elements left over, since the LEFT and RIGHT arrays are already sorted, we just add the elements of the LEFT array to the end of the combined array.

            sortedArr[mainIndex++] = left[leftIndex++];
        }
        
        while (rightIndex < right.length) { // If only the RIGHT array has elements left over, since the LEFT and RIGHT arrays are already sorted, we just add the elements of the RIGHT array into the combined array.

            sortedArr[mainIndex++] = right[rightIndex++];
        }

        return sortedArr;
    }
}
