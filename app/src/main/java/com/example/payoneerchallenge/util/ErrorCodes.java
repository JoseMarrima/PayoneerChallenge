package com.example.payoneerchallenge.util;

enum ErrorCodes {
    SocketTimeOut(-1);

    private final int code;

    public final int getCode() {
        return this.code;
    }

    ErrorCodes(int code) {
        this.code = code;
    }
}


