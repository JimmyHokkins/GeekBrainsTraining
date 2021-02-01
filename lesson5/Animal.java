package lesson5;

public abstract class Animal {
    private static int countOfAnimal = 0;
    protected String name;
    protected int limitRun;
    protected int limitSwim;

    public Animal(String name, int limitRun, int limitSwim) {
        this.name = name;
        this.limitRun = limitRun;
        this.limitSwim = limitSwim;
        countOfAnimal++;
    }

    public abstract void run(int distance);
    public void swim(int distance) {
        System.out.println("I can't swim.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLimitRun() {
        return limitRun;
    }

    public void setLimitRun(int limitRun) {
        this.limitRun = limitRun;
    }

    public int getLimitSwim() {
        return limitSwim;
    }

    public void setLimitSwim(int limitSwim) {
        this.limitSwim = limitSwim;
    }

    public static int getCountOfAnimal() {
        return countOfAnimal;
    }
}
