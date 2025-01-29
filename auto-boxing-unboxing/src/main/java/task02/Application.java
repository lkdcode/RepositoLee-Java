package task02;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

final class Application {
    private static final List<Integer> LIST = List.of(100_000_000, 1_000_000, 100_000);

    public static void main(String[] args) {
        System.out.printf("%22s %27s%n", "총합", "소요된 시간");
        System.out.println("========================================================");

        LIST.forEach(max -> {
            System.out.printf("%,48d까지의 총합%n", max);
            final var autoBoxingStart = LocalDateTime.now();
            final var autoBoxingResult = AutoBoxing.foreach(max);
            final var autoBoxingEnd = LocalDateTime.now();

            final var primitiveStart = LocalDateTime.now();
            final var primitiveResult = Primitive.foreach(max);
            final var primitiveEnd = LocalDateTime.now();

            final var primitiveParallelStart = LocalDateTime.now();
            final var primitiveParallelResult = PrimitiveParallel.foreach(max);
            final var primitiveParallelEnd = LocalDateTime.now();

            System.out.printf("%5s %,25d %,20d ns%n", "래퍼", autoBoxingResult, Duration.between(autoBoxingStart, autoBoxingEnd).getNano());
            System.out.printf("%5s %,25d %,20d ns%n", "기본", primitiveResult, Duration.between(primitiveStart, primitiveEnd).getNano());
            System.out.printf("%5s %,25d %,20d ns%n", "병렬", primitiveParallelResult, Duration.between(primitiveParallelStart, primitiveParallelEnd).getNano());
            System.out.println("--------------------------------------------------------");
        });
    }
}