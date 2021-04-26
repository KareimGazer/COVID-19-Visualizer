package com.company;


public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {

	BSTNode<K, T> root, current;
	int sze, comp;
	List<K>keys;

	public BSTMap(){
		root = current = null;
		sze = 0;
		comp = 0;//counts the number of comparisons until the key is found
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return sze;
	}

	public boolean empty(){
		return root == null;
	}

	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		root = current = null;
	}

	@Override
	public boolean update(K k, T e) {
		// TODO Auto-generated method stub
		findKey(k);
		remove(current.key);
		return insert(k, e);
	}

	@Override
	public Pair<Boolean, T> retrieve(K k) {
		// TODO Auto-generated method stub
		Pair pair = new Pair(false, null);
		BSTNode<K, T> tmp = current;
		if(findKey(k)){
			pair.first = true;
			pair.second = current.data;
			current = tmp;
		}
		return pair;
	}

	public boolean findKey(K tkey) {
		BSTNode<K, T> p = root, q = root;
		comp = 0;
		if(empty())
			return false;

		while(p != null) {
			q = p;
			if(p.key == tkey) {
				current = p;
				comp ++;
				return true;
			}
			else if(tkey.compareTo(p.key)<0) {
				p = p.left;
				comp ++;
			}
			else {
				p = p.right;
				comp ++;
			}
		}

		current = q;
		return false;
	}

	@Override
	public int nbKeyComp(K k) {
		// TODO Auto-generated method stub
		findKey(k);
		return comp;
	}

	@Override
	// don't forget to edit the size
	public boolean insert(K k, T e) {
		// TODO Auto-generated method stub
		BSTNode<K, T> p, q = current;

		if(findKey(k)) {
			current = q;  // findkey() modified current
			return false; // key already in the BST
		}

		p = new BSTNode<K, T>(k, e);
		if (empty()) {
			root = current = p;
			sze++;
			return true;
		}
		else {
			// current is pointing to parent of the new key
			if (k.compareTo(current.key)<0)
				current.left = p;
			else
				current.right = p;
			current = p;
			sze++;
			return true;
		}
	}

	@Override
	// don't forget to edit the size
	public boolean remove(K k) {
		// TODO Auto-generated method stub
		Boolean removed = new Boolean(false);
		BSTNode<K, T> p;
		p = remove_aux(k, root, removed);
		if(p!=null) sze--;
		return p!=null;
	}

	private BSTNode<K, T> remove_aux(K key, BSTNode<K, T> p, Boolean flag) {
		BSTNode<K, T> q, child = null;
		if(p == null)
			return null;
		if(key.compareTo(p.key)<0)
			p.left = remove_aux(key, p.left, flag); //go left
		else if(key.compareTo(p.key)>0)
			p.right = remove_aux(key, p.right, flag); //go right
		else {
			flag = true;
			if (p.left != null && p.right != null){ //two children
				q = find_min(p.right);
				p.key = q.key;
				p.data = q.data;
				p.right = remove_aux(q.key, p.right, flag);
			}
			else {
				if (p.right == null) //one child
					child = p.left;
				else if (p.left == null) //one child
					child = p.right;
				return child;
			}
		}
		return p;
	}

	private BSTNode<K, T> find_min(BSTNode<K, T> p){
		if(p == null)
			return null;
		while(p.left != null){
			p = p.left;
		}
		return p;
	}

	public void inOrder(BSTNode<K, T> node){
		if(node.left != null) inOrder(node.left);
		keys.insert(node.key);
		if(node.right != null) inOrder(node.right);

	}
	@Override
	public List<K> getKeys() {
		// TODO Auto-generated method stub
		keys = new LinkedList<K>();
		inOrder(root);
		keys.findFirst();
		keys.remove();
		return keys;
	}

}

