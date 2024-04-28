// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-152/problems
// TODO: Make notes

class Solution{
    static int binSearch(int minIndex, ArrayList<Integer> indices) {
        int start = 0;
        int end = indices.size() - 1;
        
        int val = indices.size();
        
        while(start <= end) {
            int mid = (start + end) / 2; 
            
            if(minIndex <= indices.get(mid)) {
                val = mid;
                end = mid - 1;
            }
            else {
                start = mid + 1;
            } 
        }
        
        // Number of indices the original index can be paired with.
        return indices.size() - val;
    }
    
    static long findGoodPairs(int a[], int n, int k){
        long pairs = 0;
        
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            map.putIfAbsent(a[i], new ArrayList<>());
            map.get(a[i]).add(i);
        }
        
        for(int i = 0; i < n; i++) {
            pairs += binSearch(i + k, map.get(a[i]));
        }
        
        return pairs;
    }
 
}