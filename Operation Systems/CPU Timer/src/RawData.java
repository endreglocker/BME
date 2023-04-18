public class RawData {
    char letter;
    int priority;
    int startTime;
    int CPU_burst;

    /**
     * WatitingTime = TurnAroundTime - BurstTime = CompletionTime - ArrivalTime - BurstTime
     * EffectiveTime = ArrivalTime + BurstTime
     */
    int completionTime;
    int waitTime;
    int effectiveTime;


    public RawData(char letter, int priority, int startTime, int CPU_burst) {
        this.letter = letter;
        this.priority = priority;
        this.startTime = startTime;
        this.CPU_burst = CPU_burst;
        completionTime = 0;
        effectiveTime = startTime + CPU_burst;
    }

    public char getLetter() {
        return letter;
    }

    public int getPriority() {
        return priority;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getCPU_burst() {
        return CPU_burst;
    }

    public String toString() {
        return "Letter: " + letter + ", Priority: " + priority + ", Start Time: " + startTime + ", CPU Burst: " + CPU_burst + ", Wait Time: " + waitTime + "\n";
    }
}
