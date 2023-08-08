// https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/

class Solution {
    static boolean reduceCount(String word, HashMap<String, Integer> wordList) {
        if(!wordList.containsKey(word)) {
            // Returning FALSE indicates that the given word was NOT present in the word list.
            return false;
        }

        if(wordList.get(word) == 1) {
            wordList.remove(word);
        }
        else {
            wordList.computeIfPresent(word, (key, value) -> value - 1);
        }

        /* 
        Return TRUE indicates that the given word WAS present in the word list, 
        and we successfully removed/reduced its count
        */
        return true;
    }

    // Bruteforce (using naiive string matching)
    public List<Integer> findSubstring1(String s, String[] words) {

        // No significant improvement in runtime
        int wordLength = words[0].length();
        int requiredSubstringLength = words.length * wordLength;
        if(s.length() == 0 || words.length == 0 || s.length() < requiredSubstringLength) return new ArrayList<>();

        ArrayList<Integer> startingIndices = new ArrayList<>();

        HashMap<String, Integer> wordList = new HashMap<>();
        for(String word: words) {
            wordList.put(word, wordList.getOrDefault(word, 0) + 1);
            // wordList.computeIfPresent(word, (key, value) -> value + 1);
            // wordList.putIfAbsent(word, 1);
        }

        HashMap<String, Integer> tallyList = new HashMap<>(wordList);
        
        int windowStartIndex = 0;
        // No need for a windowEndIndex, because the window size is fixed.
        int maxStartIndex = s.length() - requiredSubstringLength;
        while(windowStartIndex <= maxStartIndex) {
            int subWindowStart = windowStartIndex;

            while(!tallyList.isEmpty()) {
                /* 
                Unable to find the current subWindow in the word list, so we know this check is over.
                This is why increment the windowStartIndex an 
                */
                if( !reduceCount( s.substring(subWindowStart, subWindowStart + wordLength), tallyList ) ) {
                    windowStartIndex++;
                    break;
                }

                // All words checked, so we add the current windowStartIndex into the answer array.
                if(tallyList.isEmpty()) {
                    startingIndices.add(windowStartIndex);
                    /* 
                    Increment start index by only 1 to avoid missing valid matches
                    ? Can we use DP to better this increment? (Like KMP)
                    */
                    windowStartIndex++;
                }
                // All words NOT checked, so we move onto the next sub-part of the window.
                else {
                    subWindowStart += wordLength;
                }
            }

            tallyList.putAll(wordList);
        }

        return startingIndices;
    }

    // Better (Wlmiminated the comparsion of maps in this approach)
    public List<Integer> findSubstring(String s, String[] words) {

        int wordLength = words[0].length();
        int requiredSubstringLength = words.length * wordLength;
        // No significant improvement in runtime
        if(s.length() == 0 || words.length == 0 || s.length() < requiredSubstringLength) return new ArrayList<>();

        ArrayList<Integer> startingIndices = new ArrayList<>();

        HashMap<String, Integer> wordList = new HashMap<>();
        for(String word: words) {
            wordList.put(word, wordList.getOrDefault(word, 0) + 1);
            // wordList.computeIfPresent(word, (key, value) -> value + 1);
            // wordList.putIfAbsent(word, 1);
        }

        int maxStartIndex = s.length() - requiredSubstringLength;
        /*
        This loop is needed for selecting all possible start points of the window. 
        No need for a windowEndIndex, because the window size is fixed.
        */
        for(int windowStartIndex = 0; windowStartIndex <= maxStartIndex; windowStartIndex++) {
            int subWindowStart = windowStartIndex;

            // This is used to keep a tally of words within the current word.
            HashMap<String, Integer> windowWordList = new HashMap<>();

            int maxWordInWindowIndex = (windowStartIndex + requiredSubstringLength - wordLength); 
            // This loop is for iterating over the discrete words in the window.
            for(
                int wordInWindowIndex = windowStartIndex; 
                wordInWindowIndex <= maxWordInWindowIndex;
                wordInWindowIndex += wordLength
            ) {
                String wordInWindow = s.substring(wordInWindowIndex, wordInWindowIndex + wordLength);
                // Adding 1, because of the current word detected.
                int currentCount = windowWordList.getOrDefault(wordInWindow, 0) + 1;
                // This is the number of instances of this word.
                int actualCount = wordList.getOrDefault(wordInWindow, 0);

                if(
                    // actualCount == 0 // This word was NOT actually required
                    // ||
                    currentCount > actualCount 
                    /* 
                    This word is present in quantities more than necessary 
                    (e.g 0 was required, but more are present.)
                    */
                ) {
                    break;
                }

                windowWordList.put(wordInWindow, currentCount);

                /* 
                Instead of comparing maps, after the loop has ended which is a 
                more expensive operation, we just check if we were able to successfully
                reach the last iteration of the for-loop.

                If we were, it means none of the conditions were violated, so we add the 
                the window to our solution array.
                */
                if(wordInWindowIndex == maxWordInWindowIndex) {
                    startingIndices.add(windowStartIndex);
                }
            }
        } 

        return startingIndices;
    }
}