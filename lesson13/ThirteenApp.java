package lesson13;

public class ThirteenApp {

    public static float APPLE_WEIGHT = 1.0f;
    public static float ORANGE_WEIGHT = 1.5f;

    public static void main(String[] args) {

        Box<Apple> appleBox = new Box<>();
        for (int i = 0; i < 10; i++) {
            appleBox.add(new Apple(APPLE_WEIGHT));
        }

        Box<Orange> orangeBox = new Box<>();
        for (int i = 0; i < 10; i++) {
            orangeBox.add(new Orange(ORANGE_WEIGHT));
        }

        System.out.println("Box of apples weighs: " + appleBox.getWeight());
        System.out.println("Box of oranges weighs: " + orangeBox.getWeight());
        System.out.println("Result of comparing apple and orange boxes: " + appleBox.compare(orangeBox));

        Box<Apple> appleBox2 = new Box<>();
        for (int i = 0; i < 5; i++) {
            appleBox2.add(new Apple(APPLE_WEIGHT));
        }

        appleBox.addOtherBox(appleBox2);
        System.out.println("Weight of first apple box after refill: " + appleBox.getWeight());
        System.out.println("Weight of second apple box: " + appleBox2.getWeight());
        System.out.println("Result of comparing apple and orange boxes: " + appleBox.compare(orangeBox));
    }
}
