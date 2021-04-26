package com.company;

public class BSTSNode  <K extends Comparable <K>>  {
    public K key;
    public BSTSNode<K> left, right;

    // Creates a new instance of BSTNode
    public BSTSNode(K k) {
        key = k;
        left = right = null;
    }

    public BSTSNode(K k,BSTSNode<K> l, BSTSNode<K> r) {
        key = k;
        left = l;
        right = r;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public BSTSNode<K> getLeft() {
        return left;
    }

    public void setLeft(BSTSNode<K> left) {
        this.left = left;
    }

    public BSTSNode<K> getRight() {
        return right;
    }

    public void setRight(BSTSNode<K> right) {
        this.right = right;
    }

}




