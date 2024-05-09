package Project1;

// Java implementation of QuickSort
//This implementation of the quick sort algorithm was made by GeeksForGeeks.org
//modified by Jade Pearl to include the AbstractSort class, added the sort method
import java.io.*;

public class Quicksort extends AbstractSort {

    @Override
    public void sort(int[] array) throws UnsortedException {
        this.array = array.clone(); // Use clone to ensure the original array is not modified
        startSort();
        // Implement Quick Sort logic here
        quickSort(0, array.length - 1);
        endSort();
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int partitionIndex = partition(low, high);

            quickSort(low, partitionIndex - 1);
            quickSort(partitionIndex + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        incrementCount(); // Increment the count for the swap operation
    }

    // To print sorted array
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}