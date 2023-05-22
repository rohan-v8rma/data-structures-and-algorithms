class Solution {
    static int jumpEnergy(int i, int j, int heights[]) {
        return(Math.abs(heights[i - 1] - heights[j - 1]));
    }


    // Recursive solution. TOP DOWN approach
    static int energyRecursive(int finalStep, int heights[]) {
        /* 
        This is the 1st base case. 
        Since we are already at step 1, no energy expended.
        */
        if(finalStep == 1) {
            return 0;
        }

        /* 
        This is the 2nd base case. 
        At step 2, the only option is to come from step 1.
        */
        if(finalStep == 2) {
            return jumpEnergy(2, 1, heights);
        }   

        return Math.min(
            energyRecursive(finalStep - 1, heights) 
            + 
            jumpEnergy(finalStep, finalStep - 1, heights)
            ,
            energyRecursive(finalStep - 2, heights) 
            + 
            jumpEnergy(finalStep, finalStep - 2, heights)

        );
    }

    // Memoization solution, still TOP DOWN
    static int energyMemoized(int finalStep, int heights[], int[] energiesExpended) {
        /* 
        This is the 1st base case. 
        Since we are already at step 1, no energy expended.
        */
        if(finalStep == 1) {
            return 0;
        }

        /* 
        This is the 2nd base case. 
        At step 2, the only option is to come from step 1.
        */
        if(finalStep == 2) {
            return jumpEnergy(2, 1, heights);
        }   

        if(energiesExpended[finalStep] != -1)
            return energiesExpended[finalStep];

        return energiesExpended[finalStep] = Math.min(
            energyMemoized(finalStep - 1, heights, energiesExpended) 
            + 
            jumpEnergy(finalStep, finalStep - 1, heights)
            ,
            energyMemoized(finalStep - 2, heights, energiesExpended) 
            + 
            jumpEnergy(finalStep, finalStep - 2, heights)

        );
    }

    // Tabulation solution, BOTTOM UP approach.
    static int energyTabulated(int finalStep, int[] heights) {
        // For 1-based indexing.
        int[] energiesExpended = new int[finalStep + 1];

        energiesExpended[1] = 0;
        energiesExpended[2] = jumpEnergy(2, 1, heights);

        for(int step = 3; step <= finalStep; step++) {
            energiesExpended[step] = 
                Math.min(
                    energiesExpended[step - 1]
                    +
                    jumpEnergy(step, step - 1, heights)
                    ,
                    energiesExpended[step - 2]
                    +
                    jumpEnergy(step, step - 2, heights)
                );
        }

        return energiesExpended[finalStep];
    }

    // Tabulation solution, BOTTOM UP approach. CONSTANT space complexity.
    static int energyTabulatedOptimized(int finalStep, int[] heights) {
        
        /* 
        Assuming the current step is 2. 
        This will be lastStep for calculation of step 3.
        */
        int currentStepEnergy = jumpEnergy(2, 1, heights);
        /* 
        Assuming the last step is 1. 
        This will be secondToLastStep for calculation of step 3.
        */
        int lastStepEnergy = 0;

        // No step is secondToLast yet.
        int secondToLastStepEnergy = -1;

        for(int step = 3; step <= finalStep; step++) {
            secondToLastStepEnergy = lastStepEnergy;
            lastStepEnergy = currentStepEnergy;

            currentStepEnergy =  
                Math.min(
                    lastStepEnergy
                    +
                    jumpEnergy(step, step - 1, heights)
                    ,
                    secondToLastStepEnergy
                    +
                    jumpEnergy(step, step - 2, heights)
                );
        }

        return currentStepEnergy;
    }
    
    public static int frogJump(int n, int heights[]) {

        // Recursive
        // return energyRecursive(n, heights);

        // Memoization
        /* 
        The only variable is the step till which we have to go.
        So, that is why we will take a 1-D array called `energiesExpended`
        */

        // int[] energiesExpended = new int[n + 1]; // n + 1 to allow for 1-based indexing
        // for(int index = 0; index <= n; index++)
        //     energiesExpended[index] = -1;

        // return energyMemoized(n, heights, energiesExpended);


        // Tabulation
        // return energyTabulated(n, heights);

        // Tabulation with SPACE OPTIMIZATION
        return energyTabulatedOptimized(n, heights);


        // Write your code here..
    }

}