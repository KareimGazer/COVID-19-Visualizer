package com.company;

public class DynamicArray<T> implements Array<T> {

    public int sze;
    public int cap;
    public T [] elements;

    public DynamicArray(){
        this.sze = 0;
        this.cap = 0;

    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return this.sze;
    }

    @Override
    public int capacity() {
        // TODO Auto-generated method stub
        return this.cap;
    }

    @Override
    public T get(int i) {
        // TODO Auto-generated method stub
        if(i<0 || i>sze-1) throw new ArrayIndexOutOfBoundsException();
        return elements[i];
    }

    @Override
    public void set(int i, T e) {
        // TODO Auto-generated method stub
        if(i<0 || i>sze-1) throw new ArrayIndexOutOfBoundsException();
        elements[i] = e;
    }

    @Override
    public void add(T e) {
        // TODO Auto-generated method stub
        if(cap == 0){
            cap = 1;
            sze = 1;
            elements = (T[]) new Object[cap];
            elements[sze-1] = e;
        }
        else{
            if(sze<cap){
                elements[sze] = e;
                sze +=1;
            }
            else {
                cap *= 2;
                T[] tmp = (T[]) new Object[cap];
                for (int i = 0; i < sze; i++) tmp[i] = elements[i];
                tmp[sze] = e;
                sze += 1;
                elements = tmp;
            }
        }

    }

}
