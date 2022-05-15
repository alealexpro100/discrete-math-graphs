package com.example.demo2;

import java.util.Arrays;
import java.util.List;

public interface Tasks {
    //0
    List<Integer> getVertexPower(Object graph, int n);
    boolean checkEuler(Object graph, int n);
    boolean checkHalfEuler(Object graph, int n);
    boolean checkBipartite(Object graph, int n);
    boolean checkFullBipartite(Object graph, int n);
    //1
    List<Integer> getDFSPath(Object graph, int n);
    //2
    boolean checkDFSPath(List<Integer> path, Object graph, int n);
    //3
    List<Integer> getBFSPath(Object graph, int n);
    //4
    boolean checkBFSPath(List<Integer> path, Object graph, int n);
    //5
    int getCntConnectedComponents(Object path, int n);
    //6
    boolean checkCntConnectedComponents(Object path, int cnt, int n);
    //7
    Object getSpanningTree(Object graph, int n);
    //8
    List<Integer> getDistFrom(Object graph, int v, int n);
    //9
    List<List<Integer>> getShortestPathMatrix(Object graph, int n);
    //10
    List<Integer> encodeTree(Object graph, int n);
    //11
    Object decodeTree(List<Integer> path, int n);
    //12
    List<Integer> paintGraph(Object graph, int n);
}
