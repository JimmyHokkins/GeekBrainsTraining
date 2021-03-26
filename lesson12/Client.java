package lesson12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends JFrame {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean authorized;
    private String myNick;

    private JTextField loginField;
    private JTextField passField;

    private JTextArea chatArea;
    private JTextArea chatMembers;
    private JTextField textMsg;

    public Client() {
        createChatWindow();
    }

    public void start() {
        try {
            socket = new Socket("localhost", MyServer.PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            setAuthorized(false);
            Thread t = new Thread(() -> {
                try {
                    while (true) {
                        String strFromServer = in.readUTF();
                        if (strFromServer.startsWith("/authok ")) {
                            setAuthorized(true);
                            createChatControls();
                            myNick = strFromServer.split("\\s")[1];
                            break;
                        }
                        else {
                            loginField.setText(strFromServer);
                        }
                    }
                    while (true) {
                        String strFromServer = in.readUTF();
                        if (strFromServer.startsWith("/end")) {
                            createLoginControls();
                            break;
                        }
                        if (strFromServer.startsWith("/clients")) {
                            chatMembers.setText("Chat members: \n");
                            chatMembers.append(strFromServer.split("\\s", 2)[1]);
                        }
                        else {
                            chatArea.append(strFromServer);
                            chatArea.append("\n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        setAuthorized(false);
                        socket.close();
                        myNick = "";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.setDaemon(true);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    private void createChatWindow() {
        setTitle("Super chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = (int) (dimension.width * 0.5);
        int height = (int) (dimension.height * 0.6);
        setBounds(dimension.width / 2 - width / 2, dimension.height / 2 - height / 2, width, height);
        createLoginControls();
    }

    private void createLoginControls() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginField = new JTextField();
        passField = new JTextField();
        loginField.setMaximumSize(new Dimension(200, 30));
        passField.setMaximumSize(new Dimension(200, 30));
        JButton sendLogin = new JButton("Log In");
        loginField.setMargin(new Insets(5, 5, 5, 5));
        passField.setMargin(new Insets(5, 5, 5, 5));
        sendLogin.addActionListener(e -> onAuthClick());
        loginField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passField.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(Box.createVerticalGlue());
        loginPanel.add(loginField);
        loginPanel.add(passField);
        loginPanel.add(sendLogin);
        loginPanel.add(Box.createVerticalGlue());
        add(loginPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void onAuthClick() {
        if (socket == null || socket.isClosed()) {
            start();
        }
        try {
            if (loginField.getText().isEmpty() || passField.getText().isEmpty()) return;
            out.writeUTF("/auth " + loginField.getText() + " " + passField.getText());
            loginField.setText("");
            passField.setText("");
        } catch (Exception e) {
            loginField.setText("Can't authenticate.");
        }
    }    

    private void createChatControls() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());
        chatArea = new JTextArea();
        chatArea.setLineWrap(true);
        chatArea.setEditable(false);
        JScrollPane scrollMsg = new JScrollPane(chatArea);
        add(scrollMsg, BorderLayout.CENTER);
        chatMembers = new JTextArea();
        chatMembers.setLineWrap(true);
        chatMembers.setEditable(false);
        JScrollPane scrollMembers = new JScrollPane(chatMembers);
        add(scrollMembers, BorderLayout.EAST);
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());
        textMsg = new JTextField();
        JButton sendMsg = new JButton("Send");
        ActionListener listener = e -> onSendMsg();
        textMsg.addActionListener(listener);
        sendMsg.addActionListener(listener);
        chatPanel.add(textMsg, BorderLayout.CENTER);
        chatPanel.add(sendMsg, BorderLayout.EAST);
        add(chatPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void onSendMsg() {
        try {
            if (textMsg.getText().isEmpty()) return;
            out.writeUTF(textMsg.getText());
            textMsg.setText("");
        } catch (Exception e) {
            textMsg.setText("Can't send the message to the server.");
        }
    }
}