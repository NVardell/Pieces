package com.bits.pieces.topics.structures.queues;

import org.junit.Test;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Misc. Test Cases using Priority Queues
 *
 * @author NV
 * @since 10/5/2021
 */
public class PriorityQueueTests {

    @Test
    public void intCharTests() {
        int n = 34;
        String a = String.valueOf(n);
        int x = Character.getNumericValue(a.charAt(0));
        int y = Character.getNumericValue(a.charAt(1));
        assertThat(x+y, is(7));
    }

    @Test
    public void priorityQueue_MapTest(){
        Queue<Map.Entry<String, Integer>> mapPQ = new PriorityQueue<>(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        mapPQ.add(new AbstractMap.SimpleEntry<>("A",5));
        assertThat(mapPQ.size(), is(1));

        mapPQ.add(new AbstractMap.SimpleEntry<>("a", 9));
        assert mapPQ.peek() != null;
        assertThat(mapPQ.peek().getValue(), is(9));
    }
}
