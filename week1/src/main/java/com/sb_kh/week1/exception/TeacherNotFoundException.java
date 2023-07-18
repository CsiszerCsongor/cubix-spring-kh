package com.sb_kh.week1.exception;

public class TeacherNotFoundException extends RuntimeException{

    public TeacherNotFoundException(String message) {
        super(message);
    }

    public TeacherNotFoundException(String message, Throwable e) {
        super(message, e);
    }
}
