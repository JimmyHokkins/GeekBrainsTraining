package lesson7;

public class Wall implements Obstacle {

    private double height;

    public Wall(double height) {
        this.height = Math.round(height * 10) / 10.0;
    }

    @Override
    public boolean overcome(Competitor competitor) {
        return competitor.jump() >= height;
    }

    @Override
    public String getInfo() {
        return "Wall - " + height + 'm';
    }

}
