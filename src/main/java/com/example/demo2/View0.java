package com.example.demo2;


import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;


public class View0 extends goToButtons {

    @FXML
    private Canvas canvas1;

    @FXML
    private Pane pane1;

    GraphDraw<Integer> gd;

    @FXML
    void initialize() {
        System.out.println("test");
        gd=new GraphDraw<Integer>(canvas1, pane1, 20);
    }

    @FXML
    private void onClickTestAction() throws Exception {
        gd.clear();
        gd.test();
    }
}
