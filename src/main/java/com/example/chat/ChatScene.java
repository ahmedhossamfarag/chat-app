package com.example.chat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;

public class ChatScene extends Scene {
    public ChatViewController controller;

    public ChatScene(User user) {
        super(new VBox());
        setRoot(get());
        controller.setUser(user);
    }

    private Parent get(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File("resources/chat-view.fxml").toURI().toURL());
            Parent p = fxmlLoader.load();
            controller = fxmlLoader.getController();
            return p;
        }catch (IOException e){
            return null;
        }
    }
}
