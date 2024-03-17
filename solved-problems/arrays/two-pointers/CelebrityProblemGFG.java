// https://www.geeksforgeeks.org/problems/the-celebrity-problem/1
// TODO: make notes

class Solution
{ 
    // O(N^2) solution using array.
    // int celebrity(int M[][], int n)
    // {
    // 	for(int j = 0; j < n; j++) {
    // 	    int peopleWhoKnowJ = 0;
    	    
    // 	    for(int i = 0; i < n; i++) {
    // 	        if(i != j) {
    // 	            if(M[i][j] != 0) {
    // 	                peopleWhoKnowJ++;
    // 	            }
    // 	            else {
    // 	                break;
    // 	            }
    // 	        }
    // 	    }
    	    
    // 	    if(peopleWhoKnowJ == (n - 1)) {
    // 	        boolean isCel = true;
    // 	        for(int jIdx = 0; jIdx < n; jIdx++) {
    // 	            if(M[j][jIdx] != 0) {
    // 	                isCel = false;
    // 	                break;
    // 	            }
    // 	        }
    	        
    // 	        if(isCel) {
    // 	            return j;
    // 	        }
    // 	    }
    // 	}
    	
    	
    // 	return -1;
    // }
    
    // O(n)
    int celebrity(int M[][], int n) {
        int potentialCelebrity = 0;
        
        for(int i = 1; i < n; i++) {
            // If the potentialCelebrity knows i, it is NOT a celebrity.
            if(M[potentialCelebrity][i] == 1) {
                potentialCelebrity = i;
            }
            /* 
            If the potentialCelebrity doesn't know i, it is still a potential celebrity.
            
            We don't care about the test cases where there are 2 arrays with 0s only,
            because we will be cross checking whether everyone else knows the potentialCelebrity
            at end.
            */
        }
        
        for(int celIdx = 0; celIdx < n; celIdx++) {
            if(celIdx != potentialCelebrity) {
                if(M[potentialCelebrity][celIdx] != 0) return -1;
            }
        }
        
        for(int otherIdx = 0; otherIdx < n; otherIdx++) {
            if(otherIdx != potentialCelebrity) {
                // Some other person doesn't know the potentialCelebrity, so the person is not a celeb.
                if(M[otherIdx][potentialCelebrity] != 1) return -1;
            }
        }
        
        return potentialCelebrity;
    }
}
