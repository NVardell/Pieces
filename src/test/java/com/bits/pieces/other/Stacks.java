package com.bits.pieces.other;

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
}
