// https://leetcode.com/problems/time-needed-to-inform-all-employees/submissions/

class Solution {
    // Bruteforce solution (needs to be optimized due to TLE)
    // public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
    //     if(n == 1) {
    //         return informTime[0];
    //     }
    //     int maxTime = 0;

    //     for(int i = 0; i < n; i++) {
    //         for(int j = 0; j < n; j++) {
    //             if(manager[j] == i) {
    //                 informTime[j] += informTime[i];
    //                 manager[j] = manager[i];

    //                 if(informTime[j] > maxTime) {
    //                     maxTime = informTime[j];
    //                 }
    //             }

                
    //         }
    //     }

    //     return maxTime;
        
    // }


    /* 
    Better: For each person, recursively changes their manager upwards 
    while adding their manager's inform time to theirs.

    This is done until the person has no manager, and their informTime is equal to the time it will
    take for the information to reach them.

    VISUALIZE IT AS A DFS IN REVERSE DIRECTION, FOR LEAF TO ROOT.

    However, the flaw in this approach is that caching of inform times
    of nodes along the path to the topmost manager is not happening.
    */
    // public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
    //     if(n == 1) {
    //         return informTime[0];
    //     }
    //     int maxTime = 0;

    //     for(int j = 0; j < n; j++) {
    //         int manOfJ = manager[j];
    //         while(manOfJ != -1) {
    //             // Adding inform time of J's manager to J's inform time.
    //             informTime[j] += informTime[manOfJ];
    //             /*
    //             Updating J's manager to (manager of J's manager) since 
    //             we added J's manager's inform time to that of j.
    //             */
    //             manOfJ = manager[manOfJ];
    //         }

    //         // Updating manager of j after the while loop.
    //         manager[j] = manOfJ;

    //         // Updating the maximum time required based on the time it takes for info to reach J.
    //         maxTime = informTime[j] > maxTime ? informTime[j] : maxTime;
            
    //     }

    //     return maxTime;
    // }

    /*
    ____OPTIMAL (RECURSION + DP)____

    Tabulation would make things more complicated over here, and would still
    need some form of stack space in place of the call stack, so it would 
    not offer any benefits as well.
    */
    public int getAbsoluteInformTime(int person, int[] manager, int[] informTime) {
        /*
        There is no person known as -1, so it doesn't take any time
        for informing them, since they don't exist.
        */
        if(person == -1) return 0;

        /*
        Getting the absolute inform time of a person, using recursion.

        If we were to do this without recursion, we would need some amount
        of extra space, just to store the nodes in the path to the top most
        manager, so that we can update absolute inform time for those nodes
        as well.

        In this way, the next time a node comes whose manager was in this
        path, the answer will be given in almost constant time.
        */
        informTime[person] += getAbsoluteInformTime(
            manager[person], 
            manager, 
            informTime
        );
        
        /* 
        We have figured out the absolute time for informing this person,
        so we can say their manager is no one.
        */
        manager[person] = -1;
        
        return informTime[person];
    }  

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        int max = 0;

        for(int i = 0; i < n; i++) {
            max = Math.max(
                max, 
                getAbsoluteInformTime(i, manager, informTime)
            );
        }

        return max;
    }

}