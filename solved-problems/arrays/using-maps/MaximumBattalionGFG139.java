// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-139/instructions

class Solution 
{
    public static int maximumBattalions(int N, String[] names) 
    {
        HashMap<String, int[]> soldierIdx = new HashMap<>();
        
        // Getting first and last occurrences of soldier names.
        for(int i = 0; i < names.length; i++) {
            String name = names[i];
            
            int[] nameIndices = soldierIdx.getOrDefault(name, new int[]{-1, -1});
            
            if(nameIndices[0] == -1 && nameIndices[1] == -1) {
                nameIndices[0] = i;
                nameIndices[1] = i;
                soldierIdx.put(name, nameIndices);
            }
            else {
                nameIndices[1] = i;
            }
        }
        
        int battalionCt = 0;
        
        int idx = 0;
        while(idx < names.length) {
            int[] battalion = soldierIdx.get(names[idx]);    
            
            /* 
            Checking the names inside the current battalion.
            
            If they have an occurrence ahead of the end of the current battalion,
            it will get recorded.
            
            If they had an occurrence before the start of the current battalion,
            it would have been CAUGHT when we iterated the previous battalion.
            */
            for(int innerI = battalion[0] + 1; innerI < battalion[1]; innerI++) {
                battalion[1] = Math.max(soldierIdx.get(names[innerI])[1], battalion[1]);
            }
            
            battalionCt++;
            idx = battalion[1] + 1;
        }
        
        return battalionCt;
    }
    
}