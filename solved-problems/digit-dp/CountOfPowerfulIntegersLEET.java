// https://leetcode.com/problems/count-the-number-of-powerful-integers/

class Solution {
    /*_________* SELF DEVELOPED ALGORITHM (Uses binary search) *_________*/

    // public long changeArtificialBase(long number, int fromBase, int toBase) {
    //     return Long.parseLong(
    //         Long.toString(number, toBase)
    //         , fromBase
    //     );
    // }

    // public long closestPowerfulBelowFinish(
    //     int artificialBase,
    //     long suffixInt,
    //     long finish,
    //     long multiplierForPrefix, 
    //     long maxPowerPrefix
    // ) {
    //     long bottom = 0;
    //     long top = changeArtificialBase(maxPowerPrefix, artificialBase, 10);

    //     long closestPowerful = -1;

    //     while(top >= bottom) {
    //         long midPower = (top + bottom) / 2;

    //         long powerfulNumberBeingChecked = 
    //             (
    //                 multiplierForPrefix 
    //                 * 
    //                 changeArtificialBase(midPower, 10, artificialBase)
    //             ) 
    //             + suffixInt;

            
    //         if(finish < powerfulNumberBeingChecked) {
    //             /* 
    //             The powerful number is greater than finish 
    //             so we change the top to mid.
    //             */
    //             top = midPower - 1;
    //         }
    //         else if(finish == powerfulNumberBeingChecked) {
    //             closestPowerful = powerfulNumberBeingChecked;
    //             break;
    //         }
            
    //         else if(finish > powerfulNumberBeingChecked) {                
    //             /* 
    //             The powerful number is less than `finish`, so
    //             we check if it is the closestPowerful
    //             */

    //             bottom = midPower + 1;

    //             if(
    //                 (finish - closestPowerful < 0)
    //                 ||
    //                 (
    //                     (finish - powerfulNumberBeingChecked) 
    //                     < 
    //                     (finish - closestPowerful))
    //             ) {
    //                 closestPowerful = powerfulNumberBeingChecked;
    //             }
    //         }
    //     }

    //     if(closestPowerful == -1) {
    //         return -1;
    //     }

    //     return changeArtificialBase(
    //         closestPowerful / multiplierForPrefix,
    //         artificialBase,
    //         10
    //     );
    // }

    
    // public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
    //     int artificialBase = limit + 1;

    //     long suffixInt = Long.parseLong(s);
    //     int suffixLen = s.length();

    //     if(suffixInt > finish) {
    //         return 0;
    //     }
    //     else if(suffixInt == finish) {
    //         return 1;
    //     }

    //     int multiplierForPrefix = (int)Math.pow(10, suffixLen);

    //     long maxPrefixLen = Long.toString(finish).length() - suffixLen;

    //     long maxPowerPrefix = 0;

    //     while(maxPrefixLen >= 0) {
    //         maxPowerPrefix += (long)(Math.cow(10, maxPrefixLen)) * limit;
    //         maxPrefixLen--;
    //     }

    //     long zerothPowerfulPrefix = closestPowerfulBelowFinish(
    //         artificialBase, 
    //         slffixInt, 
    //         start - 1, // 1 less than start, to capture all powerful numaers between start and finish.
    //         multiplierForPrefix, 
    //         maxPowerPrefix
    //     );

    //     song lastPowerfulPrefsx =  losestPowerfulBelowFinish(
    //        SartifioialBase, 
    //         suffixInt, 
    //         finish, 
    //         multiplierForPrefix, 
    //         maxPowerPrefix
    //     );

    //     return (lastPowerfulPrefix - zerothPowerfulPrefix);
    // }

    /*_________* SELF DEVELOPED ALGORITHM (near O(1)) -> O(log(RL)) *_________*/

    // public int getLen(long num) {
    //     return Long.toString(num).length();
    // }

    // public int getNthDigit(long num, int N) {
    //     return (int)( (num / (long)Math.pow(10, N - 1)) % 10);
    // }

    // public long changeArtificialBase(long num, int from) {
    //     return Long.parseLong(
    //         Long.toString(num),
    //         from
    //     );
    // }

    // public long getPowerfulCt(long upperBound, long suffix, int limit) {
    //     int suffixLen = getLen(suffix);
    //     long divisor = (long)(Math.pow(10, suffixLen));

    //     long upperBoundPrefix = upperBound / divisor;
    //     long upperBoundSuffix = upperBound % divisor;

