package lesson7;

public class Robot implements Competitor {

    private String name;
    private double maxWallHeight;
    private int maxTrackLength;

    public Robot(String name, double maxWallHeight, int maxTrackLength) {
        this.name = name;
        this.maxWallHeight = maxWallHeight;
        this.maxTrackLength = maxTrackLength;
    }

    @Override
    public double jump() {
        return maxWallHeight;
    }

    @Override
    public int run() {
        return maxTrackLength;
    }

    @Override
    public String getInfo() {
        return "Robot " + name + " (" + maxTrackLength + "m, " + maxWallHeight + "m)";
    }
}
