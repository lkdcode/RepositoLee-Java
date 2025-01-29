package task02;

import java.util.stream.IntStream;

public class PrimitiveParallel {
    public static long foreach(final int n) {
        return IntStream.rangeClosed(0, n)
            .parallel()
            .asLongStream()
            .sum();
    }
}