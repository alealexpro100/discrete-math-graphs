package com.example.demo2;


import javafx.application.Platform;
import javafx.fxml.FXML;

import java.io.IOException;

public class goToButtons {

    @FXML
    private void onClickExit() {
        Platform.exit();
    }

    @FXML
    private void onToMainMenu() throws IOException {
        var app = new window();
        app.setFileName("MainMenu.fxml");
        app.setTitleName("Main Menu");
        app.startWindow();
    }

}
