package com.example.demo2;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GraphDraw<T> {
    private int[][] GraphLinkData;
    private ArrayList<T> GraphPointData;
    private int GraphCount;
    private int GraphLimit;
    private Canvas GraphCanvas;

    public GraphDraw(Canvas canvas, int limit) {
        GraphCanvas=canvas;
        GraphCount=0;
        GraphLimit=limit;
        GraphLinkData=new int[GraphLimit][GraphLimit];
        GraphPointData=new ArrayList<T>(GraphLimit);
    }

    public int AddPoint(T var) {
        GraphPointData.add(GraphCount, var);;
        return GraphCount++;
    }

    public void AddLink(int IdX, int IdY, int LinkSize) {
        GraphLinkData[IdX][IdY]=LinkSize;
    }

    public void test() {
        GraphicsContext gc=GraphCanvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillRect(0,0,100,100);
    }

    public void clear() {
        GraphicsContext gc=GraphCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, GraphCanvas.getWidth(), GraphCanvas.getHeight());
    }
}
