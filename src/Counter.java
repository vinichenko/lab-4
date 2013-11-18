public class Counter {
    private LamportsBakeryAlgorithm locker;
    private volatile int count;

    public Counter(int numberOfThreads) {
        this.locker = new LamportsBakeryAlgorithm(numberOfThreads);
        this.count = 1;
    }

    public LamportsBakeryAlgorithm getLocker() {
        return locker;
    }

    public int getIncCount() {
        return count++;
    }
}
