import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SelectionSort {

    //Partial Selection Sort
    private static void sort(ArrayList<Integer> array, int k) {
        for (int i = 0; i < k; i++) {
            int min = i;
            for (int j = i + 1; j < array.size(); j++) {
                if (array.get(j) < array.get(min)) {
                    min = j;
                }
            }
            int temp = array.get(min);
            array.set(min, array.get(i));
            array.set(i, temp);
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
                sort(array, k);
                System.out.println(array.get(k-1));
            } else {
                System.out.println("Input file empty.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds.");
        }

        for(int a = 0; a < array.size(); a++){
            System.out.println(array.get(a));
        }
    }
}
