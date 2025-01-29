package task02;

import java.util.stream.IntStream;

public final class Primitive {
    public static long foreach(final int n) {
        return IntStream.rangeClosed(0, n)
            .asLongStream()
            .sum();
    }
}