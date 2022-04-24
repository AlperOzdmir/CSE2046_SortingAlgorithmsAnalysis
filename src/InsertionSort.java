import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InsertionSort {

    public static void insertionSort(ArrayList<Integer> array) {
        int n = array.size();
        for (int j = 1; j < n; j++) {
            int key = array.get(j);
            int i = j-1;
            while ( (i > -1) && ( array.get(i) > key ) ) {
                array.set(i+1, array.get(i));
                i--;
            }
            array.set(i+1, key);
        }
    }

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

    public static void main(String[] args) {

        ArrayList<Integer> array = readInput("Inputs/randomDistribution.txt");
        System.out.print("Enter element index k: ");
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        try {
            if (array != null) {
                insertionSort(array);
                System.out.println(array.get(k-1));
            } else {
                System.out.println("Input file empty.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds.");
        }
    }
}
