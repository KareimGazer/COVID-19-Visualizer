package com.company;

public interface List<T> {
	boolean empty();
	boolean full();
	// Return the number of elements in the list
	int size();
	void findFirst();
	void findNext();
	boolean last();
	T retrieve();
	void update(T e);
	void insert(T e);
	void remove();
}
