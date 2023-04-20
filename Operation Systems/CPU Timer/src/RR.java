import java.util.LinkedList;

public class RR {
    private LinkedList<Task> RoundRobinList;
    private LinkedList<Task> afterQueue;
    private Task previousTask;

    private int RR_constraint;
    private int time;

    private static boolean reloaded = false;

    public RR(LinkedList<Task> RoundRobinList, LinkedList<Task> afterQueue, Task previousTask, int RR_constraint, int time) {
        this.RoundRobinList = RoundRobinList;
        this.afterQueue = afterQueue;
        this.previousTask = previousTask;
        this.RR_constraint = RR_constraint;
        this.time = time;
    }

    public void runRR() {
        Task currentRR;

        if (reloaded) {
            currentRR = RoundRobinList.pop();
            RoundRobinList.add(currentRR);
            RR_constraint = 2;
            reloaded = false;
        }

        currentRR = RoundRobinList.get(0);
        if (previousTask != currentRR && currentRR.getBurstTime() > 0) System.out.print(currentRR.getLetter());

        currentRR.setBurstTime(currentRR.getBurstTime() - 1);
        previousTask = currentRR;
        RR_constraint--;

        if (currentRR.getBurstTime() <= 0) {
            currentRR = RoundRobinList.pop();
            currentRR.setCompletionTime(time + 1);
            afterQueue.add(currentRR);
            RR_constraint = 2;
        } else if (currentRR.getBurstTime() > 0 && RR_constraint == 0) {
            currentRR = RoundRobinList.pop();
            RoundRobinList.add(currentRR);
            RR_constraint = 2;
            if (RoundRobinList.size() == 1) {
                reloaded = true;
            }
        }


    }

    public LinkedList<Task> getRoundRobinList() {
        return RoundRobinList;
    }

    public LinkedList<Task> getAfterQueue() {
        return afterQueue;
    }

    public Task getPreviousTask() {
        return previousTask;
    }

    public int getTime() {
        return time;
    }

    public int getRR_constraint() {
        return RR_constraint;
    }
}
