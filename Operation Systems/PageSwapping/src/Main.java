import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String inputString;
        LinkedList<Integer> inputList = new LinkedList<>();
        LinkedList<RawData> taskList = new LinkedList<>();

        fillTaskList(taskList);

        inputString = scanner.nextLine();

        sortInput(inputString, inputList);

        for(int x : inputList) {
            System.out.println(x);
        }
    }

    static void fillTaskList(LinkedList<RawData> taskList) {
        for (int i = 0; i < 3; i++) {
            taskList.add(new RawData(i, -1));
        }
    }

    static void sortInput(String inputString, LinkedList<Integer> inputList) {
        String[] inputArray = inputString.split(",");
        for (String s : inputArray) {
            inputList.add(Integer.parseInt(s));
        }
    }
}

