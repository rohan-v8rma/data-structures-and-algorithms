public class TestingSystemOut {
    public static void main(String[] args) {
        long start = System.nanoTime();

        long numbers = 1000000;

        for(int i = 1; i <= numbers; i++) {
            System.out.print(i);
        }

        System.out.println();

        long end = System.nanoTime();

        System.out.printf("System.out took an average of %f ns\n", ((end - start)) / (double)numbers);
    }   
}
