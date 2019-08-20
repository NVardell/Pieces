package com.bits.pieces.other;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Strings {
    @Test
    public void getStringValueOfDivision_shouldBeZero(){
        assertThat(String.valueOf(1/3), is("0"));
    }
}
