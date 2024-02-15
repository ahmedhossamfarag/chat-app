package com.example.chat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;

public class MainScene extends Scene {
    private static MainViewController controller;

    public MainScene() {
        super(MainScene.get());
        for (User u : ChatApplication.users)
            controller.addUser(u);
    }

    public static Parent get(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File("resources/main-view.fxml").toURI().toURL());
            Parent p = fxmlLoader.load();
            controller = fxmlLoader.getController();
            return p;
        }catch (IOException e){
            return null;
        }
    }
}
