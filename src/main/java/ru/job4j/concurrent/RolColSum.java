package ru.job4j.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {

        private int rowSum;
        private int colSum;

        public Sums() {
        }

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }

        @Override
        public String toString() {
            return "Sums{" + "rowSum=" + rowSum + ", colSum=" + colSum + '}';
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sum = new Sums[matrix.length];
        for (int index = 0; index < matrix.length; index++) {
            Sums sums = sumElement(matrix, index);
            sum[index] = sums;
        }
        return sum;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> taskList = new HashMap<>();
        for (int index = 0; index < matrix.length; index++) {
            taskList.put(index, getSum(matrix, index));
        }
        for (Integer key : taskList.keySet()) {
            sums[key] = taskList.get(key).get();
        }
        return sums;
    }

    private static CompletableFuture<Sums> getSum(int[][] matrix, int start) {
        return CompletableFuture.supplyAsync(() -> sumElement(matrix, start));
    }

    private static Sums sumElement(int[][] matrix, int start) {
        Sums sums = new Sums();
        for (int index = start; index < start + 1; index++) {
            for (int value = 0; value < matrix.length; value++) {
                sums.rowSum += matrix[index][value];
                sums.colSum += matrix[value][index];
            }
        }
        return sums;
    }
}
