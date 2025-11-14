package com.deliverytech.delivery.api;

public class DataConflictException extends RuntimeException {
    public DataConflictException(String msg) {
        super(msg);
    }
}
