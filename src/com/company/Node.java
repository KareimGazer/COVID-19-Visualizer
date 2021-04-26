package com.company;

public class Node<T> {

	T data;
	Node<T> next;


	public Node (T val, Node<T> next) {
		this.data = val;
		this.next = next;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}


	
}
