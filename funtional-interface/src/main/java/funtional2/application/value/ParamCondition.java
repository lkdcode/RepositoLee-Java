package funtional2.application.value;

public record ParamCondition<T>(
    String key,
    T value
) {

    public static <T> ParamCondition<?> of(String key, T value) {
        return new ParamCondition<>(key, value);
    }
}