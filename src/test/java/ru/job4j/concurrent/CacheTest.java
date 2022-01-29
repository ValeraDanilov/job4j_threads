package ru.job4j.concurrent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {

    private Cache cache;

    @Before
    public void setUp() {
        this.cache = new Cache();
        System.out.println("Before method");
    }

    @After
    public void tearDown() {
        this.cache = null;
        System.out.println("After method");
    }

    @Test
    public void whenAddThenDeleteElement() {
        this.cache.add(new Base(1, 1));
        this.cache.add(new Base(2, 2));
        this.cache.add(new Base(3, 3));
        this.cache.delete(new Base(1, 1));
        assertTrue(this.cache.add(new Base(1, 1)));
    }

    @Test
    public void whenAddThenDoNotDeleteElement() {
        this.cache.add(new Base(1, 1));
        this.cache.add(new Base(2, 2));
        this.cache.add(new Base(3, 3));
        this.cache.delete(new Base(4, 1));
        assertFalse(this.cache.add(new Base(1, 1)));
    }

    @Test(expected = OptimisticException.class)
    public void whenAddThenExceptionUpdateElement() {
        this.cache.add(new Base(1, 1));
        this.cache.add(new Base(2, 2));
        this.cache.add(new Base(3, 3));
        assertTrue(this.cache.update(new Base(1, 2)));
    }

    @Test
    public void whenAddThenDoNotUpdateElement() {
        this.cache.add(new Base(1, 1));
        assertFalse(this.cache.update(new Base(2, 1)));
    }

    @Test
    public void whenAddThenUpdateElementReturnFalse() {
        this.cache.add(new Base(1, 1));
        assertFalse(this.cache.update(new Base(2, 1)));
    }

    @Test
    public void whenAddThenUpdateElement() {
        Base base = new Base(1, 1);
        this.cache.add(base);
        base.setName("Base");
        assertTrue(this.cache.update(base));
    }
}
