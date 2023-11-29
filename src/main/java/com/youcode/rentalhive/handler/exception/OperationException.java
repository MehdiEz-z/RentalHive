package com.youcode.rentalhive.handler.exception;

import java.io.Serial;

public class OperationException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public OperationException(String msg) {
        super(msg);
    }
}
