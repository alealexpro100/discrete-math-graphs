package com.example.demo2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public static void TableViewFill(TableView tableview, String[][] points, int PointCount) {
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
            tc.setSortable(false);
            tc.setPrefWidth(45);
            tableview.getColumns().add(tc);
        }
        tableview.setItems(data);
    }
    
    public static void TableViewEditable(TableView tableview, String[][] points) {
        for (Object x: tableview.getColumns()) {
            if (x.getClass().equals(TableView.class))
                continue;
            TableColumn tc = (TableColumn)x;
            tc.setCellFactory(TextFieldTableCell.forTableColumn());
            tc.setOnEditCommit(
                new EventHandler<CellEditEvent<String[], String>>() {
                    @Override
                    public void handle(CellEditEvent<String[], String> t) {
                        points[t.getTablePosition().getRow()][t.getTablePosition().getColumn()]=t.getNewValue();
                    }
                }
            );
        }
    }
    public static void TableViewEditableNotOriented(TableView tableview, String[][] points, int PointCount) {
        for (Object x: tableview.getColumns()) {
            if (x.getClass().equals(TableView.class))
                continue;
            TableColumn tc = (TableColumn)x;
            tc.setCellFactory(TextFieldTableCell.forTableColumn());
            tc.setOnEditCommit(
                new EventHandler<CellEditEvent<String[], String>>() {
                    @Override
                    public void handle(CellEditEvent<String[], String> t) {
                        points[t.getTablePosition().getRow()][t.getTablePosition().getColumn()]=t.getNewValue();
                        points[t.getTablePosition().getColumn()][t.getTablePosition().getRow()]=t.getNewValue();
                        //Don't bit me pls.
                        TableViewFill(tableview, points, PointCount);
                        TableViewEditableNotOriented(tableview, points, PointCount);
                    }
                }
            );
        }
    }

    public static List<List<Integer>> GetMatrixList(String[][] points, int PointCount) {
        List<List<Integer>> points_list = new ArrayList<>();
        for (int i = 0; i < PointCount; i++) {
            points_list.add(new ArrayList<>());
            for (int j = 0; j < PointCount; j++) {
                if (!points[i][j].equals("0") && !points[i][j].isBlank())
                    points_list.get(i).add(j);
            }
        }
        return points_list;
    }

    public static List<List<Integer>> GetAdjacencyMatrix(String[][] points, int PointCount) {
        List<List<Integer>> points_list = new ArrayList<>();
        for (int i = 0; i < PointCount; i++) {
            points_list.add(new ArrayList<>());
            for (int j = 0; j < PointCount; j++) {
                if (!points[i][j].equals("0") && !points[i][j].isBlank())
                    points_list.get(i).add(Integer.valueOf(points[i][j]));
                else
                    points_list.get(i).add(0);
            }
        }
        return points_list;
    }

    public static String[][] SetGraph(List<List<Integer>> Graph, int PointsCount) {
        String[][] LinkData = new String[PointsCount][PointsCount];
        for (int i = 0; i < PointsCount; i++) {
            for (int j = 0; j < PointsCount; j++) {
                LinkData[i][j] = "0";
            }
        }
        //Set graph data
        for (int i=0;i<PointsCount;i++) {
            for (int j=0;j<Graph.get(i).size();j++)
                LinkData[i][Graph.get(i).get(j)]="1";
        }
        return LinkData;
    }
}
