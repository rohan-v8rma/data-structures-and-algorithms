// https://thejoboverflow.com/problem/22/

import java.util.*;
import java.util.stream.*;

// TODO: Make notes

/*
____WRONG DP APPROACH____

Initially thought this was a DP question but, 
it is a graph question just because there are 2
things to keep track of, the distance and how many
obstacles we have destroyed.

If the number of obstacles we have destroyed exceeds
k at any point, there is no support to backtrack and
try another possible path.
*/

// class Main {
//     int goToEnd(int parR, int parC, int r, int c, int[][] mat, int coins, int steps) { 
//       if(r == mat.length - 1 && c == mat[0].length - 1) {
//         return steps;
//       }
//     }
  
//     public static void main(String[] args) {
//       Scanner s = new Scanner(System.in);
//       int r = s.nextInt();
//       int c = s.nextInt();
//       int k = s.nextInt();
      
//       s.nextLine();
//       int[][] mat = new int[r][c];
      
//       for(int row = 0; row < r; row++) {
//         mat[row] = Stream.of(s.nextLine.split("")).mapToInt(Integer::parseInt).toArray();
//       }
      
//       int[][] dp = new int[r][c];
      
//       for(int[] dp1d: dp) Arrays.fill(dp1d, Integer.MAX_VALUE);
      
//       for(int row = r - 1; row >= 0; row--) {
//         for(int col = c - 1; col >= 0; col--) {
//           if(col + 1 < c) {
//             dp[r][c] = Math.max(
//               dp[r][c], mat[r][c] +   
//           }
          
//         }
//       }
      
//       System.out.println(dp[0][0] <= k ? dp[0][0] : -1);
//     }
//   }
/*
 ____CORRECT DFS____

 Gives right answer for the test case, 
 5 3 1
 000
 110
 000
 111
 000

 But gets TLE.
*/

// class Main {
//   static boolean check(int r, int c, int maxR, int maxC) {
//     return(r < maxR && r >= 0 && c < maxC && c >= 0);
//   }
  
//   static int dfs(int r, int c, int remK, int d, int[][] mat) {
//     if(
//       remK < 0 
//       || !check(r, c, mat.length, mat[0].length)
//       || dp[r][c][remK] <= d
//     ) {
//       return Integer.MAX_VALUE;
//     }
    
//     dp[r][c][remK] = d;

//     if(r == mat.length - 1 && c == mat[0].length - 1) {
//       return d;
//     }
    
//     int result = Integer.MAX_VALUE;
    
//     for(int i = 0; i < 4; i++) {
//       int newR = r + dx[i];
//       int newC = c + dy[i];
      
//       if(
//         check(newR, newC, mat.length, mat[0].length)
//       ) {
//         int newK = remK - mat[newR][newC];
        
//         result = Math.min(
//           result, 
//           dfs(
//             newR, 
//             newC, 
//             newK, 
//             d + 1, 
//             mat
//           )
//         );
//       }
//     }
    
//     return result;
//   }
  
//   static int[] dx = new int[]{0, 0, 1, -1};
//   static int[] dy = new int[]{1, -1, 0, 0};
//   static int[][][] dp;
//   static int dist;
  
//   public static void main(String[] args) {
//     Scanner s = new Scanner(System.in);
//     int r = s.nextInt();
//     int c = s.nextInt();
//     int k = s.nextInt();
    
//     s.nextLine();
//     int[][] mat = new int[r][c];
    
//     for(int row = 0; row < r; row++) {
//       mat[row] = Stream
//         .of(s.nextLine().split(""))
//         .mapToInt(Integer::parseInt)
//         .toArray();
//     }
    
//     dp = new int[r][c][k + 1];
    
//     for(int[][] dp2d: dp) {
//       for(int[] dp1d: dp2d) Arrays.fill(dp1d, Integer.MAX_VALUE);
//     }
    
//     int val = dfs(0, 0, k, 0, mat);
//     System.out.println(val == Integer.MAX_VALUE ? -1 : val);
//   }
// }


/*
 ____WRONG DFS____
 Doesn't right answer for the test case, 
 5 3 1
 000
 110
 000
 111
 000

 It gives -1 instead of 6.

 But passes all test cases.
*/