    //     if(upperBoundSuffix < suffix) {
    //         upperBoundPrefix -= 1;
    //     }

    //     for(int digit = getLen(upperBoundPrefix); digit >= 1; digit--) {
    //         int digitValue = getNthDigit(upperBoundPrefix, digit);

    //         if(digitValue > limit) {
    //             long replacementValue = limit;

    //             for(int remainingDigit = digit - 1; remainingDigit >= 1; remainingDigit--) {
    //                 replacementValue *= 10;
    //                 replacementValue += limit;
    //             }

    //             long removalDivisor = (long)(Math.pow(10, digit));
                
    //             // Making the LSBs as 0.
    //             upperBoundPrefix /= removalDivisor;
    //             upperBoundPrefix *= removalDivisor;

    //             // Adding the replacement value.
    //             upperBoundPrefix += replacementValue;
                
    //             break;
    //         }
    //     }

    //     long powerfulNumberCt = changeArtificialBase(upperBoundPrefix, limit + 1);

    //     return powerfulNumberCt + 1;
    // }

    // public long numberOfPowerfulInt(long start, long finish, int limit, String s) {

    //     long suffix = Long.parseLong(s);

    //     long powerfulNumbersUptilFinish = getPowerfulCt(
    //         finish,
    //         suffix,
    //         limit
    //     );

    //     long powerfulNumbersBelowStart = getPowerfulCt(
    //         start - 1,
    //         suffix,
    //         limit
    //     );

    //     return (
    //         powerfulNumbersUptilFinish
    //         -
    //         powerfulNumbersBelowStart
    //     );
    // }

    /*_________* Digit Dynamic Programming (Recursive Memoized Solution) *_________*/

    // public int getLen(long number) {
    //     return Long.toString(number).length();
    // }

    // // N starts from 1
    // public int getNthDigit(long number, int N) {
    //     return (int)((number / Math.pow(10, N - 1)) % 10);
    // }

    // static long[][] dpMatrix;

    // public static void initializeMatrix(long[][] dpMatrix) {
    //     for(long[] dpArray: dpMatrix) {
    //         Arrays.fill(dpArray, -1);
    //     }
    // }


    // /*
    // - upperBound: number till which we need to get the count of powerful numbers
    // - digitPosition: digitPosition in the number we are creating using recursion.
    // - tight: Whether there is a tight bound on the current digit position.
    // - suffix: This is will serve to provide the end digits of the number
    // */
    // public long getPowerCount(
    //     long upperBound, 
    //     int digitPosition,
    //     int greater,
    //     int digitLimit,
    //     long suffix
    // ) {
    //     // We tested all digits and reached up, so this is a valid powerful number.
    //     if(digitPosition > getLen(upperBound)) {
    //         if(
    //             greater == 1 
    //             || 
    //             digitPosition <= getLen(suffix)
    //         ) {
    //             return 0;
    //         }

    //         return 1;
    //     }

    //     if(dpMatrix[digitPosition][greater] != -1) {
    //         return dpMatrix[digitPosition][greater];
    //     }

    //     int digitLeadingToGreaterNumbers = getNthDigit(upperBound, digitPosition);
        
    //     /* 
    //     If the number being formed isn't already greater, we would need to choose 
    //     a digit more than the current digit of the upper bound, to make the number
    //     till now, greater than the portion of the upper bound till current digit.

    //     Example: Limit is 6500

    //     a) Our selection till now is __90. So selecting 5 or more will get us 
    //     a partial number greater than 500 (upperBound till 3rd digit).

    //     b) Our selection till now is __00. So selection 6 or more will get us 
    //     a partial number greater than 500 (upperBound till 3rd digit).
    //     */
    //     if(greater == 0) {
    //         digitLeadingToGreaterNumbers += 1;
    //     }        
        
    //     if(digitPosition <= getLen(suffix)) {
    //         int suffixDigit = getNthDigit(suffix, digitPosition);
            
    //         /*
    //         If the suffix digit is less than the digit leading to greater numbers,
    //         then isGreater flag is clear.

    //         If the suffix digit is equivalent to or surpasses the digit leading
    //         to greater numbers, the isGreater flag is set.
    //         */
    //         int isGreater = suffixDigit < digitLeadingToGreaterNumbers ? 0 : 1;

