package com.example.demo2;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class GraphDraw<T> {
    //Graph data
    private int[][] GraphLinkData;
    private ArrayList<T> GraphPointData;
    private int GraphCount;
    private int GraphLimit;

    //Data where to draw
    private Pane GraphOverlay;
    private Canvas GraphCanvas;
    private GraphicsContext gc;

    //Data with drawn objects
    private ArrayList<Node> GraphNodes;

    public GraphDraw(Canvas canvas, Pane overlay, int limit) {
        //Set draw data
        //First we create and set scene
        GraphOverlay=overlay;
        GraphCanvas=canvas;
        gc=GraphCanvas.getGraphicsContext2D();
        //Then we create data for drawn objects
        GraphNodes=new ArrayList<Node>();
        //Set default zero data
        GraphCount=0;
        GraphLimit=limit;
        GraphLinkData=new int[GraphLimit][GraphLimit];
        GraphPointData=new ArrayList<T>(GraphLimit);
    }

    private Circle DrawCircle(double x, double y, double size, Color color, double StrokeSize, Color StrokeColor) {
        Circle circle = new Circle(size);
        circle.setFill(color);
        circle.relocate(x, y);
        circle.setStrokeWidth(StrokeSize);
        circle.setStroke(StrokeColor);
        circle.relocate(x, y);
        gc.setFill(color);
        GraphNodes.add(circle);
        GraphOverlay.getChildren().add(circle);
        return circle;
    }

    private Line DrawLine(double x1, double y1, double x2, double y2, double size, Color color) {
        Line line = new Line(x1, y1, x2, y2);
        line.setStrokeWidth(size);
        line.setFill(color);
        GraphNodes.add(line);
        GraphOverlay.getChildren().add(line);
        return line;
    }

    public int AddPoint(T var) {
        GraphPointData.add(GraphCount, var);;
        return GraphCount++;
    }

    public void AddLink(int IdX, int IdY, int LinkSize) {
        GraphLinkData[IdX][IdY]=LinkSize;
    }

    public void test() {
        gc.setFill(Color.BLUE);
        gc.fillRect(0,0,100,100);
        DrawCircle(200, 200, 50, Color.ORANGE, 4, Color.BLUE);
        DrawCircle(150, 150, 50, Color.RED, 4, Color.BLUE);
        DrawLine(200, 200, 150, 150, 20, Color.BLACK).setStrokeWidth(20);;
        MouseGestures mg = new MouseGestures();
        for (Node node: GraphNodes)
            mg.makeDraggle(node);
    }

    public void clear() {
        GraphOverlay.getChildren().clear();
        GraphNodes.clear();
        gc.clearRect(0, 0, GraphCanvas.getWidth(), GraphCanvas.getHeight());
    }

    public static class MouseGestures {

        double orgSceneX, orgSceneY;
        double orgTranslateX, orgTranslateY;
    
        public void makeDraggle(Node node) {
            node.setOnMousePressed(circleOnMousePressedEventHandler);
            node.setOnMouseDragged(circleOnMouseDraggedEventHandler);
        }
    
        EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
    
            @Override
            public void handle(MouseEvent t) {

                if (t.getSource() instanceof Circle) {
                    System.out.println("Pressing circle.");
                }
    
                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
    
                Node p = ((Node) (t.getSource()));
    
                orgTranslateX = p.getTranslateX();
                orgTranslateY = p.getTranslateY();
            }
        };
    
        EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
    
            @Override
            public void handle(MouseEvent t) {

                if (t.getSource() instanceof Circle) {
                    System.out.println("Moving circle.");
                }
    
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;
    
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;
    
                Node p = ((Node) (t.getSource()));
    
                p.setTranslateX(newTranslateX);
                p.setTranslateY(newTranslateY);
    
            }
        };
    }
}
