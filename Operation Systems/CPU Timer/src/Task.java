public class Task {
    private char letter;
    private int priority;
    private int arrivalTime;
    private int burstTime;

    /**
     * WatitingTime = TurnAroundTime - BurstTime = CompletionTime - ArrivalTime - BurstTime
     * EffectiveTime = ArrivalTime + BurstTime
     */
    int completionTime;
    int effectiveTime;


    public Task(char letter, int priority, int startTime, int CPU_burst) {
        this.letter = letter;
        this.priority = priority;
        this.arrivalTime = startTime;
        this.burstTime = CPU_burst;
        completionTime = 0;
        effectiveTime = startTime + CPU_burst;
    }

    public char getLetter() {
        return letter;
    }

    public int getPriority() {
        return priority;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getWaitTime() {
        return completionTime - effectiveTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public String toString() {
        return "Letter: " + letter + ", Priority: " + priority + ", Start Time: " + arrivalTime + ", CPU Burst: " + burstTime + ", Wait Time: " + getWaitTime() + "\n";
    }
}