// class Main {
//   static boolean check(int r, int c, int maxR, int maxC) {
//     return(r < maxR && r >= 0 && c < maxC && c >= 0);
//   }
  
//   static int dfs(int r, int c, int remK, int d, int[][] mat) {
//     if(
//       remK < 0 
//       || !check(r, c, mat.length, mat[0].length)
//       || dp[r][c] <= d
//     ) {
//       return Integer.MAX_VALUE;
//     }
    
//     dp[r][c] = d;

//     if(r == mat.length - 1 && c == mat[0].length - 1) {
//       return d;
//     }
    
//     int result = Integer.MAX_VALUE;
    
//     for(int i = 0; i < 4; i++) {
//       int newR = r + dx[i];
//       int newC = c + dy[i];
      
//       if(
//         check(newR, newC, mat.length, mat[0].length)
//       ) {
//         int newK = remK - mat[newR][newC];
        
//         result = Math.min(
//           result, 
//           dfs(
//             newR, 
//             newC, 
//             newK, 
//             d + 1, 
//             mat
//           )
//         );
//       }
//     }
    
//     return result;
//   }
  
//   static int[] dy = new int[]{0, 0, 1, -1};
//   static int[] dx = new int[]{1, -1, 0, 0};
//   static int[][] dp;
//   static int dist;
  
//   public static void main(String[] args) {
//     Scanner s = new Scanner(System.in);
//     int r = s.nextInt();
//     int c = s.nextInt();
//     int k = s.nextInt();
    
//     s.nextLine();
//     int[][] mat = new int[r][c];
    
//     for(int row = 0; row < r; row++) {
//       mat[row] = Stream
//         .of(s.nextLine().split(""))
//         .mapToInt(Integer::parseInt)
//         .toArray();
//     }
    
//     dp = new int[r][c];
    
//     for(int[] dp1d: dp) Arrays.fill(dp1d, Integer.MAX_VALUE);
    
//     int val = dfs(0, 0, k, 0, mat);
//     System.out.println(val == Integer.MAX_VALUE ? -1 : val);
//   }
// }

/*
 ____CORRECT BFS____

 Gives right answer for the test case, 
 5 3 1
 000
 110
 000
 111
 000

 And doesn't get TLE.
*/

class Main {
  static boolean check(int r, int c, int maxR, int maxC) {
    return(r < maxR && r >= 0 && c < maxC && c >= 0);
  }
  
  static int[] dx = new int[]{0, 0, 1, -1};
  static int[] dy = new int[]{1, -1, 0, 0};
  static int[][][] dp;
  static int dist;
  
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int r1 = s.nextInt();
    int c1 = s.nextInt();
    int k1 = s.nextInt();
    
    s.nextLine();
    int[][] mat = new int[r1][c1];
    
    for(int row = 0; row < r1; row++) {
      mat[row] = Stream
        .of(s.nextLine().split(""))
        .mapToInt(Integer::parseInt)
        .toArray();
    }
    
    dp = new int[r1][c1][k1 + 1];
    
    for(int[][] dp2d: dp) {
      for(int[] dp1d: dp2d) Arrays.fill(dp1d, Integer.MAX_VALUE);
    }
    
    int dist = Integer.MAX_VALUE;
    
    Queue<int[]> q = new LinkedList<>();
    
    q.offer(new int[]{0, 0, k1, 0});
    
    while(!q.isEmpty()) {
      int[] qElement = q.poll();
      
      int r = qElement[0];
      int c = qElement[1];
      int remK = qElement[2];
      int d = qElement[3];
      
      if(
        remK < 0 
        || !check(r, c, mat.length, mat[0].length)
        || dp[r][c][remK] <= d
      ) {
        continue;
      }
      
      dp[r][c][remK] = d;
      
      if(r == mat.length - 1 && c == mat[0].length - 1) {
        dist = d;
        break;
      }
      
    
      for(int i = 0; i < 4; i++) {
        int newR = r + dx[i];
        int newC = c + dy[i];
      
        if(
          check(newR, newC, mat.length, mat[0].length)
        ) {
          int newK = remK - mat[newR][newC];
        
          q.offer(new int[]{newR, newC, newK, d + 1});
        }
      }
    }
    
    System.out.println(dist == Integer.MAX_VALUE ? -1 : dist);
  }
}