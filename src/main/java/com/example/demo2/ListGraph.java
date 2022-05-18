package com.example.demo2;

import java.util.*;

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

    /**
     * Нахождение минимального остовного дерев
     * по алгоритму Краскала с
     * системой непересекающихся множеств с весовой эвристикой
     * @param graph Граф, для которого строится минимальное остовное дерево
     * @param n Количество вершин в графе
     * @return Граф, представляющий собой минимальное остовное дерево
     */
    @Override
    public Object getSpanningTree(Object graph, int n) {
        List<List<To>> list = singleton.adjacencyMatrixToWeightedList(graph, n);
        int[] colors = new int[n];
        int[] sizes = new int[n];
        for (int i=0;i<n;i++) {
            colors[i] = i+1;
        }
        for (int i=0;i<n;i++) {
            sizes[i] = 1;
        }
        List<Edge> edges = new ArrayList<>();
        for (int i=0;i<n;i++) {
            for (var to : list.get(i)) {
                edges.add(new Edge(i, to.getTo(), to.getWeight()));
            }
        }
        Collections.sort(edges);

        List<List<To>> SpanningTree = new ArrayList<>(n);
        for (int i=0;i<n;i++)
            SpanningTree.add(new ArrayList<>());

        for (var edge : edges) {
            if (colors[edge.getTo()] != colors[edge.getFrom()]) {
                SpanningTree.get(edge.getFrom()).add(new To(edge.getTo(), edge.getWeight()));
                SpanningTree.get(edge.getTo()).add(new To(edge.getFrom(), edge.getWeight()));
                if (sizes[edge.getTo()] < sizes[edge.getFrom()]) {
                    int oldColor = colors[edge.getTo()];
                    int size = sizes[edge.getTo()] + sizes[edge.getFrom()];
                    for (int i=0;i<n;i++) {
                        if (colors[i] == oldColor) {
                            colors[i] = colors[edge.getFrom()];
                            sizes[i] = size;
                        }
                        else if (colors[i] == colors[edge.getFrom()]) {
                            sizes[i] = size;
                        }
                    }
                }
                else {
                    int oldColor = colors[edge.getFrom()];
                    int size = sizes[edge.getFrom()] + sizes[edge.getTo()];
                    for (int i=0;i<n;i++) {
                        if (colors[i] == oldColor) {
                            colors[i] = colors[edge.getTo()];
                            sizes[i] = size;
                        }
                        else if (colors[i] == colors[edge.getTo()]) {
                            sizes[i] = size;
                        }
                    }
                }
            }
        }

        return SpanningTree;
    }

    /**
     * Нахождение кратчайших расстояний от заданной вершины до остальных
     * по Алгоритму Дейкстры с использованием приоритетной очереди
     * @param graph Граф, в котором ищутся минимальные расстояния
     * @param v Заданная вершина
     * @param n Количество вершин в графе
     * @return Список расстояний до соответствующих вершин
     */
    @Override
    public List<Integer> getDistFrom(Object graph, int v, int n) {
        List<List<To>> list = singleton.adjacencyMatrixToWeightedList(graph, n);
        List<Integer> dist = new ArrayList<>();
        for (int i=0;i<n;i++)
            dist.add(Integer.MAX_VALUE);
        PriorityQueue<To> queue = new PriorityQueue<>();

        queue.add(new To(v, 0));
        dist.set(v, 0);
        while (queue.size() > 0) {
            To cur = queue.peek();
            queue.poll();
            for (var to : list.get(cur.getTo())) {
                if (cur.getWeight() + to.getWeight() < dist.get(to.getTo())) {
                    dist.set(to.getTo(), cur.getWeight() + to.getWeight());
                    queue.add(new To(to.getTo(), cur.getWeight() + to.getWeight()));
                }
            }
        }

        return dist;
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
