package lesson12;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    public static final int PORT = 8189;
    private List<ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Server is waiting for a connection...");
                Socket socket = serverSocket.accept();
                System.out.println("Client is connected.");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            System.out.println("Server error.");
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public synchronized void unsubscribe(ClientHandler o) {
        clients.remove(o);
    }
    public synchronized void subscribe(ClientHandler o) {
        clients.add(o);
    }
    public synchronized String getStringClients() {
        StringBuilder members = new StringBuilder();
        for (ClientHandler client : clients) {
            members.append(client.getName());
            members.append('\n');
        }
        return members.toString();
    }


}
