package com.example.demo.enums;

public enum Endpoint {
    LIBRARY("libraries/"),
    BOOK("books/"),
    AUTHOR("authors/");

    private String prefix;

    Endpoint(String prefix){
        this.prefix = prefix;
    }

    public String getPrefix(){
        return prefix;
    }
}
