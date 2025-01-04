package dsl.task01;

import java.util.stream.IntStream;

public class Example1 {
    private final String message;
    private int count;

    public Example1(String message) {
        this.message = message;
    }

    public static Example1 say(String message) {
        return new Example1(message);
    }

    public Example1 times(int count) {
        this.count = count;
        return this;
    }

    public void print() {
        IntStream.range(0, count)
            .forEach(e -> System.out.println(message));
    }

    public static void main(String[] args) {
        Example1
            .say("Hello, DSL!")
            .times(3)
            .print();

        /* VS */

        lowPrint("Hello, DSL!", 3);
    }

    public static void lowPrint(String message, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(message);
        }
    }
}