import java.util.List;

/**
 * Priority = 1
 */
public class RR {
    private List<Task> taskList;
    int sum;

    public RR(List<Task> taskList) {
        this.taskList = taskList;
        sum = 0;

    }

    public List<Task> RR_RR_comparison() {

        return taskList;
    }

    public void runRR() {
        Task previous = null;
        Task current;
        int time = 0;

        while (true) {
            sum = 0;
            current = taskList.get(0);
            if (current != previous || taskList.size() == 1) {
                System.out.print(current.getLetter());
                if (current.getBurstTime() >= 2) {
                    current.setBurstTime(current.getBurstTime() - 2);
                } else if (current.getBurstTime() == 1) {
                    current.setBurstTime(current.getBurstTime() - 1);
                }
            }

            if (current.getBurstTime() > 0) {
                taskList.remove(0);
                taskList.add(current);
            }
            if (current.getBurstTime() <= 0) taskList.remove(0);

            previous = current;
            sum_of_burst();
            if (sum == 0 || taskList.size() == 0) break;
            time++;
        }
    }

    void sum_of_burst() {
        if (taskList.size() != 0) {
            for (Task data : taskList) {
                sum += data.getBurstTime();
            }
        } else {
            sum = 0;
        }

    }

}
