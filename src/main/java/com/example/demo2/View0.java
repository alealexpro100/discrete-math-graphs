package com.example.demo2;


import javafx.fxml.FXML;
import javafx.scene.layout.Pane;


public class View0 extends goToButtons {

    @FXML
    private Pane pane1;

    GraphDraw<Integer> gd;

    @FXML
    void initialize() {
        System.out.println("test");
        gd=new GraphDraw<Integer>(pane1, 20);
    }

    @FXML
    private void onClickTestAction() throws Exception {
        gd.AddPoint(2); //0
        gd.AddPoint(4); //1
        gd.AddPoint(5); //2
        System.out.println(gd.GetPoint(0)); //2
        System.out.println(gd.GetPoint(1)); //4
        System.out.println(gd.GetPoint(2)); //5
        gd.AddLink(0, 1, 2);
        gd.AddLink(0, 1, 2);
        gd.render();
        gd.test_gr();
    }
}
