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

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class TqsStackTests {

	@Test
	@DisplayName("a) Empty on constration")
	void emptyConstruction() {
		TqsStack<Integer> s = new TqsStack<Integer>();
		assertTrue(s.isEmpty());
	}

	@Test
	@DisplayName("b) Size 0 on constration")
	void sizeConstruction() {
		TqsStack<Integer> s = new TqsStack<Integer>();
		assertEquals(0, s.size());
	}

	@Test
	@DisplayName("c) size n after n pushes")
	void checkSize() {
		TqsStack<Integer> s = new TqsStack<Integer>();
		s.push(0);
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		assertEquals(5, s.size());
	}

	@Test
	@DisplayName("d) push x and pop x")
	void pushXpop() {
		TqsStack<Integer> s = new TqsStack<Integer>();
		Integer x=3;
		s.push(x);
		assertEquals(x,s.pop());
	}

	@Test
	@DisplayName("e) push x and peek x")
	void pushXpeek() {
		TqsStack<Integer> s = new TqsStack<Integer>();
		Integer x=3;
		s.push(x);

		int sizeBefore=s.size();
		assertEquals(x,s.peek());
		int sizeAfter=s.size();
		assertEquals(sizeBefore,sizeAfter);

	}

	@Test
	@DisplayName("f) size N pop N")
	void sizeNpop() {
		TqsStack<Integer> s = new TqsStack<Integer>();
		Integer n=3;
		for (int i = 0; i < n; i++) {
			s.push(n);
		}
		assertEquals(n,s.size());
		for (int i = 0; i < n; i++) {
			s.pop();
		}
		assertEquals(0,s.size());
	}

	@Test
	@DisplayName("g) pop empty stack")
	void EmptyPop() {
		TqsStack<Integer> s = new TqsStack<Integer>();
		assertThrows(NoSuchElementException.class,() -> s.pop());
	}

	@Test
	@DisplayName("h) peek empty stack")
	void EmptyPeek() {
		TqsStack<Integer> s = new TqsStack<Integer>();
		assertThrows(NoSuchElementException.class,() -> s.peek());
	}

	@Test
	@DisplayName("h) check full limited stack")
	void LimitedStack() {
		TqsStack<Integer> s = new TqsStack<Integer>(2);
		s.push(1);
		s.push(2);
		assertThrows(IllegalStateException.class,() -> s.push(3));
	}


}
