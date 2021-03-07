package lesson11;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Output implements Runnable {
    private final DataOutputStream output;
    private final Thread thrd;

    public Output(DataOutputStream output) {
        this.output = output;
        thrd = new Thread(this);
        thrd.start();
    }

    public Thread getThrd() {
        return thrd;
    }

    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try (output) {
            while (true) {
                String msg = br.readLine();
                if (!msg.isEmpty()) output.writeUTF(msg);
                if (msg.equals("/stop")) {
                    output.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Connection is lost...");
        }
    }
}
