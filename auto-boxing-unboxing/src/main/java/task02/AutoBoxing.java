package task02;

import java.util.stream.Stream;

public final class AutoBoxing {
    public static Long foreach(final Integer n) {
        return Stream.iterate(1L, i -> i + 1)
            .limit(n)
            .reduce(0L, Long::sum);
    }
}