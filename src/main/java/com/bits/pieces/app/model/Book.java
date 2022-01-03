package com.bits.pieces.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;

/**
 * Book Model Class
 *
 * @author NV
 * @since 1/2/2022
 */
@Data
@AllArgsConstructor
public class Book implements Comparator<Book> {
    String title;

    @Override
    public int compare(Book b1, Book b2) {
        return b1.getTitle().compareTo(b2.getTitle());
    }

}
