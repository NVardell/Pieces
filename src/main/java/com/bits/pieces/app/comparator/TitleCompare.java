package com.bits.pieces.app.comparator;

import com.bits.pieces.app.model.Song;

import java.util.Comparator;

/**
 * Custom Compare for Song Titles
 *
 * @author NV
 * @since 1/2/2022
 */
public class TitleCompare implements Comparator<Song> {
    @Override
    public int compare(Song s1, Song s2) {
        return s1.getTitle().compareTo(s2.getTitle());
    }
}
