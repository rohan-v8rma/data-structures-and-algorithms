// https://www.geeksforgeeks.org/problems/rod-cutting0840/1
// TODO: Make notes

class Solution{
    /*
    ____TABULATION____
    */
    // public int cutRod(int price[], int n) {
    //     /*
    //     Simple approach, figure out best way to split smallest rod possible,
    //     then work your way up to n length rod.
    //     */
    //     for(int rodLength = 1; rodLength <= n; rodLength++) {
    //         /* 
    //         Only checking up to half the length, 
    //         otherwise the divisions would be repeated, but in exchanged positions
    //         */
    //         for(int partLength = 1; partLength <= rodLength / 2; partLength++) {
    //             price[rodLength - 1] = Math.max(
    //                 price[rodLength - 1],
    //                 /*
    //                 Here it might look like we are only splitting the rod
    //                 up into 2 parts, but it is not so.
                    
    //                 Suppose we're calculating the best possible price
    //                 for a rod of length 10, so we would add price of rod
    //                 of length 1 and that of length 9. 
                    
    //                 But that rod of length 9 would have further been
    //                 optimally divided when its price was being calculated
    //                 */
    //                 price[partLength - 1] + price[rodLength - partLength - 1]
    //             );
    //         }
    //     }
        
    //     return price[n - 1];
    // }
    
    /*
    ____CONVERTING TABULATION TO RECURSION____
    */
    static int recurse(int rodLength) {
        
        if(visited[rodLength - 1]) return price[rodLength - 1];
        /* 
        Only checking up to half the length, 
        otherwise the divisions would be repeated, but in exchanged positions
        */
        for(int partLength = 1; partLength <= rodLength / 2; partLength++) {
            price[rodLength - 1] = Math.max(
                price[rodLength - 1],
                /*
                Here it might look like we are only splitting the rod
                up into 2 parts, but it is not so.
                
                Suppose we're calculating the best possible price
                for a rod of length 10, so we would add price of rod
                of length 1 and that of length 9. 
                
                But that rod of length 9 would have further been
                optimally divided when its price was being calculated
                */
                recurse(partLength) + recurse(rodLength - partLength)
            );
        }
        
        visited[rodLength - 1] = true;
        
        return price[rodLength - 1];
    }
    
    static int[] price;
    static boolean[] visited;
    
    public int cutRod(int price[], int n) {
        
        visited = new boolean[price.length];
        
        this.price = price;
        
        /*
        Simple approach, figure out best way to split smallest rod possible,
        then work your way up to n length rod.
        */
        return recurse(n);
    }
}