public class RawData {
    boolean haveSecondChance = false;
    boolean pageLock = false;
    char frame;
    int value;
    int time = 0;

    public RawData(char ID, int value) {
        this.frame = ID;
        this.value = value;
    }

    public void setValue(int value) {
        time = 3;
        pageLock = true;
        //haveSecondChance = false;
        this.value = value;
    }

    public void increaseTime() {
        if (time == 0) {
            pageLock = false;
        } else {
            time--;
        }
    }

}
