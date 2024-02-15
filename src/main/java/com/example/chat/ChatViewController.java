package com.example.chat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class ChatViewController {
    private User user;

    @FXML
    private Label name;

    @FXML
    private ImageView send;

    @FXML
    private TextField massage;

    @FXML
    private VBox content;

    public void setUser(User user){
        this.user = user;
        this.name.setText(user.name);
    }

    public void send(){
        String massage = this.massage.getText();
        this.massage.setText("");
        try {
            Socket client = new Socket(user.host, user.port);
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            out.writeUTF(massage);
            client.shutdownInput();
            client.close();
        }catch (IOException e){

        }
        addMassage(massage , true);
    }

    public void addMassage(String massage, boolean sent){
        String url = sent? "sent-massage-view.fxml":"received-massage-view.fxml";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File("resources/" + url).toURI().toURL());
            Parent p = fxmlLoader.load();
            ((MassageController) fxmlLoader.getController()).setMassage(massage);
            Platform.runLater(()->content.getChildren().add(p));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
