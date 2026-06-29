package com.careerflow.common;

import java.time.LocalDateTime;

public record BaseResponse<T>(
        boolean success,
        String message,
        T data,
        LocalDateTime timestamp
) {

    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>(
                true,
                message,
                data,
                LocalDateTime.now()
        );
    }

    public static <T> BaseResponse<T> error(String message, T data) {
        return new BaseResponse<>(
                false,
                message,
                data,
                LocalDateTime.now()
        );
    }
}