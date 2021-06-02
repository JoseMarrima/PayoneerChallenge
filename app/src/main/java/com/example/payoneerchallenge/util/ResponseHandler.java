package com.example.payoneerchallenge.util;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;

import kotlin.jvm.internal.Intrinsics;
import retrofit2.HttpException;

public class ResponseHandler<T> {
    @NotNull
    public final Resource<T> handleSuccess(@NotNull T data) {
        return Resource.success(data);
    }

    @NotNull
    public final Resource<T> handleException(@NotNull Throwable e) {
        Exception ex = (Exception) e;
        Intrinsics.checkParameterIsNotNull(e, "e");
        return ex instanceof HttpException ? Resource.error(getErrorMessage(((HttpException)ex).code()), null) :
                (ex instanceof SocketTimeoutException ? Resource.error(getErrorMessage(ErrorCodes.SocketTimeOut.getCode()), null) :
                        Resource.error(getErrorMessage(Integer.MAX_VALUE), null));
    }

    private String getErrorMessage(int code) {
        return code == ErrorCodes.SocketTimeOut.getCode() ? "Timeout" :
                (code == 401 ? "Unauthorised" :
                        (code == 404 ? "Not found" :
                                code == 403 ? "Forbidden" :
                                code == 500 ? "Internal Server Error" :
                                "Something went wrong"));
    }
}
