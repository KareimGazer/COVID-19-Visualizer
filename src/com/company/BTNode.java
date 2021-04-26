package com.company;

public class BTNode <T> {
    public T data;
    public BTNode<T> left, right;

    /** Creates a new instance of BTNode */
    public BTNode(T val) {
        data = val;
        left = right = null;
    }

    public BTNode(T val, BTNode<T> l, BTNode<T> r){
        data = val;
        left = l;
        right = r;
    }
}
