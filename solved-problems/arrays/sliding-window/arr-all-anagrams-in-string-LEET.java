class Solution {
    public static void reduceCharacterAndRemove(char character, Map<Character, Integer> tallyTable) {
        if(tallyTable.get(character) == 1) {
            tallyTable.remove(character);
        }
        else {            
            tallyTable.computeIfPresent(character, (key, value) -> value - 1);
        }
    }

    
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> anagramStartIndices = new ArrayList<>();

        Map<Character, Integer> frequencyTable = new HashMap<>();
        for(char letter: p.toCharArray()) {
            frequencyTable.computeIfPresent(letter, (key, value) -> value + 1);
            frequencyTable.putIfAbsent(letter, 1);
        }

        Map<Character, Integer> tallyTable = new HashMap<>(frequencyTable);

        // for(Map.Entry<Character, Integer> entry: frequencyTable.entrySet()) {
        //     System.out.printf("%c, %d\n", entry.getKey(), entry.getValue());
        // }

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
}