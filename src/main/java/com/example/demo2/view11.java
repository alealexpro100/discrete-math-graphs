package com.example.demo2;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class view11 extends goToButtons {

    @FXML
    TextField textfield1;

    @FXML
    Label label1;

    @FXML
    private TableView tableview1;

    @FXML
    void initialize() {
        //Автозапуск после открытия окна.
        tableview1.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY); 
    }

    @FXML
    private void onClickCalc() {
        String code_text=textfield1.getText().replace(" ", "");
        List<Integer> code=new ArrayList<>();
        for (char x: code_text.toCharArray()) {
            code.add(x-'0');
        }

        ListGraph graph = new ListGraph();

        List<List<Integer>> decoded = (List<List<Integer>>)graph.decodeTree(code, code.size());
        System.out.println(decoded+" "+decoded.size());
        int PointCount=decoded.size();
        //Here we build table
        TableBuild.TableViewFill(tableview1, TableBuild.SetGraph(decoded, PointCount), PointCount);
    }
}
