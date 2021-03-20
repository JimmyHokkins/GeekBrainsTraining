package lesson11;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public void run() {
        try (Socket socket = new Socket(ConnInfo.SERVER_ADDR, ConnInfo.SERVER_PORT)) {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Input input = new Input(in);
            Output output = new Output(out);
            input.getThrd().join();
            output.getThrd().join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
