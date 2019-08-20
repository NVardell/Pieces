package com.bits.pieces.practice.structures.trees;

import java.util.Comparator;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 8/15/2019
 */
public class CountComparator implements Comparator<Song> {
    @Override
    public int compare(Song s1, Song s2) {
        return s1.getCount() >= s2.getCount() ? 1 : -1;
    }
}
