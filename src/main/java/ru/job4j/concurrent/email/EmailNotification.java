package ru.job4j.concurrent.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        this.pool.submit(() -> {
            StringBuilder subject = new StringBuilder();
            subject.append("Notification {").append(user.getUsername()).append("} to email {").append(user.getEmail()).append(" }");
            StringBuilder body = new StringBuilder();
            body.append("Add a new event to {").append(user.getUsername()).append("}");
            send(subject.toString(), body.toString(), user.getEmail());
        });
    }

    public void send(String subject, String body, String email) {
    }

    public void close() {
        this.pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException eo) {
                eo.printStackTrace();
            }
        }
    }
}
