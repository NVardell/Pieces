package com.bits.pieces.topics.structures.queues;

import org.junit.Test;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Misc. Unit Test for different types of Queues
 *
 * @author Nate Vardell
 * @since 8/17/2019
 */
public class QueueTests {

    private static Queue<String> stringQueue;

    @Test
    public void stringPriorityQueue_testAddAndPoll() {
        stringQueue = new PriorityQueue<>();

        stringQueue.add("Awesome");
        stringQueue.add("Amazing");

        assertThat(stringQueue.poll(), is("Amazing"));
        assertThat(stringQueue.size(), is(1));
    }

    @Test
    public void stringQueueLinkedList_testAddAndPoll() {
        stringQueue = new LinkedList<>();

        stringQueue.add("Awesome");
        stringQueue.add("Amazing");
        assertThat(stringQueue.poll(), is("Awesome"));

        stringQueue.add("Amazing2");
        assertThat(stringQueue.isEmpty(), is(false));
        assertThat(stringQueue.size(), is(2));
    }
}
