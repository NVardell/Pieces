package com.bits.pieces.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Song Model Class
 *
 * @author NV
 * @since 1/2/2022
 */
@Data
@AllArgsConstructor
public class Song {
    String title;
    int count;
}
