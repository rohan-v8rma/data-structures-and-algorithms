public class Basics {
    public static void main(String[] args) {
        
        // In memory this is 01010. First bit 0 indicates positive number
        int x = 10;
        
        // This gives us the twos complement of x. x complement + 1. Inverts the sign of x
        int twosCompOfX = (~x) + 1;
        System.out.println(twosCompOfX);
        
        // Adding 1 to X. Understand logic using twos complement above.
        int add1ToX = (-(~x));
        System.out.println(add1ToX);
        
        // Left Shift
        int multiplyXby2 = x<<1;
        System.out.println(multiplyXby2);
        
        // Right shift
        int divideXby2 = x>>1;
        System.out.println(divideXby2);
    }
}

/*
Output:
-10
11
20
5
 */
