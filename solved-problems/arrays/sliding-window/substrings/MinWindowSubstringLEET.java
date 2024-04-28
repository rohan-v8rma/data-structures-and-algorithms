//  https://leetcode.com/problems/minimum-window-substring/
// TODO: Make notes

class Solution {
    /*___BRUTEFORCE (GOT TLE)____*/

    // public static void incrementCharCountAndRemove(char character, Map<Character, Integer> tallyTable, Map<Character, Integer> letterFrequencyTable) {
    //     int increasedCount = tallyTable.get(character) + 1;
        
    //     // Desired count is achieved.
    //     if(increasedCount == letterFrequencyTable.get(character)) {
    //         tallyTable.remove(character);
    //     }
    //     else {
    //         tallyTable.put(character, increasedCount);
    //     }
    // }

    // public String minWindow(String s, String t) {
    //     Map<Character, Integer> letterFrequencyTable = new HashMap<>();
    //     Map<Character, Integer> tallyTable = new HashMap<>();        

    //     // Creating the letter frequency table.
    //     for(int index = 0; index < t.length(); index++) {
    //         char letter = t.charAt(index);

    //         // Incrementing count, if letter IS already in frequency table.
    //         letterFrequencyTable.computeIfPresent(letter, (key, value) -> value + 1);
    //         // Setting count to 1, if letter is not already in frequency table.
    //         letterFrequencyTable.putIfAbsent(letter, 1);
    //         tallyTable.putIfAbsent(letter, 0);
    //     }
        

    //     String windowString = "";

    //     int windowStartIndex = 0;
    //     while(windowStartIndex < s.length()) {
    //         /* 
    //         windowStartIndex is the starting index of the window sub-string, 
    //         so we prefer starting it from a character that is in the given string we have to match, 
    //         in order to minimize the window size.

    //         By checking if the current character is in the letter frequency table or not, we ensure that
    //         we start from the desired character.
    //         */
    //         // if(!letterFrequencyTable.containsKey( sArray[windowStartIndex] )) {
    //         if(!letterFrequencyTable.containsKey( s.charAt(windowStartIndex) )) {
    //             windowStartIndex++;
    //             continue;
    //         }
            
    //         // This is an optimization step for incrementing windowStartIndex.
    //         int nextMatchingCharacterIndex = -1;
    //         // This is for the window's end point. Initially we keep it one more than the windowStartIndex.
    //         int windowEndIndex = windowStartIndex + 1;

    //         Map<Character, Integer> tallyTableCopy = new HashMap<>(tallyTable);
    //         // incrementCharCountAndRemove(sArray[windowStartIndex], tallyTableCopy, letterFrequencyTable);
    //         incrementCharCountAndRemove(s.charAt(windowStartIndex), tallyTableCopy, letterFrequencyTable);

    //         while(windowEndIndex < s.length() && !tallyTableCopy.isEmpty()) {
    //             // char currentCharacter = sArray[windowEndIndex];
    //             char currentCharacter = s.charAt(windowEndIndex);

    //             if(nextMatchingCharacterIndex == -1 && letterFrequencyTable.containsKey(currentCharacter)) {
    //                 nextMatchingCharacterIndex = windowEndIndex;
    //             }

    //             if( tallyTableCopy.containsKey(currentCharacter) ) {
    //                 incrementCharCountAndRemove(currentCharacter, tallyTableCopy, letterFrequencyTable);
    //             }
                
    //             windowEndIndex++;
    //         }


    //         if(
    //             tallyTableCopy.isEmpty()
    //             &&
    //             (    
    //                 ( (windowEndIndex - windowStartIndex) < windowString.length() )
    //                 ||
    //                 windowString == ""
    //             )
    //         ) {
    //             windowString = s.substring(windowStartIndex, windowEndIndex);
    //         }


    //         // No matching character found
    //         if(nextMatchingCharacterIndex == -1) {
    //             break;
    //         }
            
    //         // This is the place where the next matching character is.
    //         windowStartIndex = nextMatchingCharacterIndex;
    //     }

    //     return windowString;
    // }

    /*___
    SELF DEVELOPED OPTIMAL

    The Dequeue we have used is the fastest method to shrink the window,
    as compared to reducing the window size in single steps.

    TC: O(m + n)
    SC: O(m)
    ___*/

    // int charToInt(char c) {
    //     return c - (c > 'Z' ? 'a' - 26 : 'A');
    // }

    // public String minWindow(String s, String t) {
    //     ArrayDeque<Integer> sIndices = new ArrayDeque<>();

    //     int[] ct = new int[52];
    //     boolean[] present = new boolean[52];

    //     // Getting count of characters in string `t`
    //     for(char c: t.toCharArray()) {
    //         present[charToInt(c)] = true;
    //         ct[charToInt(c)]++;
    //     }

    //     int uniqueCharCt = 0;

    //     // Counting number of unique characters we need. This will help us find our first window.
    //     for(boolean c: present) {
    //         if(c) uniqueCharCt++;
    //     }

