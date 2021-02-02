package lesson6;

public class SixApp {
    public static void main(String[] args) {

        Cat[] cats = new Cat[3];
        cats[0] = new Cat("Barsik", 400);
        cats[1] = new Cat("Murzik", 300);
        cats[2] = new Cat("Matroskin", 150);

        Plate plate = new Plate(750);
        System.out.println("Now in the plate is " + plate.getFood() + " ton of the food.");
        System.out.println("The cats begin to eat...");
        cats[0].eat(plate);
        cats[1].eat(plate);
        cats[2].eat(plate);
        System.out.println("The cats have finished eating.");
        System.out.println("Now in the plate is " + plate.getFood() + " ton of the food.");

        System.out.println("The satiety of cats:");
        for (Cat cat : cats) {
            if (cat.isSatiety()) System.out.println("The cat " + cat.getName() + " is full.");
            else System.out.println("The cat " + cat.getName() + " is hungry.");
        }

    }
}