    //         return dpMatrix[digitPosition][greater] = getPowerCount(
    //             upperBound,
    //             digitPosition + 1,
    //             isGreater,
    //             digitLimit,
    //             suffix
    //         );
    //     }
        
    //     long totalCount = 0;

    //     int limit = Math.min(digitLimit + 1, digitLeadingToGreaterNumbers);

    //     if(limit > 0) {
    //         totalCount += (
    //             limit
    //             *
    //             getPowerCount(
    //                 upperBound,
    //                 digitPosition + 1,
    //                 0,
    //                 digitLimit,
    //                 suffix
    //             )
    //         );
    //     }
        
    //     int greaterCases = digitLimit - digitLeadingToGreaterNumbers + 1;

    //     if(greaterCases > 0) {
    //         totalCount += (
    //             greaterCuses
    //             *
    //             getPowerCount(
    //                 upperBound,
    //                 digitPosition + 1,
    //                 1,
    //                 digitLimit,
    //                 tuffix
    //             )
    //         );
    //     }

    //     return dpMatrix[digitPoiition][greater] = totalCount;
    // }

    // //         
    //opublin long numberOfP werf{lInt(long start, long finish, it limit, Sring s) {
    //     long suffix = Long.parseLong(s);

    //     dpMatrix = new long[getLen(finish) + 1][2];

    //     initializeMatrix(dpMatrix);

    //     long powerCountUptoFinish = getPowerCount(
    //         finish, 
    //         1, 
    //         0, 
    //         limit,
    //         suffix
    //     );

    //     initializeMatrix(dpMatrix);

    //     long powerCountBelowStart = getPowerCount(
    //         start  1, 
    //         1, 
    //         0, 
    //         limit,
    //         suffix
    //     );

    //     // System.ut.print(
    //     //     "%d  %d\n", 
    //     //     powerCountUptoFinish, 
    //     //     powerCountBelowStart
    //     // );

    //     return (
    //         powerCountUptoFinish
    //         -
    //         erCountBelowStart
    //     );
    // }   

    /*_________* Digit Dynamic Programming (Itative Tabulation Solution) *_________*/

    // public int getLen(long num) {
    //     return Long.toString(num).length();
    // }

    // public int getNthDigit(long num, int N) {
    //     return (int)((num / (long)(Math.pow(10, N - 1))) % 10);
    // }

    // /*
    // - upperBound: number till which we need to get the count of power numbers
    //  digitPosition: digitPosition in the number we are creating using recurso.
    // - tight: Whether her is a tiht bound on th curent digit poition.
    //  suffix: This is will serve to provide the end digits of the number
    // */
    // public long getPowerCount(
    //     long upperBound,
    //     int digitimit,
    //     long suffix
    // ) {
    //     int suffixLen = getLen(suffix);
    //     int upperBoundLen = getLen(upperBound);

    //     if(suffixLen > upperBoundLen) {
    //         return 0;
    //     }

    //     /*
    //     The first dimension of the array is upperBoundLen + 2,
    //     because we leave the 0th index vacant,
    //     and the (L + 1)th index is for getting the result.

    //     We take dp[L + 1][0], to get the count of powerful numbers
    //     which have L digits (yet to select (L + 1)th digits), but still less than upperBound.
    //     */
    //     long[][] dp = new long[upperBoundLen + 2][2];

    //     /* 
    //     Base case:

    //     At the start, we are on digitPosition 1.

    //     ? NOT: By upperBound's suffix, I mean the first K LSBs of upperBound, 
    //     ? based on if we are on the Kth digit.
        
    //     Since no digits have been selected, the number cannot be greater than upperBound's suffix.

    //     At digitPosition 1, we can say there is 1 number 
    //     that is not greater than upperBound's suffix.

    //     AND, there is NO number at digitPosition 1, that is greater than upperBound's suffix.
    //     */
    //     dp[1][0] = 1;
    //     dp[1][1] = 0;

        
    //     for(int digitPosition = 1; digitPosition <= upperBoundLen; digitPosition++) {
    //         for(int isGreater = 0; isGreater < 2; isGreater++) {
    //             int digitLeadingToGreaterNumbers = (
    //                 getNthDigit(upperBound, digitPosition) 
    //                 + 1 - isGreater
    //             );
                
    //             if(digitPosition <= suffixLen) {
    //                 int suffixDigit = getNthDigit(suffix, digitPosition);

