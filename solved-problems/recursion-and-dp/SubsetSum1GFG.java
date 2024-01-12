// https://practice.geeksforgeeks.org/problems/subset-sums2234/0

class Solution{
    void pickElement(int index, int sum, ArrayList<Integer>sumArr, ArrayList<Integer>elementArr, int N) {
        if(index == N) {
            sumArr.add(sum);
            return;
        }
        
        // index = -1 is the starting condition, when no elements need to be picked.
        if(index != -1) {
            sum += elementArr.get(index);
        }
        
        // Used for loop pattern for 
        for(int pickIndex = index + 1; pickIndex <= N; pickIndex++) {
            pickElement(pickIndex, sum, sumArr, elementArr, N);
        }
    }
    
    // Returning all subset sums of a particular array
    ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int N){
        ArrayList<Integer> sumArr = new ArrayList<>();
        
        pickElement(-1, 0, sumArr, arr, N);
        
        return sumArr;
    }
}