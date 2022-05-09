package com.example.demo2;

import java.io.IOException;
import javafx.fxml.FXML;


public class MainMenu extends goToButtons {

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
    
}
