package com.example.demo2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TaskTests {
    private ListGraph test;
    private List<List<Integer>> graph1;
    private List<List<Integer>> euler;
    private List<List<Integer>> halfEuler;
    private List<List<Integer>> fullBipart;
    private List<List<Integer>> djikstra;
    private List<List<Integer>> floyd;
    private List<List<Integer>> encodeTree;
    @Before
    public void setup() {
        test = new ListGraph();
        graph1= new ArrayList<>();
        euler = new ArrayList<>();
        halfEuler = new ArrayList<>();
        fullBipart = new ArrayList<>();
        djikstra = new ArrayList<>();
        floyd = new ArrayList<>();
        encodeTree = new ArrayList<>();

        graph1.add(Arrays.asList(1, 2));
        graph1.add(Arrays.asList(0, 2));
        graph1.add(Arrays.asList(0, 1, 4));
        graph1.add(List.of());
        graph1.add(Arrays.asList(5, 6));
        graph1.add(List.of(4));
        graph1.add(List.of(4));

        euler.add(List.of(1, 2));
        euler.add(List.of(0, 2));
        euler.add(List.of(0, 1));


        halfEuler.add(List.of(1));
        halfEuler.add(List.of(0, 2));
        halfEuler.add(List.of(1, 3));
        halfEuler.add(List.of(2));

        fullBipart.add(List.of(1));
        fullBipart.add(List.of(0, 2));
        fullBipart.add(List.of(1));

        djikstra.add(List.of(0, 10, 30 , 50, 10));

        int INF = 1_000_000_000;
        djikstra.add(List.of(INF, 0, INF, INF, INF));
        djikstra.add(List.of(INF, INF, 0, INF, 10));
        djikstra.add(List.of(INF, 40, 20, 0, INF));
        djikstra.add(List.of(10, INF, 10, 30, 0));


        floyd.add(List.of(0, 28, 21, 59, 12, 27));
        floyd.add(List.of(7, 0, 24, INF, 21, 9));
        floyd.add(List.of(9, 32, 0, 13, 11, INF));
        floyd.add(List.of(8, INF, 5, 0, 16, INF));
        floyd.add(List.of(14, 13, 15, 10, 0, 22));
        floyd.add(List.of(15, 18, INF, INF, 6, 0));

        encodeTree.add(List.of(0, 2, 3));
        encodeTree.add(List.of(0, 4, 5));
        encodeTree.add(List.of(0, 9));
        encodeTree.add(List.of(0));
        encodeTree.add(List.of(1, 6));
        encodeTree.add(List.of(1, 7, 8));
        encodeTree.add(List.of(4));
        encodeTree.add(List.of(5));
        encodeTree.add(List.of(5));
        encodeTree.add(List.of(2));



    }
    //Принимает в нумерации с 0, возвращает в нумерации с 0
    @Test
    public void testDecodeTree() {

        List<Integer> lt = Arrays.asList(0, 4, 1, 5, 5, 1 ,0 ,2);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(3, 1, 2));
        expected.add(Arrays.asList(4, 5, 0));
        expected.add(Arrays.asList(0, 9));
        expected.add(List.of(0));
        expected.add(Arrays.asList(6, 1));
        expected.add(Arrays.asList(7, 8, 1));
        expected.add(List.of(4));
        expected.add(List.of(5));
        expected.add(List.of(5));
        expected.add(List.of(2));
        var exp = (List<List<Integer>>) test.decodeTree(lt, 8);
        Assert.assertEquals(expected, exp);
    }
    @Test
    public void testCntConnectedComponents() {
        int cnt = test.getCntConnectedComponents(graph1, 7);
        Assert.assertEquals(2, cnt);

    }
    @Test
    public void checkDFSPath() {
        var ans = test.getDFSPath(graph1, 7);
        for (var c : ans)
            System.out.println(c);
        Assert.assertTrue(test.checkDFSPath(ans, graph1, 7));
    }
    @Test
    public void checkBFSPath() {
        var ans = test.getBFSPath(graph1, 7);
        for (var c : ans)
            System.out.println(c);
        Assert.assertTrue(test.checkBFSPath(ans, graph1, 7));
    }
    @Test
    public void checkEuler() {
        Assert.assertFalse(test.checkEuler(graph1, 7));
        Assert.assertTrue(test.checkEuler(euler, 3));
        Assert.assertFalse(test.checkEuler(halfEuler, 4));
    }

    @Test
    public void checkHalfEuler() {
        Assert.assertTrue(test.checkHalfEuler(halfEuler, 4));
        Assert.assertFalse(test.checkHalfEuler(graph1, 7));
    }
    @Test
    public void checkBipartite() {
        Assert.assertFalse(test.checkBipartite(graph1, 7));
        Assert.assertTrue(test.checkBipartite(halfEuler, 4));
    }

    @Test
    public void checkFullBipartite() {
        Assert.assertFalse(test.checkFullBipartite(halfEuler, 4));
        Assert.assertTrue(test.checkFullBipartite(fullBipart, 3));
    }

    @Test
    public void checkGetDistFrom() {
        var ans = test.getDistFrom(djikstra,0, 5 );

        for (int c : ans)
            System.out.println(c);
        Assert.assertEquals(List.of(0, 10, 20, 40, 10), ans);

    }
    @Test
    public void checkGetShortestPathMatrix() {
        //ok
        var ans = test.getShortestPathMatrix(floyd, 6);
        for (var i : ans) {
            for (var j : i)
                System.out.print(j + " ");
            System.out.println();
        }
    }
    @Test
    public void checkEncodeTree() {
        var ans = test.encodeTree(encodeTree, 10);
        for (int c : ans)
            System.out.print(c + " ");
        System.out.println();
        Assert.assertEquals(List.of(0, 4, 1, 5, 5, 1, 0, 2), ans);
    }
    @Test
    public void checkPaintGraph() {
        //ok
        var ans = test.paintGraph(graph1, 7);
        for (var c : ans)
            System.out.println(c);
    }

}
