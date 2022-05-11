import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuickSort {

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

    public static int partition(ArrayList<Integer> array, int start, int end) {
        int pivot = array.get(start);

        while(start<=end) {
            while(array.get(start) < pivot)
                start++;

            while(array.get(end) > pivot)
                end--;

            if(start<=end) {
                int tmp = array.get(start);
                array.set(start, array.get(end));
                array.set(end, tmp);
                start++;
                end--;
            }
        }
        return start;
    }
    public static void quickSort(ArrayList<Integer> array, int start, int end) {
        int index = partition(array, start, end);

        if(start < index-1) {
            quickSort(array, start, index-1);
        }
        if(end > index) {
            quickSort(array, index, end);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> array = readInput("Inputs/size10k.txt");
        try {
            if (array != null) {
                int k = array.size();
                long startTime = System.currentTimeMillis();
                quickSort(array, 0, array.size() - 1);
                System.out.println(array.get(k - 1));
                long endTime = System.currentTimeMillis();
                System.out.println("Execution time: " + (endTime - startTime) + "ms");
            } else {
                System.out.println("Input file empty.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds.");
        }
    }
}
