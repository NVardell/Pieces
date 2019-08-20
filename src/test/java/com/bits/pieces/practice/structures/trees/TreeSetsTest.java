package com.bits.pieces.practice.structures.trees;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 8/15/2019
 */
public class TreeSetsTest {
    @Test
    public void whenDuplicateIsAddedToTreeSet_noErrorOccurs_andSizeIsUnchanged() {
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        treeSet.add(1);

        assertThat(treeSet.size(), is(1));
    }

    @Test
    public void givenSetOfSongComparatorObjects_setPrintsAlphabetically(){
        Set<SongComparator> treeSet = new TreeSet<>();
        treeSet.add(new SongComparator("How Cats Work"));
        treeSet.add(new SongComparator("Remix your Body"));
        treeSet.add(new SongComparator("Finding Emo"));

        assertThat(treeSet.size(), equalTo(3));
        assertThat(treeSet.iterator().next().getTitle(), is("Finding Emo"));
    }
}