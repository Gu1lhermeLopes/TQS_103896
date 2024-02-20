package com.mycompany.app;

import java.util.LinkedList;

public class TqsStack<T> {

    private LinkedList<T> collection = new LinkedList<T>();
    public TqsStack(){

    }
    
    public T pop(){
        return collection.pop();
    }

    public int size(){
        return collection.size();
    }

    public T peek(){
        return collection.peek();
    }

    public void push(T element){
        collection.addFirst(element);
    }

    public boolean isEmpty(){
        return collection.isEmpty();
    }
    
}
