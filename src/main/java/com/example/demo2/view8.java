package com.example.demo2;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class view8 extends goToButtons {

    @FXML
    TextField InputPointsCount;

    @FXML
    TextField textfield1;

    @FXML
    private TableView tableview1;

    @FXML
    private TableView tableview2;

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
        for (int i = 0; i < PointCount; i++)
            for (int j = 0; j < PointCount; j++)
                points[i][j]="0";
        TableBuild.TableViewFill(tableview1, points, PointCount);
        TableBuild.TableViewEditableNotOriented(tableview1, points, PointCount);
    }

    @FXML
    private void onClickCalculate() {
        int PointCount=Integer.parseInt(InputPointsCount.getText());
        List<List<Integer>> points_list = TableBuild.GetAdjacencyMatrix(points, PointCount);

        ListGraph graph = new ListGraph();

        int InitPoint=Integer.parseInt(textfield1.getText()) - 1;
        String[][] stringDists = new String[1][PointCount];
        List<Integer> dists = graph.getDistFrom(points_list, InitPoint, PointCount);
        for (int i=0; i<PointCount; i++) {
            if (dists.get(i)==Integer.MAX_VALUE)
                stringDists[0][i]="∞";
            else
                stringDists[0][i]=Integer.toString(dists.get(i));
        }
        TableBuild.TableViewFill(tableview2, stringDists, PointCount);
    }
}
