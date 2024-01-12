//  https://leetcode.com/problems/minimum-window-substring/

class Solution {

    public static void incrementCharCountAndRemove(char character, Map<Character, Integer> tallyTable, Map<Character, Integer> letterFrequencyTable) {
        int increasedCount = tallyTable.get(character) + 1;
        
        // Desired count is achieved.
        if(increasedCount == letterFrequencyTable.get(character)) {
            tallyTable.remove(character);
        }
        else {
            tallyTable.put(character, increasedCount);
        }
    }

    // First approach
    public String minWindow(String s, String t) {
        Map<Character, Integer> letterFrequencyTable = new HashMap<>();
        Map<Character, Integer> tallyTable = new HashMap<>();        

        // char[] sArray = s.toCharArray();
        // char[] tArray = t.toCharArray();

        // for(char letter: sArray) {
        //     System.out.printf(" %c ", letter);
        // }
        // System.out.println();
        // for(int index = 0; index < sArray.length; index++) {
        //     System.out.printf("%2d ", index);
        // }
        // System.out.println();

        // Creating the letter frequency table.
        // for(letter: tArray) {
        for(int index = 0; index < t.length(); index++) {
            char letter = t.charAt(index);

            // Incrementing count, if letter IS already in frequency table.
            letterFrequencyTable.computeIfPresent(letter, (key, value) -> value + 1);
            // Setting count to 1, if letter is not already in frequency table.
            letterFrequencyTable.putIfAbsent(letter, 1);
            tallyTable.putIfAbsent(letter, 0);
        }
        

        String windowString = "";

        int windowStartIndex = 0;
        while(windowStartIndex < s.length()) {
            // System.out.printf("%d, ", windowStartIndex);
            /* 
            windowStartIndex is the starting index of the window sub-string, 
            so we prefer starting it from a character that is in the given string we have to match, 
            in order to minimize the window size.

            By checking if the current character is in the letter frequency table or not, we ensure that
            we start from the desired character.
            */
            // if(!letterFrequencyTable.containsKey( sArray[windowStartIndex] )) {
            if(!letterFrequencyTable.containsKey( s.charAt(windowStartIndex) )) {
                windowStartIndex++;
                continue;
            }
            
            // This is an optimization step for incrementing windowStartIndex.
            int nextMatchingCharacterIndex = -1;
            // This is for the window's end point. Initially we keep it one more than the windowStartIndex.
            int windowEndIndex = windowStartIndex + 1;

            Map<Character, Integer> tallyTableCopy = new HashMap<>(tallyTable);
            // incrementCharCountAndRemove(sArray[windowStartIndex], tallyTableCopy, letterFrequencyTable);
            incrementCharCountAndRemove(s.charAt(windowStartIndex), tallyTableCopy, letterFrequencyTable);

            while(windowEndIndex < s.length() && !tallyTableCopy.isEmpty()) {
                // char currentCharacter = sArray[windowEndIndex];
                char currentCharacter = s.charAt(windowEndIndex);

                if(nextMatchingCharacterIndex == -1 && letterFrequencyTable.containsKey(currentCharacter)) {
                    nextMatchingCharacterIndex = windowEndIndex;
                }

                if( tallyTableCopy.containsKey(currentCharacter) ) {
                    incrementCharCountAndRemove(currentCharacter, tallyTableCopy, letterFrequencyTable);
                }
                
                windowEndIndex++;
            }


            // if(tallyTableCopy.isEmpty()) {
            // System.out.println(s.substring(windowStartIndex, windowEndIndex));
            // }          

            // for (Map.Entry<Character, Integer> entry : tallyTableCopy.entrySet()) {
            //     System.out.printf("`%c`: %d\n", entry.getKey(), entry.getValue(), entry.getValue());
            // }

            if(
                tallyTableCopy.isEmpty()
                &&
                (    
                    ( (windowEndIndex - windowStartIndex) < windowString.length() )
                    ||
                    windowString == ""
                )
            ) {
                windowString = s.substring(windowStartIndex, windowEndIndex);
            }


            // No matching character found
            if(nextMatchingCharacterIndex == -1) {
                break;
            }
            
            // This is the place where the next matching character is.
            windowStartIndex = nextMatchingCharacterIndex;
        }

        return windowString;
    }
}