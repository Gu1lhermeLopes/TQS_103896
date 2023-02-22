package com.example.project;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack<T> {
	private LinkedList<T> ll = new LinkedList<T>();
	private int limit=0;

	public TqsStack() {
		ll = new LinkedList<>();
	}

	public TqsStack(int limit) {
		ll = new LinkedList<>();
		this.limit=limit;
	}

	public boolean isEmpty() {
		return ll.isEmpty();
	}

	public int size() {
		return ll.size();
	}

	public T pop(){
		if (this.isEmpty()) {
			throw new NoSuchElementException("Stack is empty"); 
		}else
			return ll.pop();
	}

	public T peek(){
		if (this.isEmpty()) {
			throw new NoSuchElementException("Stack is empty"); 
		}else
			return ll.peek();
	}

	public void push(T t){
		if (limit!=0 && this.size() == this.limit ) {
			throw new IllegalStateException("stack full");
		}else
			ll.push(t);
	}

}
