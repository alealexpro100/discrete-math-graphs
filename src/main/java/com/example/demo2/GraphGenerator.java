package com.example.demo2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator {
    public static int[][] getRandomMatrix(int n, int origin, int bound) {
        int[][] matrix = new int[n][n];
        Random rand = new Random();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                matrix[i][j] = rand.nextInt(origin, bound);
                matrix[j][i] = matrix[i][j];
            }
        }
        return matrix;
    }
    public static int[][] getRandomConnectedMatrix(int n, int origin, int bound) {
        int[][] matrix = new int[n][n];
        ListGraph graph = new ListGraph();
        Random rand = new Random();
        List<List<Integer>> Matrix = new ArrayList<>();
        do {
            Matrix.clear();
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {

                    matrix[i][j] = rand.nextInt(origin, bound);
                    matrix[j][i] = matrix[i][j];
                }
            }
            for (int i = 0; i < n; i++) {
                Matrix.add(new ArrayList<>());
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] != 0)
                        Matrix.get(i).add(j);
                }
            }
        } while (!graph.checkCntConnectedComponents(Matrix, 1, n));
        return matrix;
    }
}
