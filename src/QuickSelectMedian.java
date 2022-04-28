import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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

    // Write this method. This time use median-of-three pivot selection.

    /*


    public static void quickSelect(ArrayList<Integer> array){

    }


    */


    public static void main(String[] args) {
        ArrayList<Integer> array = readInput("Inputs/randomDistribution.txt");
        System.out.print("Enter element index k: ");
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        try {
            if (array != null) {
                // Call the sort method here
                System.out.println(array.get(k-1));
            } else {
                System.out.println("Input file empty.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds.");
        }
    }
}
