package com.ed.globallogik.data;

import static com.ed.globallogik.data.Resource.Status.LOADING;

import androidx.annotation.Nullable;

public class Resource<T> {

    @Nullable
    T data;
    @Nullable String errorMessage;
    Status status;

    public Resource(@Nullable T data, @Nullable String errorMessage, Status status){
        this.data = data;
        this.errorMessage = errorMessage;
        this.status = status;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }

    public Status getStatus() {
        return status;
    }

    public static class Loading<T> extends Resource<T>{
        public Loading(@Nullable T data, @Nullable String errorMessage) {
            super(data, errorMessage, LOADING);
        }
    }

    public static class Success<T> extends Resource<T>{

        public Success(@Nullable T data, @Nullable String errorMessage) {
            super(data, errorMessage, Status.SUCCESS);
        }
    }

    public static class Error<T> extends Resource<T>{
        public Error(@Nullable T data, @Nullable String errorMessage) {
            super(data, errorMessage, Status.ERROR);
        }
    }

    public enum Status{
        LOADING,
        SUCCESS,
        ERROR;
    }
}
