package com.bits.pieces.topics;

import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Stacks {
    @Test
    public void whenStackIsCreated_sizeIsZero(){
        Stack<Integer> intStack = new Stack<>();
        assertThat(intStack.size(), is(0));
    }

    @Test
    public void stacksWithStringAndChars(){
        Stack<Character> charStack = new Stack<>();
        assertThat(charStack.size(), is(0));

        char c = 'A';
        charStack.add(c);
        assertThat(charStack.size(), is(1));

        StringBuilder sb = new StringBuilder();
        sb.insert(0,c);

    }
}
