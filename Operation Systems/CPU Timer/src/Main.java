import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString;
        LinkedList<Task> taskList = new LinkedList<>();


        /**
         * Input
         */
        while (scanner.hasNextLine()) {
            try {
                inputString = scanner.nextLine();
                taskList.add(separateData(inputString));
            } catch (Exception e) {
                reorderingTask(taskList);
            }

        }

        /**
         * Reordering
         */
        LinkedList<Task> RoudRobinList = new LinkedList<>();
        LinkedList<Task> SRTFList = new LinkedList<>();
        reorderingTask(taskList);
        sortingTasks(taskList, RoudRobinList, SRTFList);

        /**
         * Calculations
         */

        runCalculations(taskList);


        /**
         * Final print
         */
        /*
        for (RawData rawData : rawDataList) {
            System.out.print(rawData.toString() + "\n");
        }

        for (RawData rawData : rawDataList) {
            System.out.print(rawData.getLetter());
        }
        */
        //new RR(RoudRobinList).runRR();
    }

    static void runCalculations(LinkedList<Task> taskList) {
        int time = 0;
        int RR_constraint = 2;
        LinkedList<Task> originalData = taskList;
        LinkedList<Task> afterQueue = new LinkedList<>();

        Task previousTask = null;
        LinkedList<Task> RoundRobinList = new LinkedList<>();
        LinkedList<Task> SRTFList = new LinkedList<>();
        /**
         * Order by start time
         * Sorting by priority
         */
        reorderingTask(taskList);
        sortingTasks(taskList, RoundRobinList, SRTFList);

        while (RoundRobinList.size() > 0 || SRTFList.size() > 0) {
            if (RoundRobinList.size() > 0) {
                Task currentRR = RoundRobinList.get(0);
                if (previousTask != currentRR) System.out.print(currentRR.getLetter());

                previousTask = currentRR;
                RR_constraint--;
                currentRR.setBurstTime(currentRR.getBurstTime() - 1);

                if (currentRR.getBurstTime() == 0) {
                    currentRR = RoundRobinList.pop();
                    /**
                     * Attention!
                     * Probably time + 1 is wrong
                     */
                    currentRR.setCompletionTime(time + 1);
                    afterQueue.add(currentRR);
                } else if (currentRR.getBurstTime() > 0 && RR_constraint == 0) {
                    /**
                     * Adding the task to the end of the queue
                     */
                    currentRR = RoundRobinList.pop();
                    RoundRobinList.add(currentRR);
                    RR_constraint = 2;
                }

            }

            if (SRTFList.size() > 0) {
                Task currentSRTF = SRTFList.get(0);
                if (previousTask != currentSRTF) System.out.println(currentSRTF.getLetter());

                previousTask = currentSRTF;
                currentSRTF.setBurstTime(currentSRTF.getBurstTime() - 1);

                if (currentSRTF.getBurstTime() == 0) {
                    currentSRTF = SRTFList.pop();
                    /**
                     * Attention!
                     * Probably time + 1 is wrong
                     */
                    currentSRTF.setCompletionTime(time + 1);
                    afterQueue.add(currentSRTF);
                }
            }

            time++;
        }
    }

    static Task separateData(String inputString) {
        String[] separatedData = inputString.split(",");
        char letter = separatedData[0].charAt(0);
        int priority = Integer.parseInt(separatedData[1]);
        int startTime = Integer.parseInt(separatedData[2]);
        int CPU_burst = Integer.parseInt(separatedData[3]);
        return new Task(letter, priority, startTime, CPU_burst);
    }

    static void reorderingTask(LinkedList<Task> taskList) {
        /**
         * Sort by start time
         */
        taskList.sort((o1, o2) -> o1.getArrivalTime() - o2.getArrivalTime());

        /**
         * Sort by priority
         * If start time is the same, the higher priority will be first
         */
        /*
        for (int i = 0; i < rawDataList.size() - 1; i++) {
            for (int j = i + 1; j < rawDataList.size(); j++) {
                if (rawDataList.get(i).getArrivalTime() == rawDataList.get(j).getArrivalTime()) {
                    if (rawDataList.get(i).getPriority() < rawDataList.get(j).getPriority()) {
                        RawData temp = rawDataList.get(i);
                        rawDataList.set(i, rawDataList.get(j));
                        rawDataList.set(j, temp);
                    }
                }
            }
        }
        */
    }

    static void sortingTasks(LinkedList<Task> taskList, LinkedList<Task> RoundRobinList, LinkedList<Task> SRTFList) {
        for (Task task : taskList) {
            if (task.getPriority() == 0) {
                SRTFList.add(task);
                SRTFList.sort((o1, o2) -> o1.getBurstTime() - o2.getBurstTime());
            } else if (task.getPriority() == 1) {
                RoundRobinList.add(task);
            }
        }
    }
}

