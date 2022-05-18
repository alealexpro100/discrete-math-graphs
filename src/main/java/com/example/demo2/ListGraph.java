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
        List<List<Integer>> list = singleton.getList(graph);
        List<Integer> ans = new ArrayList<>();
        for (int k = 0; k < n - 2; ++k) {
            for (int v = 0; v < n; ++v) {
                if (list.get(v).size() == 1) {
                    int prev = list.get(v).get(0);
                    ans.add(prev);
                    list.get(v).remove(Integer.valueOf(prev));
                    list.get(prev).remove(Integer.valueOf(v));


                }
            }
        }
        return ans;
    }

    @Override
    public Object decodeTree(List<Integer> prufer, int n) {
        List<List<Integer>> list = new ArrayList<>(Collections.nCopies(n, new ArrayList<>()));
        int vertices = n + 2;
        int[] vertex_set = new int[vertices];

        // Initialize the array of vertices
        for (int i = 0; i < vertices; i++)
            vertex_set[i] = 0;

        // Number of occurrences of vertex in code
        for (int i = 0; i < vertices - 2; i++)
            vertex_set[prufer.get(i) - 1] += 1;

        System.out.print("\nThe edge set E(G) is :\n");

        // Find the smallest label not present in
        // prufer[].
        int j = 0;
        for (int i = 0; i < vertices - 2; i++) {
            for (j = 0; j < vertices; j++) {
                // If j+1 is not present in prufer set
                if (vertex_set[j] == 0) {
                    // Remove from Prufer set and print
                    // pair.
                    vertex_set[j] = -1;
                    list.get(j + 1).add(i);
                    list.get(i).add(j + 1);
                    /*System.out.print("(" + (j + 1) + ", "
                            + prufer.get(i) + ") ");*/

                    vertex_set[prufer.get(i) - 1]--;

                    break;
                }
            }
        }

        j = 0;
        int add1 = -1, add2 = -1;
        // For the last element
        for (int i = 0; i < vertices; i++) {
            if (vertex_set[i] == 0 && j == 0) {
                add1 = i + 1;
                j++;
            }
            else if (vertex_set[i] == 0 && j == 1)
                add2 = i + 1;
        }
        if (add1 != -1 && add2 != -1) {
            list.get(add1).add(add2);
            list.get(add2).add(add1);
        }
        return list;
    }

    @Override
    public List<Integer> paintGraph(Object graph, int n) {
        List<List<Integer>> adj = singleton.getList(graph);
        Integer[] result = new Integer[n];

        // Initialize all vertices as unassigned
        Arrays.fill(result, -1);

        // Assign the first color to first vertex
        result[0]  = 0;

        // A temporary array to store the available colors. False
        // value of available[cr] would mean that the color cr is
        // assigned to one of its adjacent vertices
        boolean[] available = new boolean[n];

        // Initially, all colors are available
        Arrays.fill(available, true);

        // Assign colors to remaining V-1 vertices
        for (int u = 1; u < n; u++)
        {
            // Process all adjacent vertices and flag their colors
            // as unavailable
            for (int i : adj.get(u)) {
                if (result[i] != -1)
                    available[result[i]] = false;
            }

            // Find the first available color
            int cr;
            for (cr = 0; cr < n; cr++){
                if (available[cr])
                    break;
            }

            result[u] = cr; // Assign the found color

            // Reset the values back to true for the next iteration
            Arrays.fill(available, true);
        }

        // print the result
        return Arrays.asList(result);
    }
}
