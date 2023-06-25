// User function Template for Java

class Solution {
    // We will be executing DFS in clockwise direction.
    void executeDFS(
        int[] coordinate, 
        int[][] grid, 
        boolean[][] visited, 
        ArrayList<String> island, 
        int[] referencePoint
    ) {
        
        /* 
        We do not need to check whether a cell is visited or not inside the DFS function
        because if it had already been visited, DFS would have executed from that point
        and we wouldn't have started DFS that lead to the point we are trying to visit,
        
        because the point from where we started the DFS would've already been marked as visited.
        */
        visited[coordinate[0]][coordinate[1]] = true;
        
        /* 
        Subtracting the reference point from the coordinate, so that islands that are actually
        identical are not classified as distinct due to offset in coordinate values.
        */
        island.add(toString(coordinate[0] - referencePoint[0], coordinate[1] - referencePoint[1]));
        
        
        // DFS upwards
        /*
        For the case:
        1 0 1 <-- This bit.
        1 1 1    
        */
        if(
            (coordinate[0] - 1 >= 0)
            &&
            !visited[coordinate[0] - 1][coordinate[1]]      
            && 
            (grid[coordinate[0] - 1][coordinate[1]] == 1)
        ) {
            executeDFS(
                new int[]{coordinate[0] - 1, coordinate[1]},
                grid,
                visited,
                island,
                referencePoint
            );
        }
        
        // DFS to the right
        if(
            (coordinate[1] + 1 < (grid[0].length))
            &&
            !visited[coordinate[0]][coordinate[1] + 1]
            && 
            (grid[coordinate[0]][coordinate[1] + 1] == 1)
        ) {
            executeDFS(
                new int[]{coordinate[0], coordinate[1] + 1},
                grid,
                visited,
                island,
                referencePoint
            );
        }
        
        // DFS downwards
        if(
            (coordinate[0] + 1 < (grid.length))
            &&
            !visited[coordinate[0] + 1][coordinate[1]]
            && 
            (grid[coordinate[0] + 1][coordinate[1]] == 1)
        ) {
            executeDFS(
                new int[]{coordinate[0] + 1, coordinate[1]},
                grid,
                visited,
                island,
                referencePoint
            );
        }
        
        // DFS to the left
        if(
            (coordinate[1] - 1 >= 0)
            &&
            !visited[coordinate[0]][coordinate[1] - 1]
            && 
            (grid[coordinate[0]][coordinate[1] - 1] == 1)
        ) {
            executeDFS(
                new int[]{coordinate[0], coordinate[1] - 1},
                grid,
                visited,
                island,
                referencePoint
            );
        }
        
        return;
        
        
    }

    private String toString(int row, int column) {
        return (Integer.toString(row) + Integer.toString(column));
    }

    int countDistinctIslands(int[][] grid) {
        
        int outerIndexLim = grid.length - 1;
        int innerIndexLim = grid[0].length - 1;
        
        /* 
        ArrayList inside the set for storing the islands. 
        int[] inside the ArrayList for storing 2D co-ordinates.
        */
        Set<ArrayList<String>> islandSet = new HashSet<>();
        
        boolean[][] visited = new boolean[outerIndexLim + 1][innerIndexLim + 1];
        
        for(int outerIndex = 0; outerIndex <= outerIndexLim; outerIndex++) {
            for(int innerIndex = 0; innerIndex <= innerIndexLim; innerIndex++) {
                if(grid[outerIndex][innerIndex] == 0) {
                    visited[outerIndex][innerIndex] = true;
                }
                else if(!visited[outerIndex][innerIndex]) {
                    ArrayList<String> island = new ArrayList<>();
                    
                    executeDFS(
                        new int[]{outerIndex, innerIndex},
                        grid,
                        visited,
                        island,
                        new int[]{outerIndex, innerIndex}
                    );
                    
                    islandSet.add(island);
                }
            }
        }
        
        
        /*
        It is essential to stringify the point coordinates, before adding them into the set.
        
        This is because although the contents of the ArrayLists storing the coordinates of the 
        islands may be same, the hashcode is what gets stored in the end.
        
        These hashcodes are ways of referencing where the data of the arrays are stored.
        
        Since each coordinate inside the ArrayList is an array so it will be having its own unique
        hashcode.
                
        Uncomment the below printing code to understand.
        */
        // for(ArrayList<int[]> island: islandSet) {
        //     System.out.println("Island: ");
        //     System.out.println(island.hashcode());
        //     for(int[] coordinate: island) {
        //         System.out.printf("(%d, %d), ", coordinate[0], coordinate[1]);
        //     }
        //     System.out.println(' ');
        // }
        
        
        return islandSet.size();
    }
}
