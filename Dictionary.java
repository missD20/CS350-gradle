package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;

public class Dictionary implements Iterable<String> {

    private HashSet<String> words;

    /**
     * Construct a new dictionary from the words in the Jar resource /words.txt
     * @throws IOException if the resource cannot be located or read
     */
    public Dictionary() throws IOException {
        words = new HashSet<>();

        InputStream wordsIn = Dictionary.class.getResourceAsStream("/words.txt");
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(wordsIn, StandardCharsets.UTF_8))
        ) {
            String word = in.readLine();
            while (word != null) {
                words.add(word.toLowerCase());
                word = in.readLine();
            }
        }
    }

    /**
     * Construct a dictionary from an array of words (intended for testing
     * purposes)
     * @param shortWordList an array of words
     */
    public Dictionary(String[] shortWordList) {
        words = new HashSet<>();
        for (String word: shortWordList) {
            words.add(word.toLowerCase());
        }
    }

    /**
     * Check to see if a given string is in the dictionary
     * @param string a possible word
     * @return true iff the string is in the dictionary
     */
    public boolean contains(String string) {
        return words.contains(string.toLowerCase());
    }

    /**
     * Provide access to the list of words stored.
     */
    @Override
    public Iterator<String> iterator() {
        return words.iterator();
    }

}
