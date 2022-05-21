package com.example.demo2;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class view5 extends goToButtons {

    @FXML
    TextField InputPointsCount;

    @FXML
    TextField textfield1;

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
        for (int i = 0; i < PointCount; i++)
            for (int j = 0; j < PointCount; j++)
                points[i][j]="0";
        TableBuild.TableViewFill(tableview1, points, PointCount);
        TableBuild.TableViewEditableNotOriented(tableview1, points, PointCount);
    }

    @FXML
    private void onClickCalculate() {
        List<List<Integer>> points_list = new ArrayList<>();
        int PointCount=Integer.parseInt(InputPointsCount.getText());
        for (int i = 0; i < PointCount; i++) {
            points_list.add(new ArrayList<>());
            for (int j = 0; j < PointCount; j++) {
                points_list.get(i).add(Integer.parseInt(points[i][j]));
                System.out.print(points[i][j]+" ");
            }
            System.out.print("\n");
        }

        points_list=GraphPerformancesSingleton.getInstance().adjacencyMatrixToList(points_list, PointCount);
        ListGraph graph = new ListGraph();

        textfield1.setText(String.format("%d", graph.getCntConnectedComponents(points_list, PointCount)));
    }
}
