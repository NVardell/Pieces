package com.bits.pieces.topics;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class Strings {

    private static final String text = "Julia Evans was born on 25-09-1984. "
            + "She is currently living in the USA (United States of America).";


    @Test
    public void getSubString() {
        assertEquals("USA (United States of America).", text.substring(67));
    }

    @Test
    public void getStringValueOfDivision_shouldBeZero() {
        assertThat(String.valueOf(1 / 3), is("0"));
    }

    @Test
    public void concatStringStream(){
        Stream<String> stream = Stream.of("1", "3", "5");
        assertThat(stream.collect(Collectors.joining()), is("135"));
    }

    @Test
    public void concatStringArray(){
        List<String> array = Arrays.asList("1", "3", "5");
        assertThat(String.join("", array), is("135"));
    }
}
