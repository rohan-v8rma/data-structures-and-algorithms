// https://leetcode.com/problems/substring-with-concatenation-of-all-words

class Solution {
    /* 
    Bruteforce: O(M*N)
    N - Length of string
    M - Number of words in list.

    We keep each index of string as a start index of a new window, and try to find
    the M words within that window.
    */
    public List<Integer> findSubstring(String s, String[] words) {

        int wordLen = words[0].length();
        int requiredSubstrLen = words.length * wordLen;
        // No significant improvement in runtime
        if(s.length() == 0 || words.length == 0 || s.length() < requiredSubstrLen) {
            return new ArrayList<>();
        }

        ArrayList<Integer> startingIndices = new ArrayList<>();

        HashMap<String, Integer> wordList = new HashMap<>();
        for(String word: words) {
            wordList.put(word, wordList.getOrDefault(word, 0) + 1);
            // wordList.computeIfPresent(word, (key, value) -> value + 1);
            // wordList.putIfAbsent(word, 1);
        }

        int maxStartIndex = s.length() - requiredSubstrLen;
        
        /*
        This loop is needed for selecting all possible start points of the window. 
        No need for a windowEndIndex, because the window size is fixed.
        */
        for(int windowStartIdx = 0; windowStartIdx <= maxStartIndex; windowStartIdx++) {
              
            // This is used to keep a tally of words within the current substring.
            HashMap<String, Integer> wordsInWindow = new HashMap<>();

            int wordIdx; 
            // This variable helps us to check if we were able to successfully check all words.
            int lastWordIdx = (windowStartIdx + requiredSubstrLen - wordLen); 
            
            // This loop is for iterating over the discrete words in the window.
            for(
                wordIdx = windowStartIdx; 
                wordIdx <= lastWordIdx;
                wordIdx += wordLen
            ) {
                
                String wordInWindow = s.substring(wordIdx, wordIdx + wordLen);
                
                // Adding 1, because of the current word detected.
                
                int currentCount = wordsInWindow.getOrDefault(wordInWindow, 0) + 1;
                
                // This is the number of instances of this word.
                int actualCount = wordList.getOrDefault(wordInWindow, 0);

                /* 
                This word is present in quantities more than necessary 
                (e.g 0 was required, but more are present.)
                */
                if(currentCount > actualCount) {
                    wordIdx = -1;
                    break;
                }

                wordsInWindow.put(wordInWindow, currentCount);
            }

            /* 
            We check if we were able to successfully reach the last iteration of the for-loop,
            by checking if wordIdx was not set to -1.

            If it wasn't, it means no word exceeded its required count, so we add the window's
            starting index to our solution array.
            */
            if(wordIdx != -1) {
                startingIndices.add(windowStartIdx);
            }
        } 

        return startingIndices;
    }

}