package lesson5;

public class Dog extends Animal {

    public Dog(String name) {
        super(name, 500, 10);
    }

    @Override
    public void run(int distance) {
        if (distance > 0 && distance <= limitRun) System.out.println(name + " ran " + distance + " m.");
        else System.out.println("I don't run that distance.");
    }

    @Override
    public void swim(int distance) {
        if (distance > 0 && distance <= limitSwim) System.out.println(name + " swam " + distance + " m.");
        else System.out.println("I don't swim that distance.");
    }
}
