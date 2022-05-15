package com.example.demo2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GraphPerformancesSingleton {
    private static GraphPerformancesSingleton instance;
    private GraphPerformancesSingleton(){}
    public static GraphPerformancesSingleton getInstance() {
        if (instance == null) {
            instance = new GraphPerformancesSingleton();
        }
        return instance;
    }
    public List<List<Integer>> adjacencyMatrixToList(Object mt, int n) {
        List<List<Integer>> list = new ArrayList<>(Collections.nCopies(n, new ArrayList<>()));
        var matrix = (List<List<Integer>>)mt;
        for (int i = 0; i < matrix.size(); ++i) {
            for (int j = 0; j < matrix.get(i).size(); ++i)
                if (matrix.get(i).get(j) == 1)
                    list.get(i).add(j);

        }
        return list;
    }
    public List<List<Integer>> incidenceMatrixToList(Object mt, int n) {
        List<List<Integer>> list = new ArrayList<>(Collections.nCopies(n, new ArrayList<>()));
        var matrix = (List<List<Integer>>)mt;
        if (matrix.size() == 0)
            return list;
        for (int j = 0; j < matrix.get(0).size(); ++j){
            int from = -1;
            int to = -2;
            for (int i = 0; i < matrix.size(); ++i) {
                if (matrix.get(i).get(j) == 1) {
                    if (from == -1)
                        from = i ;
                    else
                        to = i ;
                }
                if (matrix.get(i).get(j) == -1) {
                    to = j ;
                }
            }
            list.get(from).add(to);
            list.get(to).add(from);
        }
         return list;
    }
    public List<List<Integer>> getList(Object mt){
        return (List<List<Integer>>)mt;
    }



}
