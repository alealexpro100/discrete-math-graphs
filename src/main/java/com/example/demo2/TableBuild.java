package com.example.demo2;

import java.util.Arrays;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.util.Callback;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;

public class TableBuild {
    public static void FillTableView(TableView tableview, String[][] points, int PointCount) {
        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(points));
        tableview.getItems().clear();
        tableview.getColumns().clear();
        for (int i = 0; i < PointCount; i++) {
            TableColumn tc = new TableColumn(Integer.toString(i+1));
            final int colNo = i;
            tc.setCellValueFactory(
                new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                        return new SimpleStringProperty((p.getValue()[colNo]));
                    }
                }
            );
            tc.setCellFactory(TextFieldTableCell.forTableColumn());
            tc.setSortable(false);
            tc.setPrefWidth(45);
            tc.setOnEditCommit(
                new EventHandler<CellEditEvent<String[], String>>() {
                    @Override
                    public void handle(CellEditEvent<String[], String> t) {
                        points[t.getTablePosition().getRow()][t.getTablePosition().getColumn()]=t.getNewValue();
                    }
                }
            );
            tableview.getColumns().add(tc);
        }
        tableview.setItems(data);
    }
}
