package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        String[] process = new String[]{"\\", "|", "/", "\\", "|", "/"};
        int count = 0;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (count == process.length) {
                    count = 0;
                }
                System.out.print("\r load: " + process[count]);
                count++;
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread action = new Thread(new ConsoleProgress());
        action.start();
        Thread.sleep(100000);
        action.interrupt();
    }
}
