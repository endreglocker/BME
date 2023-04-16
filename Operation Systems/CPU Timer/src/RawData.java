public class RawData {
    char letter;
    int priority;
    int startTime;
    int CPU_burst;

    public RawData(char letter, int priority, int startTime, int CPU_burst) {
        this.letter = letter;
        this.priority = priority;
        this.startTime = startTime;
        this.CPU_burst = CPU_burst;
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
}
