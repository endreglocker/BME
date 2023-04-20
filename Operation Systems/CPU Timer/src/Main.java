import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString;
        LinkedList<Task> taskList = new LinkedList<>();

        while (scanner.hasNextLine()) {
            try {
                inputString = scanner.nextLine();
                taskList.add(separateData(inputString));
            } catch (Exception e) {
                reorderingTask(taskList);
            }
        }

        runCalculations(taskList);

    }

    static void runCalculations(LinkedList<Task> taskList) {
        int time = 0;
        int RR_constraint = 2;
        Task previousTask = null;

        LinkedList<Task> originalData = new LinkedList<>(taskList);
        LinkedList<Task> afterQueue = new LinkedList<>();
        LinkedList<Task> RoundRobinList = new LinkedList<>();
        LinkedList<Task> SRTFList = new LinkedList<>();

        time += taskList.get(0).getArrivalTime();

        reorderingTask(taskList);


        while (RoundRobinList.size() > 0 || SRTFList.size() > 0 || taskList.size() > 0) {
            if (taskList.size() > 0) {
                sortingTasks(taskList, RoundRobinList, SRTFList, time);
            }

            if (RoundRobinList.size() > 0) {
                RR rr = new RR(RoundRobinList, afterQueue, previousTask, RR_constraint, time);
                rr.runRR();
                RoundRobinList = rr.getRoundRobinList();
                afterQueue = rr.getAfterQueue();
                previousTask = rr.getPreviousTask();
                RR_constraint = rr.getRR_constraint();
                time = rr.getTime();

            } else if (SRTFList.size() > 0) {
                SRTF srtf = new SRTF(SRTFList, afterQueue, previousTask, time);
                srtf.runSRTF();
                SRTFList = srtf.getSRTFList();
                afterQueue = srtf.getAfterQueue();
                previousTask = srtf.getPreviousTask();
                time = srtf.getTime();

            }

            time++;
            increaseTime(RoundRobinList, SRTFList);
        }


        print(originalData, afterQueue);
    }

    static void increaseTime(LinkedList<Task> RoundRobinList, LinkedList<Task> SRTFList) {
        for (Task task : SRTFList) {
            task.increaseInnerCounter();
        }
        for (Task task : RoundRobinList) {
            task.increaseInnerCounter();
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
        taskList.sort((o1, o2) -> o1.getArrivalTime() - o2.getArrivalTime());

        /*
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

    static void sortingTasks(LinkedList<Task> taskList, LinkedList<Task> RoundRobinList, LinkedList<Task> SRTFList, int time) {
        while (taskList.size() > 0 && taskList.get(0).getArrivalTime() == time) {
            if (taskList.get(0).getPriority() == 0) {
                SRTFList.add(taskList.pop());
                SRTFList.sort((o1, o2) -> o1.getBurstTime() - o2.getBurstTime());
            } else if (taskList.get(0).getPriority() == 1) {
                RoundRobinList.add(taskList.pop());
            }
        }
    }


    static void print(LinkedList<Task> originalData, LinkedList<Task> afterQueue) {
        System.out.println("");

        for (int i = 0; i < originalData.size() - 1; i++) {
            for (Task task : afterQueue) {
                if (originalData.get(i).getLetter() == task.getLetter()) {
                    System.out.print(task.getLetter() + ":" + task.getInnerCounter() + ",");
                }
            }
        }

        for (Task task : afterQueue) {
            if (originalData.get(originalData.size() - 1).getLetter() == task.getLetter()) {
                System.out.print(task.getLetter() + ":" + task.getInnerCounter());
            }
        }
    }

}

