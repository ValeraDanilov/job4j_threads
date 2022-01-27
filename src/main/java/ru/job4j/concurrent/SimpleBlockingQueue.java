package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private int count;

    public SimpleBlockingQueue() {
    }

    public SimpleBlockingQueue(int count) {
        this.count = count;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (this.queue.size() > this.count) {
            wait();
        }
        this.queue.add(value);
        notify();
    }

    public synchronized T poll() throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }
        T element = this.queue.poll();
        notifyAll();
        return element;
    }
}
