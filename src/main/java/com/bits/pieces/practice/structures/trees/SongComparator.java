package com.bits.pieces.practice.structures.trees;

import lombok.Data;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 8/15/2019
 */
@Data
public class SongComparator implements Comparable<SongComparator> {

    private final String title;


    @Override
    public int compareTo(SongComparator song) {
        return this.title.compareTo(song.getTitle());
    }
}
