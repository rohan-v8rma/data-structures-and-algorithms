// https://leetcode.com/problems/number-of-people-aware-of-a-secret/

class Solution {
    /*
    ____Bruteforce approach (TABULATION)____
    */
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int[] forgetArray = new int[n + 1];
        int[] startSharingArray = new int[n + 1];
        int peopleWhoKnow = 1;

        int MOD = 1000000007;

        int peopleWhoShare = 0;

        startSharingArray[1 + delay] = 1;

        if(1 + forget <= n) {
            forgetArray[1 + forget] = 1;
        }

        for(int day = 1; day <= n; day++) {
            peopleWhoKnow = (peopleWhoKnow - forgetArray[day]) % MOD;
            peopleWhoShare = (peopleWhoShare - forgetArray[day]) % MOD;

            peopleWhoShare = (peopleWhoShare + startSharingArray[day]) % MOD;

            peopleWhoKnow = (peopleWhoKnow + peopleWhoShare) % MOD;

            if(day + forget <= n) {
                forgetArray[day + forget] = (
                    forgetArray[day + forget] 
                    + 
                    peopleWhoShare
                ) % MOD;
            }

            if(day + delay <= n) {
                startSharingArray[day + delay] = (
                    startSharingArray[day + delay] 
                    + 
                    peopleWhoShare
                ) % MOD;
            }
        }

        return (int)((MOD + peopleWhoKnow) % MOD);
    }

    /*
    ____RECURSIVE MEMOIZATION____
    */

    // int MOD = 1000000007;

    // int add(int a, int b) {
    //     return (a + b) % MOD;
    // }

    // int getKnowCount(int currentDay, int finalDay, int delay, int forget) {
    //     // This user will get to know past the final day, making this useless for us.
    //     if(currentDay > finalDay) return 0;
    //     if(dp[currentDay] != -1) return dp[currentDay];

    //     int peopleWhoKnow = 0;

    //     for(int futureDay = currentDay + delay; futureDay < currentDay + forget; futureDay++) {
    //         peopleWhoKnow = add(
    //             peopleWhoKnow, 
    //             // The current user is sharing the secret everyday to 1 new person.
    //             getKnowCount(futureDay, finalDay, delay, forget)
    //         );
    //     }

    //     return dp[currentDay] = (
    //         // The current user forgets, since it is before the final day.
    //         currentDay + forget <= finalDay 
    //         ? peopleWhoKnow
    //         // The current user retains the secret, since forget date is past the final date.
    //         : add(1, peopleWhoKnow)
    //     );
    // }

    // int[] dp;

    // public int peopleAwareOfSecret(int n, int delay, int forget) {
    //     dp = new int[finalDay + 1];

    //     return getKnowCount(1, n, delay, forget);
    // }
}