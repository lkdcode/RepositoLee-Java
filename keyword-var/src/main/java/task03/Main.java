package task03;

import java.time.LocalDateTime;

class Main {
    public static void main(String[] args) {
        input("lkdcode");
    }

    static void input(final String target) {
        final var value = target.length() > 5
            ? "lkdcode"
            : LocalDateTime.now();

        print(value);
    }

    static void print(final String value) {
        System.out.println(value);
        System.out.println(value.getClass());
    }

    static void print(final LocalDateTime value) {
        System.out.println(value);
        System.out.println(value.getClass());
    }

    static void print(final Object value) {
        System.out.println(value);
        System.out.println(value.getClass());
    }
}