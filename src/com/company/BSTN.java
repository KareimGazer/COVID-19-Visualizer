package com.company;

public class BSTN <T> {
    public int key;
    public T data;
    public BSTN<T> left, right;

    /** Creates a new instance of BSTNode */
    public BSTN(int k, T val) {
        key = k;
        data = val;
        left = right = null;
    }

    public BSTN(int k, T val, BSTN<T> l, BSTN<T> r) {
        key = k;
        data = val;
        left = l;
        right = r;
    }
}

