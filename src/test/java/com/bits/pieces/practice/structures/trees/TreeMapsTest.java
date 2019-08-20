package com.bits.pieces.practice.structures.trees;

import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 8/15/2019
 */
public class TreeMapsTest {
    @Test
    public void whenDuplicateIsAddedToTreeMap_originalValueIsReplaced_andSizeIsUnchanged() {
        Map<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(1,1);
        treeMap.put(1,2);

        assertThat(treeMap.get(1), is(2));
        assertThat(treeMap.size(), is(1));
    }

    @Test
    public void whenUsingCustomCompareClasses_treeIsSortedCorrectlyByKey() {
        Map<Song, String> songMapTitles = new TreeMap<>(new TitleComparator());
        songMapTitles.put(new Song("Fire Song", 2), "Two");
        songMapTitles.put(new Song("Serious Song", 3), "Three");
        songMapTitles.put(new Song("Another Song", 1), "One");

        songMapTitles.forEach((key, value) -> System.out.println("Song Title = " + key + "\tSong Count = " + value));

        System.out.println("\n");
        Map<Song, String> songMapCount = new TreeMap<>(new CountComparator());
        songMapCount.put(new Song("Fire Song", 2), "Two");
        songMapCount.put(new Song("Serious Song", 1), "One");
        songMapCount.put(new Song("Another Song", 3), "Three");

        songMapCount.forEach((key, value) -> System.out.println("Song Title = " + key + "\tSong Count = " + value));


        songMapTitles = new TreeMap<>(new TitleComparator());
        songMapTitles.put(new Song("B", 2), "Two");
        songMapTitles.put(new Song("C", 3), "Three");
        songMapTitles.put(new Song("A", 1), "One");

        songMapTitles.forEach((key, value) -> System.out.println("Song Title = " + key + "\tSong Count = " + value));
    }
}