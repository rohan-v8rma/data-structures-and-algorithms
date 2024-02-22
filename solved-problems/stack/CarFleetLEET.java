// https://leetcode.com/problems/car-fleet/

class Solution {
    // // Ok solution - O(N.log(N)). Space - O(N)
    // public int carFleet(int target, int[] position, int[] speed) {

    //     double doubleTarget = target;

    //     // Combine the array into 1, so that sorting is easier.
    //     int[][] carDatas = new int[speed.length][2];

    //     for(int i = 0; i < speed.length; i++) {
    //         carDatas[i][0] = position[i];
    //         carDatas[i][1] = speed[i];
    //     }

    //     Arrays.sort(carDatas, (car1, car2) -> Integer.compare(car2[0], car1[0]));

    //     ArrayDeque<int[]> stack = new ArrayDeque<>();
    //     // First fleet.
    //     stack.push(carDatas[0]);

    //     for(int i = 1; i < carDatas.length; i++) {
    //         int[] carData = carDatas[i];

    //         int[] prevFleet = stack.peek();
        
    //         double timeToDest = (doubleTarget - carData[0]) / carData[1];
    //         double prevFleetTime = (doubleTarget - prevFleet[0]) / prevFleet[1];

    //         if(prevFleetTime < timeToDest) {
    //             // A new fleet is formed.
    //             stack.push(carData);
    //         }
    //     }

    //     return stack.size();  
    // }

    // // Ok solution - O(N.log(N)). Space - O(1)
    // public int carFleet(int target, int[] position, int[] speed) {
    //     double doubleTarget = target;

    //     // Combine the array into 1, so that sorting is easier.
    //     int[][] carDatas = new int[speed.length][2];

    //     for(int i = 0; i < speed.length; i++) {
    //         carDatas[i][0] = position[i];
    //         carDatas[i][1] = speed[i];
    //     }

    //     Arrays.sort(carDatas, (car1, car2) -> Integer.compare(car2[0], car1[0]));

    //     // First fleet.
    //     int[] currentFleet = carDatas[0];

    //     int numOfFleets = 1;

    //     for(int i = 1; i < carDatas.length; i++) {
    //         int[] carData = carDatas[i];
        
    //         double timeToDest = (doubleTarget - carData[0]) / carData[1];
    //         double prevFleetTime = (doubleTarget - currentFleet[0]) / currentFleet[1];

    //         if(prevFleetTime < timeToDest) {
    //             // A new fleet is formed.
    //             currentFleet = carData;

    //             numOfFleets++;
    //         }
    //     }

    //     return numOfFleets;  
    // }

    // Ok solution - O(N.log(N)). Space - O(1)
    public int carFleet(int target, int[] position, int[] speed) {
        double doubleTarget = target;

        // Combine the array into 1, so that sorting is easier.
        double[][] carDatas = new double[speed.length][2];

        for(int i = 0; i < speed.length; i++) {
            carDatas[i][0] = position[i];
            carDatas[i][1] = (doubleTarget - position[i]) / speed[i];
        }

        Arrays.sort(carDatas, (car1, car2) -> Double.compare(car2[0], car1[0]));

        // First fleet.
        double[] currentFleet = carDatas[0];

        int numOfFleets = 1;

        for(int i = 1; i < carDatas.length; i++) {
            double[] carData = carDatas[i];
        
            if(currentFleet[1] < carData[1]) {
                // A new fleet is formed.
                currentFleet = carData;

                numOfFleets++;
            }
        }

        return numOfFleets;  
    }
}
