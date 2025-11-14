package com.deliverytech.delivery.api;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
