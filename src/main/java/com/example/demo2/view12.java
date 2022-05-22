package com.example.demo2;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class view12 extends goToButtons {

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

        List<Integer> paints = graph.paintGraph(points_list, PointCount);
        System.out.println("Ex: "+graph.paintGraph(points_list, PointCount)+" "+points_list);

        GraphDraw.SetGraph(points_list, PointCount);
        try {
            GraphDraw.TextOut();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        GraphDraw.RenderStupid();
        for (int i=0; i<paints.size(); i++) {
            Color color;
            switch (paints.get(i)) {
                case 0:
                    color=Color.ORANGE;
                    break;
                case 1:
                    color=Color.RED;
                    break;
                case 2:
                    color=Color.BLUE;
                    break;
                case 3:
                    color=Color.GREEN;
                    break;
                case 4:
                    color=Color.YELLOW;
                    break;
                case 5:
                    color=Color.PURPLE;
                    break;
                case 6:
                    color=Color.CYAN;
                    break;
                case 7:
                    color=Color.BROWN;
                    break;
                case 8:
                    color=Color.DARKGRAY;
                    break;
                default:
                    System.out.println("Wrong color. Setting to WHITE.");
                    color=Color.WHITE;
                    break;
            }
            try {
                GraphDraw.GetNodeCircle(i).setFill(color);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
