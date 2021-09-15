package com.bits.pieces.topics;


import java.io.File;

/**
 * Prints out all the filenames in the given directory and any
 * subdirectories. Also prints out their file sizes in bytes.
 *
 * @author Nate Vardell
 * @since 8/17/2019
 */
public class DirectoryR {
    public static void list(File directory) {
        File[] files = directory.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) list(file);
            else System.out.println(file.length() + ": " + file);
        }
    }

    public static void main(String... args) {
        File directory;

        if (args.length == 0)
            directory = new File(".");
        else
            directory = new File(args[0]);

        list(directory);
    }
}
