package com.example.demo1;


import javafx.application.Platform;
import javafx.fxml.FXML;

import java.io.IOException;

public class goToButtons {

    @FXML
    private void onClickExit() {

        Platform.exit();


    }
    @FXML
    private void onTo2() throws IOException {
        var app = new HelloApplication();
        app.setFileName("hello-view.fxml");
        app.setTitleName("Application 2");
        app.startWindow();


    }
    @FXML
    private void onTo5() throws IOException {

        var app = new HelloApplication();
        app.setFileName("view5.fxml");
        app.setTitleName("Application 5");
        app.startWindow();



    }
    @FXML
    private void onTo8() throws IOException {
        var app = new HelloApplication();
        app.setFileName("view8.fxml");
        app.setTitleName("Application 8");
        app.startWindow();


    }
    @FXML
    private void onTo11() throws IOException {
        var app = new HelloApplication();
        app.setFileName("view11.fxml");
        app.setTitleName("Application 11");
        app.startWindow();


    }
}
