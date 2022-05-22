package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class window extends Application {
    private String fileName = "MainMenu.fxml";
    private String titleName = "Главное меню";
    static Stage stage = null;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public static void setStage(Stage stage) {
        window.stage = stage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        startWindow();
    }

    public void startWindow() throws IOException {
        if (stage == null)
            stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(window.class.getResource(fileName));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(window.class.getResourceAsStream("logo.png")));
        stage.setTitle(titleName);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}