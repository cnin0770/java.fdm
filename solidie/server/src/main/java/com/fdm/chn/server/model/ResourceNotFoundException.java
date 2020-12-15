package com.fdm.chn.server.model;

public class ResourceNotFoundException extends RuntimeException {
    private static final String message = "Resource not found.";

    public ResourceNotFoundException() {
        super(message);
    }
}
