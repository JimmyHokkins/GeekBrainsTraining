package lesson13;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private final ArrayList<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight() {
        return fruits.size() == 0 ? 0f : fruits.get(0).getWeight() * fruits.size();
    }

    public boolean compare(Box<?> box) {
        return this.getWeight() == (box.getWeight());
    }

    public void addOtherBox(Box<T> box) {
        fruits.addAll(box.fruits);
        box.fruits.clear();
    }
}
