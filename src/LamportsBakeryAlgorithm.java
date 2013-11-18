import java.util.concurrent.atomic.*;

public class LamportsBakeryAlgorithm {
    private AtomicIntegerArray flag;
    private AtomicIntegerArray label;
    private final int numberOfThreads;

    public LamportsBakeryAlgorithm(int numberOfThreads) {
        flag = new AtomicIntegerArray(numberOfThreads);
        label = new AtomicIntegerArray(numberOfThreads);
        this.numberOfThreads = numberOfThreads;
    }

    public void lock(int threadId) {
        flag.set(threadId, 1);
        label.set(threadId, max(label) + 1);
        flag.set(threadId, 0);

        for (int i = 0; i < numberOfThreads; ++i) {
            if (i != threadId) {
                while (flag.get(i) != 0) {
                }
                while (label.get(i) != 0 && (label.get(i) < label.get(threadId) ||
                        (label.get(i) == label.get(threadId) && threadId > i))) {
                }
            }
        }
    }

    public void unlock(int threadId) {
        label.set(threadId, 0);
    }

    private int max(AtomicIntegerArray array) {
        AtomicInteger max = new AtomicInteger();
        max.set(array.get(0));

        for (int i = 1; i < array.length(); ++i) {
            if (max.get() < array.get(i)) max.set(array.get(i));
        }
        return max.get();
    }
}
