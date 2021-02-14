package lesson7;

public class Cat implements Competitor {

    private String name;
    private double maxWallHeight;
    private int maxTrackLength;

    public Cat(String name, double maxWallHeight, int maxTrackLength) {
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
        return "Cat " + name + " (" + maxTrackLength + "m, " + maxWallHeight + "m)";
    }
}
