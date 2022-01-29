package ru.job4j.concurrent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CASCountTest {

    private CASCount count;

    @Before
    public void setUp() {
        this.count = new CASCount();
        System.out.println("Before method");
    }

    @After
    public void tearDown() {
        this.count = null;
        System.out.println("After method");
    }

    @Test
    public void whenTwoThreadsIncrementElementThenReturnValue() throws InterruptedException {
        Thread first = new Thread(() -> {
            for (int index = 0; index < 10; index++) {
                this.count.increment();
            }
        });
        Thread second = new Thread(() -> {
            for (int index = 0; index < 10; index++) {
                this.count.increment();
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(this.count.get(), is(20));
    }
}
