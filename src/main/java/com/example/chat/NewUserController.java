package com.example.chat;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewUserController {
    @FXML
    private TextField name;

    @FXML
    private TextField host;

    @FXML
    private TextField port;

    public User getUser(){
        return new User(name.getText(), host.getText(), Integer.parseInt(port.getText()));
    }
}
