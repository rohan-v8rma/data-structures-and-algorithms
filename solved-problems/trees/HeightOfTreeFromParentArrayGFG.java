// https://www.geeksforgeeks.org/height-generic-tree-parent-array/

import java.util.*;

class Main {
    /*
    If node has no parent, it has a height of 0.
    
    If the node was already visited, we can return its height;
    it will be incremented one-by-one to the leaf node's height 
    upon each backtracking step.
    */
    static int dfs(int[] parent, int[] height, int i) {
      if(parent[i] == -1) {
        height[i] = 0;
        return 0;
      }
      
      // Node already visited;
      if(height[i] >= 0) {
        return height[i];
      }
      
      return height[i] = 1 + dfs(parent, height, parent[i]);
    }
  
    public static void main(String[] args) {
      int[] parent = new int[]{-1, 0, 0, 2, 2, 3};
      // int[] parent = new int[]{-1, 0, 0, 0, 3, 1, 1, 2};
      int[] height = new int[parent.length];
      
      Arrays.fill(height, -1);
      
      int maxHeight = 0;
      
      for(int i = parent.length - 1; i >= 0; i--) {
        maxHeight = Math.max(maxHeight, dfs(parent, height, i));
      }
      
      System.out.println(maxHeight);
  }
}