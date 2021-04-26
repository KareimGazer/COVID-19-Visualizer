package com.company;

public class BSTSet<K extends Comparable<K>> implements Set<K> {

	BSTSNode<K> root, current;
	int sze, comp;
	List<K>keys;

	public BSTSet(){
		root = current = null;
		sze = 0;
		comp = 0;//counts the number of comparisons until the key is found
		keys = new LinkedList<K>();
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

	public boolean find(K tkey) {
		BSTSNode<K> p = root, q = root;
		comp = 0;

		if(empty())
			return false;

		while(p != null) {
			q = p;
			if(p.key == tkey) {
				current = p;
				comp +=1;
				return true;
			}
			else if(tkey.compareTo(p.key)<0)
				p = p.left;
			else
				p = p.right;
		}

		current = q;
		return false;
	}

	@Override
	public int nbKeyComp(K k) {
		// TODO Auto-generated method stub
		find(k);
		return comp;
	}

	@Override
	public boolean insert(K k) {
		// TODO Auto-generated method stub
		BSTSNode<K> p, q = current;

		if(find(k)) {
			current = q;  // findkey() modified current
			return false; // key already in the BST
		}

		p = new BSTSNode<K>(k);
		if (empty()){
			root = current = p;
			sze++;
			return true;
		}
		else {
			// current is pointing to parent of the new key
			if (k.compareTo(current.key)<0) {
				current.left = p;
				comp++;
			}
			else {
				current.right = p;
				comp++;
			}
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
		BSTSNode<K> p;
		p = remove_aux(k, root, removed);
		current = root = p;
		if(p!=null) sze--;
		return p!=null;
	}

	private BSTSNode<K> remove_aux(K key, BSTSNode<K> p, Boolean flag) {
		BSTSNode<K> q, child = null;
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

	private BSTSNode<K> find_min(BSTSNode<K> p){
		if(p == null)
			return null;
		while(p.left != null){
			p = p.left;
		}
		return p;
	}

	public void inOrder(BSTSNode<K> node){
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
