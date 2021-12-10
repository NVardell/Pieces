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

    private static Queue<String> pq;

    @Test
    public void tempQueueTest() {
        pq = new PriorityQueue<>();
        pq.add("Awesome");
        pq.add("Amazing");
        assertThat(pq.poll(), is("Amazing"));
        assertThat(pq.size(), is(1));

        Queue<String> llq = new LinkedList<>();
        llq.add("Awesome");
        llq.add("Amazing");
        assertThat(llq.poll(), is("Awesome"));
        llq.add("Amazing2");
        System.out.println(pq);
        System.out.println(llq);
        System.out.println(llq.element());
        assertThat(llq.isEmpty(), is(false));
    }
}
