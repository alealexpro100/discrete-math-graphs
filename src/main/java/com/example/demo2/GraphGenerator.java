package com.example.demo2;

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
}
