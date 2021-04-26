package com.company;

public interface Array<T> {

    int size();
    int capacity();
    T get(int i);
    void set(int i, T e);
    void add(T e);

}

