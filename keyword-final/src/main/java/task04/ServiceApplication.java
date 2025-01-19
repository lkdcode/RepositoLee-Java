package task04;

import java.util.List;

class ServiceApplication {

    public static void logic() {
        final String message = "lkdcode";
        Runnable runnable = () -> System.out.println(message);
        runnable.run();
    }

    public static void someMethod2() {
        final String message = "lkdcode";
        Runnable runnable = () -> {
//            message = "lkdcode exception";
            System.out.println(message);
        };
        runnable.run();
    }

    public static void someStream(List<String> list) {
        String prefix = "LKDCODE";
        final var newList = list
            .stream()
            .map(e -> {
                e = "Hello World";
//                e = prefix + "byebye"; /*prefix 가 final 이면 사용 가능하다.*/
//                prefix = "hihi"; /* 재할당은 금지한다. effectively final*/
                return e.toUpperCase();
            })
            .toList();
        System.out.println(newList);
    }
}