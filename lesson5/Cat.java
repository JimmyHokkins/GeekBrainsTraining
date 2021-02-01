package lesson5;

public class Cat extends Animal {

    public Cat(String name) {
        super(name, 200, 0);
    }

    @Override
    public void run(int distance) {
        if (distance > 0 && distance <= limitRun) System.out.println(name + " ran " + distance + " m.");
        else System.out.println("I don't run that distance.");
    }

}
