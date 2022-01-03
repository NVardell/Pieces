package com.bits.pieces.app.comparator;

import com.bits.pieces.app.model.Song;

import java.util.Comparator;

/**
 * Custom Comparator for Song Model
 *
 * @author NV
 * @since 1/2/2022
 */
public class CountCompare implements Comparator<Song> {
    @Override
    public int compare(Song s1, Song s2) {
        return s1.getCount() >= s2.getCount() ? 0 : -1;
    }
}
