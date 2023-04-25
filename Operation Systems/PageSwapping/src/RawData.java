public class RawData {
    boolean isUsed = false;
    boolean pageLock = false;
    int ID;
    int value;
    int time = -1;

    public RawData(int ID, int value) {
        this.ID = 0;
        this.value = 0;
    }

    public void setPageLock(boolean pageLock) {
        this.pageLock = pageLock;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getTime() {
        return time;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public boolean isPageLock() {
        return pageLock;
    }

    public int getValue() {
        return value;
    }

    public int getID() {
        return ID;
    }

    public boolean isUsed() {
        return isUsed;
    }
}