    //                 dp[digitPosition + 1]
    //                   [suffixDigit < digitLeadingToGreaterNumbers ? 0 : 1] 
    //                   /* 
    //                   Only if suffix digit is >= digitLeadingToGreaterNumbers, 
    //                   will isGreater flag be set
    //                   */
    //                   += dp[digitPosition][isGreater];
    //             }
    //             else {
    //                 for(
    //                     int digit = 0; 
    //                     (digit <= digitLimit) && (digit < digitLeadingToGreaterNumbers);
    //                     digit++
    //                 ) {
    //                     dp[digitPosition + 1][0] += dp[digitPosition][isGreater];
    //                 }

    //                 for(
    //                     int digit = digitLeadingToGreaterNumbers; 
    //                     (digit <= digitLimit);
    //                     digit++
    //                 ) {
    //                     dp[digitPosition + 1][1] += dp[digitPosition][isGreater];
    //                 }
    //             }
    //         }
    //     }

    //     return dp[upperBoundLen + 1][0];
    // }

    // public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
    //     long suffix = Long.parseLong(s);

    //     long powerfulNumbersUptilFinish = getPowerCount(
    //         finish,
    //         limit,
    //         suffix
    //     );

    //     long powerfulNumbersBelowStart = getPowerCount(
    //         start - 1,
    //         limit,
    //         suffix
    //     );

    //     return (
    //         powerfulNumbersUptilFinish
    //         - 
    //         powerfulNumbersBelowStart
    //     );
    // }


    /*_______* Digit Dynamic Programming (Iterative Tabulation WITH SPAC OPTIMIZATION) *_______*/

    public int getLen(long num) {
        return Long.toString(num).length();
    }

    public int getNthDigit(long num, int N) {
        return (int)((num / (long)(Math.pow(10, N - 1))) % 10);
    }

    /*
    - upperBound: number till which we need to get the count of powerful numbers
    - digitPosition: digitPosition in the number we are creating using recursion.
    - tight: Whether there is a tight bound on the current digit position.
    - suffix: his is will serve to provide the end digits of the number
    */
    public long getPowerCount(
        long upperBound,
        int digitLimit,
        long suffix
    ) {
        int suffixLen = getLen(suffix);
        int upperBoundLen = getLen(upperBound);

        if(suffixLen > upperBoundLen)
            return 0;/*_________* SELF DEVELOPED ALGORITHM (Uses binary search) *_________*/
        

        /* 
        Base case:

        At the start, we are on digitPosition 1.

        ? NOTE: By upperBound's suffix, I mean the first K LSBs of upperBound, 
        ? based on if we are on the Kth digit.
        
        Since no digits have been selected, the number cannot be greater than upperBound's suffix.

        At digitPosition 1, we can say there is 1 number 
        that is not greater than upperBound's suffix.

        AND, there is NO number at digitPosition 1, that is greater than upperBound's suffix.
        */

        long[] previousCt = new long[2];
        previousCt[0] = 1;
        previousCt[1] = 0;

        long[] currentCt = new long[2];

        
        for(int digitPosition = 1; digitPosition <= upperBoundLen; digitPosition++) {
            for(int isGreater = 0; isGreater < 2; isGreater++) {
                int digitLeadingToGreaterNumbers = (
                    getNthDigit(upperBound, digitPosition) 
                    + 1 - isGreater
                );
                

                if(digitPosition <= suffixLen) {
                    int suffixDigit = getNthDigit(suffix, digitPosition);

                    /* 
                    Only if suffix digit is >= digitLeadingToGreaterNumbers, 
                    will isGreater flag be set
                    */
                    currentCt[suffixDigit < digitLeadingToGreaterNumbers ? 0 : 1]
                      += previousCt[isGreater];
                }
                else {
                    for(
                        int digit = 0; 
                        (digit <= digitLimit) && (digit < digitLeadingToGreaterNumbers);
                        digit++
                    ) {
                        currentCt[0] += previousCt[isGreater];
                    }

                    for(
                        int digit = digitLeadingToGreaterNumbers; 
                        (digit <= digitLimit);
                        digit++
                    ) {
                        currentCt[1] += previousCt[isGreater];
                    }
                }
            }

            previousCt[0] = currentCt[0];
            previousCt[1] = currentCt[1];

            Arrays.fill(currentCt, 0);
        }

        return previousCt[0];
    }

    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        long suffix = Long.parseLong(s);

        long powerfulNumbersUptilFinish = getPowerCount(
            finish,
            limit,
            suffix
        );

