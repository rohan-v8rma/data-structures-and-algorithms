// Problem link: https://leetcode.com/problems/permutation-sequence

class Solution {
    static int fact(int i) {
        int returnVal = i;
        for(int element = 2; element < i; element++) {
            returnVal *= element;
        }

        return returnVal;
    }

    /* 
    This function technically takes O(N), and it is run
    N times, to get N labels. 

    So, effective TC of this is O(N^2).
    */
    static char getNthLabel(int N, boolean[] takenLabels) {
        int labelNum = 0;
        char label = '1';
        while(labelNum <= N) {
            if(takenLabels[label - '1'] == false) {
                if(labelNum == N) {
                    takenLabels[label - '1'] = true;
                    return label;
                }
                labelNum++;
            }
            label++;
        }

        return label;
    }

    public String getPermutation(int n, int k) {
        /* 
        We subtract 1 because the 1st permutation is actually the 0th one.
        */
        k -= 1;

        int inc = fact(n);
        boolean[] takenLabels = new boolean[n];
        char[] permSequence = new char[n];

        for(int labelIdx = 0; labelIdx < n; labelIdx++) {
            inc /= (n - labelIdx);

            // getting how many need to be skipped.
            int N = k / inc;
            k %= inc;
            permSequence[labelIdx] = getNthLabel(N, takenLabels);
        }

        return new String(permSequence);
    }
}