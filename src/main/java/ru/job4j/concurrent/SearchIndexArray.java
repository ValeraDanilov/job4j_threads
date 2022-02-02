package ru.job4j.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SearchIndexArray<T> extends RecursiveTask<Integer> {

    private final Object[] list;
    private final T object;
    private final int from;
    private final int to;

    public SearchIndexArray(Object[] list, T object, int from, int to) {
        this.list = list;
        this.object = object;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if ((this.to - this.from) <= 10) {
            for (int value = this.from; value < this.to; value++) {
                if (this.list[value].equals(this.object)) {
                    return value;
                }
            }
            return -1;
        }
        int mid = (this.from + this.to) / 2;
        SearchIndexArray<T> search = new SearchIndexArray<>(this.list, this.object, this.from, mid);
        SearchIndexArray<T> searchIndex = new SearchIndexArray<>(this.list, this.object, mid, this.to);
        search.fork();
        searchIndex.fork();
        return Math.max(search.join(), searchIndex.join());
    }

    public static <T> int search(Object[] arrays, T element) {
        return new ForkJoinPool().invoke(new SearchIndexArray<>(arrays, element, 0, arrays.length));
    }
}
