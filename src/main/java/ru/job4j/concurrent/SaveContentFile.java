package ru.job4j.concurrent;

import java.io.*;

public class SaveContentFile {

    private final File file;

    public SaveContentFile(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) {
        try (BufferedWriter o = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < content.length(); i++) {
                o.write(content.charAt(i));
            }
        } catch (IOException eo) {
            eo.printStackTrace();
        }
    }
}
