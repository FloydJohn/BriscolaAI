package AI;

public class Stats {
    private int won;
    private int tied;
    private int lost;
    private long elapsedMs;
    private int testNumber;
    private int points = 0;

    public Stats(int testNumber){
        this.testNumber = testNumber;
    }

    public Stats(int won, int tied, int lost) {
        this.won = won;
        this.tied = tied;
        this.lost = lost;
    }

    public int getWon() {
        return won;
    }

    public void incWon() {
        won++;
    }

    public int getTied() {
        return tied;
    }

    public void incTied() {
        tied++;
    }

    public int getLost() {
        return lost;
    }

    public void incLost() {
        lost++;
    }

    public long getElapsedMs() {
        return elapsedMs;
    }

    public void setElapsedMs(ElapsedTimer timer) {
        elapsedMs = timer.elapsed();
    }

    @Override
    public String toString() {
        return String.format("%d points: %d won\t%d tie\t%d lost\t%.1f \t[%d ms]", points, won, tied, lost, getWonPercentage(), elapsedMs);
    }

    public float getWonPercentage() {
        return (float) won / testNumber * 100;
    }

    public boolean betterThan(Stats record) {
        return points > record.points;
    }

    public void addPoints(int points) {
        if (points > 60) incWon();
        else if (points == 60) incTied();
        else incLost();
        this.points += points;
    }

    public int getPoints() {
        return points;
    }
}
