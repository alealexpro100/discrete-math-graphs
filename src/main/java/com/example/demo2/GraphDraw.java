package com.example.demo2;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

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
    final private double WidthMax=300;
    final private double HeightMax=300;
    private double Width=WidthMax;
    private double Height=HeightMax;

    //Data where to draw
    private Pane GraphOverlay;

    //Data with drawn objects
    // TODO: Keep lines and nodes in different lists and make them gettable
    private Node[][] LinkNodes;
    private Circle[] CircleNodes;

    /** 
     * @param overlay Pane where graphs will be rendered
     * @param limit Limit of graph nodes
     */
    public GraphDraw(Pane overlay, int limit) {
        Limit=limit;
        //Set draw data
        GraphOverlay=overlay;
        //Set nodes data (keep lines and circles)
        LinkNodes=new Node[Limit][Limit];
        CircleNodes=new Circle[Limit];
        //Set default zero data
        Count=0;
        LinkData=new int[Limit][Limit];
        PointData=new ArrayList<T>(Limit);
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
     * @throws Exception
     */
    public int AddPoint(T var) throws Exception {
        if (Count+1>Limit)
            throw new Exception("GraphDraw: Limit exceeded! Limit is "+Limit+". Requested "+(Count+1)+" new size.");
        PointData.add(Count, var);
        return Count++;
    }

    /** 
     * @param var Node to add
     * @return Index of added node
     * @throws Exception
     */
    public int SetPoint(T var, int Id) throws Exception {
        if (Id>=Count)
            throw new Exception("GraphDraw: index out bounds! Count is "+Count+". Requested id is "+Id+".");
        PointData.set(Id, var);
        return Id;
    }
    
    /** 
     * @param Id Position of needed node
     * @return T Return requested node
     * @throws Exception
     */
    public T GetPoint(int Id) throws Exception {
        if (Id>=Count)
            throw new Exception("GraphDraw: index out bounds! Count is "+Count+". Requested id is "+Id+".");
        return PointData.get(Id);
    }
    
    /** 
     * @param Id Node to remove
     * @throws Exception
     */
    public void DelPoint(int Id) throws Exception {
        if (Id>=Count)
            throw new Exception("GraphDraw: index out bounds! Count is "+Count+". Requested id is "+Id+".");
        if (Count==0)
            throw new Exception("GraphDraw: nothing to delete! Count is "+Count+". Requested id is "+Id+".");
        PointData.remove(Id);
        --Count;
    }

    /** 
     * @param x Index of first node
     * @param y Index of second node
     * @param size Size between nodes
     * @throws Exception
     */
    public void AddLink(int x, int y, int size) throws Exception {
        if (x>=Count)
            throw new Exception("GraphDraw: index (x) out bounds! Count is "+Count+". Requested x is "+x+".");
        if (y>=Count)
            throw new Exception("GraphDraw: index (y) out bounds! Count is "+Count+". Requested y is "+y+".");
        LinkData[x][y]=size;
    }
    
    /** 
     * @param x Index of first node
     * @param y Index of second node
     * @return Size between nodes
     * @throws Exception
     */
    public int GetLink(int x, int y) throws Exception {
        if (x>=Count)
            throw new Exception("GraphDraw: index (x) out bounds! Count is "+Count+". Requested x is "+x+".");
        if (y>=Count)
            throw new Exception("GraphDraw: index (y) out bounds! Count is "+Count+". Requested y is "+y+".");
        return LinkData[x][y];
    }

    /** 
     * @param x Index of first node
     * @param y Index of second node
     * @param size Size between nodes
     * @throws Exception
     */
    public void SetLink(int x, int y, int size) throws Exception {
        if (x>=Count)
            throw new Exception("GraphDraw: index (x) out bounds! Count is "+Count+". Requested x is "+x+".");
        if (y>=Count)
            throw new Exception("GraphDraw: index (y) out bounds! Count is "+Count+". Requested y is "+y+".");
        LinkData[x][y]=size;
    }
    
    /** 
     * @param x Index of first node
     * @param y Index of second node
     * @throws Exception
     */
    public void DelLink(int x, int y) throws Exception {
        if (x>=Count)
            throw new Exception("GraphDraw: index (x) out bounds! Count is "+Count+". Requested x is "+x+".");
        if (y>=Count)
            throw new Exception("GraphDraw: index (y) out bounds! Count is "+Count+". Requested y is "+y+".");
        LinkData[x][y]=0;
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

    /*
        Draw part. There are all draw functions.
    */

    private void DrawNode(Node node) {
        GraphOverlay.getChildren().add(node);
        GraphOverlay.setMinSize(Width, Height);
    }
    
    private Circle DrawCircle(double x, double y, double size, Color color, double StrokeSize, Color StrokeColor) {
        Width = Double.max(WidthMax, x+size*2);
        Height = Double.max(HeightMax, y+size*2);
        Circle circle = new Circle(x, y, size);
        circle.setFill(color);
        circle.setStrokeWidth(StrokeSize);
        circle.setStroke(StrokeColor);
        return circle;
    }
    
    private Line DrawLine(double x1, double y1, double x2, double y2, double size, Color color) {
        Width = Double.max(WidthMax,  Double.max(x1, x2));
        Height = Double.max(HeightMax,  Double.max(y1, y2));
        Line line = new Line(x1, y1, x2, y2);
        line.setStrokeWidth(size);
        line.setStroke(color);
        return line;
    }

    private Arrow DrawArrow(double x1, double y1, double x2, double y2, double size, Color color) {
        Width = Double.max(WidthMax,  Double.max(x1, x2));
        Height = Double.max(HeightMax,  Double.max(y1, y2));
        Arrow arrow = new Arrow(x1, y1, x2, y2);
        arrow.setStrokeWidth(size);
        arrow.setStroke(color);
        return arrow;
    }

    private void draw() {
        MouseGestures mg = new MouseGestures();
        for (Node[] nodes: LinkNodes)
            for (Node node: nodes)
                if (node != null) {
                    mg.makeDraggle(node);
                    DrawNode(node);
                }
        for (Node node: CircleNodes)
            if (node != null) {
                mg.makeDraggle(node);
                DrawNode(node);
            }
    }

    private void RenderStupid() {
        Random rand = new Random();
        for (int i=0; i<Count; i++) {
            double new_x=rand.nextDouble()*HeightMax, new_y=rand.nextDouble()*WidthMax;
            CircleNodes[i]=DrawCircle(new_x, new_y, 25, Color.ORANGE, 0.5, Color.BLUE);
        }
        for (int i_x=0; i_x<Count; ++i_x) {
            for (int i_y=0; i_y<Count; ++i_y) {
                if (LinkData[i_x][i_y]!=0) {
                    LinkNodes[i_x][i_y]=DrawArrow(CircleNodes[i_x].getCenterX(), CircleNodes[i_x].getCenterY(), CircleNodes[i_y].getCenterX(), CircleNodes[i_y].getCenterY(), 8, Color.BLACK);
                }
            }
        }
    }

    // TODO: Implement correct build way
    public void render() {
        ClearRender();
        this.RenderStupid();
        draw();
    }

    public void TextOut() throws Exception {
        // Раскомментируйте что работает. Должно исправить вывод кириллицы в консоль.
        PrintStream printStream = new PrintStream(System.out, true, "UTF-8");
        //PrintStream printStream = new PrintStream(System.out, true, "Windows-1251");
        //PrintStream printStream = new PrintStream(System.out, true, "cp866");
        printStream.printf("Количество вершин: %s.\n", Count);
        printStream.printf("Таблица смежности:\n");
        for (int i_x=0; i_x<Count; ++i_x) {
            printStream.printf("%s | ", i_x);
            for (int i_y=0; i_y<Count; ++i_y)
                printStream.printf("%4s ", LinkData[i_x][i_y]);
            printStream.printf("\n");
        }
        printStream.printf("Вывод точек (их данных):\n");
        for (int i=0; i<PointsCount(); i++)
            printStream.printf("Точка %2s: %s\n", i, (GetPoint(i) != null) ? GetPoint(i).toString() : "none");
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
        draw();
    }

    /** 
     * Clear rendered result
     */
    public void ClearRender() {
        GraphOverlay.getChildren().clear();
        LinkNodes = new Node[Limit][Limit];
        CircleNodes = new Circle[Limit];
    }

    public static class MouseGestures {

        double orgSceneX, orgSceneY;
        double orgTranslateX, orgTranslateY;
    
        public void makeDraggle(Node node) {
            node.setOnMousePressed(OnMousePressedEventHandler);
            node.setOnMouseDragged(OnMouseDraggedEventHandler);
        }
    
        EventHandler<MouseEvent> OnMousePressedEventHandler = new EventHandler<MouseEvent>() {
    
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
    
        EventHandler<MouseEvent> OnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
    
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
