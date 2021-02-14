package lesson7;

public class RunningTrack implements Obstacle {

    private int length;

    public RunningTrack(int length) {
        this.length = length;
    }

    @Override
    public boolean overcome(Competitor competitor) {
        return competitor.run() >= length;
    }

    @Override
    public String getInfo() {
        return "RunningTrack - " + length + 'm';
    }
}
