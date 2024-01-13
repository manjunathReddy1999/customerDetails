package com.example.customer.exception;

import java.io.Serializable;

/**
 * CustomRuntimeException class for Customer
 * @author manjunathreddy1999
 */
public class CustomRunTimeException extends RuntimeException implements Serializable {

    /**
     * CustomerRunTimeException is a constructo pointing to super class with message
     * @param message
     */
    public CustomRunTimeException(String message) {
        super(message);
    }
}
