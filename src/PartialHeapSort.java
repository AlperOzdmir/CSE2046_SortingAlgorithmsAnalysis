import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PartialHeapSort {

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

    static void heapify(ArrayList<Integer> array, int n, int i)
    {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left child
        int right = 2 * i + 2; // right child
        // If left child is larger than root
        if (left < n && array.get(left) > array.get(largest))
            largest = left;
        // If right child is larger than root
        if (right < n && array.get(right) > array.get(largest))
            largest = right;
        // If root is not largest
        if (largest != i) {
            // swap a[i] with a[largest]
            int temp = array.get(i);
            array.set(i, array.get(largest));
            array.set(largest, temp);

            heapify(array, n, largest);
        }
    }

    static void heapSort(ArrayList<Integer> array, int n)
    {
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            /* Move current root element to end*/
            // swap a[0] with a[i]
            int temp = array.get(0);
            array.set(0, array.get(i));

            array.set(i, temp);

            heapify(array, i, 0);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> array = readInput("Inputs/randomDistribution.txt");
        System.out.print("Enter element index k: ");
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        try {
            if (array != null) {
                //sort(array, k);
                heapSort(array, k);
                System.out.println(array.get(k-1));
            } else {
                System.out.println("Input file empty.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds.");
        }
    }
}
