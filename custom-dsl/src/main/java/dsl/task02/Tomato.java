package dsl.task02;

record Tomato(
    Color color,
    Size size,
    Usage usage
) {
    public static Tomato of(Color color, Size size, Usage usage) {
        return new Tomato(color, size, usage);
    }

    public enum Color {
        RED,
        GREEN,
        YELLOW,
        BLUE,
        ;
    }

    public enum Size {
        SMALL,
        MEDIUM,
        LARGE,
        ;
    }

    public enum Usage {
        DANCER,
        JUICE,
        KETCHUP,
        ;
    }

    @Override
    public String toString() {
        return String.format("Tomato[ color=%-8s size=%-8s usage=%-8s ]", color, size, usage);
    }
}