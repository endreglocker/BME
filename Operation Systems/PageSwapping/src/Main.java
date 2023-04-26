import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String inputString;
        LinkedList<Integer> inputList = new LinkedList<>();
        LinkedList<RawData> taskList = new LinkedList<>();


        fillTaskList(taskList);
        try {
            inputString = scanner.nextLine();
            sortInput(inputString, inputList);
        } catch (Exception e) {

        }

        /// A dirty bit bezavar! (:
        LinkedList<Integer> absList = new LinkedList<>();
        for (int temp : inputList) {
            if (temp < 0) {
                absList.add(-temp);
            } else {
                absList.add(temp);
            }
        }

        runTask(taskList, absList);
    }

    static void fillTaskList(LinkedList<RawData> taskList) {

        taskList.add(new RawData('A', -1));
        taskList.add(new RawData('B', -1));
        taskList.add(new RawData('C', -1));
    }

    static void sortInput(String inputString, LinkedList<Integer> inputList) {
        String[] inputArray = inputString.split(",");
        for (String s : inputArray) {
            inputList.add(Integer.parseInt(s));
        }
    }

    static void runTask(LinkedList<RawData> taskList, LinkedList<Integer> inputList) {
        int numberOfMemoryAccess = inputList.size();
        int mistakeCounter = 0;
        boolean flagged = false;
        //boolean previuslyFlagged = false;

        while (inputList.size() > 0) {
            flagged = prefetched(taskList, inputList);

            if (flagged) mistakeCounter++;

            if (!flagged) {
                for (int i = 0; i < taskList.size(); i++) {
                    if (!taskList.get(0).pageLock) {
                        SecondChance(taskList, inputList);
                    } else {
                        System.out.print("*");
                        inputList.pop();

                    }
                    break;
                }
            }


            for (int i = 0; i < taskList.size(); i++) {
                taskList.get(i).increaseTime();
            }

            //previuslyFlagged = flagged;
            flagged = false;
        }

        System.out.println("\n" + (numberOfMemoryAccess - mistakeCounter));
    }

    static boolean prefetched(LinkedList<RawData> taskList, LinkedList<Integer> inputList) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).value == inputList.get(0)) {
                System.out.print("-");
                inputList.pop();

                //taskList.get(i).pageLock = false;

                taskList.get(i).haveSecondChance = true;

                /*
                RawData temp = taskList.remove(i);
                taskList.add(temp);
                */

                return true;

            }
        }
        return false;
    }

    static void SecondChance(LinkedList<RawData> taskList, LinkedList<Integer> inputList) {
        if (!taskList.get(0).haveSecondChance) {
            System.out.print(taskList.get(0).frame);

            /// set time & lock & value
            taskList.get(0).setValue(inputList.pop());

            RawData temp = taskList.pop();
            taskList.add(temp);

        } else {
            taskList.get(0).haveSecondChance = false;
            RawData temp = taskList.pop();
            taskList.add(temp);
        }
    }
}

