package com.example.demo2;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * The GraphDraw class provides handy way to render graphs using standard library
 */
public class GraphDraw<T> {
    //Graph data
    private int[][] LinkData;
    private ArrayList<T> PointData;
    private int Count;
    private int Limit;
    private double WidthMax=500;
    private double HeightMax=500;

    //Data where to draw
    private Pane GraphOverlay;

    //Data with drawn objects
    // TODO: Keep lines and nodes in different lists and make them gettable
    private Node[][] LinkNodes;
    private Node[] CircleNodes;
    
    private void DrawNode(Node node) {
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
        //Set nodes data (keep lines and circles)
        LinkNodes=new Node[Limit][Limit];
        CircleNodes=new Node[Limit];
        //Set default zero data
        Count=0;
        Limit=limit;
        LinkData=new int[Limit][Limit];
        PointData=new ArrayList<T>(Limit);
    }
    
    private Circle DrawCircle(double x, double y, double size, Color color, double StrokeSize, Color StrokeColor) {
        WidthMax = Double.max(WidthMax, x+size*2);
        HeightMax = Double.max(HeightMax, y+size*2);
        Circle circle = new Circle(size);
        circle.setFill(color);
        circle.setStrokeWidth(StrokeSize);
        circle.setStroke(StrokeColor);
        circle.relocate(x, y);
        return circle;
    }
    
    private Line DrawLine(double x1, double y1, double x2, double y2, double size, Color color) {
        WidthMax = Double.max(WidthMax,  Double.max(x1, x2));
        HeightMax = Double.max(HeightMax,  Double.max(y1, y2));
        Line line = new Line(x1, y1, x2, y2);
        line.setStrokeWidth(size);
        line.setStroke(color);
        return line;
    }

    private Arrow DrawArrow(double x1, double y1, double x2, double y2, double size, Color color) {
        WidthMax = Double.max(WidthMax,  Double.max(x1, x2));
        HeightMax = Double.max(HeightMax,  Double.max(y1, y2));
        Arrow arrow = new Arrow(x1, y1, x2, y2);
        arrow.setStrokeWidth(size);
        arrow.setStroke(color);
        return arrow;
    }
    
    /** 
     * @return Count of points
     */
    public int PointsCount() {
        return Count;
    }
    
    /** 
     * @param var Node to add
     * @return Index of added node
     */
    public int AddPoint(T var) {
        PointData.add(Count, var);
        return Count++;
    }

    /** 
     * @param var Node to add
     * @return Index of added node
     */
    public int SetPoint(T var, int Id) {
        PointData.set(Id, var);
        return Id;
    }
    
    /** 
     * @param pos Position of needed node
     * @return T Return requested node
     */
    public T GetPoint(int pos) {
        return PointData.get(pos);
    }
    
    /** 
     * @param pos Node to remove
     */
    public void DelPoint(int pos) {
        PointData.remove(pos);
        --Count;
    }

    /** 
     * @param x Index of first node
     * @param y Index of second node
     * @param size Size between nodes
     */
    public void AddLink(int x, int y, int size) {
        LinkData[x][y]=size;
    }
    
    /** 
     * @param x Index of first node
     * @param y Index of second node
     * @return Size between nodes
     */
    public int GetLink(int x, int y) {
        return LinkData[x][y];
    }

    /** 
     * @param x Index of first node
     * @param y Index of second node
     * @param size Size between nodes
     */
    public void SetLink(int x, int y, int size) {
        LinkData[x][y]=size;
    }
    
    /** 
     * @param x Index of first node
     * @param y Index of second node
     */
    public void DelLink(int x, int y) {
        LinkData[x][y]=0;
    }

    private void RenderStupid() {
        int DivX=(int)this.WidthMax/this.Limit, DivY=(int)this.HeightMax/this.Limit;
        for (int i_x=0; i_x<this.Limit; ++i_x) {
            for (int i_y=0; i_y<this.Limit; ++i_y) {
                if (LinkData[i_x][i_y]!=0)
                    DrawArrow((i_x+1)*DivX-DivX/2, (i_y+1)*DivY-DivY/2, 300, 300, 8, Color.BLACK);
            }
        }
    }

    // TODO: Implement correct build way
    public void render() {
        this.RenderStupid();
    }

    public void TextOut() {
        System.out.printf("Таблица смежности:\n");
        for (int i_x=0; i_x<this.Limit; ++i_x) {
            for (int i_y=0; i_y<this.Limit; ++i_y)
                System.out.printf("%4s ", LinkData[i_x][i_y]);
            System.out.printf("\n");
        }
        System.out.printf("Вывод точек (их данных):\n");
        for (int i=0; i<PointsCount(); i++)
            System.out.printf("Точка %2s: %s\n", i, (GetPoint(i) != null) ? GetPoint(i).toString() : "none");
    }

    public void test_gr() {
        this.ClearRender();
        CircleNodes[0]=DrawCircle(400, 400, 50, Color.ORANGE, 4, Color.BLUE);
        CircleNodes[1]=DrawCircle(150, 150, 50, Color.RED, 4, Color.BLUE);
        DrawLine(200, 200, 150, 150, 20, Color.CYAN).setStrokeWidth(20);
        LinkNodes[0][0]=DrawArrow(200, 200, 300, 300, 10, Color.PURPLE);
        LinkNodes[2][1]=DrawArrow(200, 200, 300, 300, 2, Color.CYAN); // Самый удачный размер
        LinkNodes[1][3]=DrawArrow(200, 200, 300, 300, 5, Color.ORANGE);
        LinkNodes[0][2]=DrawArrow(200, 200, 300, 300, 7, Color.RED);
        LinkNodes[4][0]=DrawArrow(200, 200, 300, 300, 1, Color.BROWN);
        MouseGestures mg = new MouseGestures();
        for (Node node: CircleNodes)
            if (node != null) {
                mg.makeDraggle(node);
                DrawNode(node);
            }
        for (Node[] nodes: LinkNodes)
            for (Node node: nodes)
                if (node != null) {
                    mg.makeDraggle(node);
                    DrawNode(node);
                }
    }

    /** 
     * Clear rendered result
     */
    public void ClearRender() {
        GraphOverlay.getChildren().clear();
        LinkNodes = new Node[Limit][Limit];
        CircleNodes = new Node[Limit];
    }

    /** 
     * Clear everything
     */
    public void Clear() {
        this.ClearRender();
        Count=0;
        LinkData=new int[Limit][Limit];
        PointData.clear();
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
