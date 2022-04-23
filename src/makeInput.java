import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class makeInput {
    public static void main(String[] args) {
        try {
            FileWriter myWriter = new FileWriter("Inputs/smallInput.txt");
            int min = 1;
            int max = 100;
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            ArrayList<Integer> list = new ArrayList<>();
            list.add(randomNum);
            myWriter.write(randomNum + "\n");
            boolean isDuplicate = false;
            while (list.size() < 100) {
                randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
                for (Integer integer : list) {
                    if (integer == randomNum) {
                        isDuplicate = true;
                        break;
                    }
                }
                if (!isDuplicate) {
                    list.add(randomNum);
                    myWriter.write(randomNum + "\n");
                }
                isDuplicate = false;
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
