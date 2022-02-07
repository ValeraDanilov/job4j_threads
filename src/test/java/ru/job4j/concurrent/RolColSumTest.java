package ru.job4j.concurrent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class RolColSumTest {

    private int[][] arrays;

    @Before
    public void setUp() {
        this.arrays = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Before method");
    }

    @After
    public void tearDown() {
        this.arrays = null;
        System.out.println("After method");
    }

    @Test
    public void whenAddArraysThenReturnSum() {
        RolColSum.Sums[] res = {new RolColSum.Sums(6, 12), new RolColSum.Sums(15, 15), new RolColSum.Sums(24, 18)};
        assertArrayEquals(RolColSum.sum(this.arrays), res);
    }

    @Test
    public void whenAddArraysInTwoTreadThenReturnSum() throws ExecutionException, InterruptedException {
        RolColSum.Sums[] res = {new RolColSum.Sums(6, 12), new RolColSum.Sums(15, 15), new RolColSum.Sums(24, 18)};
        assertArrayEquals(RolColSum.asyncSum(this.arrays), res);
    }
}
