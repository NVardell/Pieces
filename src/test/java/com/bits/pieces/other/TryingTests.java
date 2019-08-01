package com.bits.pieces.other;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests cases for Try blocks
 *
 * @author Nate Vardell
 * @since 7/20/2019
 */
@Slf4j
public class TryingTests {

    @Test
    public void assertAllOfTheThings(){
        assertThat(tryResources(), is(false));
        assertThat(tryMoreResources(), is(false));
    }

    private boolean tryResources(){
        try(FileReader file = new FileReader("Test.txt")){
            return true;
        }
        finally { return false; }
    }

    private boolean tryMoreResources() {
        try (PrintWriter writer = new PrintWriter(new File("test.txt"))) {
            writer.println("Hello World");
            throw new Exception("Throwing Exception");
        } catch (FileNotFoundException e) { e.printStackTrace();
        } finally { return false; }
    }
}
