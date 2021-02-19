package lesson8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    private Map<String, String> book;

    public PhoneBook() {
        this.book = new HashMap<>();
    }

    public void add(String lastName, String phoneNumber) {
        book.put(phoneNumber, lastName);
    }

    public ArrayList<String> get(String lastName) {
        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, String> item : book.entrySet()) {
            if (item.getValue().equals(lastName)) result.add(item.getKey());
        }
        return result;
    }

}
