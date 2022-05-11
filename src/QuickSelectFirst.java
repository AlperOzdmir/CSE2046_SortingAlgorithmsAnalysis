import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuickSelectFirst {

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

    // Write this method. Choose the first element as the pivot.
    public static int partition(ArrayList<Integer> array, int low,
                                int high)
    {
        int pivot = array.get(low), pivotloc = high;
        for (int i = high; i >= low; i--) {
            // inserting elements of less value
            // to the left of the pivot location
            if (array.get(i) > pivot) {
                int temp = array.get(i);
                array.set(i, array.get(pivotloc));
                array.set(pivotloc, temp);
                pivotloc--;
            }
        }

        // swapping pivot to the final pivot location
        int temp = array.get(low);
        array.set(low, array.get(pivotloc));
        array.set(pivotloc, temp);

        return pivotloc;
    }



    static int quickSelect(ArrayList<Integer> array, int low, int high, int k){
         int partPos = partition(array, low, high);
        // if partition value is equal to the kth position,
        // return value at k.
        if (partPos == k - 1)
            return array.get(partPos);

        // if partition value is less than kth position,
        // search right side of the array.
        else if (partPos < k - 1)
            return quickSelect(array, partPos + 1, high, k);

        // if partition value is more than kth position,
        // search left side of the array.
        else
            return quickSelect(array, low, partPos - 1, k);
    }





    public static void main(String[] args) {
        ArrayList<Integer> array = readInput("Inputs/size10k.txt");
        ArrayList<Integer> arrayCopy = array;
        try {
            if (array != null) {
                int k = array.size();
                long startTime = System.currentTimeMillis();
                quickSelect(arrayCopy, 0, array.size()-1, k);
                System.out.println(array.get(k-1));
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
