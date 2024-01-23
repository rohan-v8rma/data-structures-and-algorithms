// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-138/problems

class Solution {
    /*_____Bit Manipulation and DP Tabulation Approach____*/
    
    static boolean[] notPrime;
    static int md = 1000000007;
    
    public int getPrimeBitRep(int N) {
        int rep = 0;
        int bit = 0;
        
        for(int i = 2; i <= N; i++) {
            if(!notPrime[i]) {
                if(N % i == 0) {
                    rep |= 1 << bit;
                }
                
                if(N % (i * i) == 0) {
                    return -1;
                }
                
                bit++;
            }
        }
        
        return rep;
    }
    
    public int primeSubsets(int N, int[] Arr) {
        int constraintOnValueOfArrElement = 30;
        
        notPrime = new boolean[constraintOnValueOfArrElement + 1];
        
        int primeCt = 0;
        
        for(int i = 2; i*i <= constraintOnValueOfArrElement; i++) {
            if(!notPrime[i]) {
                for(int j = i * i; j <= constraintOnValueOfArrElement; j += i) {
                    notPrime[j] = true;
                }
            }
        }
        
        for(int i = 2; i <= constraintOnValueOfArrElement; i++) {
            if(!notPrime[i]) {
                primeCt++;
            }
        }
        
        int numOfBitsMasksPossible = (1 << primeCt);
        
        int[] ct = new int[numOfBitsMasksPossible + 1];
        
        for(int element: Arr) {
            
            int[] newCt = new int[numOfBitsMasksPossible + 1];
            
            int primeBitRep = getPrimeBitRep(element);
            
            if(primeBitRep != -1) {
                for(int i = 0; i < ct.length; i++) {
                    
                    if((ct[i] != 0) && ((i & primeBitRep) == 0)) {
                        int newRep = i | primeBitRep;    
                        newCt[newRep] = ct[i];
                    }
                }
                
                
                // For the individual element as a subset.
                newCt[primeBitRep] = (newCt[primeBitRep] + 1) % md;
            }
            
            for(int i = 0; i < ct.length; i++) {
                ct[i] = (ct[i] + newCt[i]) % md;
            }
        }
        
        int totalCt = 0;
        
        for(int i = 1; i < ct.length; i++) {
            totalCt = (totalCt + ct[i]) % md;    
        }
        
        
        return totalCt;
    }
    
    /*_____Bit Manipulation and DP Memoization Approach____*/
    
    // static boolean[] notPrime;
    // static int md = 1000000007;
    
    // public int getPrimeBitRep(int N) {
    //     int rep = 0;
    //     int bit = 0;
        
    //     for(int i = 2; i <= N; i++) {
    //         if(!notPrime[i]) {
    //             if(N % i == 0) {
    //                 rep |= 1 << bit;
    //             }
                
    //             if(N % (i * i) == 0) {
    //                 return -1;
    //             }
                
    //             bit++;
    //         }
    //     }
        
    //     return rep;
    // }
    
    // public int decideIfPartOfSubset(int idx, int[] arr, int currentBitMask) {
    //     if(arr.length == idx) {
    //         if(currentBitMask != 0) {
    //             return 1;
    //         }
            
    //         return 0;
    //     }
        
    //     if(dpMatrix[idx][currentBitMask] != -1) {
    //         return dpMatrix[idx][currentBitMask];
    //     }
        
    //     int primeBitRepOfElementAtIdx = getPrimeBitRep(arr[idx]);
        
    //     // System.out.printf("%d, %s, %d\n", arr[idx], Integer.toString(primeBitRepOfElementAtIdx, 2), currentBitMask);

    //     int total = 0;
        
    //     if(
    //         (primeBitRepOfElementAtIdx != -1) 
    //         && ((primeBitRepOfElementAtIdx & currentBitMask) == 0)
    //     ) {
    //         total = decideIfPartOfSubset(
    //             idx + 1, 
    //             arr, primeBitRepOfElementAtIdx | currentBitMask) % md;
    //     }
        
    //     total = (total + decideIfPartOfSubset(idx + 1, arr, currentBitMask)) % md;
        
    //     return dpMatrix[idx][currentBitMask] = total;
    // }
    
    // static int[][] dpMatrix;
    
    // public int primeSubsets(int N, int[] Arr) {
    //     int constraintOnValueOfArrElement = 30;
        
    //     notPrime = new boolean[constraintOnValueOfArrElement + 1];
        
    //     for(int i = 2; i*i <= constraintOnValueOfArrElement; i++) {
    //         if(!notPrime[i]) {
    //             for(int j = i * i; j <= constraintOnValueOfArrElement; j += i) {
    //                 notPrime[j] = true;
    //             }
    //         }
    //     }
        
    //     int primeCt = 0;
        
    //     for(int i = 2; i <= constraintOnValueOfArrElement; i++) {
    //         if(!notPrime[i]) {
    //             primeCt++;
    //         }
    //     }
        
    //     int numOfBitMasksPossible = (1 << primeCt);
        
    //     dpMatrix = new int[N][numOfBitMasksPossible];
        
    //     for(int i = 0; i < N; i++) {
    //         Arrays.fill(dpMatrix[i], -1);
    //     }
        
    //     return decideIfPartOfSubset(0, Arr, 0);
    // }
}
        
