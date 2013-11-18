public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 4;
        Counter counter = new Counter(numberOfThreads);
        Thread[] thread = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; ++i) {
            thread[i] = new Thread(new PrimeNumberCounter(i, counter));
            thread[i].start();
        }

        for (int i = 0; i < numberOfThreads; ++i) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
