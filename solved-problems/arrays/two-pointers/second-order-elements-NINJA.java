// O(N) solution.   
public class Solution {
    public static int[] getSecondOrderElements(int n, int []a) {
        // Write your code here.
        int secondLargest = -1;
        int largest = a[0];

        int smallest = a[0];
        int secondSmallest = Integer.MAX_VALUE;
        for(int element: a) {
            if(element > largest) {
                secondLargest = largest;
                largest = element;
            }
            /* 
            The element is rejected from above because it may be equal to 
            largest. Since we need that secondLargest is less than 
            (basically not equal to) largest, we add that check as well.
            */
            else if(
                element > secondLargest 
                && 
                element != largest
            ) {
                secondLargest = element;
            }

            if(element < smallest) {
                secondSmallest = smallest;
                smallest = element;
            }
            /* 
            The element is rejected from above because it may be equal to 
            smallest. Since we need that secondSmaller is greater than 
            (basically not equal to) smallest, we add that check as well.
            */
            else if(
                element < secondSmallest 
                && 
                element != smallest
            ) {
                secondSmallest = element;
            }
        }
    }
}