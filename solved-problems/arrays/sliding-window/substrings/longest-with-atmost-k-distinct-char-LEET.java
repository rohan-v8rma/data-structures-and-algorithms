// https://www.codingninjas.com/studio/problems/distinct-characters_2221410

public class Solution {
	// Sliding window approach using HashMap
	// public static int kDistinctChars(int k, String str) {
	// 	int strLen = 1;

	// 	int maxSize = 1;

	// 	int startIdx = 0;

	// 	HashMap<Character, Integer> charCounts = new HashMap<>();

	// 	for(int endIdx = 0; endIdx < str.length(); endIdx++) {
	// 		// Including the new character into the current window
	// 		char currChar = str.charAt(endIdx); 
	// 		charCounts.put(currChar, 1 + charCounts.getOrDefault(currChar, 0));

	// 		/* 
	// 		Removing characters from the current window as long as there are more than
	// 		k distinct characters in the window.
	// 		*/
	// 		while(charCounts.size() > k) {
	// 			char charToRemove = str.charAt(startIdx++);
	// 			int count = charCounts.get(charToRemove);

	// 			if(count == 1) {
	// 				charCounts.remove(charToRemove);
	// 			}
	// 			else {
	// 				charCounts.put(charToRemove, count - 1);
	// 			}
	// 		}

	// 		maxSize = Math.max(maxSize, endIdx - startIdx + 1);
	// 	}


	// 	return maxSize;
	// }

	/* 
	Sliding window approach using character map table
	since the string contains only lower case alphabets.
	*/
	public static int kDistinctChars(int k, String str) {
		int maxSize = 0;
		
		int numOfDistinctChars = 0;

		int startIdx = 0;

		int[] charCounts = new int[26];
		

		for(int endIdx = 0; endIdx < str.length(); endIdx++) {
			// Including the new character into the current window
			int currCharIdx = str.charAt(endIdx) - 97; 

			if(charCounts[currCharIdx] == 0) {
				numOfDistinctChars++;
			}
			charCounts[currCharIdx]++;

			/* 
			Removing characters from the current window as long as there are more than
			k distinct characters in the window.
			*/
			while(numOfDistinctChars > k) {
				int charToRemoveIdx = str.charAt(startIdx++) - 97;
				int count = charCounts[charToRemoveIdx];
				
				if(count == 1) {
					numOfDistinctChars--;
				}
				
				charCounts[charToRemoveIdx]--;
			}

			/* 
			If we had to count the number of sub-strings with atmost k distinct characters,
			we would have been adding the window size to the count, so that we do NOT miss out
			the smaller sub-strings with the window; which also obviously have 
			atmost k distinct characters.

			But, in this case we just need the size of the largest substring.
			*/
			maxSize = Math.max(maxSize, endIdx - startIdx + 1);
		}


		return maxSize;
	}
}
