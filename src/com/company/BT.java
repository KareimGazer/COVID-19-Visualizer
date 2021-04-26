package com.company;
import java.util.Stack;

public class BT<T> {
    BTNode<T> root, current;

    /**
     * Creates a new instance of BT
     */
    public BT() {
        root = current = null;
    }

    public boolean empty() {
        return root == null;
    }

    public T retrieve() {
        return current.data;
    }

    public void update(T val) {
        current.data = val;
    }

    public boolean insert(Relative rel, T val) {
        switch(rel) {
            case Root:
                if(!empty()) return false;
                current = root = new BTNode<T>(val);
                return true;
            case Parent:	//This is an impossible case.
                return false;
            case LeftChild:
                if(current.left != null) return false;
                current.left = new BTNode<T>(val);
                current = current.left;
                return true;
            case RightChild:
                if(current.right != null) return false;
                current.right = new BTNode<T> (val);
                current = current.right;
                return true;
            default:
                return false;
        }
    }

    public void deleteSubtree(){
        if(current == root){
            current = root = null;
        }
        else {
            BTNode<T> p = current;
            find(Relative.Parent);
            if(current.left == p)
                current.left = null;
            else
                current.right = null;
            current = root;
        }
    }

    public boolean find(Relative rel){
        switch (rel) {
            case Root:	// Easy case
                current = root;
                return true;
            case Parent:
                if(current == root) return false;
                current = findparent(current, root);
                return true;
            case LeftChild:
                if(current.left == null) return false;
                current = current.left;
                return true;
            case RightChild:
                if(current.right == null) return false;
                current = current.right;
                return true;
            default:
                return false;
        }
    }

    private BTNode<T> findparent(BTNode<T> p, BTNode<T> t) {
        // Stack is used to store the right pointers of nodes
        Stack<BTNode<T>> stack = new Stack<BTNode<T>>();
        BTNode<T> q = t;
        while(q.right != p && q.left != p) {
            if(q.right != null)
                stack.push(q.right);

            if(q.left != null)
                q = q.left;
            else
                q = stack.pop(); // Go right here
        }
        return q;
    }

}


