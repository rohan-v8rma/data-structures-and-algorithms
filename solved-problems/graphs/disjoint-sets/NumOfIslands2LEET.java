// This question is premium only on leetcode, so refer the GeeksForGeeks link for the problem statement:
// https://www.geeksforgeeks.org/problems/number-of-islands/1

class Solution {
    static int numOfIslands;
    
    /* 
    The extra datastructure 
    that is required to associate island cells 
    with a particular disjoint set parent
    */
    static HashMap<Long, Integer> pointParentMap;
    
    // These 2 are anyways used in any disjoint set implementation
    static ArrayList<Integer> parents;
    static ArrayList<Integer> setSizeByParent;
    
    // This is the return value.
    static ArrayList<Integer> result;
    
    boolean check(int r, int c, int mR, int mC) {
        return (r >= 0 && c >= 0 && r < mR && c < mC);
    }
    
    int getParent(int set) {
        int parent = parents.get(set);
    
        while(parent != parents.get(parent)) {
            parent = parents.get(parent);
        }
        
        // PATH COMPRESSION
        parents.set(set, parent);
        
        return parent;
    }
    
    long getPt(long r, int c) {
        return (r << 31) + c;
    }
    
    int unionBySize(int set1, int set2) {
        int set1Parent = getParent(set1);
        int set2Parent = getParent(set2);
        
        if(set1Parent == set2Parent) return set1Parent;
        
        // 2 islands combined into 1.
        numOfIslands--;
        
        int set1Size = setSizeByParent.get(set1Parent);
        int set2Size = setSizeByParent.get(set2Parent);
        
        if(
            set1Size
            >= set2Size
        ) {
            parents.set(set2Parent, set1Parent);
            setSizeByParent.set(set1Parent, set1Size + set2Size);
            
            return set1Parent;
        }
        else {
            parents.set(set1Parent, set2Parent);
            setSizeByParent.set(set2Parent, set1Size + set2Size);
            
            return set2Parent;
        }
    }
    
    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        numOfIslands = 0;
        parents = new ArrayList<>();
        pointParentMap = new HashMap<>();
        setSizeByParent = new ArrayList<>();
        result = new ArrayList<>();
        
        int[][] grid = new int[rows][cols];
        
        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};
        
        for(int[] operator: operators) {
            int r = operator[0];
            int c = operator[1];
            grid[r][c] = 1;
            
            if(!pointParentMap.containsKey(getPt(r, c))) {
                int parent = -1;
                
                for(int i = 0; i < 4; i++) {
                    int aR = r + dx[i];
                    int aC = c + dy[i];
                    
                    if(check(aR, aC, rows, cols)) {
                        if(grid[aR][aC] == 1) {
                            int parentOfAdjacentPt = getParent(pointParentMap.get(getPt(aR, aC)));
                            
                            if(parent == -1) {
                                parent = parentOfAdjacentPt;
                            }
                            else {
                                parent = unionBySize(parent, parentOfAdjacentPt);
                            }
                        }
                    }
                }   
                
                // System.out.printf("%s: %d, ", Arrays.toString(operator), parent);
                
                // No adjacent islands, so this is a new independent island.
                if(parent == -1) {
                    parent = parents.size();
                    parents.add(parents.size());
                    
                    // Set containing 1 element.
                    setSizeByParent.add(1);
                    numOfIslands++;
                }
                
                pointParentMap.put(getPt(r, c), parent);
                
                // System.out.println(parents);    
            }
            
            
            result.add(numOfIslands);
        }
        
        
        return result;
    }
    
}