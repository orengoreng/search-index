package com.sample.countwords.exception;


public class SearchIndexException extends RuntimeException {

    private String message;

    public SearchIndexException(String message) {
        super(message);
    }

    public SearchIndexException(String message, Throwable cause) {
        super(message, cause);
    }

}
