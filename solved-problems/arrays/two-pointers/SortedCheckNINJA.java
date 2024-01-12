public class Solution {
    public static int isSorted(int n, int []a) {
        // Write your code here.
        int lastElement = a[0];

        for(int element : a) {
            if(element < lastElement) {
                return 0;
            }

            lastElement = element;
        }

        return 1;

        // for(int element : a) {
        //     if(element >= lastElement) {
        //         lastElement = element;
        //         continue;
        //     }

        //     return 0;
        // }

        // return 1;
    }
}