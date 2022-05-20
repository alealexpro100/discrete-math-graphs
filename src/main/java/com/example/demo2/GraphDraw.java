package com.example.demo2;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * The GraphDraw class provides handy way to render graphs using standard library
 */
public class GraphDraw<T> {
    //Graph data
    private int[][] LinkData;
    private ArrayList<T> PointData;
    private int Count;
    private int Limit;
    final private double margin=20;
    final private double HeightMax=400;
    final private double WidthMax=300;
    private double Height=HeightMax-margin;
    private double Width=WidthMax-margin;

    //Data where to draw
    private Pane GraphOverlay;

    //Data with drawn objects
    private Node[][] LinkNodes;
    private Text[][] LinkTextNodes;
    private Circle[] CircleNodes;
    private Text[] CircleTextNodes;

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
        LinkTextNodes=new Text[Limit][Limit];
        CircleTextNodes=new Text[Limit];
        //Set default zero data
        Count=0;
        LinkData=new int[Limit][Limit];
        PointData=new ArrayList<T>(Limit);
    }

    /** 
     * @param Graph Graph to set
     * WARNING: NOT TESTED.
     */
    public void SetGraph(Object Graph) {
        List<List<To>> tree = (List<List<To>>)Graph;
        Count=tree.size();
        //Set graph data
        LinkData=new int[Count][Count];
        PointData=new ArrayList<T>(Count);
        for (int i=0;i<Count;i++) {
            for (int j=0;j<tree.get(i).size();j++) {
                LinkData[i][j]=tree.get(i).get(j).getTo();
            }
            PointData.add((T)tree.get(i).get(0).getWeight());
        }
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
     * @return (T) Requested node
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
        GraphOverlay.setMinSize(Width+margin, Height+margin);
    }

    private Text DrawText(double x, double y, double size, Color color, double StrokeSize, Color StrokeColor, String text_string) {
        Height = Double.max(HeightMax, x+size*2);
        Width = Double.max(WidthMax, y+size*2);
        Text text = new Text(x-size/3, y+size/3, text_string);
        text.setFill(color);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, size));
        text.setStrokeWidth(StrokeSize);
        text.setStroke(StrokeColor);
        return text;
    }
    
    private Circle DrawCircle(double x, double y, double size, Color color, double StrokeSize, Color StrokeColor) {
        Height = Double.max(HeightMax, x+size*2);
        Width = Double.max(WidthMax, y+size*2);
        Circle circle = new Circle(x, y, size);
        circle.setFill(color);
        circle.setStrokeWidth(StrokeSize);
        circle.setStroke(StrokeColor);
        return circle;
    }
    
    private Line DrawLine(double x1, double y1, double x2, double y2, double size, Color color) {
        Height = Double.max(HeightMax,  Double.max(x1, x2));
        Width = Double.max(WidthMax,  Double.max(y1, y2));
        Line line = new Line(x1, y1, x2, y2);
        line.setStrokeWidth(size);
        line.setStroke(color);
        return line;
    }

    private Arrow DrawArrow(double x1, double y1, double x2, double y2, double size, Color color) {
        Height = Double.max(HeightMax,  Double.max(x1, x2));
        Width = Double.max(WidthMax,  Double.max(y1, y2));
        Arrow arrow = new Arrow(x1, y1, x2, y2);
        arrow.setStrokeWidth(size);
        arrow.setStroke(color);
        return arrow;
    }

    private void draw() {
        for (Node[] nodes: LinkNodes)
            for (Node node: nodes)
                if (node != null) {
                    DrawNode(node);
                }
        for (Text[] nodes: LinkTextNodes)
            for (Text node: nodes)
                if (node != null) {
                    DrawNode(node);
                }
        for (Circle node: CircleNodes)
            if (node != null) {
                DrawNode(node);
            }
        for (Text node: CircleTextNodes)
            if (node != null) {
                DrawNode(node);
            }
    }

    private void RenderLink() {
        for (int i_x=0; i_x<Count; ++i_x) {
            for (int i_y=0; i_y<Count; ++i_y) {
                if (LinkData[i_x][i_y]!=0) {
                    double length_x=CircleNodes[i_x].getCenterX()-CircleNodes[i_y].getCenterX(), length_y=CircleNodes[i_x].getCenterY()-CircleNodes[i_y].getCenterY();
                    double length=Math.sqrt(Math.pow(length_x,2)+Math.pow(length_y,2));
                    double x1=CircleNodes[i_x].getCenterX()-(length_x)*CircleNodes[i_x].getRadius()/length,
                           y1=CircleNodes[i_x].getCenterY()-(length_y)*CircleNodes[i_x].getRadius()/length,
                           x2=CircleNodes[i_y].getCenterX()+(length_x)*CircleNodes[i_y].getRadius()/length,
                           y2=CircleNodes[i_y].getCenterY()+(length_y)*CircleNodes[i_y].getRadius()/length;
                    LinkTextNodes[i_x][i_y]=DrawText((x1+x2)/2, (y1+y2)/2, 30, Color.BLACK, 2, Color.WHITE, Integer.toString(LinkData[i_x][i_y]));
                    LinkNodes[i_x][i_y]=DrawArrow(x1, y1, x2, y2, 4, Color.BLACK);
                }
            }
        }
    }

    /** 
     * Render graph in stupid way using Random.
     * It it suitable for ANY graph.
     */
    public void RenderStupid() {
        ClearRender();
        Random rand = new Random();
        double DiffXMin=(WidthMax+margin)/Count,  DiffYMin=(HeightMax+margin)/Count;
        double MinDist=Math.sqrt(Math.pow(DiffXMin, 2)+Math.pow(DiffYMin, 2));
        for (int i=0; i<Count; i++) {
            double new_x=margin+rand.nextDouble()*(WidthMax-2*margin),
                new_y=margin+rand.nextDouble()*(HeightMax-2*margin);
            for (int j=0; j<i; j++) {
                while (Math.abs(new_x+CircleNodes[j].getCenterX())<DiffXMin || Math.abs(new_y+CircleNodes[j].getCenterY())<DiffYMin
                 || Math.sqrt(Math.pow(new_x-CircleNodes[j].getCenterX(), 2)+Math.pow(new_y-CircleNodes[j].getCenterY(), 2))<MinDist) {
                    new_x=margin+rand.nextDouble()*(WidthMax-2*margin);
                    new_y=margin+rand.nextDouble()*(HeightMax-2*margin);
                }
            }
            CircleTextNodes[i]=DrawText(new_x, new_y, 20, Color.BLACK, 1, Color.WHITE, Integer.toString(i));
            CircleNodes[i]=DrawCircle(new_x, new_y, 15, Color.ORANGE, 0.5, Color.BLUE);
        }
        RenderLink();
        draw();
    }

    /** 
     * Render graph as a tree
     * @param Id Point render from
     * @throws Exception
     */
    public void RenderTree(int Id) throws Exception {
        if (Id>=Count)
            throw new Exception("GraphDraw: index out bounds! Count is "+Count+". Requested id is "+Id+".");
        throw new Exception("GraphDraw: RenderTree is not implemented yet!");
        //double div_x=(WidthMax+margin)/2,  div_y=(HeightMax+margin)/2;
    }

    /** 
     * Clear rendered result
     */
    public void ClearRender() {
        GraphOverlay.getChildren().clear();
        LinkNodes = new Node[Limit][Limit];
        CircleNodes = new Circle[Limit];
    }

    /** 
     * @param Id Position of needed circle node
     * @return Return requested circle
     * @throws Exception
     */
    public Circle GetNodeCircle(int Id) throws Exception {
        if (Id>=Count)
            throw new Exception("GraphDraw: index out bounds! Count is "+Count+". Requested id is "+Id+".");
        return CircleNodes[Id];
    }

    /** 
     * @param x Index of first node
     * @param y Index of second node
     * @return Node object (Arrow or Line)
     * @throws Exception
     */
    public Node GetNodeLink(int x, int y) throws Exception {
        if (x>=Count)
            throw new Exception("GraphDraw: index (x) out bounds! Count is "+Count+". Requested x (Node) is "+x+".");
        if (y>=Count)
            throw new Exception("GraphDraw: index (y) out bounds! Count is "+Count+". Requested y (Node) is "+y+".");
        return LinkNodes[x][y];
    }

    /** 
     * @param Id Position of needed text circle node
     * @return Return requested text
     * @throws Exception
     */
    public Text GetNodeTextCircle(int Id) throws Exception {
        if (Id>=Count)
            throw new Exception("GraphDraw: index out bounds! Count is "+Count+". Requested id is "+Id+".");
        return CircleTextNodes[Id];
    }

    /** 
     * @param x Index of first node
     * @param y Index of second node
     * @return Text object (Arrow or Line)
     * @throws Exception
     */
    public Text GetNodeTextLink(int x, int y) throws Exception {
        if (x>=Count)
            throw new Exception("GraphDraw: index (x) out bounds! Count is "+Count+". Requested x (Node) is "+x+".");
        if (y>=Count)
            throw new Exception("GraphDraw: index (y) out bounds! Count is "+Count+". Requested y (Node) is "+y+".");
        return LinkTextNodes[x][y];
    }

    /** 
     * Output contained information to console.
     * NOTE: It writes in russian language.
     * @throws Exception
     */
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

}
