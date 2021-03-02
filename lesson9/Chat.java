package lesson9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Chat extends JFrame {

    public Chat() {
        setTitle("Super chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = (int) (dimension.width * 0.5);
        int height = (int) (dimension.height * 0.6);
        setBounds(dimension.width / 2 - width / 2, dimension.height / 2 - height / 2, width, height);
        addControls();
        setVisible(true);
    }

    private void addControls() {
        setLayout(new BorderLayout());
        JTextArea ta = new JTextArea();
        ta.setLineWrap(true);
        ta.setEditable(false);
        JScrollPane scroll = new JScrollPane(ta);
        add(scroll, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JTextField tf = new JTextField();
        JButton send = new JButton("Send");
        ActionListener listener = e -> {
            if (tf.getText().length() > 0) {
                ta.append(tf.getText() + '\n');
                tf.setText("");
            }
        };
        tf.addActionListener(listener);
        send.addActionListener(listener);
        panel.add(tf, BorderLayout.CENTER);
        panel.add(send, BorderLayout.EAST);
        add(panel, BorderLayout.SOUTH);
    }
}
