package com.example.demo2;


import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;


public class View0 extends goToButtons {

    @FXML
    private Canvas canvas1;

    GraphDraw gd=new GraphDraw(canvas1, 20);

    @FXML
    void initialize() {
        System.out.println("test");
    }

    @FXML
    private void onClickTestAction() throws Exception {
        gd.test();
    }
}
