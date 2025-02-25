package edu.odu.cs.cs350;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Set;

/**
 * A set of simple tests - in a later section we will learn how to use
 * a unit test framework to write things like this.
 */
public class TestSoundEx {

    private static final String[] nearHomophones = {
        "skit", "scat",
        "to", "two",
        "psych", "sack",
        "knife", "naff",
        "book", "back",
        "quick", "quake",
        "sadder", "cider"
    };

    @Test
    public void SoundexBasicTest()
    {
        String[] shortWordList = {"a", "the", "to", "two", "too", "sew", "so"};
        Dictionary dictionary = new Dictionary(shortWordList);
        NearHomophones soundex = new NearHomophones(dictionary);

        Set<String> actual = soundex.soundsLike("to");
        assertThat(actual.contains("too"), is(true));
        assertThat(actual.contains("two"), is(true));
        assertThat(actual.contains("a"), is(false));

        actual = soundex.soundsLike("so");
        assertThat(actual.contains("too"), is(false));
        assertThat(actual.contains("two"), is(false));
        assertThat(actual.contains("sew"), is(true));
    }
    
}