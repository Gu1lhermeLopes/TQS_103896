package com.mycompany.app;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack<T> {

    private LinkedList<T> collection = new LinkedList<T>();
    private int limit = 0;

    public TqsStack(){
        collection = new LinkedList<T>();
    }

    public TqsStack(int limit){
        this.limit = limit;
        collection = new LinkedList<T>();
    }
    
    public T pop(){
        if (collection.isEmpty()){
            throw new NoSuchElementException("Stack is empty");
        }
        return collection.pop();
    }

    public int size(){
        return collection.size();
    }

    public T peek(){
        if (collection.isEmpty()){
            throw new NoSuchElementException("Stack is empty");
        }
        return collection.peek();
    }

    public void push(T element){
        if (limit > 0 && collection.size() == limit){
            throw new IllegalStateException("Stack is full");
        }
        collection.addFirst(element);
    }

    public boolean isEmpty(){
        return collection.isEmpty();
    }
    
}
