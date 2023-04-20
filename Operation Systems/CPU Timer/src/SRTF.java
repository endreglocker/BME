import java.util.LinkedList;

public class SRTF {
    private LinkedList<Task> SRTFList;
    private LinkedList<Task> afterQueue;
    private Task previousTask;
    private int time;

    public SRTF(LinkedList<Task> SRTFList, LinkedList<Task> afterQueue, Task previousTask, int time) {
        this.SRTFList = SRTFList;
        this.afterQueue = afterQueue;
        this.previousTask = previousTask;
        this.time = time;
    }

    public void runSRTF() {
        Task currentSRTF = SRTFList.get(0);
        if (previousTask != currentSRTF) System.out.print(currentSRTF.getLetter());
        currentSRTF.decreaseInnerCounter();
        currentSRTF.setBurstTime(currentSRTF.getBurstTime() - 1);
        previousTask = currentSRTF;

        if (currentSRTF.getBurstTime() == 0) {
            currentSRTF = SRTFList.pop();

            currentSRTF.setCompletionTime(time + 1);
            afterQueue.add(currentSRTF);
        }
    }

    public int getTime() {
        return time;
    }

    public Task getPreviousTask() {
        return previousTask;
    }

    public LinkedList<Task> getAfterQueue() {
        return afterQueue;
    }

    public LinkedList<Task> getSRTFList() {
        return SRTFList;
    }
}
