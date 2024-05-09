package Project1;

public abstract class AbstractSort {
    protected int[] array;
    private long startTime;
    private static int count;
    private String name;

    public abstract void sort(int[] array) throws UnsortedException;

    public void startSort() {
        this.array = array.clone(); //clone the array so that the original is not modified.
        startTime = System.nanoTime();
        count = 0;
    }

    public void endSort() throws UnsortedException {
        // Compute elapsed time
        if(!isSorted()) {
            throw new UnsortedException();
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
    }

    public static void incrementCount() {
        count++;
    }

    public void setName(String n) {
        name = n;
    }
    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public long getTime() {
        return System.nanoTime() - startTime;
    }

    public boolean isSorted() {
        // Check if the array is sorted, assuming ascending order
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }
}
