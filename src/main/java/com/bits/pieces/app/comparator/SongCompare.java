package com.bits.pieces.app.comparator;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * Custom Comparator for Song Model
 *
 * @author NV
 * @since 1/2/2022
 */
@Data
@AllArgsConstructor
public class SongCompare implements Comparable<SongCompare> {

    String title;

    @Override
    public int compareTo(SongCompare song) {
        return this.title.compareTo(song.getTitle());
    }
}
