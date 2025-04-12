package funtional2.application.value;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    SUCCESS_OK("SUCCESS", OK, "요청에 성공했습니다."),

    // ...Custom
    SOMETHING_EXCEPTION("CODE-01", BAD_REQUEST, "...Description"),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String description;
}