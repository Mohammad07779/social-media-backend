package com.Mohammad.CareerXpert.Utility;


public class ApiResponse<T> {

    private T data;
    private String message;

    public ApiResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public ApiResponse() {

    }

    public T getDate() {
        return data;
    }

    public void setDate(T date) {
        this.data = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
