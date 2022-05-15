package com.example.demo2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListGraph implements Tasks{
    GraphPerformancesSingleton singleton;
    {
        singleton = GraphPerformancesSingleton.getInstance();
    }
    void dfs(List<List<Integer>> list, int v,  boolean[] visited, List<Integer> path) {
        visited[v] = true;
        path.add(v);
        for (var to : list.get(v)) {
            if (!visited[to])
                dfs(list, to, visited, path);
        }
    }

    void bfs(List<List<Integer>> list, int v, boolean[] visited, List<Integer> path) {
        visited[v] = true;
        path.add(v);
        List<Integer> Q = new LinkedList<>();
        Q.add(v);
        while (Q.size() > 0) {
            int u = Q.get(0);
            Q.remove(0);
            for (var l : list.get(u)) {
                if (!visited[l]) {
                    visited[l] = true;
                    path.add(l);
                    Q.add(l);
                }
            }
        }

    }
    @Override
    public List<Integer> getVertexPower(Object graph, int n) {
        List<List<Integer>> list = singleton.getList(graph);

        List<Integer> powers = new ArrayList<>();
        for (var l : list){
            powers.add(l.size());
        }
        return powers;
    }

    @Override
    public boolean checkEuler(Object graph, int n) {
        if (getCntConnectedComponents(graph, n) == 1)
            for (var v : getVertexPower(graph, n))
                if (v % 2 == 1)
                    return false;

        return true;
    }

    @Override
    public boolean checkHalfEuler(Object graph, int n) {
        int cntOdd = 0;
        if (getCntConnectedComponents(graph, n) == 1)
            for (var v : getVertexPower(graph, n))
                if (v % 2 == 1)
                    cntOdd++;

        return cntOdd <= 2;
    }

    @Override
    public boolean checkBipartite(Object graph, int n) {
        List<List<Integer>> list = singleton.getList(graph);
        return false;
    }

    @Override
    public boolean checkFullBipartite(Object graph, int n) {
        List<List<Integer>> list = singleton.getList(graph);
        return false;
    }

    @Override
    public List<Integer> getDFSPath(Object graph, int n) {
        List<List<Integer>> list = singleton.getList(graph);
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; ++i) {
            dfs(list, i, visited, path);
        }
        return path;
    }

    @Override
    public boolean checkDFSPath(List<Integer> path, Object graph, int n) {
        List<List<Integer>> list = singleton.getList(graph);
        return false;
    }

    @Override
    public List<Integer> getBFSPath(Object graph, int n) {
        List<List<Integer>> list = singleton.getList(graph);
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; ++i) {
            if (!visited[i])
                bfs(list, i, visited, path);
        }

        return path;
    }

    @Override
    public boolean checkBFSPath(List<Integer>path, Object graph, int n) {
        List<List<Integer>> list = singleton.getList(graph);
        return false;
    }

    @Override
    public int getCntConnectedComponents(Object path, int n) {
        List<List<Integer>> list = singleton.getList(path);
        List<Integer> path2 = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int cnt = 0;

        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                cnt++;
                dfs(list, i, visited, path2);
            }

        }
        return cnt;
    }

    @Override
    public boolean checkCntConnectedComponents(Object path, int cnt, int n) {
        return cnt == getCntConnectedComponents(path, n);

    }

    @Override
    public Object getSpanningTree(Object graph, int n) {
        List<List<Integer>> list = singleton.getList(graph);
        return null;
    }

    @Override
    public List<Integer> getDistFrom(Object graph, int v, int n) {
        List<List<Integer>> list = singleton.getList(graph);
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
