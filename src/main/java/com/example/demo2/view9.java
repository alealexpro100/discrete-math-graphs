package com.example.demo2;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class view9 extends goToButtons {

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
    private void onClickBuild() {
        int PointCount=Integer.parseInt(InputPointsCount.getText());
        List<List<Integer>> points_list = TableBuild.GetMatrixList(points, PointCount);

        ListGraph graph = new ListGraph();

        List<List<Integer>> points_short_list = graph.getShortestPathMatrix(points_list, PointCount);

        System.out.println(points_short_list);
        
        String[][] points_short = new String[PointCount][PointCount];
        for (int i = 0; i < PointCount; i++)
            for (int j = 0; j < PointCount; j++) {
                int val=points_short_list.get(i).get(j);
                if (val>=1_000_000_000)
                    points_short[i][j]="∞";
                else
                    points_short[i][j]=String.valueOf(val);
            }
        TableBuild.TableViewFill(tableview2, points_short, PointCount);
    }
}
