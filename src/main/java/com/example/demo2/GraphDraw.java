package com.example.demo2;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * The GraphDraw class provides handy way to render graphs using standard library...
 */
public class GraphDraw<T> {
    //Graph data
    private int[][] GraphLinkData;
    private ArrayList<T> GraphPointData;
    private int GraphCount;
    private int GraphLimit;
    private double WidthMax=500;
    private double HeightMax=500;

    //Data where to draw
    private Pane GraphOverlay;

    //Data with drawn objects
    private ArrayList<Node> GraphNodes;
    
    private void NodeAddAndDraw(Node node) {
        GraphNodes.add(node);
        GraphOverlay.getChildren().add(node);
        GraphOverlay.setMinSize(WidthMax, HeightMax);
    }

    /** 
     * @param overlay Pane where graphs will be rendered
     * @param limit Limit of graph nodes
     */
    public GraphDraw(Pane overlay, int limit) {
        //Set draw data
        GraphOverlay=overlay;
        GraphNodes=new ArrayList<Node>();
        //Set default zero data
        GraphCount=0;
        GraphLimit=limit;
        GraphLinkData=new int[GraphLimit][GraphLimit];
        GraphPointData=new ArrayList<T>(GraphLimit);
    }
    
    private Circle DrawCircle(double x, double y, double size, Color color, double StrokeSize, Color StrokeColor) {
        WidthMax = Double.max(WidthMax, x+size*2);
        HeightMax = Double.max(HeightMax, y+size*2);
        Circle circle = new Circle(size);
        circle.setFill(color);
        circle.relocate(x, y);
        circle.setStrokeWidth(StrokeSize);
        circle.setStroke(StrokeColor);
        circle.relocate(x, y);
        NodeAddAndDraw(circle);
        return circle;
    }
    
    private Line DrawLine(double x1, double y1, double x2, double y2, double size, Color color) {
        WidthMax = Double.max(WidthMax,  Double.max(x1, x2));
        HeightMax = Double.max(HeightMax,  Double.max(y1, y2));
        Line line = new Line(x1, y1, x2, y2);
        line.setStrokeWidth(size);
        line.setStroke(color);
        GraphNodes.add(line);
        NodeAddAndDraw(line);
        return line;
    }

    private Arrow DrawArrow(double x1, double y1, double x2, double y2, double size, Color color) {
        WidthMax = Double.max(WidthMax,  Double.max(x1, x2));
        HeightMax = Double.max(HeightMax,  Double.max(y1, y2));
        Arrow arrow = new Arrow(x1, y1, x2, y2);
        arrow.setStrokeWidth(size);
        arrow.setStroke(color);
        GraphNodes.add(arrow);
        NodeAddAndDraw(arrow);
        return arrow;
    }
    
    /** 
     * @return Count of points
     */
    public int PointsCount() {
        return GraphCount;
    }
    
    /** 
     * @param var Node to add
     * @return Index of added node
     */
    public int AddPoint(T var) {
        GraphPointData.add(GraphCount, var);
        return GraphCount++;
    }
    
    /** 
     * @param pos Position of needed node
     * @return T Return requested node
     */
    public T GetPoint(int pos) {
        return GraphPointData.get(pos);
    }

    
    /** 
     * @param x Index of first node
     * @param y Index of second node
     * @param size Size between nodes
     */
    public void AddLink(int x, int y, int size) {
        GraphLinkData[x][y]=size;
    }

    
    /** 
     * @param x Index of first node
     * @param y Index of second node
     * @return Size between nodes
     */
    public int GetLink(int x, int y) {
        return GraphLinkData[x][y];
    }

    
    /** 
     * @param pos Node to remove
     */
    public void DelPoint(int pos) {
        GraphPointData.remove(pos);
    }

    
    /** 
     * @param x Index of first node
     * @param y Index of second node
     */
    public void DelLink(int x, int y) {
        GraphLinkData[x][y]=0;
    }

    // TODO: Implement correct build way
    public void render() {
        
    }

    public void test_gr() {
        this.ClearRender();
        DrawCircle(400, 400, 50, Color.ORANGE, 4, Color.BLUE);
        DrawCircle(150, 150, 50, Color.RED, 4, Color.BLUE);
        DrawLine(200, 200, 150, 150, 20, Color.CYAN).setStrokeWidth(20);
        DrawArrow(200, 200, 300, 300, 5, Color.PURPLE);
        MouseGestures mg = new MouseGestures();
        for (Node node: GraphNodes)
            mg.makeDraggle(node);
    }

    /** 
     * Clear rendered result
     */
    public void ClearRender() {
        GraphOverlay.getChildren().clear();
        GraphNodes.clear();
    }

    /** 
     * Clear everything
     */
    public void clear() {
        this.ClearRender();
        GraphCount=0;
        GraphLinkData=new int[GraphLimit][GraphLimit];
        GraphPointData.clear();
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
