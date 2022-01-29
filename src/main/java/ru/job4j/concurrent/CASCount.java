package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {

    private final AtomicReference<Integer> checkCount = new AtomicReference<>(0);

    public void increment() {
        int value;
        do {
            value = this.checkCount.get();
        } while (!checkCount.compareAndSet(value, value + 1));
    }

    public int get() {
        return this.checkCount.get();
    }
}
