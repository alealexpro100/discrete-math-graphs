package com.example.demo2;


import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;


public class View0 extends goToButtons {

    @FXML
    private Canvas canvas1;

    GraphDraw<Integer> gd;

    @FXML
    void initialize() {
        System.out.println("test");
        gd=new GraphDraw<Integer>(canvas1, 20);
        gd.test();
    }

    @FXML
    private void onClickTestAction() throws Exception {
        gd.clear();
    }
}
