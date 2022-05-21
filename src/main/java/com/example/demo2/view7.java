package com.example.demo2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TextField;

public class view7 extends goToButtons {

    @FXML
    TextField InputPointsCount;

    @FXML
    private TableView tableview1;

    String[][] points;
    GraphDraw GraphDraw;

    @FXML
    private Pane pane1;

    GraphDraw gd;

    @FXML
    void initialize() {
        //Автозапуск после открытия окна.
        GraphDraw = new GraphDraw(pane1, 20);
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
        TableBuild.TableViewEditable(tableview1, points);

        gd=new GraphDraw(pane1, PointCount);
    }

    @FXML
    private void onClickBuild() {
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
        GraphDraw.SetGraph(points_list, PointCount);
        try {
            GraphDraw.TextOut();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        GraphDraw.RenderStupid();
    }
}
