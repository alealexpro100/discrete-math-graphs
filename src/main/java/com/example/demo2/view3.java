package com.example.demo2;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class view3 extends goToButtons {

    @FXML
    TextField InputPointsCount;

    @FXML
    TextField textfield1;

    @FXML
    private TableView tableview1;

    String[][] points;
    GraphDraw GraphDraw;
    
    List<Integer> BFSPath;
    int BFSIndex;

    @FXML
    private Pane pane1;

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
    }

    @FXML
    private void onClickBFS() {
        int PointCount=Integer.parseInt(InputPointsCount.getText());
        List<List<Integer>> points_list = TableBuild.GetMatrixList(points, PointCount);

        ListGraph graph = new ListGraph();

        BFSPath = graph.getBFSPath(points_list, PointCount);
        String ans="";
        for (int x: BFSPath)
            ans+=(x+1);
        textfield1.setText(ans);

        GraphDraw.SetGraph(points_list, PointCount);
        try {
            GraphDraw.TextOut();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        GraphDraw.RenderStupid();
    }

    @FXML
    private void onClickNext() {
        int index;
        try {
            if (BFSIndex!=0) {
                index=BFSPath.get(BFSIndex-1);
                GraphDraw.GetNodeCircle(index).setFill(GraphDraw.DefaultCircleColor);
            }
            if (BFSIndex==BFSPath.size())
                BFSIndex=0;
            index=BFSPath.get(BFSIndex);
            GraphDraw.GetNodeCircle(index).setFill(Color.RED);
            BFSIndex++;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
