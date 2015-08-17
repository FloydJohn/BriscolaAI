package AI;

public class ElapsedTimer {
    long oldTime;
    public ElapsedTimer() {
        oldTime = System.currentTimeMillis();
    }
    public long elapsed() {
        return System.currentTimeMillis() - oldTime;
    }
}


