package ru.job4j.concurrent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Before
    public void setUp() {
        System.out.println("Before method");
    }

    @After
    public void tearDown() {
        System.out.println("After method");
    }

    @Test
    public void addAndReturnNumbers() throws InterruptedException {
        SimpleBlockingQueue<Integer> simple = new SimpleBlockingQueue<>(5);
        List<Integer> list = new ArrayList<>();
        Thread first = new Thread(() -> {
            for (int index = 1; index <= 10; index++) {
                try {
                    simple.offer(index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread second = new Thread(() -> {
            for (int index = 1; index <= 10; index++) {
                try {
                    list.add(simple.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(list, is(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
    }
}
