package com.example.chat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MassageController {
    @FXML
    private Label massage;


    public void setMassage(String massage) {
        this.massage.setText(massage);
    }
}
