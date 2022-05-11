import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class QuickSelectMedian {

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
    public static int medianPivot(ArrayList<Integer> array, int first, int last) { //Find the median pivot for Quick Sort with median of three

        //Sort array for first, last and middle elements and use the second largest element as pivot

        int mid = (last) / 2;

        int[] sortingArr = {array.get(first), array.get(mid), array.get(last)};
        Arrays.sort(sortingArr);

        //System.out.println("\tMiddle of Arr at Index= " + mid + " : " + array[mid]);
        int middleValue = sortingArr[1];

        //System.out.println("\t"+Arrays.toString(sortingArr));
        //Swap with the last for pivot
        int temp = array.get(last);
        array.set(last, middleValue);
        if (middleValue == array.get(first)) {
            array.set(first, temp);
        } else if (middleValue == array.get(mid)) {
            array.set(mid, temp);
        }
        return partition(array, first, last);
    }
    public static void medianQuickSelect(ArrayList<Integer> array, int low, int high, int k) { //Method for Quick Sort with median-of-three pivot selection
        if (low >= high)
            return;

        if (low < high) {

            int pivot = medianPivot(array, low, high);
            quickSelect(array, low, high, k);
        }
    }
    static int quickSelect(ArrayList<Integer> array, int low, int high, int k){
        if (k > 0 && k <= high - low + 1)
        {

            int pos = partition(array, low, high);

            if (pos-low == k-1)
                return array.get(pos);

            if (pos-low > k-1)
                return quickSelect(array, low, pos-1, k);

            return quickSelect(array, pos+1, high, k-pos+low-1);
        }

        // If k is more than number of elements in array
        return Integer.MAX_VALUE;
    }

    static void swap(ArrayList<Integer> array, int i, int j) {
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);

    }
    public static int partition(ArrayList<Integer> array, int low,
                                int high)
    {

        int pivot = array.get(high), pivotloc = low;
        for (int i = low; i <= high; i++) {
            // inserting elements of less value
            // to the left of the pivot location
            if (array.get(i) < pivot) {
                swap(array, pivotloc, i);
                pivotloc++;
            }
        }
        swap(array,pivotloc, high);

        // swapping pivot to the final pivot location

        return pivotloc;
    }
    static int medianThree(ArrayList<Integer> array, int l, int h) {
        int pivot;
        if (((array.get(l) > array.get(h)) && (array.get(l) < array.get(h/2))) || ((array.get(l) < array.get(h)) &&
                array.get(l) > array.get(h/2)))
            pivot = array.get(l);
        else if (((array.get(h) > array.get(l)) && (array.get(h) < array.get(h/2))) || ((array.get(h) < array.get(l)) &&
                array.get(h) > array.get(h/2)))
            pivot = array.get(h);
        else
            pivot = array.get(h/2);
        swap(array, pivot + l, h);
        return partition(array, l, h);
    }

    public static void main(String[] args) {
        ArrayList<Integer> array = readInput("Inputs/size10k.txt");
        try {
            if (array != null) {
                int k = array.size();
                long startTime = System.currentTimeMillis();
                medianQuickSelect(array, 0, array.size() - 1, k);
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
