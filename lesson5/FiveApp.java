package lesson5;

public class FiveApp {
    public static void main(String[] args) {

        Cat cat = new Cat("Tom");
        Dog dog = new Dog("Spot");

        cat.run(100);
        cat.swim(50);

        dog.run(270);
        dog.swim(5);

        System.out.println(Animal.getCountOfAnimal());
    }
}
