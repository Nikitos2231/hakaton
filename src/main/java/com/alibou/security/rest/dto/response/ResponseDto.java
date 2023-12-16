package com.alibou.security.rest.dto.response;

import lombok.Builder;
import lombok.Value;

import static com.alibou.security.util.ErrorCode.BASE_ERROR;
import static com.alibou.security.util.ErrorCode.OK;

@Value
@Builder(toBuilder = true)
public class ResponseDto<T> {
    int errorCode;
    String errorMessage;
    T resultData;

    public static <T> ResponseDto<T> ok() {
        return ResponseDto.<T>builder()
                .errorCode(OK)
                .build();
    }

    public static <T> ResponseDto<T> ok(T resultData) {
        return ResponseDto.<T>builder()
                .errorCode(OK)
                .resultData(resultData)
                .build();
    }

    public static <T> ResponseDto<T> error(int errorCode, String errorMessage) {
        return ResponseDto.<T>builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }

    public static <T> ResponseDto<T> error(int errorCode, String errorMessage, T body) {
        return ResponseDto.<T>builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .resultData(body)
                .build();
    }

    public static <T> ResponseDto<T> error(String errorMessage) {
        return ResponseDto.<T>builder()
                .errorCode(BASE_ERROR)
                .errorMessage(errorMessage)
                .build();
    }
}
