import java.util.Arrays;

public class QuickSort2 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 10, 7, 3};
        quickSort(arr, 0, 6);
        System.out.println(Arrays.toString(arr));
    }

    //TODO: Figure out how exactly this works.
    //? This algorithm is also faulty as the pivot element is not reaching its correct index position after reach pass. Try taking the example {1, 2, 4, 5, 3}. The middle element 4, when taken as pivot, would be at last position after first pass, when it should be second-last index. BUT IT DOES WORK and is MORE EFFICIENT since shifting of elements is less.

    public static void quickSort(int[] unsortedArr, int start, int end) { // end is inclusive

        if(end - start <= 0) { // If the sub-array has one element, there is nothing to swap in the array. Also, the array can faultily have less than 1 element, which is also a case that requires no action
            return;
        }

        // Selecting the pivot element
        int pivot = (start + end) / 2;
        int pivotElement = unsortedArr[pivot];

        int temp;

        // Dynamic indices for comparison of elements
        int left = start;
        int right = end;

        while(left <= right) {
            while(unsortedArr[left] < pivotElement) { // So, even if `left` reaches the pivotElement, it will stop. Since the pivotElement is definitely present in the array, this helps safeguard against `left` index going out of bounds of the array.
                left++;
            }
            while(unsortedArr[right] > pivotElement) { // So, even if `right` reaches the pivotElement, it will stop. Since the pivotElement is definitely present in the array, this helps safeguard against `right` index going out of bounds of the array.
                right--;
            }

            // if(left < right) . We removed this for the BELOW case when both left and right are at the pivot element, so the replacement operation has no meaning BUT we need to perform the increment operation here, since the above while loops won't run, but we need to recursively call quickSort again, which shouldn't be including the pivot element.

            if(left <= right) { // If 'left' and 'right' pointer stop before CROSSING each other, it means there is a violation of the condition that elements to the left of the pivot should be less than it and all elements to right of the pivot should be greater than it. So we must swap the elements pointed to by the 'left' and 'right' pointer in order to rectify this violation.
                temp = unsortedArr[left];
                unsortedArr[left] = unsortedArr[right];
                unsortedArr[right] = temp;
                left++;
                right--;
            }
        }
        quickSort(unsortedArr, left, end);
        quickSort(unsortedArr, start, right);
    }

}
