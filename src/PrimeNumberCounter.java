public class PrimeNumberCounter implements Runnable {
    private final Counter counter;
    private final int threadId;
    private final int maxNumber = 1000;

    public PrimeNumberCounter(int threadId, Counter counter) {
        this.counter = counter;
        this.threadId = threadId;
    }

    private boolean isPrime(int number) {
        for (int i = 2; i * i <= number; ++i)
            if (number % i == 0) return false;
        return true;
    }

    @Override
    public void run() {
        int i = 0;

        while (i < maxNumber) {
            counter.getLocker().lock(threadId);
            try {
                i = counter.getIncCount();
            } finally {
                counter.getLocker().unlock(threadId);
            }
            if (isPrime(i))
                System.out.println("Thread " + threadId + ": " + i);
        }
    }
}
