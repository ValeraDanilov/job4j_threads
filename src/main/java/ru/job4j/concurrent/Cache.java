package ru.job4j.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Cache {

    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return this.memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        Base stored = this.memory.computeIfPresent(model.getId(), (key, value) -> {
            if (value.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            value.setVersion(model.getVersion() + 1);
            value.setName(model.getName());
            return value;
        });

        return stored != null;
    }

    public void delete(Base model) {
        this.memory.remove(model.getId());
    }
}
