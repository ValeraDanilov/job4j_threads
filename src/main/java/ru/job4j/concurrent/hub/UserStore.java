package ru.job4j.concurrent.hub;

import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class UserStore {

    private final Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        return this.users.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return this.users.replace(user.getId(), this.users.get(user.getId()), user);
    }

    public synchronized boolean delete(User user) {
        return this.users.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User firstUser = this.users.get(fromId);
        User secondUser = this.users.get(toId);
        if (firstUser == null || secondUser == null || firstUser.getAmount() < amount) {
            return false;
        }
        firstUser.setAmount(firstUser.getAmount() - amount);
        secondUser.setAmount(secondUser.getAmount() + amount);
        return true;
    }
}
