package com.example.demo2;

import java.util.List;

public class IncidenceMatrixGraph implements Tasks{
    GraphPerformancesSingleton singleton;
    {
        singleton = GraphPerformancesSingleton.getInstance();
    }
    @Override
    public List<Integer> getVertexPower(Object graph, int n) {
        return null;
    }

    @Override
    public boolean checkEuler(Object graph, int n) {
        return false;
    }

    @Override
    public boolean checkHalfEuler(Object graph, int n) {
        return false;
    }

    @Override
    public boolean checkBipartite(Object graph, int n) {
        return false;
    }

    @Override
    public boolean checkFullBipartite(Object graph, int n) {
        return false;
    }

    @Override
    public List<Integer> getDFSPath(Object graph, int n) {
        return null;
    }

    @Override
    public boolean checkDFSPath(List<Integer> path, Object graph, int n) {
        return false;
    }

    @Override
    public List<Integer> getBFSPath(Object graph, int n) {
        return null;
    }

    @Override
    public boolean checkBFSPath(List<Integer>path, Object graph, int n) {
        return false;
    }

    @Override
    public int getCntConnectedComponents(Object path, int n) {
        return 0;
    }

    @Override
    public boolean checkCntConnectedComponents(Object path, int cnt, int n) {
        return false;
    }

    @Override
    public Object getSpanningTree(Object graph, int n) {
        return null;
    }

    @Override
    public List<Integer> getDistFrom(Object graph, int v, int n) {
        return null;
    }

    @Override
    public List<List<Integer>> getShortestPathMatrix(Object graph, int n) {
        return null;
    }

    @Override
    public List<Integer> encodeTree(Object graph, int n) {
        return null;
    }

    @Override
    public Object decodeTree(List<Integer> path, int n) {
        return null;
    }

    @Override
    public List<Integer> paintGraph(Object graph, int n) {
        return null;
    }
}
