package com.bits.pieces.topics.structures.queues;

import org.junit.Test;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 8/17/2019
 */
public class QueueTests {

    @Test
    public void tempQueueTest() {
        Queue<String> pq = new PriorityQueue<>();
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
