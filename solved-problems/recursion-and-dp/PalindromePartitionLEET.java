// https://leetcode.com/problems/palindrome-partitioning/

class Solution {
    /* 
    This paritioning problem can be reduced to 2 fundamental choices:

    1. Start a new string 
    2. Add the current character to the last string.

    OR 
    Using the second pattern of array recursion:
    At each recursion level, we select where the next partition must occur.

    BOTH patterns are comparable in efficiency due to similar palindromic checking, before proceeding into further recursion calls.

    Only disadvantage with the first pattern is the complex if-else conditions having to be used, as well as the need of string mutation, which requires the StringBuilder class, adding a bit of an overhead.
    */

    static boolean isSubstringPalindrome(String s, int startIndex, int endIndex) {
        endIndex--;
        while(startIndex < endIndex) {
            if(s.charAt(startIndex++) != s.charAt(endIndex--)) {
                return false;
            }
        }

        return true;
    }

    static boolean isStringPalindrome(String s) {
        int startIndex = 0;
        int endIndex = s.length() - 1;

        while(startIndex < endIndex) {
            if(s.charAt(startIndex++) != s.charAt(endIndex--)) {
                return false;
            }
        }

        return true;
    }

    static void firstPattern(int index, String s, ArrayList<String> partitionArr, ArrayList<ArrayList<String>> solutionArr) {
        if(index == s.length()) {
            // For the last unchecked sub-string.
            if(!isStringPalindrome(partitionArr.get(partitionArr.size() - 1))) {                
                return;
            }

            // Cloning so that changes to partitionArr upon backtracking don't affect the current partition.
            solutionArr.add(new ArrayList<>(partitionArr));
            return;
        }

        
        if(    
            (
                /* 
                There is already a sub-string in the partitionArr and it is a palindrome,
                so it is OK to start a new partition.
                */
                (partitionArr.size() > 0) 
                &&
                ( isStringPalindrome( partitionArr.get(partitionArr.size() - 1) ) )
            )
            ||
            // No pre-existing sub-strings in the partitionArr
            (partitionArr.size() == 0)
        ) {
            // Adding a new string array (CREATING A PARTITION)
            partitionArr.add(s.substring(index, index + 1));
            firstPattern(index + 1, s, partitionArr, solutionArr);
            partitionArr.remove(partitionArr.size() - 1);
        }
        

        if(partitionArr.size() != 0) {
            int lastIndex = partitionArr.size() - 1;
            String originalString = partitionArr.get(lastIndex);
            StringBuilder sb = new StringBuilder(originalString);
            sb.append(s.charAt(index));
            /* 
            No need for palindrome check because the current sub-string is NOT complete,
            and we are not creating a new partition.
            */
            partitionArr.set(lastIndex, sb.toString());
            firstPattern(index + 1, s, partitionArr, solutionArr);
            // Backtracking step
            partitionArr.set(lastIndex, originalString);
        }        
    }
    
    // This is a more efficient solution, not making using of built-in methods, as well as using a more appropriate pattern.
    static void secondPattern(int index, String s, ArrayList<String> partitionArr, ArrayList<ArrayList<String>> solutionArr) {
        if(index == s.length()) {
            // Cloning so that changes to partitionArr upon backtracking don't affect the current partition.
            // solutionArr.add((ArrayList)partitionArr.clone());
            solutionArr.add(new ArrayList<>(partitionArr));
            return;
        }

        // endIndex is exclusive.
        for(int endIndex = index + 1; endIndex <= s.length(); endIndex++) {
            if(isSubstringPalindrome(s, index, endIndex)) {
                partitionArr.add(s.substring(index, endIndex));
                secondPattern(endIndex, s, partitionArr, solutionArr);
                partitionArr.remove(partitionArr.size() - 1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        ArrayList<ArrayList<String>> solutionArr = new ArrayList<>();

        ArrayList<String> partitionArr = new ArrayList<>();

        // firstPattern(0, s, partitionArr, solutionArr);
        secondPattern(0, s, partitionArr, solutionArr);

        return (List)solutionArr;
    }
}