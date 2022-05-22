package com.example.demo2;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;
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
        TableBuild.TableViewEditableNotOriented(tableview1, points, PointCount);

        gd=new GraphDraw(pane1, PointCount);
    }

    @FXML
    private void onClickBuild() {
        int PointCount=Integer.parseInt(InputPointsCount.getText());
        List<List<Integer>> points_list = TableBuild.GetMatrixList(points, PointCount);

        ListGraph graph = new ListGraph();

        List<List<To>> SpanningTree = (List<List<To>>)graph.getSpanningTree(points_list, PointCount);

        GraphDraw.SetGraphTo(SpanningTree, PointCount, false);
        try {
            GraphDraw.TextOut();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        GraphDraw.RenderStupid();
    }
}
