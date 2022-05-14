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
            items.add("Task "+Integer.toString(i));
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

    @FXML
    private void onTo0() throws IOException {
        var app = new window();
        app.setFileName("view0.fxml");
        app.setTitleName("Application 0");
        app.startWindow();
    }
    @FXML
    private void onTo1() throws IOException {
        var app = new window();
        app.setFileName("view1.fxml");
        app.setTitleName("Application 1");
        app.startWindow();
    }
    @FXML
    private void onTo2() throws IOException {
        var app = new window();
        app.setFileName("hello-view.fxml");
        app.setTitleName("Application 2");
        app.startWindow();
    }
    @FXML
    private void onTo3() throws IOException {
        var app = new window();
        app.setFileName("view3.fxml");
        app.setTitleName("Application 3");
        app.startWindow();
    }
    @FXML
    private void onTo4() throws IOException {

        var app = new window();
        app.setFileName("view4.fxml");
        app.setTitleName("Application 4");
        app.startWindow();
    }
    @FXML
    private void onTo5() throws IOException {
        var app = new window();
        app.setFileName("view5.fxml");
        app.setTitleName("Application 5");
        app.startWindow();
    }
    @FXML
    private void onTo6() throws IOException {
        var app = new window();
        app.setFileName("view6.fxml");
        app.setTitleName("Application 6");
        app.startWindow();
    }
    @FXML
    private void onTo7() throws IOException {
        var app = new window();
        app.setFileName("view7.fxml");
        app.setTitleName("Application 7");
        app.startWindow();
    }
    @FXML
    private void onTo8() throws IOException {
        var app = new window();
        app.setFileName("view8.fxml");
        app.setTitleName("Application 8");
        app.startWindow();
    }
    @FXML
    private void onTo9() throws IOException {
        var app = new window();
        app.setFileName("view9.fxml");
        app.setTitleName("Application 9");
        app.startWindow();
    }
    @FXML
    private void onTo10() throws IOException {
        var app = new window();
        app.setFileName("view10.fxml");
        app.setTitleName("Application 10");
        app.startWindow();
    }
    @FXML
    private void onTo11() throws IOException {
        var app = new window();
        app.setFileName("view11.fxml");
        app.setTitleName("Application 11");
        app.startWindow();
    }
    @FXML
    private void onTo12() throws IOException {
        var app = new window();
        app.setFileName("view12.fxml");
        app.setTitleName("Application 12");
        app.startWindow();
    }
}
