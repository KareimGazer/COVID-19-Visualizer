package com.company;

public interface Set<K extends Comparable<K>> {
	// Return the size of the set.
	int size();

	// Return true if the map is full.
	boolean full();

	// Remove all elements from the map.
	void clear();

	// Search for key k and returns true if it exists, false otherwise.
	boolean find(K k);

	// Return the number of key comparisons needed to find the key k.
	int nbKeyComp(K k);

	// Insert a new key if does not exist and return true. If k already exists, return false.
	boolean insert(K k);

	// Remove the key k if it exists and return true. If the key does not exist return false.
	boolean remove(K k);

	// Return the list of keys in increasing order.
	List<K> getKeys();

}
