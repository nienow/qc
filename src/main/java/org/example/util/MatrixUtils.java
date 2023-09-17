package org.example.util;

import org.redfx.strange.Complex;

public class MatrixUtils {

    public static Complex[][] identity(int n) {
        Complex[][] identity = new Complex[n][n];

        for (int i = 0; i < n; i++) {
            identity[i][i] = Complex.ONE;
        }

        return identity;
    }

//    public static Complex[][] inverse() {
//        Complex[][] matrix = new Complex[4][4];
//        matrix[0][2] = Complex.ONE;
//        matrix[1][3] = Complex.ONE;
//        matrix[2][0] = Complex.ONE;
//        matrix[3][1] = Complex.ONE;
//        return matrix;
//    }

    public static Complex[][] cnotMatrix(int n) {
        int half = n / 2;
        Complex[][] matrix = new Complex[n][n];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                matrix[i][i] = Complex.ONE;
            } else {
                if (i < half) {
                    matrix[i][i + half] = Complex.ONE;
                } else {
                    matrix[i][i - half] = Complex.ONE;
                }
            }
        }
        return matrix;
    }

//    public static Complex[][] cnotInverse() {
//        Complex[][] matrix = new Complex[4][4];
//        matrix[0][2] = Complex.ONE;
//        matrix[1][1] = Complex.ONE;
//        matrix[2][0] = Complex.ONE;
//        matrix[3][3] = Complex.ONE;
//        return matrix;
//    }

    public static double[][] multiply(double[][] a, double[][] b) {
        int aRows = a.length;
        int aColumns = a[0].length;
        int bRows = b.length;
        int bColumns = b[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("a:Rows: " + aColumns + " did not match b:Columns " + bRows + ".");
        }

        double[][] resultant = new double[aRows][bColumns];

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                resultant[i][j] = 0.00000;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    resultant[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return resultant;
    }

    public static double[][] tensorProduct(double[][] a, double[][] b) {
        int aRows = a.length;
        int aColumns = a[0].length;
        int bRows = b.length;
        int bColumns = b[0].length;

        double[][] resultant = new double[aRows * bRows][aColumns * bColumns];

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < aColumns; j++) {
                for (int k = 0; k < bRows; k++) {
                    for (int l = 0; l < bColumns; l++) {
                        resultant[i * bRows + k][j * bColumns + l] = a[i][j] * b[k][l];
                    }
                }
            }
        }

        return resultant;
    }
}
