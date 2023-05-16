import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {21, 3, 4, 8, 5, 6, 12, 13};
        System.out.println( Arrays.toString( mergeSort(arr)) );
    }
    static int[] mergeSort(int[] unsortedArr) {

        if(unsortedArr.length == 1) { // Base condition of recursive function
            return unsortedArr;
        }

        int middle = unsortedArr.length / 2;

        int[] left = mergeSort(Arrays.copyOfRange(unsortedArr, 0, middle));

        int[] right = mergeSort(Arrays.copyOfRange(unsortedArr, middle, unsortedArr.length));

        return merge(left, right);
    }

    static int[] merge(int[] left, int[] right) {
        int[] sortedArr = new int[left.length + right.length];

        int minSize = (left.length < right.length) ? left.length : right.length;

        int leftIndex = 0;
        int rightIndex = 0;
        int mainIndex = 0;

        
        // It is better to use while-loop in this situation because it takes certain amount of processing to figure out how long the for-loop should be run. Using the while-loop allows us to run the process until a required condition is NOT met anymore.
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
