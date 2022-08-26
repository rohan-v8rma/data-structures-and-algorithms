import java.util.Arrays;

public class QuickSort1 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 10, 7, 3};
        quickSort(arr, 0, 6);
        System.out.println(Arrays.toString(arr));
    }

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

        // Swapping the pivot element to the last indice
        unsortedArr[pivot] = unsortedArr[right];
        unsortedArr[right] = pivotElement;
        right--; // Moving `right` pointer ahead of the pivot element.

        while(left <= right) {
            while( (left <= end) && (unsortedArr[left] < pivotElement) ) { // The first condition is to ensure 'left' pointer doesn't go out of bounds. The second condition is for stopping the 'left' pointer as soon as an element greater than or equal to the pivot is found.
                left++;
            }
            while( (right >= start) && (unsortedArr[right] > pivotElement) && (left <= right) ) { // The first condition is to ensure 'right' pointer doesn't go out of bounds. The second condition is for stopping the 'right' pointer as soon as an element greater than or equal to the pivot is found. The third condition is for stopping the 'right' pointer as soon as the 'right' pointer crosses the 'left' pointer. If the 'right' pointer crosses the 'left' pointer, it means all element to the right of the 'right' pointer are greater than or equal to the pivot. So, we can replace the element just to the right of the 'right' pointer, which is pointed to by the 'left' pointer.
                right--;
            }

            if (left < right) { // If the above WHILE loops which increment the 'left' and 'right' pointer end, and the 'left' pointer is still less than the 'right' pointer, we should swap the elements pointed to by the 'left' and 'right' pointer, because it is a VIOLATION of the condition that all elements bigger than pivot should be on the right of it and all elements small than pivot should be on the left of it.
                temp = unsortedArr[left];
                unsortedArr[left] = unsortedArr[right];
                unsortedArr[right] = temp;
                left++;
                right--;
            }
        }
        // The above WHILE loop ends when the right bound crosses the left bound. At this point, all elements to the left of the left bound are less than the pivot and all elements including the left bound as well as to its right are greater than or equal to the pivot, so we can replace the left bound with the last element, which is the pivot.


        temp = unsortedArr[left];
        unsortedArr[left] = unsortedArr[end];
        unsortedArr[end] = temp;

        quickSort(unsortedArr, start, right); // At the end, the 'right' ptr is adjacent to the 'left' ptr, where the 'left' ptr is to its right.
        quickSort(unsortedArr, ++left,  end); // The 'left' ptr is the pivot itself, so we need to call quickSort on the sub-array after the pivot, which is why we use ++left.




        //? This algorithm is also faulty as the pivot element is not reaching its correct index position after reach pass. Try taking the example {1, 2, 4, 5, 3}. The middle element 4, when taken as pivot, would be at last position after first pass, when it should be second-last index. BUT IT DOES WORK and is MORE EFFICIENT since shifting of elements is less.

//        while(left <= right) {
//            while(unsortedArr[left] < pivotElement) { // So, even if `left` reaches the pivotElement, it will stop. Since the pivotElement is definitely present in the array, this helps safeguard against `left` index going out of bounds of the array.
//                left++;
//            }
//            while(unsortedArr[right] > pivotElement) { // So, even if `right` reaches the pivotElement, it will stop. Since the pivotElement is definitely present in the array, this helps safeguard against `right` index going out of bounds of the array.
//                right--;
//            }
//
//            if(left <= right) { // It is possible that in the above two while loops,
//                temp = unsortedArr[left];
//                unsortedArr[left] = unsortedArr[right];
//                unsortedArr[right] = temp;
//                left++;
//                right--;
//            }
//        }
//        quickSort(unsortedArr, left, end);
//        quickSort(unsortedArr, start, right);


        //? This logic is a bit flawed considering the algorithm of quick sort. We are swapping elements in the beginning of the array with elements in the end of the array, irrespective of their size with respect to the pivot
//        while( start != end ) {
//            while (unsortedArr[start++] < pivotElement); // So, even if start arrives on the pivot element, it will stop
//            while (unsortedArr[end--] > pivotElement);
//
//            temp = unsortedArr[start];
//            unsortedArr[start] = unsortedArr[end];
//            unsortedArr[end] = temp;
//
//            if(unsortedArr[start] == pivotElement) {
//                pivot = start;
//            }
//            else if(unsortedArr[end] == pivotElement) {
//                pivot = end;
//            }
//
//        }

    }

}
