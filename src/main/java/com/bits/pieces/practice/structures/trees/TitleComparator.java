package com.bits.pieces.practice.structures.trees;

import java.util.Comparator;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 8/15/2019
 */
public class TitleComparator implements Comparator<Song> {
    @Override
    public int compare(Song song1, Song song2) {
        return song1.getTitle().compareTo(song2.getTitle());
    }
}
