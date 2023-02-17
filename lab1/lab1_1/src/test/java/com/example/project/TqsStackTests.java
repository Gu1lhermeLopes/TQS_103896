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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;

class TqsStackTests {

	@Test
	@DisplayName("NEW==EMPTY")
	void onConstruction() {
		TqsStack s = new TqsStack();
		assertEquals(true, s.isEmpty(), "new stack shoud be empty");
		assertEquals(0, s.size(), "shoud be 0");
	}
	@Test
	void checkSize() {
		TqsStack s = new TqsStack();
		s.push(0);
		assertEquals(1, s.size(), "shoud be 1");
		s.push(0);
		assertEquals(2, s.size(), "shoud be 2");
	}

	@Test
	void checkPop() {
		TqsStack s = new TqsStack();
		s.push(0);
		s.push(1);

		assertEquals(1, s.pop(), "shoud be 1");
		assertEquals(1, s.size(), "shoud be 1");
	}

	@Test
	void checkPeek() {
		TqsStack s = new TqsStack();
		s.push(0);
		s.push(1);

		assertEquals(1, s.peek(), "shoud be 1");
		assertEquals(2, s.size(), "shoud be 2");
	}

	@Test
	void EmptyPeek() {
		TqsStack s = new TqsStack();
	

		assertEquals(null, s.peek(), "shoud be null");

	}
}
