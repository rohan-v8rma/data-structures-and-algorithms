public class TestingFasterInput {
    public static void main(String[] args) {
        FasterInput ip = new FasterInput();

        long start = System.nanoTime();

        long numbers = 1000000;

        for(int i = 1; i <= numbers; i++) {
            ip.nextInt();
        }

        long end = System.nanoTime();

        System.out.printf("FasterInput took an average of %f ns\n", ((end - start)) / (double)numbers);
    }
}