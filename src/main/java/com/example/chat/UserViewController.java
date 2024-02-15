package com.example.chat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserViewController {
    @FXML
    private Label name;


    public void setName(String name) {
        this.name.setText(name);
    }
}
