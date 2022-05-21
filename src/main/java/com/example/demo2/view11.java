package com.example.demo2;

import java.util.Arrays;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.util.Callback;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
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
        String code=textfield1.getText();

        int PointCount=4;
        String[][] points = new String[PointCount][PointCount];
        for (int i = 0; i < PointCount; i++)
            for (int j = 0; j < PointCount; j++)
                points[i][j]="0";
        //Here we build table
        TableBuild.TableViewFill(tableview1, points, PointCount);
    }
}
