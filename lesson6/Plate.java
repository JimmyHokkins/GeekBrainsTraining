package lesson6;

public class Plate {
    private int food;
    public Plate(int food) {
        this.food = food;
    }
    public void info() {
        System.out.println("plate: " + food);
    }

    public boolean decreaseFood(int n) {
        if (n <= food) {
            food -= n;
            return true;
        }
        return false;
    }

    public void addFood(int addition) {
        food += addition;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }
}
