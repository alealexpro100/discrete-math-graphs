package com.example.demo2;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;

public class view2 extends goToButtons {

    @FXML
    TextField InputPointsCount;

    @FXML
    TextField textfield1;

    @FXML
    Label label1;

    @FXML
    private TableView tableview1;

    String[][] points;

    @FXML
    void initialize() {
        //Автозапуск после открытия окна.
        tableview1.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY); 
        InputPointsCount.setText("3");
        onClickUpdateTable();
    }

    @FXML
    private void onClickUpdateTable() {
        int PointCount=Integer.parseInt(InputPointsCount.getText());
        points = new String[PointCount][PointCount];
        int[][] randomMatrix = GraphGenerator.getRandomMatrix(PointCount, 0, 10);
        for (int i = 0; i < PointCount; i++)
            for (int j = 0; j < PointCount; j++)
                points[i][j]=Integer.toString(randomMatrix[i][j]);
        TableBuild.TableViewFill(tableview1, points, PointCount);
    }

    @FXML
    private void onClickCheckDFS() {
        if (textfield1.getText().isEmpty()) {
            label1.setText("Пустое поле");
            label1.setTextFill(Color.RED);
            return;
        }

        int PointCount=Integer.parseInt(InputPointsCount.getText());
        List<List<Integer>> points_list = TableBuild.GetMatrixList(points, PointCount);

        ListGraph graph = new ListGraph();

        List<Integer> DFSPath = graph.getDFSPath(points_list, PointCount);
        System.out.println(DFSPath);

        String ans="";
        for (int x: DFSPath)
            ans+=(x+1);
        if (textfield1.getText().equals(ans)) {
            label1.setText("Правильно");
            label1.setTextFill(Color.GREEN);
        }
        else {
            label1.setText("Неправильно");
            label1.setTextFill(Color.RED);
        }
    }
}
