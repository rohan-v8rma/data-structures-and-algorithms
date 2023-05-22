package solved-problems;

class Solution {

    
    public int orangesRotting(int[][] grid) {

        int outerIndexLim = grid.length - 1;
        int innerIndexLim = grid[0].length - 1;

        /* 
        Instead of using the queue for oranges that are yet to be rotten,
        we will use it for oranges that are rotten, and whose neighbors 
        have to be rotten.
        */
        Queue<int[]> bfsQueue = new LinkedList<>();
        int timeTaken = 0;

        for(int outerIndex = 0; outerIndex <= outerIndexLim; outerIndex++) {
            for(int innerIndex = 0; innerIndex <= innerIndexLim; innerIndex++) {
                // Adding the already rotten oranges to queue.
                if(grid[outerIndex][innerIndex] == 2) {
                    /* 
                    Third element is the time taken. 
                    If we encounter this rotten orange from queue, 
                    we will set the time taken to 0, since it took 0 seconds to rot this orange,
                    as it was already rotten.
                    */
                    bfsQueue.offer(new int[]{outerIndex, innerIndex, 0});;
                }
            }
        }

        while(!bfsQueue.isEmpty()) {
            int[] currentRottenOrange = bfsQueue.poll();
            /* 
            We update the time taken according to the time 
            it took to rot the current rotten orange.
            */
            timeTaken = currentRottenOrange[2];

            int outerIndex = currentRottenOrange[0];
            int innerIndex = currentRottenOrange[1];

            if(
                (outerIndex - 1 >= 0) 
                && 
                (grid[outerIndex - 1][innerIndex] == 1)
            ) {
                grid[outerIndex - 1][innerIndex] = 2;
                /* 
                Adding the newly rotten orange to the queue of rotten oranges to be processed.

                We increment the add 1 to the timeTaken because the new orange we are adding 
                will be rotted after 1 second of the current rotted orange,
                which is responsible for rotting of the new orange.
                */
                bfsQueue.offer(new int[]{outerIndex - 1, innerIndex, timeTaken + 1});
            }
                
            if(
                (outerIndex + 1 <= outerIndexLim) 
                && 
                (grid[outerIndex + 1][innerIndex] == 1)
            ) {
                grid[outerIndex + 1][innerIndex] = 2;
                /* 
                Adding the newly rotten orange to the queue of rotten oranges to be processed.

                We increment the add 1 to the timeTaken because the new orange we are adding 
                will be rotted after 1 second of the current rotted orange,
                which is responsible for rotting of the new orange.
                */
                bfsQueue.offer(new int[]{outerIndex + 1, innerIndex, timeTaken + 1});
            }

            if(
                (innerIndex - 1 >= 0)
                && 
                (grid[outerIndex][innerIndex - 1] == 1)
            ) {
                grid[outerIndex][innerIndex - 1] = 2;
                /* 
                Adding the newly rotten orange to the queue of rotten oranges to be processed.

                We increment the add 1 to the timeTaken because the new orange we are adding 
                will be rotted after 1 second of the current rotted orange,
                which is responsible for rotting of the new orange.
                */
                bfsQueue.offer(new int[]{outerIndex, innerIndex - 1, timeTaken + 1});
            }

            if(
                (innerIndex + 1 <= innerIndexLim)
                && 
                (grid[outerIndex][innerIndex + 1] == 1)
            ) {
                grid[outerIndex][innerIndex + 1] = 2;
                /* 
                Adding the newly rotten orange to the queue of rotten oranges to be processed.

                We increment the add 1 to the timeTaken because the new orange we are adding 
                will be rotted after 1 second of the current rotted orange,
                which is responsible for rotting of the new orange.
                */
                bfsQueue.offer(new int[]{outerIndex, innerIndex + 1, timeTaken + 1});
            }
        }

        
        if(areFreshOrangesLeft(grid)) {
            return -1;
        }

        return timeTaken;
            
    }


    boolean areFreshOrangesLeft(int[][] grid) {
        int ct = 0;
        
        for(int[] array: grid) {
            for(int element: array) {
                if(element == 1) {
                    return true;
                }
            }
        }

        return false;
    }
}