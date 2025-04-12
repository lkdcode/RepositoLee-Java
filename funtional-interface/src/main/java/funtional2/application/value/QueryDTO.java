package funtional2.application.value;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder(value = {"code", "_embedded"})
public final class QueryDTO<T> {

    private final String code;
    private final T _embedded;

    private QueryDTO(ResponseCode code) {
        this.code = code.getCode();
        this._embedded = (T) code.getDescription();
    }

    private QueryDTO(ResponseCode code, T _embedded) {
        this.code = code.getCode();
        this._embedded = _embedded;
    }

    public static QueryDTO<Void> ok() {
        return new QueryDTO<>(ResponseCode.SUCCESS_OK);
    }

    public static <T> QueryDTO<T> ok(final T _embedded) {
        return new QueryDTO<>(ResponseCode.SUCCESS_OK, _embedded);
    }

    public static <T> QueryDTO<T> ok(final ResponseCode code, final T _embedded) {
        return new QueryDTO<>(code, _embedded);
    }

    public static <T> QueryDTO<T> warn(final ResponseCode code) {
        return new QueryDTO<>(code);
    }

    public static <T> QueryDTO<T> warn(final ResponseCode code, final T _embedded) {
        return new QueryDTO<>(code, _embedded);
    }

    public static <T> QueryDTO<T> error(final ResponseCode code) {
        return new QueryDTO<>(code);
    }

    public static <T> QueryDTO<T> error(final ResponseCode code, final T _embedded) {
        return new QueryDTO<>(code, _embedded);
    }
}