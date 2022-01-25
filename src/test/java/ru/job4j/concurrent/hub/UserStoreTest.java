package ru.job4j.concurrent.hub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserStoreTest {

    private UserStore store;

    @Before
    public void before() {
        this.store = new UserStore();
        System.out.println("Before method");
    }

    @After
    public void after() {
        this.store = null;
        System.out.println("After method");
    }

    @Test
    public void testAddReturnTrue() {
        assertTrue(this.store.add(new User(1, 200)));
    }

    @Test
    public void testAddReturnFalse() {
        this.store.add(new User(1, 200));
        assertFalse(this.store.add(new User(1, 200)));
    }

    @Test
    public void testUpdateReturnTrue() {
        this.store.add(new User(1, 200));
        assertTrue(this.store.update(new User(1, 4000)));
    }

    @Test
    public void testUpdateReturnFalse() {
        this.store.add(new User(1, 200));
        assertFalse(this.store.update(new User(2, 4000)));
    }

    @Test
    public void testDeleteReturnTrue() {
        this.store.add(new User(1, 200));
        assertTrue(this.store.delete(new User(1, 200)));
    }

    @Test
    public void testDeleteReturnFalse() {
        this.store.add(new User(1, 200));
        assertFalse(this.store.delete(new User(2, 200)));
    }

    @Test
    public void transferReturnTrue() {
        this.store.add(new User(1, 100));
        this.store.add(new User(2, 0));
        assertTrue(this.store.transfer(1, 2, 50));
    }

    @Test
    public void transferReturnFalse() {
        this.store.add(new User(1, 100));
        this.store.add(new User(2, 0));
        assertFalse(this.store.transfer(2, 1, 50));
    }
}
