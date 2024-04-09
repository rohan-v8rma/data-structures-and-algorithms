// https://leetcode.com/problems/replace-question-marks-in-string-to-minimize-its-value/
// TODO: Make notes

class Solution {
    /*_____O(26) approach for getting next character to be used_____*/

    static char getLowest(int[] prefixOccur, int[] suffixOccur) {
        int lowestI = 0;
        
        /*
        Metric used for selection:
        prefixOccur[i] + suffixOccur[i]

        - prefixOccur tells the cost of current alphabet, 
          directly (as per definition)
        - suffixOccur tells us that as compared to previous cost 
          of the same letters, occuring after this index, 
          now each letter will cost us 1 unit more.

        In '?aaa' suppose the a's were about to cost x, x + 1, and x + 2,
        but we used an `a` in the question mark, so they will now cost
        x + 1, x + 2, x + 3. An increase in the cost of all 3 a's by 1.
        */
        for(int i = 1; i < 26; i++) {
            if(
                (prefixOccur[i] + suffixOccur[i])
                < (prefixOccur[lowestI] + suffixOccur[lowestI])
            ) {
                lowestI = i;
            }
        }
        
        return (char)(lowestI + 'a');
    }
    
    public String minimizeStringValue(String s) {
        char[] str = s.toCharArray();

        int[] suffixOccur = new int[26];
        
        int numOfQ = 0;
        
        for(char c: str) {
            if(c != '?') suffixOccur[c - 'a']++;
        }
        
        int[] prefixOccur = new int[26];
    
        ArrayList<Character> quesReplaceValues = new ArrayList<>();
        
        for(int i = 0; i < str.length; i++) {
            if(str[i] == '?') {
                char lowest = getLowest(prefixOccur, suffixOccur);
                
                prefixOccur[lowest - 'a']++;

                quesReplaceValues.add(lowest);
            }
            else {
                prefixOccur[str[i] - 'a']++;
                suffixOccur[str[i] - 'a']--;
            }
        }
    
        /* 
        Sorting to enforce lexographical order.

        When we're sorting, it doesn't matter what characters 
        come between the question marks.

        Suppose "?cba?" is our string, with calculated 
        values of question marks as {'c', 'a'}.

        Once we sort the characters, we get {'a', 'c'}

        Cost before sorting = Cc + (Cc + 1) + Cb + Ca + (Ca + 1)
        Cost after sorting = Ca + Cc + Cb + (Ca + 1) + (Cc + 1)

        So, no change in cost if we sort the replacement characters.
        */
        Collections.sort(quesReplaceValues);
        int replValueIdx = 0;

        for(int i = 0; i < str.length; i++) {
            if(str[i] == '?') {
                str[i] = quesReplaceValues.get(replValueIdx++);
            }

            if(replValueIdx == quesReplaceValues.size()) break;
        }
        
        return String.valueOf(str);
    }

    /*_____Tree Set Approach for getting next character to be used____*/

    // static char getLowest(int[] prefixOccur, int[] suffixOccur) {
    //     int lowestI = 0;
        
    //     /*
    //     Metric used for selection:
    //     prefixOccur[i] + suffixOccur[i]

    //     - prefixOccur tells the cost of current alphabet, 
    //       directly (as per definition)
    //     - suffixOccur tells us that as compared to previous cost 
    //       of the same letters, occuring after this index, 
    //       now each letter will cost us 1 unit more.

    //     In '?aaa' suppose the a's were about to cost x, x + 1, and x + 2,
    //     but we used an `a` in the question mark, so they will now cost
    //     x + 1, x + 2, x + 3. An increase in the cost of all 3 a's by 1.
    //     */
    //     for(int i = 1; i < 26; i++) {
    //         if(
    //             (prefixOccur[i] + suffixOccur[i])
    //             < (prefixOccur[lowestI] + suffixOccur[lowestI])
    //         ) {
    //             lowestI = i;
    //         }
    //     }
        
    //     return (char)(lowestI + 'a');
    // }
    
    // public String minimizeStringValue(String s) {
    //     char[] str = s.toCharArray();

    //     int[] suffixOccur = new int[26];
        
    //     int numOfQ = 0;
        
    //     for(char c: str) {
    //         if(c != '?') suffixOccur[c - 'a']++;
    //     }
        
    //     int[] prefixOccur = new int[26];
    
    //     TreeSet<Character> chars = new TreeSet<>((a1, a2) -> {
    //         int result = Integer.compare(
    //             (prefixOccur[a1 - 'a'] + suffixOccur[a1 - 'a']),
    //             (prefixOccur[a2 - 'a'] + suffixOccur[a2 - 'a'])
    //         );

    //         if(result == 0) result = Integer.compare(a1, a2);
    //         return result;
    //     });

    //     for(char c = 'a'; c <= 'z'; c++) chars.add(c);

    //     ArrayList<Character> quesReplaceValues = new ArrayList<>();
        
    //     for(int i = 0; i < str.length; i++) {
    //         if(str[i] == '?') {
    //             char lowest = chars.first();
                
    //             chars.remove(lowest);

    //             prefixOccur[lowest - 'a']++;

    //             chars.add(lowest);

    //             quesReplaceValues.add(lowest);
    //         }
    //         else {
    //             chars.remove(str[i]);

    //             prefixOccur[str[i] - 'a']++;
    //             suffixOccur[str[i] - 'a']--;

    //             chars.add(str[i]);
    //         }
    //     }
    
    //     /* 
    //     Sorting to enforce lexographical order.

    //     When we're sorting, it doesn't matter what characters 
    //     come between the question marks.

    //     Suppose "?cba?" is our string, with calculated 
    //     values of question marks as {'c', 'a'}.

    //     Once we sort the characters, we get {'a', 'c'}

    //     Cost before sorting = Cc + (Cc + 1) + Cb + Ca + (Ca + 1)
    //     Cost after sorting = Ca + Cc + Cb + (Ca + 1) + (Cc + 1)

    //     So, no change in cost if we sort the replacement characters.
    //     */
    //     Collections.sort(quesReplaceValues);
    //     int replValueIdx = 0;

    //     for(int i = 0; i < str.length; i++) {
    //         if(str[i] == '?') {
    //             str[i] = quesReplaceValues.get(replValueIdx++);
    //         }

    //         if(replValueIdx == quesReplaceValues.size()) break;
    //     }
        
    //     return String.valueOf(str);
    // }
}