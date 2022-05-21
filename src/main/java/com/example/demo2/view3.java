package com.example.demo2;

import java.util.Arrays;

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

public class view3 extends goToButtons {

    @FXML
    TextField InputPointsCount;

    @FXML
    private TableView tableview1;

    String[][] points;

    @FXML
    private Pane pane1;

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
    private void onClickDFS() {
        int PointCount=Integer.parseInt(InputPointsCount.getText());
        for (int i = 0; i < PointCount; i++) {
            for (int j = 0; j < PointCount; j++) {
                System.out.print(points[i][j]+" ");
            }
            System.out.print("\n");
        }
    }
}
