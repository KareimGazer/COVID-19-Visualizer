package com.company;

public class LinkedList <T> implements List <T>{
	public Node<T> head;
	private Node<T> current;
	private int size;

	public LinkedList ()
	{
		this.head = new Node<T>(null, null);
		this.current = head;
		this.size = 0;
	}
	public boolean empty () {
		return size == 0;
	}
	public boolean full () { return false; }
	public void findFirst () {
		current = head;
	}
	public void findNext () {
		current = current.next;
	}
	public T retrieve () {
		return current.data;
	}
	public void update (T val) {
		current.data = val;
	}
	public boolean last () {
		return current.next == null;
	}
	public void insert (T val) {
		this.size += 1;
		Node<T> tmp;
		if (empty()) {
			current = head = new Node<T> (val, null);
		}
		else {
			tmp = current.next;
			current.next = new Node<T> (val, null);
			current = current.next;
			current.next = tmp;
		}

	}

	public void remove () {
		this.size--;
		if (current == head) {
			head = head.next;
		}
		else {
			Node<T> tmp = head;

			while (tmp.next != current)
				tmp = tmp.next;

			tmp.next = current.next;
		}

		if (current.next == null)
			current = head;
		else
			current = current.next;
	}

	public int size() {

		return this.size;
	}

}



