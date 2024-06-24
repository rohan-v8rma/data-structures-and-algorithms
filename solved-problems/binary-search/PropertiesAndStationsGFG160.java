// https://practice.geeksforgeeks.org/contest/gfg-weekly-160-rated-contest/problems
// TODO: Understand editorial explanation when free: https://www.youtube.com/live/92m3K6qP_68

class Solution {
    // /*
    // ____SELF DEVELOPED____
    // */
    // public static long minAmountRequired(int n, int[] station, int p) {
    //     Arrays.sort(station);
        
    //     long cost = 0;
        
    //     // Houses already evenly taken from each gap value.
    //     long offset = 0;
    //     // Cost of house bracket, till which houses are finished.
    //     long houseCostOffset = 0;
        
    //     PriorityQueue<Integer> intervals = new PriorityQueue<>();
         
    //     intervals.offer(Integer.MAX_VALUE);
    //     intervals.offer(Integer.MAX_VALUE);
        
    //     for(int i = 0; i < station.length - 1; i++) {
    //         int numOfHousesInBw = station[i + 1] - station[i] - 1;
        
    //         intervals.offer(numOfHousesInBw / 2 + numOfHousesInBw % 2);
            
    //         if(numOfHousesInBw / 2 != 0) 
    //             intervals.offer(numOfHousesInBw / 2);
    //     }
        
    //     // System.out.println(intervals);
        
    //     while(p != 0) {
    //         long numOfDir = intervals.size();
            
    //         long numOfHouses = intervals.poll() - offset;
            
    //         // System.out.println(p);
            
    //         if(numOfHouses > 0) {
    //             if(p >= numOfHouses * numOfDir) {
    //                 // System.out.println("hello");
    //                 cost += (
    //                     numOfDir 
    //                     * (
    //                         (numOfHouses * (numOfHouses + 1)) / 2 
    //                         + numOfHouses * houseCostOffset
    //                     )
    //                 );
                    
    //                 p -= (int)(numOfHouses * numOfDir);
    //                 offset += numOfHouses;
    //                 houseCostOffset += numOfHouses;
    //             }
    //             else {
    //                 long low = 0;
    //                 long high = numOfHouses;
                    
    //                 long housesToTake = 0;
                    
    //                 while(low <= high) {
    //                     long mid = (low + high) / 2;
                        
    //                     if(mid * numOfDir <= p) {
    //                         housesToTake = Math.max(
    //                             mid,
    //                             housesToTake
    //                         );
                            
    //                         low = mid + 1;
    //                     }
    //                     else {
    //                         high = mid - 1;
    //                     }
    //                 }
                    
    //                 // System.out.println(housesToTake);
                
    //                 cost += (
    //                     numOfDir 
    //                     * (
    //                         (housesToTake * (housesToTake + 1)) / 2 
    //                         + housesToTake * houseCostOffset
    //                     )
    //                 );
                    
    //                 // System.out.printf("cost: %d", cost);
                    
    //                 p -= housesToTake * numOfDir;
    //                 offset += housesToTake;
    //                 houseCostOffset += housesToTake;
                    
    //                 // System.out.println(p);
                    
    //                 cost += p * (houseCostOffset + 1);
    //                 p -= p;
    //             }
    //         }
    //     }
    
    //     return cost;
    // }
    
    /*
    ____OPTIMIZED____
    */
    
    static void offerMax(PriorityQueue<Integer> pq, int val, int maxVal) {
        pq.offer(Math.min(val, maxVal));
    }
    
    public static long minAmountRequired(int n, int[] station, int p) {
        Arrays.sort(station);
        
        long cost = 0;
        
        // Houses already evenly taken from each gap value.
        long offset = 0;
        // Cost of house bracket, till which houses are finished.
        long houseCostOffset = 0;
        
        PriorityQueue<Integer> intervals = new PriorityQueue<>();
         
        offerMax(intervals, Integer.MAX_VALUE, p);
        offerMax(intervals, Integer.MAX_VALUE, p);
        
        for(int i = 0; i < station.length - 1; i++) {
            int numOfHousesInBw = station[i + 1] - station[i] - 1;
        
            offerMax(intervals, numOfHousesInBw / 2 + numOfHousesInBw % 2, p);
            
            if(numOfHousesInBw / 2 != 0) 
                offerMax(intervals, numOfHousesInBw / 2, p);
        }
        
        // System.out.println(intervals);
        
        while(p != 0) {
            long numOfDir = intervals.size();
            
            long numOfHouses = intervals.poll() - offset;
            
            // System.out.println(p);
            
            if(numOfHouses > 0) {
                if(p >= numOfHouses * numOfDir) {
                    // System.out.println("hello");
                    cost += (
                        numOfDir 
                        * (
                            (numOfHouses * (numOfHouses + 1)) / 2 
                            + numOfHouses * houseCostOffset
                        )
                    );
                    
                    p -= (int)(numOfHouses * numOfDir);
                    offset += numOfHouses;
                    houseCostOffset += numOfHouses;
                }
                else {
                    long low = 0;
                    long high = numOfHouses;
                    
                    long housesToTake = 0;
                    
                    while(low <= high) {
                        long mid = (low + high) / 2;
                        
                        if(mid * numOfDir <= p) {
                            housesToTake = Math.max(
                                mid,
                                housesToTake
                            );
                            
                            low = mid + 1;
                        }
                        else {
                            high = mid - 1;
                        }
                    }
                    
                    // System.out.println(housesToTake);
                
                    cost += (
                        numOfDir 
                        * (
                            (housesToTake * (housesToTake + 1)) / 2 
                            + housesToTake * houseCostOffset
                        )
                    );
                    
                    // System.out.printf("cost: %d", cost);
                    
                    p -= housesToTake * numOfDir;
                    offset += housesToTake;
                    houseCostOffset += housesToTake;
                    
                    // System.out.println(p);
                    
                    cost += p * (houseCostOffset + 1);
                    p -= p;
                }
            }
        }
    
        return cost;
    }
}
