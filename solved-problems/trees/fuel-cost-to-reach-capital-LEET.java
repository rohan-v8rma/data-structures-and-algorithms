// https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital

class Solution {
    // SELF DEVELOPED ALGO: O(N^N)
    // public long minimumFuelCost(int[][] roads, int seats) {
    //     int numOfNodes = roads.length + 1;

    //     ArrayList<HashSet<Integer>> adjacencyList = new ArrayList<>(numOfNodes);
        
    //     for(int i = 0; i < numOfNodes; i++) {
    //         adjacencyList.add(new HashSet<>());
    //     }

    //     HashSet<Integer> emptySet = new HashSet<>();

    //     // Initializing the adjacency list.
    //     for(int[] road: roads) {
    //         adjacencyList.get(road[0]).add(road[1]);
    //         adjacencyList.get(road[1]).add(road[0]);
    //     }

    //     int[] nodeDelegateCt = new int[numOfNodes + 1];

    //     long fuelCost = 0;
        
    //     int copyOfN = numOfNodes;
    //     while(copyOfN != 1) {
    //         /*
    //         In worst case, when 1 element removed per iteration, this takes O(N^N)
    //         */
    //         for(int node = 1; node < numOfNodes; node++) {
    //             HashSet<Integer> adjNodes = adjacencyList.get(node);
    //             if(adjNodes.size() == 1) {
    //                 // Adding 1 for the delegate whose city other delegates are in.
    //                 int delegatesAtNode = nodeDelegateCt[node] + 1;
    //                 int nextNode = adjNodes.iterator().next();

    //                 fuelCost += 
    //                     delegatesAtNode / seats 
    //                     + 
    //                     (delegatesAtNode % seats != 0 ? 1 : 0)
    //                 ;
                    
    //                 nodeDelegateCt[nextNode] += delegatesAtNode;

    //                 adjacencyList.get(nextNode).remove(node);
    //                 adjNodes.remove(nextNode);

    //                 copyOfN--;
    //             }
    //         }
    //     }
    
    //     return fuelCost;
    // }

    static long fuelCost;
    static ArrayList<ArrayList<Integer>> adjacencyList;
    static int seatCt;

    // OPTIMAL: O(N) solution using recursion
    public long minimumFuelCost(int[][] roads, int seats) {
        int numOfNodes = roads.length + 1;

        if(numOfNodes == 1) {
            return 0;
        }

        /* 
        Initializing the 2d arraylist.
        
        By specifying how large the arraylist is going to be, multiplied by a scaling factor, we save 
        the time dedicated to resizing later on.
        */
        adjacencyList = new ArrayList<>(numOfNodes * 2);
        /*
        IS THIS SIZE WE SPECIFY IN THE CONSTRUCTOR THE RESIZE LIMIT OR WHAT?

        We still need to initialize each index with a new arraylist, that can be inserted into.
        */
        for(int index = 0; index < numOfNodes; index++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Initializing the adjacency list.
        for(int[] road: roads) {
            adjacencyList.get(road[0]).add(road[1]);
            adjacencyList.get(road[1]).add(road[0]);
        }

        // System.out.println(adjacencyList.capacity);

        fuelCost = 0;
        seatCt = seats;

        getDelegatesAtThisNode(0, -1);

        return fuelCost;
    }

    static int getDelegatesAtThisNode(int currentNode, int parent) {
        ArrayList<Integer> adjNodes = adjacencyList.get(currentNode);

        // This count is of the delegate whose city this node is.
        int delegatesAtThisNode = 1;

        for(int adjNode: adjNodes) {
            // This check is enough since only 1 parent is possible for a particular node.
            if(adjNode != parent) {
                float delegatesAtAdjNode = getDelegatesAtThisNode(adjNode, currentNode);
                
                fuelCost += (int)Math.ceil(delegatesAtAdjNode / seatCt);

                delegatesAtThisNode += delegatesAtAdjNode;
            }
        }

        return delegatesAtThisNode;
    }
}