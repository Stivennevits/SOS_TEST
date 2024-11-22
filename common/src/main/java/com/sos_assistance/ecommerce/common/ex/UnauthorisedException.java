package com.sos_assistance.ecommerce.common.ex;

public class UnauthorisedException extends RuntimeException {
    public UnauthorisedException(String message) {
        super(message);
    }
}