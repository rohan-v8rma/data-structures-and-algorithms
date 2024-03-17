// Problem link: https://www.codingninjas.com/studio/problems/ninja-s-training_3621003

public class Solution {
    // This solution causes TLE due to repeated recursive calls.
    static int selectRecursive(int prevSelected, int day, int n, int points[][]) {
        if(day == n) {
            return 0;
        }

        int maxMerit = 0;
        int currentMerit;

        for(int job = 0; job < 3; job++) {
            if(job == prevSelected) {
                continue;
            }

            currentMerit = points[day][job] + selectRecursive(job, day + 1, n, points);
            maxMerit = Math.max(currentMerit, maxMerit);        
        }

        return maxMerit;
    }

    /* 
    This solutions solves TLE but fails in test cases with deep recursion
    since stack space is limited.
    */
    static int selectRecursiveMemoized(
        int jobToSelect, 
        int day, 
        int n, 
        int points[][], 
        int memoMatrix[][]
    ) {
        if(day >= n) {
            return 0;
        }
        
        if(memoMatrix[day][jobToSelect] != 0) {
            return memoMatrix[day][jobToSelect];
        }

        int maxMerit = 0;
        int currentMerit;
        int memoJob = 0;

        for(int nextJob = 0; nextJob < 3; nextJob++) {
            if(nextJob == jobToSelect) {
                continue;
            }

            currentMerit = 
                points[day][jobToSelect] 
                + 
                selectRecursiveMemoized(nextJob, day + 1, n, points, memoMatrix);
            
            if(currentMerit > maxMerit) {
                memoJob = nextJob;
                maxMerit = currentMerit;
            }
        }

        return memoMatrix[day][jobToSelect] = maxMerit;
    }

    // This removes the problem of stack space getting full since no recursion.
    static int selectTabulated(int n, int points[][], int tMatrix[][]) {
        tMatrix[0][0] = points[0][0];
        tMatrix[0][1] = points[0][1];
        tMatrix[0][2] = points[0][2];


        for(int day = 1; day < n; day++) {
            for(int jobToSelect = 0; jobToSelect < 3; jobToSelect++) {
                
                int prevResult = 0;
                for(int prevJob = 0; prevJob < 3; prevJob++) {
                    if(jobToSelect == prevJob) {
                        continue;
                    }

                    if(tMatrix[day - 1][prevJob] > prevResult) {
                        prevResult = tMatrix[day - 1][prevJob];
                    }
                }

                tMatrix[day][jobToSelect] = points[day][jobToSelect] + prevResult;    
            }
        }

        return Math.max(
            Math.max(
                tMatrix[n - 1][0], 
                tMatrix[n - 1][1]
            ),
            tMatrix[n - 1][2]
        );
    }

    // This makes use of just 6 variables instead of a matrix.
    static int selectTabulatedOptimized(int n, int points[][]) {
        int prevJob0 = points[0][0];
        int prevJob1 = points[0][1];
        int prevJob2 = points[0][2];

        int newJob0, newJob1, newJob2;


        for(int day = 1; day < n; day++) {
            newJob0 = points[day][0] + Math.max(prevJob1, prevJob2);
            newJob1 = points[day][1] + Math.max(prevJob0, prevJob2);
            newJob2 = points[day][2] + Math.max(prevJob0, prevJob1);

            prevJob0 = newJob0;
            prevJob1 = newJob1;
            prevJob2 = newJob2;
        }

        return Math.max(
            Math.max(
                prevJob0, 
                prevJob1
            ),
            prevJob2
        );
    }

    public static int ninjaTraining(int n, int points[][]) {
        // -1 is the prevSelected argument since we haven't selected any activity yet.
        // return selectRecursive(-1, 0, n, points);

        // int[][] memoMatrix = new int[n][3];
        
        // return Math.max(
        //     Math.max(
        //         selectRecursiveMemoized(0, 0, n, points, memoMatrix),
        //         selectRecursiveMemoized(1, 0, n, points, memoMatrix)
        //     ),
        //     selectRecursiveMemoized(2, 0, n, points, memoMatrix)
        // );

        // return selectTabulated(n, points, memoMatrix);
        return selectTabulatedOptimized(n, points);
    }

}