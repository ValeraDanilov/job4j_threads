package ru.job4j.concurrent;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile implements Content {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent() {
        return content(a -> true);
    }

    public String getContentWithoutUnicode() {
        return content(a -> a < 0x80);
    }

    @Override
    public synchronized String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedReader i = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = i.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException eo) {
            eo.printStackTrace();
        }
        return output.toString();
    }
}
