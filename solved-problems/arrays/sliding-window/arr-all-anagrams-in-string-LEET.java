class Solution {
    public static void reduceCharacterAndRemove(char character, Map<Character, Integer> tallyTable) {
        if(tallyTable.get(character) == 1) {
            tallyTable.remove(character);
        }
        else {            
            tallyTable.computeIfPresent(character, (key, value) -> value - 1);
        }
    }

    // Normal Solution 1 (used HashMap) 
    // ! NOT SPACE OPTIMIZED
    public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> anagramStartIndices = new ArrayList<>();

        Map<Character, Integer> frequencyTable = new HashMap<>();
        
        for(char letter: p.toCharArray()) {
            frequencyTable.computeIfPresent(letter, (key, value) -> value + 1);
            frequencyTable.putIfAbsent(letter, 1);
        }

        Map<Character, Integer> tallyTable = new HashMap<>(frequencyTable);

        int maxStartIndex = s.length() - p.length();
        int windowStartIndex = 0;
        int windowEndIndex = 0;
        while(windowStartIndex <= maxStartIndex && windowEndIndex < s.length()) {
            if(tallyTable.containsKey(s.charAt(windowEndIndex))) {
                reduceCharacterAndRemove(s.charAt(windowEndIndex), tallyTable);

                if(tallyTable.isEmpty()) {
                    anagramStartIndices.add(windowStartIndex);
                    tallyTable.put(s.charAt(windowStartIndex), 1);
                    // Moving the start of the window forward (and end of the window below)
                    windowStartIndex++;
                }
                /* 
                If the tally table is empty, we increment windowEndIndex to check the next unchecked character.
                OR
                If the tally table is NOT empty, simply increment windowEndIndex to check the unchecked characters
                to try and make it empty

                IN BOTH CASES WE HAVE TO INCREMENT windowEndIndex
                */
                windowEndIndex++;
            }
            /* 
            tallyTable doesn't contain the character, but character at start of window 
            is equal to character at end of window, so we can just shift windowStartIndex
            and all the characters within the window will remain same.

            This also handles the case where window size is 0, so we keep it 0 by shifting both pointers.
            */
            else if(s.charAt(windowStartIndex) == s.charAt(windowEndIndex)) {
                windowStartIndex++;
                // Incrementing windowEndIndex so that checking of characters takes place from next unchecked character.
                windowEndIndex++;
                continue;
            }
            else if(frequencyTable.containsKey(s.charAt(windowEndIndex))) {
                tallyTable.computeIfPresent(s.charAt(windowStartIndex), (key, value) -> value + 1);
                tallyTable.putIfAbsent(s.charAt(windowStartIndex), 1);
                windowStartIndex++;
                // Keep the windowEndIndex at the same place only.
            }
            // Resetting the counts and reducing the window size to 0, if non-matching character found.
            else {
                windowEndIndex++;
                tallyTable.putAll(frequencyTable);
                windowStartIndex = windowEndIndex;
            }
        }

        return anagramStartIndices;   
    }


    // Normal Solution 2 (used HashMap) AND used for-loop to iterate over the main string.
    // ! NOT SPACE OPTIMIZED
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> anagramStartIndices = new ArrayList<>();

        Map<Character, Integer> frequencyTable = new HashMap<>();
        
        for(char letter: p.toCharArray()) {
            frequencyTable.put(letter, frequencyTable.getOrDefault(letter, 0) + 1);
            // frequencyTable.computeIfPresent(letter, (key, value) -> value + 1);
            // frequencyTable.putIfAbsent(letter, 1);
        }

        int maxStartIndex = s.length() - p.length();

        int currentIndex = 0;
        Map<Character, Integer> tallyTable = new HashMap<>();
        for(int windowStartIndex = 0; windowStartIndex <= maxStartIndex; windowStartIndex++) {
            int windowEndIndex = windowStartIndex + p.length() - 1;

            // The tally table is empty, so we need to start matching from the start of the window itself.
            if(tallyTable.isEmpty()) {
                currentIndex = windowStartIndex;
            }
            
            while(currentIndex <= windowEndIndex) {
                char currentCharacter = s.charAt(currentIndex);

                int currentCount = tallyTable.getOrDefault(currentCharacter, 0) + 1; 
                // Add 1 because of the currently seen character.
                
                int actualCount = frequencyTable.getOrDefault(currentCharacter, 0);
                
                // This character is NOT needed, so we change the windowStartIndex.
                if(actualCount == 0) {
                    // This will be incremented in the next iteration.
                    windowStartIndex = currentIndex;
                    tallyTable = new HashMap<>();
                    break;
                }
                // The currentCharacter, is an extra one 
                else if(currentCount == actualCount + 1) {
                    /*
                    But, it is the same as the window start character
                    so we retain the same count by shifting the window forward
                    */
                    if(s.charAt(windowStartIndex) == currentCharacter) {
                        // Incrementing the current index to be checked
                        currentIndex++;
                    }
                    else {
                        // Removing the starting character.
                        reduceCharacterAndRemove(s.charAt(windowStartIndex), tallyTable);
                        windowStartIndex++;
                        continue;
                    }
                    
                    break;
                }

                tallyTable.put(currentCharacter, currentCount);

                // All characters checked
                if(currentIndex == windowEndIndex) {
                    anagramStartIndices.add(windowStartIndex);
                    // Adding the starting index of window to our solution array
                    char windowStartingChar = s.charAt(windowStartIndex);
                    reduceCharacterAndRemove(windowStartingChar, tallyTable);
                }
                
                // We need to check the next character.
                currentIndex++;
            }
        }

        return anagramStartIndices;   
    }

    
    static boolean isTallyTableEmpty(int[] tallyTable) {
        for(int index = 0; index < 26; index++) {
            if(tallyTable[index] != 0) {
                return false;
            }
        }
        return true;
    }

    
    // Optimal (use arrays for map, resulting in lower overhead)
    //? Space optimized.
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> anagramStartIndices = new ArrayList<>();

        int[] frequencyTable = new int[26];
        // Map<Character, Integer> frequencyTable = new HashMap<>();
        
        for(char letter: p.toCharArray()) {
            frequencyTable[letter - 'a']++;
            // frequencyTable.put(letter, frequencyTable.getOrDefault(letter, 0) + 1);
            // frequencyTable.computeIfPresent(letter, (key, value) -> value + 1);
            // frequencyTable.putIfAbsent(letter, 1);
        }

        int maxStartIndex = s.length() - p.length();

        int currentIndex = 0;

        int[] tallyTable = new int[26];
        // Map<Character, Integer> tallyTable = new HashMap<>();
        for(int windowStartIndex = 0; windowStartIndex <= maxStartIndex; windowStartIndex++) {
            int windowEndIndex = windowStartIndex + p.length() - 1;

            // The tally table is empty, so we need to start matching from the start of the window itself.
            if(isTallyTableEmpty(tallyTable)) {
                currentIndex = windowStartIndex;
            }
            
            while(currentIndex <= windowEndIndex) {
                int currentCharacterIndex = s.charAt(currentIndex) - 'a';

                int currentCount = tallyTable[currentCharacterIndex] + 1; 
                // Add 1 because of the currently seen character.
                
                int actualCount = frequencyTable[currentCharacterIndex];
                
                // This character is NOT needed, so we change the windowStartIndex.
                if(actualCount == 0) {
                    // This will be incremented in the next iteration.
                    windowStartIndex = currentIndex;
                    tallyTable = new int[26];
                    break;
                }
                // The currentCharacter, is an extra one 
                else if(currentCount == actualCount + 1) {
                    /*
                    But, it is the same as the window start character
                    so we retain the same count by shifting the window forward
                    */
                    if((s.charAt(windowStartIndex) - 'a') == currentCharacterIndex) {
                        // Incrementing the current index to be checked
                        currentIndex++;
                    }
                    else {
                        // Removing the starting character.
                        tallyTable[s.charAt(windowStartIndex) - 'a']--;
                        windowStartIndex++;
                        continue;
                    }
                    
                    break;
                }

                tallyTable[currentCharacterIndex] = currentCount;

                // All characters checked
                if(currentIndex == windowEndIndex) {
                    anagramStartIndices.add(windowStartIndex);
                    // Adding the starting index of window to our solution array
                    int windowStartingCharIndex = s.charAt(windowStartIndex) - 'a';
                    tallyTable[windowStartingCharIndex]--;
                }
                
                // We need to check the next character.
                currentIndex++;
            }
        }

        return anagramStartIndices;   
    }

    
}