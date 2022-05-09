package com.example.demo2;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GraphDraw {
    private int[][] GraphLinkData;
    private int[] GraphPointData;
    private int GraphCount;
    private int GraphLimit;
    private GraphicsContext GraphGraphicsContext;

    public GraphDraw(Canvas canvas, int limit) {
        GraphGraphicsContext=canvas.getGraphicsContext2D();
        GraphCount=0;
        GraphLimit=limit;
        GraphLinkData=new int[GraphLimit][GraphLimit];
        GraphPointData=new int[GraphLimit];
    }

    public void add_point() {
        
    }

    public void test() {
        GraphGraphicsContext.setFill(Color.BLUE);
        GraphGraphicsContext.fillRect(0,0,100,100);
    }
}
