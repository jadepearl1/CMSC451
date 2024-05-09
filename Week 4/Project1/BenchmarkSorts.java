package Project1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BenchmarkSorts {
    public static void main(String[] args) throws UnsortedException {
        // Initialize sorting algorithms
        AbstractSort heap = new Heapsort();
        AbstractSort quick = new Quicksort();
        heap.setName("HeapSort");
        quick.setName("QuickSort");
        runWarmUp(heap);
        runWarmUp(quick);

        // Generate and benchmark data sets
        for (int n = 100; n <= 1200; n += 100) {
            for (int i = 0; i < 40; i++) {
                int[] data = generateRandomData(n);

                heap.sort(data.clone()); // Clone to ensure the same data for both algorithms
                quick.sort(data.clone());
            }
            writeResultsToFile("HeapResults.txt", heap, n);
            writeResultsToFile("QuickResults.txt", quick, n);
        }
    }

    private static void runWarmUp(AbstractSort algorithm) throws UnsortedException {
        for (int i = 0; i < 100; i++) {
            int[] data = generateRandomData(100);  // Adjust the size for warm-up
            algorithm.sort(data);
        }
    }

    private static int[] generateRandomData(int size) {
        int[] data = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(1000); // Adjust as needed
        }
        return data;
    }

    private static void writeResultsToFile(String filename, AbstractSort algorithm, int dataSetSize) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename), true))) {
            writer.write(dataSetSize + " ");
            for (int i = 0; i < 40; i++) {
                writer.write(algorithm.getCount() + " " + algorithm.getTime() + " ");
            }
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing results to file: " + filename);
        }
    }
}