    //     int endIdx = 0;

    //     int windowStart = Integer.MAX_VALUE;
    //     int windowEnd = Integer.MIN_VALUE;

    //     // Finding initial window in which all the required character counts present.
    //     while(uniqueCharCt != 0 && endIdx < s.length()) {
    //         int cNum = charToInt(s.charAt(endIdx));

    //         if(present[cNum]) {
    //             ct[cNum]--;
    //             sIndices.offerLast(endIdx);

    //             // Count of 1 unique character met, so decrement the counter variable.
    //             if(ct[cNum] == 0) uniqueCharCt--;
    //         }
            
    //         endIdx++;
    //     }

    //     /* 
    //     Removing any extra characters from beginning
    //     E.g.: s = 'bba', t = 'ab'

    //     So first 'b' of s has to be removed.
    //     */
    //     while(!sIndices.isEmpty()) {
    //         int cNum = charToInt(s.charAt(sIndices.peekFirst()));
        
    //         // This character is needed in the window.
    //         if(ct[cNum] == 0) break;

    //         // The character was extra in the window.
    //         sIndices.pollFirst();
    //         ct[cNum]++;
    //     }

    //     // If this window contains all the required characters, update window.
    //     if(uniqueCharCt == 0) {
    //         windowStart = sIndices.peekFirst();
    //         windowEnd = sIndices.peekLast();
    //     }
        
    //     while(endIdx < s.length() && !sIndices.isEmpty()) {
    //         // Removing the first character in the current window.
    //         int charRemoved = charToInt(s.charAt(sIndices.pollFirst()));
    //         ct[charRemoved]++;

    //         // Trying to get back another instance ahead of the removed character.
    //         while(ct[charRemoved] != 0 && endIdx < s.length()) {
    //             int cNum = charToInt(s.charAt(endIdx));

    //             if(present[cNum]) {
    //                 ct[cNum]--;
    //                 sIndices.offerLast(endIdx);
    //             }
            
    //             endIdx++;
    //         }

    //         /* 
    //         Removing any extra characters, which we might have accumulated 
    //         while searching for the removed character instance
    //         */
    //         while(!sIndices.isEmpty()) {
    //             int charToBeRemoved = charToInt(s.charAt(sIndices.peekFirst()));

    //             // This character is needed in the window.            
    //             if(ct[charToBeRemoved] >= 0) break;

    //             // The character was extra in the window.
    //             sIndices.pollFirst();
    //             ct[charToBeRemoved]++;
    //         }

    //         // If the removed character was actually successfully recouped.
    //         if(ct[charRemoved] == 0) {
    //             int newWindowStart = sIndices.peekFirst();
    //             int newWindowEnd = sIndices.peekLast();

    //             // Update window, if new window is smaller.
    //             if(newWindowEnd - newWindowStart < windowEnd - windowStart) {
    //                 windowEnd = newWindowEnd;
    //                 windowStart = newWindowStart;
    //             }
    //         }
    //     }
        
    //     return (windowStart > windowEnd ? "" : s.substring(windowStart, windowEnd + 1));
    // }

    /*
    OPTIMIZED FOR SPACE O(1)

    TC: worse O(m + n)
    SC: O(52 * 2)
    */

    int charToInt(char c) {
        return c - (c > 'Z' ? 'a' - 26 : 'A');
    }

    public String minWindow(String s, String t) {
        int[] ct = new int[52];
        boolean[] present = new boolean[52];

        // Getting count of characters in string `t`
        for(char c: t.toCharArray()) {
            present[charToInt(c)] = true;
            ct[charToInt(c)]++;
        }

        int uniqueCharCt = 0;

        // Counting number of unique characters we need. This will help us find our first window.
        for(boolean c: present) {
            if(c) uniqueCharCt++;
        }

        int sIdx = 0;
        int eIdx = 0;
        int windowStart = 0;
        int windowEnd = Integer.MAX_VALUE;

        while(eIdx < s.length()) {
            // Getting all the characters needed in the window
            while(uniqueCharCt != 0 && eIdx < s.length()) {
                int charToAdd = charToInt(s.charAt(eIdx));

                if(present[charToAdd]) {
                    ct[charToAdd]--;

                    if(ct[charToAdd] == 0) uniqueCharCt--;
                }

                eIdx++;
            }

            // Removing any unnecessary accumulated characters.
            while(uniqueCharCt == 0 && sIdx < eIdx) {
                if(eIdx - sIdx < windowEnd - windowStart) {
                    windowStart = sIdx;
                    windowEnd = eIdx;
                }

                int charToRemove = charToInt(s.charAt(sIdx));

                if(present[charToRemove]) {
                    if(ct[charToRemove] == 0) uniqueCharCt++;

                    ct[charToRemove]++;
                }
                
                sIdx++;
            }
        }

        
        return (windowEnd == Integer.MAX_VALUE ? "" : s.substring(windowStart, windowEnd));
    }
}