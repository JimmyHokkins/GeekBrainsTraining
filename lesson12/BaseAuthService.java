package lesson12;

import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService {
    private class Entry {
        private final String login;
        private final String pass;
        private final String nick;

        public Entry(String login, String pass, String nick) {
            this.login = login;
            this.pass = pass;
            this.nick = nick;
        }
    }

    private final List<Entry> entries;

    public BaseAuthService() {
        entries = new ArrayList<>();
        entries.add(new Entry("billy@gmail.com", "123", "Bill"));
        entries.add(new Entry("johnny@gmail.com", "1234", "John"));
        entries.add(new Entry("maria@gmail.com", "12345", "Mary"));
    }

    @Override
    public void start() {
        System.out.println("Authentication service is started.");
    }

    @Override
    public String getNickByLoginPass(String login, String pass) {
        for (Entry o : entries) {
            if (o.login.equals(login) && o.pass.equals(pass)) return o.nick;
        }
        return null;
    }

    @Override
    public void stop() {
        System.out.println("Authentication service is stopped.");
    }
}
