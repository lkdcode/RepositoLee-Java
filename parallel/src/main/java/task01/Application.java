package task01;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Application {
    public static void main(String[] args) {
        List<Integer> integers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
            .parallel()
            .map(e -> e * 2)
            .toList();

        System.out.println(integers);
    }
}