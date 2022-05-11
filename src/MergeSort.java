import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MergeSort {
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

    public static void divideArray(ArrayList<Integer> array, int indexStart, int indexEnd) {

        if (indexStart < indexEnd && (indexEnd - indexStart) >= 1) {
            int middleElement = (indexEnd + indexStart) / 2;

            divideArray(array, indexStart, middleElement);
            divideArray(array,middleElement + 1, indexEnd);

            mergeArray(array, indexStart, middleElement, indexEnd);
        }
    }

    public static void mergeArray(ArrayList<Integer> array, int indexStart, int indexMiddle, int indexEnd) {

        ArrayList<Integer> tempArray = new ArrayList<>();

        int tempLeftIndex = indexStart;
        int tempRightIndex = indexMiddle + 1;

        while (tempLeftIndex <= indexMiddle && tempRightIndex <= indexEnd) {

            if (array.get(tempLeftIndex) <= array.get(tempRightIndex)) {
                tempArray.add(array.get(tempLeftIndex));
                tempLeftIndex++;
            } else {
                tempArray.add(array.get(tempRightIndex));
                tempRightIndex++;
            }
        }

        while (tempLeftIndex <= indexMiddle) {
            tempArray.add(array.get(tempLeftIndex));
            tempLeftIndex++;
        }

        while (tempRightIndex <= indexEnd) {
            tempArray.add(array.get(tempRightIndex));
            tempRightIndex++;
        }

        for (int i = 0; i < tempArray.size(); indexStart++)
            array.set(indexStart, tempArray.get(i++));
    }

    public static void main(String[] args) {

        ArrayList<Integer> array = readInput("Inputs/size10k.txt");
        try {
            if (array != null) {
                int k = array.size();
                long startTime = System.currentTimeMillis();
                divideArray(array, 0, array.size() - 1);
                System.out.println(array.get(k - 1));
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


