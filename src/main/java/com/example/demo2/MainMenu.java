package com.example.demo2;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;


public class MainMenu extends goToButtons {

    @FXML
    private ListView<String> ListView1;

    @FXML
    void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i=0; i<13; i++)
            items.add(Integer.toString(i));
        ListView1.setItems(items);
        ListView1.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + ListView1.getSelectionModel().getSelectedItem());
                try {
                    onToChosen(ListView1.getSelectionModel().getSelectedItem());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void onToChosen(String item) throws IOException {
        var app = new window();
        app.setFileName("view"+item+".fxml");
        app.setTitleName("Application "+item);
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
