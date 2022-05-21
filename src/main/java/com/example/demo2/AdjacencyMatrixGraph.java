package com.example.demo2;

import java.util.List;

public class AdjacencyMatrixGraph implements Tasks {
    GraphPerformancesSingleton singleton;
    ListGraph listConvert;
    {
        singleton = GraphPerformancesSingleton.getInstance();
        listConvert = new ListGraph();
    }
    @Override
    public List<Integer> getVertexPower(Object graph, int n) {
        List<List<Integer>> list = singleton.adjacencyMatrixToList(graph, n);
        return listConvert.getVertexPower(list, n);

    }

    @Override
    public boolean checkEuler(Object graph, int n) {
        List<List<Integer>> list = singleton.adjacencyMatrixToList(graph,  n);
        return listConvert.checkEuler(list, n);

    }

    @Override
    public boolean checkHalfEuler(Object graph, int n) {
        List<List<Integer>> list = singleton.adjacencyMatrixToList(graph,  n);
        return listConvert.checkHalfEuler(list, n);

    }

    @Override
    public boolean checkBipartite(Object graph, int n) {
        List<List<Integer>> list = singleton.adjacencyMatrixToList(graph, n);
        return listConvert.checkBipartite(list, n);

    }

    @Override
    public boolean checkFullBipartite(Object graph, int n) {
        List<List<Integer>> list = singleton.adjacencyMatrixToList(graph, n);
        return listConvert.checkFullBipartite(list, n);

    }

    @Override
    public List<Integer> getDFSPath(Object graph, int n) {
        List<List<Integer>> list = singleton.adjacencyMatrixToList(graph,  n);
        return listConvert.getDFSPath(list, n);

    }

    @Override
    public boolean checkDFSPath(List<Integer> path, Object graph, int n) {
        List<List<Integer>> list = singleton.adjacencyMatrixToList(graph, n);
        return listConvert.checkDFSPath(path, list, n);
    }

    @Override
    public List<Integer> getBFSPath(Object graph, int n) {
        List<List<Integer>> list = singleton.adjacencyMatrixToList(graph, n);
        return listConvert.getBFSPath(list, n);
    }

    @Override
    public boolean checkBFSPath(List<Integer> path, Object graph, int n) {
        List<List<Integer>> list = singleton.adjacencyMatrixToList(graph, n);
        return listConvert.checkBFSPath(path, list, n);
    }

    @Override
    public int getCntConnectedComponents(Object path, int n) {
        return listConvert.getCntConnectedComponents(path, n);
    }

    @Override
    public boolean checkCntConnectedComponents(Object path, int cnt, int n) {
        return listConvert.checkCntConnectedComponents(path, cnt, n);
    }

    @Override
    public Object getSpanningTree(Object graph, int n) {
        List<List<To>> list = singleton.adjacencyMatrixToWeightedList(graph, n);
        return listConvert.getSpanningTree(list, n);
    }

    @Override
    public List<Integer> getDistFrom(Object graph, int v, int n) {
        List<List<To>> list = singleton.adjacencyMatrixToWeightedList(graph, n);
        return listConvert.getDistFrom(list, v, n);
    }

    @Override
    public List<List<Integer>> getShortestPathMatrix(Object graph, int n) {
        List<List<To>> list = singleton.adjacencyMatrixToWeightedList(graph, n);
        return listConvert.getShortestPathMatrix(list, n);
    }

    @Override
    public List<Integer> encodeTree(Object graph, int n) {
        List<List<Integer>> list = singleton.adjacencyMatrixToList(graph, n);
        return listConvert.encodeTree(list, n);
    }

    @Override
    public Object decodeTree(List<Integer> path, int n) {
        return listConvert.decodeTree(path, n);
    }

    @Override
    public List<Integer> paintGraph(Object graph, int n) {
        List<List<Integer>> list = singleton.adjacencyMatrixToList(graph, n);
        return listConvert.paintGraph(list, n);
    }
}
