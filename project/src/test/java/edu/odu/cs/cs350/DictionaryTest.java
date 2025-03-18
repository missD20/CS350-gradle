
package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class DictionaryTest {

    @Test
    public void testDictionary() throws IOException {
        Dictionary d = new Dictionary();
        assertThat(d.contains("I"), is(true));
        assertThat(d.contains("i"), is(true));
        assertThat(d.contains("with"), is(true));
        assertThat(d.contains("xyx"), is(false));
    }

    @Test
    public void testSmallDictionary() throws IOException {
        String[] words = {"abc", "XYX"};
        Dictionary d = new Dictionary(words);
        assertThat(d.contains("I"), is(false));
        assertThat(d.contains("with"), is(false));
        assertThat(d.contains("xyx"), is(true));
        assertThat(d.contains("abc"), is(true));
    }

}