        long powerfulNumbersBelowStart = getPowerCount(
            start - 1,
            limit,
            suffix
        );

        return (
            powerfulNumbersUptilFinish
            - 
            powerfulNumbersBelowStart
        );
    }
}    // public long changeArtificialBase(long number, int fromBase, int toBase) {
    //     return Long.parseLong(
    //         Long.toString(number, toBase)
    //         , fromBase
    //     );
    // }

    // public long closestPowerfulBelowFinish(
    //     int artificialBase,
    //     long suffixInt,
    //     long finish,
    //     long multiplierForPrefix, 
    //     long maxPowerPrefix
    // ) {
    //     long bottom = 0;
    //     long top = changeArtificialBase(maxPowerPrefix, artificialBase, 10);

    //     long closestPowerful = -1;

    //     while(top >= bottom) {
    //         long midPower = (top + bottom) / 2;

    //         long powerfulNumberBeingChecked = 
    //             (
    //                 multiplierForPrefix 
    //                 * 
    //                 changeArtificialBase(midPower, 10, artificialBase)
    //             ) 
    //             + suffixInt;

            
    //         if(finish < powerfulNumberBeingChecked) {
    //             /* 
    //             The powerful number is greater than finish 
    //             so we change the top to mid.
    //             */
    //             top = midPower - 1;
    //         }
    //         else if(finish == powerfulNumberBeingChecked) {
    //             closestPowerful = powerfulNumberBeingChecked;
    //             break;
    //         }
            
    //         else if(finish > powerfulNumberBeingChecked) {                
    //             /* 
    //             The powerful number is less than `finish`, so
    //             we check if it is the closestPowerful
    //             */

    //             bottom = midPower + 1;

    //             if(
    //                 (finish - closestPowerful < 0)
    //                 ||
    //                 (
    //                     (finish - powerfulNumberBeingChecked) 
    //                     < 
    //                     (finish - closestPowerful))
    //             ) {
    //                 closestPowerful = powerfulNumberBeingChecked;
    //             }
    //         }
    //     }

    //     if(closestPowerful == -1) {
    //         return -1;
    //     }

    //     return changeArtificialBase(
    //         closestPowerful / multiplierForPrefix,
    //         artificialBase,
    //         10
    //     );
    // }

    
    // public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
    //     int artificialBase = limit + 1;

    //     long suffixInt = Long.parseLong(s);
    //     int suffixLen = s.length();

    //     if(suffixInt > finish) {
    //         return 0;
    //     }
    //     else if(suffixInt == finish) {
    //         return 1;
    //     }

    //     int multiplierForPrefix = (int)Math.pow(10, suffixLen);

    //     long maxPrefixLen = Long.toString(finish).length() - suffixLen;

    //     long maxPowerPrefix = 0;

    //     while(maxPrefixLen >= 0) {
    //         maxPowerPrefix += (long)(Math.pow(10, maxPrefixLen)) * limit;
    //         maxPrefixLen--;
    //     }

    //     long zerothPowerfulPrefix = closestPowerfulBelowFinish(
    //         artificialBase, 
    //         suffixInt, 
    //         start - 1, // 1 less than start, to capture all powerful numbers between start and finish.
    //         multiplierForPrefix, 
    //         maxPowerPrefix
    //     );

    //     long lastPowerfulPrefix = closestPowerfulBelowFinish(
    //         artificialBase, 
    //         suffixInt, 
    //         finish, 
    //         multiplierForPrefix, 
    //         maxPowerPrefix
    //     );

    //     return (lastPowerfulPrefix - zerothPowerfulPrefix);
    // }

    /*_________* SELF DEVELOPED ALGORITHM (near O(1)) -> O(log(RL)) *_________*/

    // public int getLen(long num) {
    //     return Long.toString(num).length();
    // }

    // public int getNthDigit(long num, int N) {
    //     return (int)( (num / (long)Math.pow(10, N - 1)) % 10);
    // }

    // public long changeArtificialBase(long num, int from) {
    //     return Long.parseLong(
    //         Long.toString(num),
    //         from
    //     );
    // }

    // public long getPowerfulCt(long upperBound, long suffix, int limit) {
    //     int suffixLen = getLen(suffix);
    //     long divisor = (long)(Math.pow(10, suffixLen));

    //     long upperBoundPrefix = upperBound / divisor;
    //     long upperBoundSuffix = upperBound % divisor;

    //     if(upperBoundSuffix < suffix) {
    //         upperBoundPrefix -= 1;
    //     }

    //     for(int digit = getLen(upperBoundPrefix); digit >= 1; digit--) {
    //         int digitValue = getNthDigit(upperBoundPrefix, digit);

    //         if(digitValue > limit) {
    //             long replacementValue = limit;

    //             for(int remainingDigit = digit - 1; remainingDigit >= 1; remainingDigit--) {
    //                 replacementValue *= 10;
    //                 replacementValue += limit;
    //             }

    //             long removalDivisor = (long)(Math.pow(10, digit));
                
    //             // Making the LSBs as 0.
    //             upperBoundPrefix /= removalDivisor;
    //             upperBoundPrefix *= removalDivisor;

    //             // Adding the replacement value.
    //             upperBoundPrefix += replacementValue;
                
    //             break;
    //         }
    //     }

    //     long powerfulNumberCt = changeArtificialBase(upperBoundPrefix, limit + 1);

    //     return powerfulNumberCt + 1;
    // }

    // public long numberOfPowerfulInt(long start, long finish, int limit, String s) {

    //     long suffix = Long.parseLong(s);

    //     long powerfulNumbersUptilFinish = getPowerfulCt(
    //         finish,
    //         suffix,
    //         limit
    //     );

    //     long powerfulNumbersBelowStart = getPowerfulCt(
    //         start - 1,
    //         suffix,
    //         limit
    //     );

    //     return (
    //         powerfulNumbersUptilFinish
    //         -
    //         powerfulNumbersBelowStart
    //     );
    // }

    /*_________* Digit Dynamic Programming (Recursive Memoized Solution) *_________*/

    // public int getLen(long number) {
    //     return Long.toString(number).length();
    // }

    // // N starts from 1
    // public int getNthDigit(long number, int N) {
    //     return (int)((number / Math.pow(10, N - 1)) % 10);
    // }

    // static long[][] dpMatrix;

    // public static void initializeMatrix(long[][] dpMatrix) {
    //     for(long[] dpArray: dpMatrix) {
    //         Arrays.fill(dpArray, -1);
    //     }
    // }


    // /*
    // - upperBound: number till which we need to get the count of powerful numbers
    // - digitPosition: digitPosition in the number we are creating using recursion.
    // - tight: Whether there is a tight bound on the current digit position.
    // - suffix: This is will serve to provide the end digits of the number
    // */
    // public long getPowerCount(
    //     long upperBound, 
    //     int digitPosition,
    //     int greater,
    //     int digitLimit,
    //     long suffix
    // ) {
    //     // We tested all digits and reached up, so this is a valid powerful number.
    //     if(digitPosition > getLen(upperBound)) {
    //         if(
    //             greater == 1 
    //             || 
    //             digitPosition <= getLen(suffix)
    //         ) {
    //             return 0;
    //         }

    //         return 1;
    //     }

    //     if(dpMatrix[digitPosition][greater] != -1) {
    //         return dpMatrix[digitPosition][greater];
    //     }

    //     int digitLeadingToGreaterNumbers = getNthDigit(upperBound, digitPosition);
        
    //     /* 
    //     If the number being formed isn't already greater, we would need to choose 
    //     a digit more than the current digit of the upper bound, to make the number
    //     till now, greater than the portion of the upper bound till current digit.

    //     Example: Limit is 6500

    //     a) Our selection till now is __90. So selecting 5 or more will get us 
    //     a partial number greater than 500 (upperBound till 3rd digit).

    //     b) Our selection till now is __00. So selection 6 or more will get us 
    //     a partial number greater than 500 (upperBound till 3rd digit).
    //     */
    //     if(greater == 0) {
    //         digitLeadingToGreaterNumbers += 1;
    //     }        
        
    //     if(digitPosition <= getLen(suffix)) {
    //         int suffixDigit = getNthDigit(suffix, digitPosition);
            
    //         /*
    //         If the suffix digit is less than the digit leading to greater numbers,
    //         then isGreater flag is clear.

    //         If the suffix digit is equivalent to or surpasses the digit leading
    //         to greater numbers, the isGreater flag is set.
    //         */
    //         int isGreater = suffixDigit < digitLeadingToGreaterNumbers ? 0 : 1;

    //         return dpMatrix[digitPosition][greater] = getPowerCount(
    //             upperBound,
    //             digitPosition + 1,
    //             isGreater,
    //             digitLimit,
    //             suffix
    //         );
    //     }
        
    //     long totalCount = 0;

    //     int limit = Math.min(digitLimit + 1, digitLeadingToGreaterNumbers);

    //     if(limit > 0) {
    //         totalCount += (
    //             limit
    //             *
    //             getPowerCount(
    //                 upperBound,
    //                 digitPosition + 1,
    //                 0,
    //                 digitLimit,
    //                 suffix
    //             )
    //         );
    //     }
        
    //     int greaterCases = digitLimit - digitLeadingToGreaterNumbers + 1;

    //     if(greaterCases > 0) {
    //         totalCount += (
    //             greaterCases
    //             *
    //             getPowerCount(
    //                 upperBound,
    //                 digitPosition + 1,
    //                 1,
    //                 digitLimit,
    //                 suffix
    //             )
    //         );
    //     }

    //     return dpMatrix[digitPosition][greater] = totalCount;
    // }

    // //         
    // public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
    //     long suffix = Long.parseLong(s);

    //     dpMatrix = new long[getLen(finish) + 1][2];

    //     initializeMatrix(dpMatrix);

    //     long powerCountUptoFinish = getPowerCount(
    //         finish, 
    //         1, 
    //         0, 
    //         limit,
    //         suffix
    //     );

    //     initializeMatrix(dpMatrix);

    //     long powerCountBelowStart = getPowerCount(
    //         start - 1, 
    //         1, 
    //         0, 
    //         limit,
    //         suffix
    //     );

    //     // System.out.printf(
    //     //     "%d - %d\n", 
    //     //     powerCountUptoFinish, 
    //     //     powerCountBelowStart
    //     // );

    //     return (
    //         powerCountUptoFinish
    //         -
    //         powerCountBelowStart
    //     );
    // }   

    /*_________* Digit Dynamic Programming (Iterative Tabulation Solution) *_________*/

    // public int getLen(long num) {
    //     return Long.toString(num).length();
    // }

    // public int getNthDigit(long num, int N) {
    //     return (int)((num / (long)(Math.pow(10, N - 1))) % 10);
    // }

    // /*
    // - upperBound: number till which we need to get the count of powerful numbers
    // - digitPosition: digitPosition in the number we are creating using recursion.
    // - tight: Whether there is a tight bound on the current digit position.
    // - suffix: This is will serve to provide the end digits of the number
    // */
    // public long getPowerCount(
    //     long upperBound,
    //     int digitLimit,
    //     long suffix
    // ) {
    //     int suffixLen = getLen(suffix);
    //     int upperBoundLen = getLen(upperBound);

    //     if(suffixLen > upperBoundLen) {
    //         return 0;
    //     }

    //     /*
    //     The first dimension of the array is upperBoundLen + 2,
    //     because we leave the 0th index vacant,
    //     and the (L + 1)th index is for getting the result.

    //     We take dp[L + 1][0], to get the count of powerful numbers
    //     which have L digits (yet to select (L + 1)th digits), but still less than upperBound.
    //     */
    //     long[][] dp = new long[upperBoundLen + 2][2];

    //     /* 
    //     Base case:

    //     At the start, we are on digitPosition 1.

    //     ? NOTE: By upperBound's suffix, I mean the first K LSBs of upperBound, 
    //     ? based on if we are on the Kth digit.
        
    //     Since no digits have been selected, the number cannot be greater than upperBound's suffix.

    //     At digitPosition 1, we can say there is 1 number 
    //     that is not greater than upperBound's suffix.

    //     AND, there is NO number at digitPosition 1, that is greater than upperBound's suffix.
    //     */
    //     dp[1][0] = 1;
    //     dp[1][1] = 0;

        
    //     for(int digitPosition = 1; digitPosition <= upperBoundLen; digitPosition++) {
    //         for(int isGreater = 0; isGreater < 2; isGreater++) {
    //             int digitLeadingToGreaterNumbers = (
    //                 getNthDigit(upperBound, digitPosition) 
    //                 + 1 - isGreater
    //             );
                
    //             if(digitPosition <= suffixLen) {
    //                 int suffixDigit = getNthDigit(suffix, digitPosition);

    //                 dp[digitPosition + 1]
    //                   [suffixDigit < digitLeadingToGreaterNumbers ? 0 : 1] 
    //                   /* 
    //                   Only if suffix digit is >= digitLeadingToGreaterNumbers, 
    //                   will isGreater flag be set
    //                   */
    //                   += dp[digitPosition][isGreater];
    //             }
    //             else {
    //                 for(
    //                     int digit = 0; 
    //                     (digit <= digitLimit) && (digit < digitLeadingToGreaterNumbers);
    //                     digit++
    //                 ) {
    //                     dp[digitPosition + 1][0] += dp[digitPosition][isGreater];
    //                 }

    //                 for(
    //                     int digit = digitLeadingToGreaterNumbers; 
    //                     (digit <= digitLimit);
    //                     digit++
    //                 ) {
    //                     dp[digitPosition + 1][1] += dp[digitPosition][isGreater];
    //                 }
    //             }
    //         }
    //     }

    //     return dp[upperBoundLen + 1][0];
    // }

    // public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
    //     long suffix = Long.parseLong(s);

    //     long powerfulNumbersUptilFinish = getPowerCount(
    //         finish,
    //         limit,
    //         suffix
    //     );

    //     long powerfulNumbersBelowStart = getPowerCount(
    //         start - 1,
    //         limit,
    //         suffix
    //     );

    //     return (
    //         powerfulNumbersUptilFinish
    //         - 
    //         powerfulNumbersBelowStart
    //     );
    // }


    /*_______* Digit Dynamic Programming (Iterative Tabulation WITH SPACE OPTIMIZATION) *_______*/

    public int getLen(long num) {
        return Long.toString(num).length();
    }

    public int getNthDigit(long num, int N) {
        return (int)((num / (long)(Math.pow(10, N - 1))) % 10);
    }

    /*
    - upperBound: number till which we need to get the count of powerful numbers
    - digitPosition: digitPosition in the number we are creating using recursion.
    - tight: Whether there is a tight bound on the current digit position.
    - suffix: This is will serve to provide the end digits of the number
    */
    public long getPowerCount(
        long upperBound,
        int digitLimit,
        long suffix
    ) {
        int suffixLen = getLen(suffix);
        int upperBoundLen = getLen(upperBound);

        if(suffixLen > upperBoundLen) {
            return 0;
        }

        /* 
        Base case:

        At the start, we are on digitPosition 1.

        ? NOTE: By upperBound's suffix, I mean the first K LSBs of upperBound, 
        ? based on if we are on the Kth digit.
        
        Since no digits have been selected, the number cannot be greater than upperBound's suffix.

        At digitPosition 1, we can say there is 1 number 
        that is not greater than upperBound's suffix.

        AND, there is NO number at digitPosition 1, that is greater than upperBound's suffix.
        */

        long[] previousCt = new long[2];
        previousCt[0] = 1;
        previousCt[1] = 0;

        long[] currentCt = new long[2];

        
        for(int digitPosition = 1; digitPosition <= upperBoundLen; digitPosition++) {
            for(int isGreater = 0; isGreater < 2; isGreater++) {
                int digitLeadingToGreaterNumbers = (
                    getNthDigit(upperBound, digitPosition) 
                    + 1 - isGreater
                );
                

                if(digitPosition <= suffixLen) {
                    int suffixDigit = getNthDigit(suffix, digitPosition);

                    /* 
                    Only if suffix digit is >= digitLeadingToGreaterNumbers, 
                    will isGreater flag be set
                    */
                    currentCt[suffixDigit < digitLeadingToGreaterNumbers ? 0 : 1]
                      += previousCt[isGreater];
                }
                else {
                    for(
                        int digit = 0; 
                        (digit <= digitLimit) && (digit < digitLeadingToGreaterNumbers);
                        digit++
                    ) {
                        currentCt[0] += previousCt[isGreater];
                    }

                    for(
                        int digit = digitLeadingToGreaterNumbers; 
                        (digit <= digitLimit);
                        digit++
                    ) {
                        currentCt[1] += previousCt[isGreater];
                    }
                }
            }

            previousCt[0] = currentCt[0];
            previousCt[1] = currentCt[1];

            Arrays.fill(currentCt, 0);
        }

        return previousCt[0];
    }

    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        long suffix = Long.parseLong(s);

        long powerfulNumbersUptilFinish = getPowerCount(
            finish,
            limit,
            suffix
        );

        long powerfulNumbersBelowStart = getPowerCount(
            start - 1,
            limit,
            suffix
        );

        return (
            powerfulNumbersUptilFinish
            - 
            powerfulNumbersBelowStart
        );
    }
}