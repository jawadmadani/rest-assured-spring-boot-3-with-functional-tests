package com.jawad.restassuredspring3functionaltests;

public class GeneralException extends RuntimeException {
    public GeneralException(String message, Throwable err) {
        super(message, err);
    }
}
