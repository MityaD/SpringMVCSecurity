package com.yakut.spring.exception;

public class NoUserTableException extends Exception{
    public NoUserTableException(Long id) {
        System.out.println("Нет пользователя с ID: " + id );
    }
}
