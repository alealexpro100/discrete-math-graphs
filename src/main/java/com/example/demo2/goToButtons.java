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
        app.setTitleName("Главное меню");
        app.startWindow();
    }

    @FXML
    private void onTo0() throws IOException {
        var app = new window();
        app.setFileName("view0.fxml");
        app.setTitleName("0. Типы графа");
        app.startWindow();
    }
    @FXML
    private void onTo1() throws IOException {
        var app = new window();
        app.setFileName("view1.fxml");
        app.setTitleName("1. Обход в глубину (DFS)");
        app.startWindow();
    }
    @FXML
    private void onTo2() throws IOException {
        var app = new window();
        app.setFileName("view2.fxml");
        app.setTitleName("2. Проверка обхода в глубину (DFS)");
        app.startWindow();
    }
    @FXML
    private void onTo3() throws IOException {
        var app = new window();
        app.setFileName("view3.fxml");
        app.setTitleName("3. Обход в ширину (BFS)");
        app.startWindow();
    }
    @FXML
    private void onTo4() throws IOException {

        var app = new window();
        app.setFileName("view4.fxml");
        app.setTitleName("4. Проверка обхода в ширину (BFS)");
        app.startWindow();
    }
    @FXML
    private void onTo5() throws IOException {
        var app = new window();
        app.setFileName("view5.fxml");
        app.setTitleName("5. Компонент связанность");
        app.startWindow();
    }
    @FXML
    private void onTo6() throws IOException {
        var app = new window();
        app.setFileName("view6.fxml");
        app.setTitleName("6. Проверка компонент связанности");
        app.startWindow();
    }
    @FXML
    private void onTo7() throws IOException {
        var app = new window();
        app.setFileName("view7.fxml");
        app.setTitleName("7. Построение минимального остовного дерева");
        app.startWindow();
    }
    @FXML
    private void onTo8() throws IOException {
        var app = new window();
        app.setFileName("view8.fxml");
        app.setTitleName("8. Кратчайшие пути между вершинами");
        app.startWindow();
    }
    @FXML
    private void onTo9() throws IOException {
        var app = new window();
        app.setFileName("view9.fxml");
        app.setTitleName("9. Матрица кратчайших путей");
        app.startWindow();
    }
    @FXML
    private void onTo10() throws IOException {
        var app = new window();
        app.setFileName("view10.fxml");
        app.setTitleName("10. Кодирование Прюфера");
        app.startWindow();
    }
    @FXML
    private void onTo11() throws IOException {
        var app = new window();
        app.setFileName("view11.fxml");
        app.setTitleName("11. Декодирование Прюфера");
        app.startWindow();
    }
    @FXML
    private void onTo12() throws IOException {
        var app = new window();
        app.setFileName("view12.fxml");
        app.setTitleName("12. Раскраска дерева");
        app.startWindow();
    }

}
