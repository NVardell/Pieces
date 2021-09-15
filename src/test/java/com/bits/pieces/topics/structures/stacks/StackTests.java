package com.bits.pieces.topics.structures.stacks;

import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 8/17/2019
 */
public class StackTests {

    @Test
    public void tempStackTest() {
        Stack<String> stack = new Stack<>();
        stack.push("One");
        stack.push("Two");
        stack.push("Three");

        assertThat(stack.search("Three"), is(1));
        assertThat(stack.search("Two"), is(2));
        assertThat(stack.search("Four"), is(-1));
        assertThat(stack.empty(), is(false));
        assertThat(stack.pop(), is("Three"));
        System.out.println(stack);
    }
}
