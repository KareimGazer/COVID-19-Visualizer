package com.company;

public class BSTNode  <K extends Comparable <K>, T>  {
		public K key;
		public T data;
		public BSTNode<K , T> left, right;
		
		// Creates a new instance of BSTNode
		public BSTNode(K k, T val) {
			key = k;
			data = val;
			left = right = null;
		}
		
		public BSTNode(K k, T val, BSTNode<K , T> l, BSTNode<K , T> r) {
			key = k;
			data = val;
			left = l;
			right = r;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public BSTNode<K, T> getLeft() {
			return left;
		}

		public void setLeft(BSTNode<K, T> left) {
			this.left = left;
		}

		public BSTNode<K, T> getRight() {
			return right;
		}

		public void setRight(BSTNode<K, T> right) {
			this.right = right;
		}
		
	}




