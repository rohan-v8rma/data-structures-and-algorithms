// https://leetcode.com/problems/find-players-with-zero-or-one-losses

class Solution {
    /* 
    Highly inefficient solution using a sorted map. 
    O(M + N * log(M)) where, 
    
    M is number of entries in map.
    log(M) is insertion time of element.
    N is number of matches.
    */
    // public List<List<Integer>> findWinners(int[][] matches) {
    //     List<List<Integer>> answer = new ArrayList<>();

    //     Map<Integer, Integer> counts = new TreeMap<>();

    //     for(int[] match: matches) {
    //         int winner = match[0];
    //         int loser = match[1];

    //         counts.put(winner, counts.getOrDefault(winner, 0));
    //         counts.put(loser, 1 + counts.getOrDefault(loser, 0));
    //     }

    //     ArrayList<Integer> zeroArr = new ArrayList<>();
    //     ArrayList<Integer> oneArr = new ArrayList<>();
        
    //     for(Map.Entry<Integer, Integer> entry: counts.entrySet()) {
    //         int lossCt = entry.getValue();
    //         switch(lossCt) {
    //             case 0:
    //                 zeroArr.add(entry.getKey());
    //                 break;
    //             case 1:
    //                 oneArr.add(entry.getKey());
    //         }
    //     }
        
    //     answer.add(zeroArr);
    //     answer.add(oneArr);
    //     return answer;
    // }

    /* 
    Approach using 3 sets:
    TC: O(N + K.log(K) + L.log(L))
    
    N is number of matches
    K is number of elements in zero loss set.
    L is number of elements in one loss set.

    Worst case: O(N + N.log(N))
    */
    // public List<List<Integer>> findWinners(int[][] matches) {
    //     List<List<Integer>> answer = new ArrayList<>();

    //     Set<Integer> discardedElements = new HashSet<>();
    //     Set<Integer> zeroLoss = new HashSet<>();
    //     Set<Integer> oneLoss = new HashSet<>();

    //     for(int[] match: matches) {
    //         int winner = match[0];
    //         int loser = match[1];

    //         if(!discardedElements.contains(winner) && !oneLoss.contains(winner)) {
    //             zeroLoss.add(winner);
    //         }

    //         if(!discardedElements.contains(loser)) {
    //             if(oneLoss.contains(loser)) {
    //                 oneLoss.remove(loser);
    //                 discardedElements.add(loser);
    //             }
    //             else {
    //                 zeroLoss.remove(loser);
    //                 oneLoss.add(loser);
    //             }
    //         }
    //     }

    //     ArrayList<Integer> zeroArr = new ArrayList<>(zeroLoss);
    //     ArrayList<Integer> oneArr = new ArrayList<>(oneLoss);

    //     // The numbers need to be in ascending order according to the test cases.
    //     Collections.sort(zeroArr);
    //     Collections.sort(oneArr);

    //     answer.add(zeroArr);
    //     answer.add(oneArr);
    //     return answer;

    // }

    /*
    OPTIMAL
    Least TC out of all: O(N + M)

    N is the number of matches
    M is the max person (so we iterate from 1 to M to get people with 0 or 1 losses)
    */
    public List<List<Integer>> findWinners(int[][] matches) {
        // Arrays.asList creates a fixed size list.
        List<List<Integer>> answer = Arrays.asList(new ArrayList<>(), new ArrayList<>());
        
        /* 
        This is because a person is represent using any number from 1 to 10^5. + 1 because 1-indexing.

        We can do this because constraints allow it. If max was 10^8, it wouldn't have been possible 
        and resulted in TLE.
        */
        int[] lossCt = new int[100000 + 1];
        
        /* 
        Players who haven't played any game yet are at -1.
        This is an extremely performant command. 
        Removing it didn't lead to next to no performance improvements.
        */
        Arrays.fill(lossCt, -1);

        int maxPerson = 1;

        for(int[] match: matches) {
            int winner = match[0];
            int loser = match[1];

            if(lossCt[winner] == -1) {
                lossCt[winner] = 0;
                maxPerson = Math.max(maxPerson, winner);
            }

            if(lossCt[loser] == -1) {
                lossCt[loser] = 1;
                maxPerson = Math.max(maxPerson, loser);
            }
            else {
                lossCt[loser]++;
            }
        }

        for(int person = 1; person <= maxPerson; person++) {
            if(lossCt[person] == 0) {
                answer.get(0).add(person);
            }
            else if(lossCt[person] == 1) {
                answer.get(1).add(person);
            }
        }

        return answer;
    }
}