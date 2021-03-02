package lesson8;

import java.util.*;

public class PhoneBook {
    private Map<String, Set<String>> book;

    public PhoneBook() {
        this.book = new HashMap<>();
    }

    public void add(String lastName, String phoneNumber) {
        if (!book.containsKey(lastName)) book.put(lastName, new HashSet<>());
        book.get(lastName).add(phoneNumber);
    }

    public Set<String> get(String lastName) {
        return book.get(lastName);
    }

}
