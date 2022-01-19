package ru.job4j.concurrent;

import java.util.function.Predicate;

public interface Content {

   String content(Predicate<Character> filter);

}
