package com.example.chat;

import javafx.application.Application;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ChatApplication extends Application {

    public static int port = 6789;
    public static boolean run = true;
    public static final Path usersFile = Path.of("users.txt");
    public static final ArrayList<User> users = new ArrayList<>();
    public static Stage stage;
    public static void startChat(User user) {
        ChatScene scene = new ChatScene(user);
        stage.setScene(scene);
        Thread thread = new Thread(()->{
            try (ServerSocket socket = new ServerSocket(port)) {
                while (run){
                    Socket server = socket.accept();
                    DataInputStream in = new DataInputStream(server.getInputStream());
                    scene.controller.addMassage(in.readUTF(), false);
                    server.close();
                }
            }catch (Exception e){
            }
        });
        thread.start();
    }

    public static void addUser(User user) {
        users.add(user);
    }

    @Override
    public void start(Stage stage) {
        try {
            if (!Files.exists(usersFile)) Files.createFile(usersFile);
            for (String s : Files.readAllLines(usersFile))
                users.add(User.of(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ChatApplication.stage = stage;
        stage.setScene(new MainScene());
        stage.setTitle("Chat");
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(e->{
            try {
                String[] arr = new String[users.size()];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = users.get(i).toString();
                }
                String txt = String.join("\n", arr);
                Files.writeString(usersFile, txt);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            run = false;
            System.exit(0);
        });

        setPort();
    }

    private void setPort() {
        ButtonType loginButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        Dialog<VBox> dialog = new Dialog<>();
        dialog.setTitle("New Port");
        TextField txt = new TextField();
        dialog.getDialogPane().setContent(txt);
        dialog.getDialogPane().getButtonTypes().add(loginButtonType);
        dialog.showAndWait();
        try {
            port = Integer.parseInt(txt.getText());
        }catch (Exception e){

        }
    }

    public static void main(String[] args) {
       launch();
    }
}