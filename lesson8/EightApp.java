package lesson8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EightApp {
    public static void main(String[] args) {
        String[] words = { "alpha", "beta", "gamma", "epsilon", "omega", "delta", "zeta", "alpha",
                "gamma", "omega" };
        showUniqueWords(words);

        PhoneBook book = new PhoneBook();
        book.add("Ivanov", "123-456-789");
        book.add("Petrov", "987-654-321");
        book.add("Sidorov", "456-987-123");
        book.add("Ivanov", "852-963-741");
        book.add("Conor", "951-753-486");

        System.out.println(book.get("Ivanov"));
    }

    public static void showUniqueWords(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        System.out.println("The unique words: " + set);
        System.out.println("Count of the unique words: " + set.size());
    }
}
