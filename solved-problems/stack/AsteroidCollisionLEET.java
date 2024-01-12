// https://leetcode.com/problems/asteroid-collision

class Solution {
    // // Uses ArrayList as a stack
    // public int[] asteroidCollision(int[] asteroids) {
    //     ArrayList<Integer> remainingAsteroids = new ArrayList<>();

    //     for(int asteroid: asteroids) {
    //         while(asteroid < 0 && getPrevAsteroid(remainingAsteroids) > 0) {
    //             int collisionResult = getPrevAsteroid(remainingAsteroids) + asteroid;

    //             /* 
    //             Asteroid on top of stack is bigger, so current asteroid explodes
    //             (we set it to 0 in order to indicate that)
    //             OR
    //             Both are equal in magnitude, so both explode
    //             */
    //             if(collisionResult >= 0) {
    //                 asteroid = 0; // By setting asteroid to 0, while-loop is also broken.
    //             }
                
    //             /*
    //             Current asteroid bigger, so previous asteroid explodes.
    //             OR
    //             Both are equal in magnitude, so both explode
    //             */
    //             if(collisionResult <= 0) {
    //                 remainingAsteroids.remove(remainingAsteroids.size() - 1);
    //             }
    //         }

    //         // If the asteroid didn't explode, add it to the array
    //         if(asteroid != 0) {
    //             remainingAsteroids.add(asteroid);
    //         }

    //     }

    //     return remainingAsteroids.stream().mapToInt(Integer::intValue).toArray();
    // }

    // static int getPrevAsteroid(ArrayList<Integer> remainingAsteroids) {
    //     if(remainingAsteroids.isEmpty()) {
    //         return -1001;
    //     }
        
    //     return remainingAsteroids.get(remainingAsteroids.size() - 1);
    // }

    // // Uses Deque as a stack
    // public int[] asteroidCollision(int[] asteroids) {
    //     // Using stack is not advisable since it has lot of overhead of synchronization (being thread-safe)
    //     // Stack<Integer> remainingAsteroids = new Stack<>();
        
    //     // Deque is a better option, for most applications
    //     Deque<Integer> remainingAsteroids = new ArrayDeque<>();

    //     for(int asteroid: asteroids) {
            
    //         while(
    //             !remainingAsteroids.isEmpty() // There are unexploded asteroids available for conflict
    //             &&
    //             asteroid < 0 // Current asteroid moving RIGHT
    //             &&
    //             remainingAsteroids.peek() > 0 // Previous asteroid moving LEFT
    //         ) {
    //             int collisionResult = remainingAsteroids.peek() + asteroid;

    //             /* 
    //             Asteroid on top of stack is bigger, so current asteroid explodes
    //             (we set it to 0 in order to indicate that)
    //             */
    //             if(collisionResult > 0) {
    //                 asteroid = 0; // By setting asteroid to 0, while-loop is also broken.
    //             }
    //             /*
    //             Current asteroid bigger, so previous asteroid explodes.
    //             */
    //             else if(collisionResult < 0) {
    //                 remainingAsteroids.pop();
    //             }
    //             // Both are equal in magnitude, so both explode
    //             else {
    //                 asteroid = 0;
    //                 remainingAsteroids.pop();
    //             }
    //         }

    //         if(asteroid != 0) {
    //             remainingAsteroids.push(asteroid);
    //         }
    //     }

    //     int[] returnVal = remainingAsteroids.stream().mapToInt(Integer::intValue).toArray();
    //     // Reversing the array, since deque maintains LIFO order, which we don't want.
    //     for(int i = 0; i < returnVal.length / 2; i++) {
    //         int temp = returnVal[i];
    //         returnVal[i] = returnVal[returnVal.length - 1 - i];
    //         returnVal[returnVal.length - 1 - i] = temp;
    //     }
    //     return returnVal;
    // }

    // static int getPrevAsteroid(ArrayList<Integer> remainingAsteroids) {
    //     if(remainingAsteroids.isEmpty()) {
    //         return -1001;
    //     }
        
    //     return remainingAsteroids.get(remainingAsteroids.size() - 1);
    // }
    
    // Uses primitive array as stack
    public int[] asteroidCollision(int[] asteroids) {

        // n + 1 stack because 1st element is a base value for comparison.
        int[] stack = new int[asteroids.length + 1];

        // The stack currently holds a 0 at 0th index. This serves as a base value for comparison.
        int stackTopIdx = 0;

        for(int asteroid: asteroids) {
            while(asteroid < 0 && stack[stackTopIdx] > 0) {
                int collisionResult = stack[stackTopIdx] + asteroid;

                /* 
                Asteroid on top of stack is bigger, so current asteroid explodes
                (we set it to 0 in order to indicate that)
                OR
                Both are equal in magnitude, so both explode
                */
                if(collisionResult >= 0) {
                    asteroid = 0; // By setting asteroid to 0, while-loop is also broken.
                }
                
                /*
                Current asteroid bigger, so previous asteroid explodes.
                OR
                Both are equal in magnitude, so both explode
                */
                if(collisionResult <= 0) {
                    stackTopIdx--; // Decrementing stack top pointer, for popping an element.
                }
            }

            // If the asteroid didn't explode, add it to the array
            if(asteroid != 0) {
                stack[++stackTopIdx] = asteroid;
            }

        }

        int[] remainingAsteroids = new int[stackTopIdx];
        for(int i = 0; i < stackTopIdx; i++) {
            remainingAsteroids[i] = stack[i + 1]; // i + 1, because index 0 is just for comparison
        }

        return remainingAsteroids;
    }
}