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

    @FXML
    private void onTo0() throws IOException {
        var app = new window();
        app.setFileName("view0.fxml");
        app.setTitleName("Типы графа");
        app.startWindow();
    }
    @FXML
    private void onTo1() throws IOException {
        var app = new window();
        app.setFileName("view1.fxml");
        app.setTitleName("Обход в глубину (DFS)");
        app.startWindow();
    }
    @FXML
    private void onTo2() throws IOException {
        var app = new window();
        app.setFileName("view2.fxml");
        app.setTitleName("Application 2");
        app.startWindow();
    }
    @FXML
    private void onTo3() throws IOException {
        var app = new window();
        app.setFileName("view3.fxml");
        app.setTitleName("Обход в ширину (BFS)");
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
        app.setTitleName("Компонент связанность");
        app.startWindow();
    }
    @FXML
    private void onTo6() throws IOException {
        var app = new window();
        app.setFileName("view6.fxml");
        app.setTitleName("Проверка компонент связанности");
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
