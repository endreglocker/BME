import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString;
        List<RawData> rawDataList = new ArrayList<>();


        /**
         * Input
         */
        while (scanner.hasNextLine()) {
            try {
                inputString = scanner.nextLine();
                rawDataList.add(separateData(inputString));
            } catch (Exception e) {
            }

        }

        /**
         * Reordering
         */
        reordering(rawDataList);


        /**
         * Calculations
         */

        runCalculations(rawDataList);


        /**
         * Final print
         */
        for (RawData rawData : rawDataList) {
            System.out.println(rawData.toString());
        }

    }

    static void runCalculations(List<RawData> rawDataList) {
        int sum = 1;
        int time = 0;
        while (sum != 0) {
            /**
             * run algoritms
             */


            /**
             * reevaluate sum
             */
            sum = 0;
            for (RawData rawData : rawDataList) {
                sum += rawData.getCPU_burst();
            }

            time++;
        }
    }

    static RawData separateData(String inputString) {
        String[] separatedData = inputString.split(",");
        char letter = separatedData[0].charAt(0);
        int priority = Integer.parseInt(separatedData[1]);
        int startTime = Integer.parseInt(separatedData[2]);
        int CPU_burst = Integer.parseInt(separatedData[3]);
        return new RawData(letter, priority, startTime, CPU_burst);
    }

    static void reordering(List<RawData> rawDataList) {
        /**
         * Sort by start time
         */
        rawDataList.sort((o1, o2) -> o1.getStartTime() - o2.getStartTime());

        /**
         * Sort by priority
         * If start time is the same, the higher priority will be first
         */
        for (int i = 0; i < rawDataList.size() - 1; i++) {
            for (int j = i + 1; j < rawDataList.size(); j++) {
                if (rawDataList.get(i).getStartTime() == rawDataList.get(j).getStartTime()) {
                    if (rawDataList.get(i).getPriority() < rawDataList.get(j).getPriority()) {
                        RawData temp = rawDataList.get(i);
                        rawDataList.set(i, rawDataList.get(j));
                        rawDataList.set(j, temp);
                    }
                }
            }
        }
    }
}

