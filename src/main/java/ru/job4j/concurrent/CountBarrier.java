package ru.job4j.concurrent;

public class CountBarrier {

    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (this.monitor) {
            this.count++;
            this.monitor.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this.monitor) {
            while (this.count < this.total) {
                this.monitor.wait();
            }
        }
    }
}
