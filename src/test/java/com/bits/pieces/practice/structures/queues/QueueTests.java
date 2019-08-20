package com.bits.pieces.practice.structures.queues;

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

    Queue priorityQueue = new PriorityQueue();
    Queue linkedListQueue = new LinkedList();
    Queue blockingQueue = new PriorityBlockingQueue();

    @Test
    public void tempQueueTest() {
        Queue<String> pq = new PriorityQueue<>();
        pq.add("Awesome");
        pq.add("Amazing");
        assertThat(pq.poll(), is("Amazing"));

        Queue<String> llq = new LinkedList<>();
        llq.add("Awesome");
        llq.add("Amazing");
        assertThat(llq.poll(), is("Awesome"));
    }
}
