package com.example.chat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;

public class MainViewController {
    @FXML
    private ImageView addChat;

    @FXML
    private VBox users;

    public void addChat(){
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("resources/new-user-view.fxml").toURI().toURL());
        ButtonType loginButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        Dialog<VBox> dialog = new Dialog<>();
        dialog.setTitle("New User");
        dialog.getDialogPane().setContent(fxmlLoader.load());
        dialog.getDialogPane().getButtonTypes().add(loginButtonType);
        dialog.showAndWait();
        User user = ((NewUserController) fxmlLoader.getController()).getUser();
        ChatApplication.addUser(user);
        addUser(user);
        } catch (Exception e) {
        }
    }

    public void addUser(User user) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File("resources/user-view.fxml").toURI().toURL());
            Parent p = fxmlLoader.load();
            ((UserViewController) fxmlLoader.getController()).setName(user.name);
            users.getChildren().add(p);
            p.setOnMouseClicked(e->{
                ChatApplication.startChat(user);
            });
        } catch (IOException e) {
        }
    }
}
