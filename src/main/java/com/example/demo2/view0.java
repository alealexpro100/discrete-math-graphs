package com.example.demo2;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class view0 extends goToButtons {

    @FXML
    TextField InputPointsCount;

    @FXML
    TextField textfield1;

    @FXML
    TextField textfield2;

    @FXML
    TextField textfield3;

    @FXML
    TextField textfield4;

    @FXML
    TextField textfield5;

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
        List<List<Integer>> points_list = TableBuild.GetMatrixList(points, PointCount);
        
        ListGraph graph = new ListGraph();

        String[][] Powers = new String[1][PointCount];
        List<Integer> PowersList = graph.getVertexPower(points_list, PointCount);
        for (int i=0; i<PointCount; i++) {
            Powers[0][i]=PowersList.get(i).toString();
        }
        TableBuild.TableViewFill(tableview2, Powers, PointCount);
        textfield1.setText(Integer.toString(graph.getCntConnectedComponents(points_list, PointCount)));
        if (graph.checkEuler(points_list, PointCount)) {
            textfield2.setText("Да");
        } else {
            textfield2.setText("Нет");
        }
        if (graph.checkHalfEuler(points_list, PointCount)) {
            textfield3.setText("Да");
        } else {
            textfield3.setText("Нет");
        }
        if (graph.checkBipartite(points_list, PointCount)) {
            textfield4.setText("Да");
        } else {
            textfield4.setText("Нет");
        }
        if (graph.checkFullBipartite(points_list, PointCount)) {
            textfield5.setText("Да");
        } else {
            textfield5.setText("Нет");
        }
    }
}
