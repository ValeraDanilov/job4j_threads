package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wgets implements Runnable {
    private final String url;
    private final int speed;

    public Wgets(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(this.url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            long time = 0;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                time = System.currentTimeMillis() - start >= 1000 ? time : 1000;
                Thread.sleep(time);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wgets(url, speed));
        wget.start();
        wget.join();
    }
}
