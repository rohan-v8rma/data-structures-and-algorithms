// Problem link: https://leetcode.com/problems/next-permutation

class Solution {
    // See full intuition in notebook

    // BETTER (Self Developed)
    // public void nextPermutation(int[] currPerm) {
    //     int prevElement = 0;
    
    //     List<Integer> elementsAvailable = new ArrayList<>();

    //     /*
    //     This signals that no element can be changed, and we must start from 
    //     the beginning of the permutations.
    //     */
    //     int indexToBeChanged = -1;

    //     for(int index = currPerm.length - 1; index >= 0; index--) {
    //         int currentElement = currPerm[index];
    //         if(currentElement < prevElement) {
    //             indexToBeChanged = index;
    //             break;
    //         }

    //         prevElement = currentElement;
    //         elementsAvailable.add(currentElement);
    //     }

    //     if(indexToBeChanged == -1) {
    //         Arrays.sort(currPerm);
    //         return;
    //     }

    //     Collections.sort(elementsAvailable);

    //     int subElement = 0;
    //     for(int subIndex = 0; subIndex < elementsAvailable.size(); subIndex++) {
    //         if(elementsAvailable.get(subIndex) > currPerm[indexToBeChanged]) {
    //             subElement = elementsAvailable.get(subIndex);

    //             // Placing the element we want to replace in the elementsAvailable arr
    //             elementsAvailable.set(subIndex, currPerm[indexToBeChanged]);
    //             break;
    //         }
    //     }

    //     currPerm[indexToBeChanged++] = subElement;
        
    //     for(int element: elementsAvailable) {
    //         currPerm[indexToBeChanged++] = element;
    //     }

    //     return;   
    // }

    // OPTIMAL
    public void nextPermutation(int[] currPerm) {
        /*
        This signals that no element can be changed, and we must start from 
        the beginning of the permutations.
        */
        int indexToBeChanged = -1;

        for(int index = currPerm.length - 2; index >= 0; index--) {
            if(currPerm[index] < currPerm[index + 1]) {
                indexToBeChanged = index;
                break;
            }
        }

        if(indexToBeChanged != -1) {
            int index;
            int elementToBeChanged = currPerm[indexToBeChanged];
            /* 
            Finding the element just greater than the one we want to change, on its RHS
            */
            for(index = currPerm.length - 1; index > indexToBeChanged; index--) {
                if(currPerm[index] > elementToBeChanged) {
                    break;
                }
            }
            currPerm[indexToBeChanged] = currPerm[index];
            currPerm[index] = elementToBeChanged;
        }

        /* 
        Reversing the portion of the array to RHS of dip index, to change it
        from descending to ascending.
        */
        int left = indexToBeChanged + 1;
        int right = currPerm.length - 1;
        int temp;
        while(left < right) {
            temp = currPerm[left];
            currPerm[left++] = currPerm[right];
            currPerm[right--] = temp;
        }

        return;   
    }

}