package com.bank.simplelifebank.exception;

import java.time.LocalDateTime;

public class ErrorDetails {

    private LocalDateTime time;
    private String message;
    private String detail;
    private String errorCode;

    public ErrorDetails(LocalDateTime time, String message, String detail, String errorCode) {
        this.time = time;
        this.message = message;
        this.detail = detail;
        this.errorCode = errorCode;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
