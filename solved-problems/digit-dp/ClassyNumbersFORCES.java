import java.util.*;

public class ClassyNumbersFORCES {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String str = "22";
        str.indexOf("2");

        int n = s.nextInt();

        for(int i = 0; i < n; i++) {
            long L = s.nextLong();
            long R = s.nextLong();

            System.out.println(getClassyCtMemoize(L, R));
        }        
    }

    static int getLen(long num) {
        return Long.toString(num).length();
    }

    static int getNthDigit(long num, int n) {
        return (int)(num / Math.pow(10, n - 1)) % 10;
    }

    /*
    The recursive function has to have
    - a tight flag
    - a count of zeroes till now.
    - the current digit
    */


    static long classyNumbersDigitSelection(int tight, int nonZeroCt, int currentDigit, long M) {
        /*  
        This condition checks if at any point the count of non-zero numbers is greater than 3,
        return 0 so as to not add to the count on non-zero numbers.
        */
        if(nonZeroCt > 3) {
            return 0;
        }
        // If the above check is passed, and all digits have been processed, this number is certified as classy
        else if(currentDigit == 0) {
            return 1;
        }
        
        long totalCount = 0;

        int upperLimit = 10;

        if(tight == 1) { 
            // Setting the upper limit if tight flag is on.
            upperLimit = getNthDigit(M, currentDigit);

            if(upperLimit != 0 && upperLimit != 10) {
                // Selecting the upper-limit digit, only if it is non-0, because 0 is selected below as a digit.
                totalCount += classyNumbersDigitSelection(
                    1, 
                    nonZeroCt + 1, 
                    currentDigit - 1, 
                    M
                );
            }
        }

        // Selection of 0 as a digit.
        totalCount += classyNumbersDigitSelection(
            upperLimit == 0 ? 1 : 0, 
            nonZeroCt, 
            currentDigit - 1, 
            M
        );
            
        for(int digit = 1; digit < upperLimit; digit++) {
            totalCount += classyNumbersDigitSelection(
                0, 
                nonZeroCt + 1, 
                currentDigit - 1,
                M
            );
        }
    

        return totalCount;
    }

    // Recursive solution (No DP)
    static long getClassyCt(long L, long R) {

        int lenL = getLen(L - 1);
        int lenR = getLen(R);

        // System.out.printf("%d, %d\n", lenL, lenR);
        // System.out.printf("%d, %d\n", classyNumbersDigitSelection(true, 0, lenR, R, true), classyNumbersDigitSelection(true, 0, lenL, L - 1, true));

        if(L == 0) {
            return classyNumbersDigitSelection(1, 0, lenR, R);
        }

        return (
            classyNumbersDigitSelection(1, 0, lenR, R)
            -
            classyNumbersDigitSelection(1, 0, lenL, L - 1)
        );
    }

    static long[][][] dpMatrix;

    /*
    The recursive function has to have
    - a tight flag
    - a count of zeroes till now.
    - the current digit
    */

    static long classyNumbersDigitSelectionMemoize(int tight, int nonZeroCt, int currentDigit, long M) {
        /*  
        This condition checks if at any point the count of non-zero numbers is greater than 3,
        return 0 so as to not add to the count on non-zero numbers.
        */
        if(nonZeroCt > 3) {
            return 0;
        }
        else if(dpMatrix[tight][nonZeroCt][currentDigit] != -1) {
            return dpMatrix[tight][nonZeroCt][currentDigit];
        }
        // If the first check is passed, and all digits have been processed, this number is certified as classy
        else if(currentDigit == 0) {
            return 1;
        }
        
        long totalCount = 0;

        int upperLimit = 10;

        if(tight == 1) { 
            // Setting the upper limit if tight flag is on.
            upperLimit = getNthDigit(M, currentDigit);

            if(upperLimit != 0 && upperLimit != 10) {
                // Selecting the upper-limit digit, only if it is non-0, because 0 is selected below as a digit.
                totalCount += classyNumbersDigitSelectionMemoize(
                    1, 
                    nonZeroCt + 1, 
                    currentDigit - 1, 
                    M
                );
            }
        }

        // Selection of 0 as a digit.
        totalCount += classyNumbersDigitSelectionMemoize(
            upperLimit == 0 ? 1 : 0, 
            nonZeroCt, 
            currentDigit - 1, 
            M
        );
            
        for(int digit = 1; digit < upperLimit; digit++) {
            totalCount +=  classyNumbersDigitSelectionMemoize(
                0, 
                nonZeroCt + 1, 
                currentDigit - 1,
                M
            );
        }

        return dpMatrix[tight][nonZeroCt][currentDigit] = totalCount;
    }
    
    static void initialize3dMatrix(long[][][] dpMatrix) {
        for(int tight = 0; tight < 2; tight++) {
            for(int nonZeroCt = 0; nonZeroCt <= 3; nonZeroCt++) {
                Arrays.fill(dpMatrix[tight][nonZeroCt], -1);
            }
        }
    }

    // Recursive solution with memoization
    static long getClassyCtMemoize(long L, long R) {

        int lenL = getLen(L - 1);
        int lenR = getLen(R);

        // System.out.printf("%d, %d\n", lenL, lenR);
        // System.out.printf("%d, %d\n", classyNumbersDigitSelection(true, 0, lenR, R, true), classyNumbersDigitSelection(true, 0, lenL, L - 1, true));
        dpMatrix = new long[2][3 + 1][lenR + 1];
        
        initialize3dMatrix(dpMatrix);
        
        long classyNumbersUptoR = classyNumbersDigitSelectionMemoize(1, 0, lenR, R);

        if(L == 0) {
            return classyNumbersUptoR;
        }

        initialize3dMatrix(dpMatrix);

        long classyNumbersBelowL = classyNumbersDigitSelectionMemoize(1, 0, lenL, L - 1);

        System.out.printf("%d, %d\n", lenR, lenL);
        System.out.printf("%d, %d\n", classyNumbersUptoR, classyNumbersBelowL);

        return (
            classyNumbersUptoR
            -
            classyNumbersBelowL
        );
    }

}
