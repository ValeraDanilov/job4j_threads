package ru.job4j.concurrent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SearchIndexArrayTest {

    private final String[] newStrings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};

    @Before
    public void setUp() {
        System.out.println("Before method");
    }

    @After
    public void tearDown() {
        System.out.println("After method");
    }

    @Test
    public void whenSearchIndexInArrayAndReturn0() {
        int res = SearchIndexArray.search(this.newStrings, "A");
        assertThat(res, is(0));
    }

    @Test
    public void whenSearchIndexInArrayAndReturn12() {
        int res = SearchIndexArray.search(this.newStrings, "L");
        assertThat(res, is(11));
    }

    @Test
    public void whenSearchIndexInArrayAndReturn6() {
        int res = SearchIndexArray.search(this.newStrings, "G");
        assertThat(res, is(6));
    }

    @Test
    public void whenSearchIndexInArrayAndReturnMinusOne() {
        int res = SearchIndexArray.search(this.newStrings, "a");
        assertThat(res, is(-1));
    }
}
