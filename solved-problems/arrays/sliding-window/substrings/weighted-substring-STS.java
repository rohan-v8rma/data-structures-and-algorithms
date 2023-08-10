public class Solution {
    static int calculateLetterWeight(char letter) {
        letter = Character.toUpperCase(letter);

        int currentWeight;

        for(int letterPos = 1; letterPos <= (letter - 'A' + 1); letterPos++) {
            if(letterPos == 1) {
                currentWeight = 1;
            }
            else {
                currentWeight = currentWeight * (letterPos + 1);
            }        
        }

        return currentWeight;
    }

    public static void main(String[] args) {
        for(char c = 'a'; c <= 122; c++) {
            System.out.printf("Letter weight of %c: %d\n", c, calculateLetterWeight(c));
        }
    }
}
