import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HeapSort {

    private static ArrayList<Integer> readInput(String fileName) {
        try {
            File myObj = new File(fileName);
            Scanner input = new Scanner(myObj);
            ArrayList<Integer> array = new ArrayList<>();
            while (input.hasNextLine()) {
                String data = input.nextLine();
                array.add(Integer.parseInt(data));
            }
            input.close();
            return array;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }


    public static void sort(ArrayList<Integer> array)
    {
        int n = array.size();

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i);

        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end
            int temp = array.get(0);
            array.set(0, array.get(i));
            array.set(i, temp);

            // call max heapify on the reduced heap
            heapify(array, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    public static void heapify(ArrayList<Integer> array, int n, int i)
    {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2

        // If left child is larger than root
        if (l < n && array.get(l) > array.get(largest))
            largest = l;

        // If right child is larger than largest so far
        if (r < n && array.get(r) > array.get(largest))
            largest = r;

        // If largest is not root
        if (largest != i)
        {
            int swap = array.get(i);
            array.set(i, array.get(largest));
            array.set(largest, swap);
            // Recursively heapify the affected sub-tree
            heapify(array, n, largest);
        }
    }


    public static void main(String[] args) {
        ArrayList<Integer> array = readInput("Inputs/size10k.txt");
        try {
            if (array != null) {
                int k = array.size() / 2;
                long startTime = System.currentTimeMillis();
                sort(array);
                System.out.println(array.get(k-1));
                long endTime = System.currentTimeMillis();
                System.out.println("Execution time: " + (endTime - startTime) + " ms");
            } else {
                System.out.println("Input file empty.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds.");
        }
    }
}
