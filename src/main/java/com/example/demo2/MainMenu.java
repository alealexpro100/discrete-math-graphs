package com.example.demo2;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;


public class MainMenu extends goToButtons {

    @FXML
    private ListView<String> ListView1;

    @FXML
    void initialize() {
        ListView1.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try {
                    onToChosen(ListView1.getSelectionModel().getSelectedItem().substring(0, 2).replace(".", ""), ListView1.getSelectionModel().getSelectedItem());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void onToChosen(String item, String name) throws IOException {
        var app = new window();
        app.setFileName("view"+item+".fxml");
        app.setTitleName(name);
        app.startWindow();
    }

    @FXML
    private void onToExample() throws IOException {
        var app = new window();
        app.setFileName("Example.fxml");
        app.setTitleName("Example");
        app.startWindow();
    }

}
