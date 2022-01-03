package com.bits.pieces.topics.structures.trees;

import com.bits.pieces.app.comparator.SongCompare;
import com.bits.pieces.app.model.Book;
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
        Set<SongCompare> treeSet = new TreeSet<>();
        treeSet.add(new SongCompare("How Cats Work"));
        treeSet.add(new SongCompare("Remix your Body"));
        treeSet.add(new SongCompare("Finding Emo"));

        assertThat(treeSet.size(), equalTo(3));
        assertThat(treeSet.iterator().next().getTitle(), is("Finding Emo"));
    }

    @Test
    public void givenSomeBooks_assertTheyAreInTheTree() {
        Book b1 = new Book("How Cats Work");
        Book b2 = new Book("Remix your Body");
        Book b3 = new Book("Finding Emo");

        Set<Book> tree = new TreeSet<>();
        tree.add(b1);
        tree.add(b2);
        tree.add(b3);

        assertThat(tree.size(), is(3));
    }
}
