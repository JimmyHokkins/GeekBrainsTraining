package lesson11;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        Socket socket;
        try (ServerSocket serverSocket = new ServerSocket(ConnInfo.SERVER_PORT)) {
            System.out.println("Server started, waiting connection...");
            socket = serverSocket.accept();
            System.out.println("Client is connected.");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Input input = new Input(in);
            Output output = new Output(out);
            input.getThrd().join();
            output.getThrd().join();
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
