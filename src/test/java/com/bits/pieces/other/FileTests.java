package com.bits.pieces.other;

import org.junit.Test;

import java.io.File;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 8/24/2019
 */
public class FileTests {

    private static final String DIR = "C:/Users/NV/Desktop";      // YUP

    @Test
    public void listDesktopContents() {
        Set<String> contents = listFilesUsingJavaIO(DIR);
        assertThat(contents, notNullValue());
        contents.forEach(System.out::println);
    }

    public Set<String> listFilesUsingJavaIO(String dir) {
        System.out.println("Looking for files in - " + dir);
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }
}
