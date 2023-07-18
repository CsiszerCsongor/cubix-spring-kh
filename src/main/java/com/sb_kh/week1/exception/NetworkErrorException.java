package com.sb_kh.week1.exception;

public class NetworkErrorException extends RuntimeException{

    public NetworkErrorException(String message, Throwable ex) {
        super(message, ex);
    }

    public NetworkErrorException(String message) {
        super(message);
    }

}
