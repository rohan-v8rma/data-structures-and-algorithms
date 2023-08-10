// Problem link: https://leetcode.com/problems/two-sum
import java.util.*;
import java.util.*;

class Solution {
    /* 
    BRUTEFORCE: O(n^2) solution
    Surprisingly works even though N = 10^4
    */
    public int[] twoSum1(int[] nums, int target) {
        int[] solution = new int[2];

        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    solution[0] = i;
                    solution[1] = j;
                    return solution;            
                }
            }
        }

        return solution;
    }


    void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    // Special sorting function that sorts arr and moves arr2 accordingly.
    void quickSort(int[] arr, int[] arr2, int start, int end) {
        if(end <= start) {
            return;
        }

        int pivot = (start + end) / 2;

        for(int index = start; index <= end; index++) {
            System.out.printf("%d, ", arr[index]);
        }
        System.out.printf("\npivot: %d\n", arr[pivot]);


        swap(arr, end, pivot);
        swap(arr2, end, pivot);
        
        int left = start;
        int right = end - 1;

        while(left <= right) {
            while(
                left < end
                && 
                /* 
                The element pointed to by left pointer 
                has to be less than pivot
                */
                arr[left] <= arr[end]
            ) {
                left++;
            }
            
            while(
                right > start 
                && 
                /* 
                The element pointed to by right pointer 
                has to be less than or equal to pivot
                */
                arr[right] > arr[end]
                ) {
                right--;
            }

            // The elements pointed to by left and right are swappable.
            if(left < right) {
                swap(arr, left, right);
                swap(arr2, left, right);
                left++;
                right--;
            }
            else {
                break;
            }
        }

        swap(arr, left, end);
        swap(arr2, left, end);

        quickSort(arr, arr2, start, left - 1);
        quickSort(arr, arr2, left + 1, end);

        for(int index = start; index <= end; index++) {
            System.out.printf("%d, ", arr[index]);
        }
        System.out.println();
    }

    /* 
    O(n.log(n)) solution, but operations extremely costly
    
    This is because secondary array has to be maintained and sorted USING 
    custom quicksort function, so that we are able to preserve the
    index values.
    */
    public int[] twoSum(int[] nums, int target) {
        int[] solution = new int[2];

        int n = nums.length;
        int [] arr2 = new int[n];
        for(int index = 0; index < n; index++) {
            arr2[index] = index;
        }

        quickSort(nums, arr2, 0, n - 1);

        for(int index = 0; index < n; index++) {
            System.out.printf("%d, ", nums[index]);
        }

        // This pointer points to the smallest element in the array
        int pointer1 = 0;
        // This pointer points to the largest element in the array
        int pointer2 = nums.length - 1;

        while(pointer1 < pointer2) {
            
            // Making pointer1 point to a larger element
            if(nums[pointer1] + nums[pointer2] < target) {
                pointer1++;
            }
            // Making pointer2 point to a smaller element
            else if(nums[pointer1] + nums[pointer2] > target) {
                pointer2--;
            }
            else {
                break;
            }
        }

        solution[0] = arr2[pointer1];
        solution[1] = arr2[pointer2];        

        return solution;
    }

    /*
    * O(n): Video solution using Maps

    So space complexity increased to O(n), but TC decreased to O(n)
    
    This is better since we can buy more memory but time cannot be bought.
    */
    public int[] twoSum3(int[] nums, int target) {
        
        Map<Integer, Integer> numbersSeen = new HashMap<>();

        int[] solution = new int[2];

        for(int index = 0; index < nums.length; index++) {
            int currentNum = nums[index];
            if(numbersSeen.containsKey(target - currentNum)) {
                solution[0] = numbersSeen.get(target - currentNum);
                solution[1] = index;
                return solution;
            }
            else {
                numbersSeen.put(currentNum, index);
            }
        }

        return solution;
    }
}