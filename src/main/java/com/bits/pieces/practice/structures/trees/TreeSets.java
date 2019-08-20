package com.bits.pieces.practice.structures.trees;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 8/15/2019
 */
public class TreeSets {
    public static void main(String[] args) {
        new TreeSets().go();
    }

    public void go(){
        Book b1 = new Book("How Cats Work");
        Book b2 = new Book("Remix your Body");
        Book b3 = new Book("Finding Emo");

        TreeSet<Book> tree = new TreeSet<Book>();
        tree.add(b1);
        tree.add(b2);
        tree.add(b3);
        tree.forEach(System.out::println);
    }

}


//class Book {
//    String title;
//    public Book(String t) {title = t;}
//}


//class Book implements Comparable<Book> {
//    String title;
//    public Book(String t) {title = t;}
//    @Override
//    public int compareTo(Book b1) {return this.title.compareTo(b1.title);}
//}

class Book implements Comparator<Book> {
    String title;
    public Book(String t) {this.title = t;}
    public String getTitle() {
        return title;
    }

    @Override
    public int compare(Book b1, Book b2) {
        return b1.getTitle().compareTo(b2.getTitle());
    }
}