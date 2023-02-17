/*
 * Copyright 2015-2022 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.example.project;

import java.util.LinkedList;

public class TqsStack {
	private LinkedList<Integer> ll;

	public TqsStack() {
		ll = new LinkedList<Integer>();
	}

	public boolean isEmpty() {
		return ll.isEmpty();
	}

	public int size() {
		return ll.size();
	}

	public Integer pop(){
		return ll.pop();
	}

	public Integer peek(){
		return ll.peek();
	}

	public void push(Integer t){
		ll.push(t);
	}

}
