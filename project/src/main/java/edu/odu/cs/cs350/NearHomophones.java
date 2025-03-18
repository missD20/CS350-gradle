package edu.odu.cs.cs350;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class uses a modified SoundEx code to locate words that 
 * approximately sound like a given word. 
 */
public class NearHomophones {

    private Map<String, Set<String>> soundAlike;

    /**
     * Create the NearHomophones collection.
     * 
     * @param dictionary a dictionary of words to be considered for future
     *                   sound-alikes.
     */
    public NearHomophones(Dictionary dictionary) {
        soundAlike = new HashMap<String, Set<String>>();
        collectNearHomophones(dictionary);
    }

    /**
     * Return a (possibly empty) collection of words that sound roughly
     * like the given word.
     * 
     * @param word a word whose near-homophones are desired
     * @return a possibly empty set of similar-sounding
     *         words.
     */
    public Set<String> soundsLike(String word) {
        String soundex = SoundEx.soundEx(word);
        Set<String> homophones = soundAlike.get(soundex);
        if (homophones != null) {
            return Collections.unmodifiableSet(homophones);
        } else {
            return new HashSet<String>();
        }

    }




    /**
     * Collect all words into a mapping by soundex code.
     * @param dictionary all words to be considered
     * @param soundAlike a mapping from soundex codes to words
     * @param soundExCodes list of all soundex codes
     */
    private void collectNearHomophones(Dictionary dictionary) {
        for (String word : dictionary) {
            String soundEx1 = SoundEx.soundEx(word);
            Set<String> homophones = soundAlike.get(soundEx1);
            if (homophones == null) {
                homophones = new HashSet<>();
                soundAlike.put(soundEx1, homophones);
            }
            homophones.add(word);
        }
    }


}
