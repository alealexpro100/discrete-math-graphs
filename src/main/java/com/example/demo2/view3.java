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

    GraphDraw<Integer> gd;

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
        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(points));
        tableview1.getItems().clear();
        tableview1.getColumns().clear();
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
            tc.setOnEditCommit(
                new EventHandler<CellEditEvent<String[], String>>() {
                    @Override
                    public void handle(CellEditEvent<String[], String> t) {
                        points[t.getTablePosition().getRow()][t.getTablePosition().getColumn()]=t.getNewValue();
                    }
                }
            );
            tableview1.getColumns().add(tc);
        }
        tableview1.setItems(data);

        gd=new GraphDraw<Integer>(pane1, PointCount);
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
