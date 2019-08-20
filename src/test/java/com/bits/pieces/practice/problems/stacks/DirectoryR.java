package com.bits.pieces.practice.problems.stacks;


/******************************************************************************
 *  https://introcs.cs.princeton.edu/java/43stack/DirectoryR.java.html
 *
 *  Compilation:  javac DirectoryR.java
 *  Execution:    java DirectoryR directory-name
 *
 *  Prints out all of the files in the given directory and any
 *  subdirectories. Also prints out their file sizes in bytes.
 *
 ******************************************************************************/

import java.io.File;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 8/17/2019
 */
public class DirectoryR {
    public static void list(File directory) {
        File[] files = directory.listFiles();
        if (files == null) return;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) list(files[i]);
            else System.out.println(files[i].length() + ": " + files[i]);
        }
    }

    public static void main(String[] args) {
        File directory = new File(args[0]);
        list(directory);
    }
}
