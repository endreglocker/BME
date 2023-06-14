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
            System.out.print("");
        }

        /// Removing dirty bit! (:
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
        //int preSize = inputList.size();

        int mistakeCounter = 0;
        int pageLockIndex;
        boolean flagged;

        while (inputList.size() > 0) {
            flagged = prefetched(taskList, inputList);
            pageLockIndex = hasLockIndex(taskList);

            if (!flagged) {
                if (pageLockIndex == -1) {
                    System.out.print("*");
                    inputList.pop();
                } else {
                    for (int i = 0; i < 3; i++) {
                        if (taskList.get(i).pageLock) {
                            //i++;
                        } else {
                            if (taskList.get(i).haveSecondChance) {
                                RawData temp = taskList.get(i);
                                temp.haveSecondChance = false;
                                taskList.remove(i);
                                taskList.add(temp);
                                i--;
                            } else {
                                System.out.print(taskList.get(i).frame);

                                RawData temp = taskList.get(i);
                                taskList.remove(i);
                                temp.setValue(inputList.pop());
                                taskList.add(temp);
                                break;
                            }
                        }

                    }
                }
            } else {
                mistakeCounter++;
            }


            for (RawData rawData : taskList) {
                rawData.increaseTime();
            }
        }

        System.out.println("\n" + (numberOfMemoryAccess - mistakeCounter));
    }

    static boolean prefetched(LinkedList<RawData> taskList, LinkedList<Integer> inputList) {
        for (RawData rawData : taskList) {
            if (rawData.value == inputList.get(0)) {
                System.out.print("-");
                rawData.haveSecondChance = true;
                inputList.pop();
                return true;

            }
        }
        return false;
    }

    static int hasLockIndex(LinkedList<RawData> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            if (!taskList.get(i).pageLock) return i;
        }

        return -1;
    }
}

