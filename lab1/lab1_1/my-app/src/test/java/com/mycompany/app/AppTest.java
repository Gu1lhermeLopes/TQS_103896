package com.mycompany.app;


import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private TqsStack<Integer> myStack;

    @BeforeEach
    void setUp() {
        myStack = new TqsStack<Integer>();
    }


    @Test
    public void stackIsEmpty()
    {
        Assertions.assertTrue(myStack.isEmpty());
        Assertions.assertEquals(0, myStack.size());
    }

    @Test
    public void stackSize()
    {
        myStack.push(1);
        Assertions.assertEquals(1, myStack.size());
        Assertions.assertFalse(myStack.isEmpty());
    }

    @Test
    public void stackPop()
    {
        myStack.push(1);
        myStack.push(2);
        Assertions.assertEquals(myStack.pop(), 2);
    }

    @Test
    public void stackPeek()
    {
        myStack.push(1);
        myStack.push(2);
        Assertions.assertEquals(myStack.peek(), 2);
        Assertions.assertEquals(2, myStack.size());
    }

    @Test
    public void dropStack()
    {
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.pop();
        myStack.pop();
        myStack.pop();
        Assertions.assertEquals(0,myStack.size());
    }

    @Test
    public void popException(){
        Exception exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            myStack.pop();
        });
        Assertions.assertEquals(exception.getMessage(), "Stack is empty" );
    }

    @Test
    public void peekException(){
        Exception exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            myStack.peek();
        });
        Assertions.assertEquals(exception.getMessage(), "Stack is empty" );
    }

    @Test
    public void pushException(){
        myStack = new TqsStack<Integer>(2);
        myStack.push(1);
        myStack.push(2);
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            myStack.push(3);
        });
        Assertions.assertEquals(exception.getMessage(), "Stack is full" );
    }





}
