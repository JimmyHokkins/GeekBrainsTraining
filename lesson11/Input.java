package lesson11;

import java.io.DataInputStream;
import java.io.IOException;

public class Input implements Runnable {

    private final DataInputStream input;
    private final Thread thrd;

    public Input(DataInputStream input) {
        this.input = input;
        thrd = new Thread(this);
        thrd.start();
    }

    public Thread getThrd() {
        return thrd;
    }

    @Override
    public void run() {
        try (input) {
            while (true) {
                String inputStr = input.readUTF();
                if (inputStr.equals("/stop")) {
                    input.close();
                    break;
                }
                System.out.println(inputStr);
            }
        } catch (IOException e) {
            System.out.println("Connection is lost...");
        }
    }
}
