public class Solution {
    public static int countDistinct(
        ArrayList<Integer> arr,
        int n
    ) {
        // Write your code here.

        int lastElement = arr.get(0);

        int distinctElements = 1;

        for(int index = 1; index < n; index++) {
            int currentElement = arr.get(index);
            
            if(currentElement > lastElement) {
                distinctElements++;
                lastElement = currentElement;
            }
        }

        return (distinctElements);
    }
}