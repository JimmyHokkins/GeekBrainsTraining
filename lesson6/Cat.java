package lesson6;

public class Cat {
    private final String name;
    private int appetite;
    private boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }
    public void eat(Plate p) {
        if (p.getFood() < appetite) {
            System.out.println("The cat is not happy. He doesn't have enough to eat.");
        }
        else {
            p.decreaseFood(appetite);
            satiety = true;
        }
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public boolean isSatiety() {
        return satiety;
    }

    public void setSatiety(boolean satiety) {
        this.satiety = satiety;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
    }
}
