public class BinPalindrome {
    public static void main(String[] args) {
        System.out.println(binPalindrome(9));
        System.out.println(binPalindromeBetter(9));
    }
    
    public static boolean binPalindrome(int num) {
        int original = num;
        int reverse = 0;
        
        // This while-loop essentially reverses the number according to base-2, instead of base-10. So 10 (1010 in binary), will become 5(0101 in binary).
        while(num > 0) {
            // Adding the least significant bit to reverse.
            reverse |= (num & 1);
            // right shifting num, to get next LSB in next iteration
            num >>= 1;
            
            // left shifting reverse, so that by the end of the iterations, this LSB of num, becomes MSB of reverse.
            reverse <<= 1;
        }
        
        // Right shifting once to compensate for the iteration when num becomes 0.
        reverse >>= 1;
        
        
        
        System.out.printf("%d, %d\n", original, reverse);
        
        return (reverse == original);
    }

    public static boolean binPalindromeBetter(int num) {
        int original = num;
        int reverse = 0;
        
        // This while-loop essentially reverses the number according to base-2, instead of base-10. So 10 (1010 in binary), will become 5(0101 in binary).
        while(num > 0) {
            /* 
            left shifting reverse, so that by the end of the iterations, this LSB of num, becomes MSB of reverse
            Only done if num > 0 and we are going to add another bit to the reverse.
            */
            reverse <<= 1;
            
            // Adding the least significant bit to reverse.
            reverse |= (num & 1);
            // right shifting num, to get next LSB in next iteration
            num >>= 1;
        }
        
        
        
        System.out.printf("%d, %d\n", original, reverse);
        
        return (reverse == original);
    }
}
